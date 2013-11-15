package com.example.assignment03;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GameSurfaceView extends GLSurfaceView {
	private  GlRenderer renderer;
	private Game game;
	
	public Triangle triangle;
	public GameSurfaceView(Game context) {
		super(context);
		game=context;
		triangle = new Triangle();
		setEGLContextClientVersion(2);
		renderer = new GlRenderer(game.getMeshObjects());
		renderer.triangle=triangle;
		this.setRenderer(renderer);
		
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		
	}



}
