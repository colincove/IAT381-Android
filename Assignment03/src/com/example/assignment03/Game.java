package com.example.assignment03;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Game extends Activity {
	private GLSurfaceView glView;
	protected List<MeshGameObject> meshObjects;
	private ObjData data=null;
	private ObjReader objReader;
	private ObjMesh pyramidMesh;
	private MeshGameObject pyramidObject;
	public Game() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		meshObjects = new ArrayList<MeshGameObject>();
		if(data==null){
			Resources res = getResources();
	        objReader = new ObjReader(R.raw.pyramid, res);
	        data = objReader.read();
	        pyramidMesh = new ObjMesh(data);
	        pyramidObject = new MeshGameObject(this, pyramidMesh);
			}
		glView = new GameSurfaceView(this);
		setContentView(glView);
		
		
	}
	public void addMeshObject(MeshGameObject meshObj){
		meshObjects.add(meshObj);
	}
public void removeMeshObject(MeshGameObject meshObj){
	meshObjects.remove(meshObj);
	}
public List<MeshGameObject> getMeshObjects(){
	return meshObjects;
}
	@Override
	protected void onPause(){
		super.onPause();
		glView.onPause();
	}
	@Override
	protected void onResume(){
		super.onResume();
		glView.onResume();
	}

}
