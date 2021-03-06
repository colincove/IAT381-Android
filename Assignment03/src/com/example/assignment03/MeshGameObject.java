package com.example.assignment03;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class MeshGameObject  {
    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +

            "attribute vec4 vPosition;" +
            "void main() {" +
            // the matrix must be included as a modifier of gl_Position
            "  gl_Position = vPosition * uMVPMatrix;" +
            "}";

        private final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";
        static float triangleCoords[] = { // in counterclockwise order:
       	 	-0.5f,   -0.5f, 0.0f,   // top
             0.5f,  - 0.5f, 0.0f,   // bottom left
             0.0f,    0.5f, 0.0f    // bottom right
       };
	private ObjMesh mesh;
	static final int COORDS_PER_VERTEX = 3;
	  private final int vertexCount;
	    private final int vertexStride=COORDS_PER_VERTEX * 4;
	 private final FloatBuffer vertexBuffer;
	 private final int mProgram;
	    private int mPositionHandle;
	    private int mColorHandle;
	    private int mMVPMatrixHandle;
	    float color[] = { 1.0f, 0.0f, 0.0f };
	public MeshGameObject(ObjMesh mesh) {
		
		//game.addMeshObject(this);
		this.mesh = mesh;
		vertexCount = triangleCoords.length/COORDS_PER_VERTEX;
		ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length*4);
		
		bb.order(ByteOrder.nativeOrder());
		
		vertexBuffer = bb.asFloatBuffer();
		
		vertexBuffer.put(triangleCoords);
		vertexBuffer.position(0);
		
		  int vertexShader = GlRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                  vertexShaderCode);
int fragmentShader = GlRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                    fragmentShaderCode);

mProgram = GLES20.glCreateProgram();             // create empty OpenGL Program
GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
GLES20.glLinkProgram(mProgram);  
		
	}
	public ObjMesh getMesh(){
		return mesh;
	}
	public void rotate(){
		
	}
	
	public void draw(float[] mvpMatrix){
		// Add program to OpenGL environment
		
        GLES20.glUseProgram(mProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        GLES20.glEnableVertexAttribArray(mPositionHandle);

        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                                     GLES20.GL_FLOAT, false,
                                     vertexStride, vertexBuffer);

        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        GLES20.glUniform4fv(mColorHandle, 0, color, 0);

        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
	}

}
