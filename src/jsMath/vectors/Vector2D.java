package jsMath.vectors;

import java.lang.Math;

public class Vector2D {
	
	public float x;
	public float y;
	
	//A class representing a vector in 2d space
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getY() {
		return y;
	}
	//Add 2 vectors together
	public static Vector2D addVector(Vector2D v1, Vector2D v2) {
		Vector2D c = new Vector2D(v1.x + v2.x, v1.y+v2.y);
		return c;
	}
	//Distance between 2 vectors at their endpoints
	public static float distance(Vector2D v1, Vector2D v2) {
		float x2 = (float) Math.pow(v1.x - v2.x, 2);
		float y2 = (float) Math.pow(v1.y - v2.y, 2);
		float c = (float) Math.sqrt(x2 + y2);
		
		return c;
	}
	
	public float length() {
		float c = (float) Math.sqrt(x * x + y*y);
		return c;
	}
	
	
	//Dot product of 2 vectors
	public static float dot(Vector2D v1, Vector2D v2) {
		float c = v1.x * v2.x + v1.y * v2.y;
		return c;
	}
	
	//Create a vector of length one in the same direction 
	public Vector2D normalize() {
		float l = this.length();
		Vector2D v2 = new Vector2D(x/l, y/l);
		return v2;
	}
	//Return the angle between 2 vectors
	public static float angle(Vector2D v1, Vector2D v2) {
		float theta;
		if (v1.normalized() && v2.normalized()) {
			theta = (float) Math.acos(dot(v1,v2));
		}
		else {
			Vector2D v3 = v1.normalize();
			Vector2D v4 = v2.normalize();
			theta = (float) Math.acos(dot(v3,v4));
		}
		return theta;
	}
	//Create a vector perpindicular to the current vector
	public static Vector2D perp(Vector2D v1) {
		Vector2D v2 = new Vector2D(-v1.y, v1.x);
		return v2;
	}
	//Check if a vector is normalized
	public boolean normalized() {
		return (this.length() == 1);
	}
}
