package com.example.assignment03;

public class ObjMesh {
	private ObjData meshData;
	private float[] glFaces;
	public ObjMesh(ObjData meshData) {
		this.meshData=meshData;
		glFaces  = getGLFaces(meshData);
	}
	public float[] getGlFaces(){
		return glFaces;
	}
	private float[] getGLFaces(ObjData data){
		float[] result = new float[data.getFaceCount()*3];
		for(int i=0;i<data.getFaceCount();i++){
			
			result[i*3]=data.getVertices()[data.getFaces()[i][0]][0];
			result[i*3+1]=data.getVertices()[data.getFaces()[i][1]][1];
			result[i*3+2]=data.getVertices()[data.getFaces()[i][2]][2];
		}
		return result;
	}
}
