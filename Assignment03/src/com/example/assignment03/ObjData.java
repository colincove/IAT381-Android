package com.example.assignment03;

public class ObjData {
	private int vSize = 100;
	private int fSize = 100;
	private int fIndex=0;
	private int vIndex=0;
	public float[][] vertices; 
	public int[][] faces;
	public ObjData() {
		vertices =   new float[vSize][3];
		faces =   new int[fSize][3];
	}
	public void addFace(int v1, int v2, int v3){		
		if(fIndex==fSize){
			
			int[][] tmp = faces;
			faces=new int[fSize+100][3];
			
			for(int i=0;i<fSize;i++){
				faces[i]=tmp[i];
				tmp[i]=null;
			}
			tmp=null;
			fSize=fSize+100;
		}
		faces[fIndex][0]=v1;
		faces[fIndex][1]=v2;
		faces[fIndex][2]=v3;
		fIndex++;
	}
	public void addVertice(float x, float y, float z){
		if(vIndex==vSize){
			
			float[][] tmp = vertices;
			vertices=new float[vSize+100][3];
			
			for(int i=0;i<vSize;i++){
				vertices[i]=tmp[i];
				tmp[i]=null;
			}
			tmp=null;
			vSize=vSize+100;
		}
		vertices[vIndex][0]=x;
		vertices[vIndex][1]=y;
		vertices[vIndex][2]=z;
		vIndex++;
	}
	
}
