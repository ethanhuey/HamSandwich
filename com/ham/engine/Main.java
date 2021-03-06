package com.ham.engine;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.ham.game.Game;
import com.ham.game.Time;

import static org.lwjgl.opengl.GL11.*;
public class Main 
{
	
	public static void main(String[] args) 
	{
		initDisplay();
		initGL();
		initGame();
		gameLoop();
		cleanup();
	}
	private static void initGame()
	{
		Game.game = new Game();
	}
	private static void gameLoop()
	{
		Time.init();
		
		while (!Display.isCloseRequested())
		{
			Time.update();
			getInput();
			update();
			render(); 
		}
	}
	
	private static void getInput()
	{
		Game.game.getInput();
	}
	private static void update()
	{
		Game.game.update();
	}
	private static void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		Game.game.render();
		
		Display.update();
		Display.sync(60);
	}
	
	private static void cleanup()
	{
		Display.destroy();
		Keyboard.destroy();
	}
	private static void initGL()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
		glMatrixMode(GL_MODELVIEW);
		
		glDisable(GL_DEPTH_TEST);
		
		glClearColor(0,0,0,0);
	}

	private static void initDisplay()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Keyboard.create();
			Display.setVSyncEnabled(true);
		}
		catch (LWJGLException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
