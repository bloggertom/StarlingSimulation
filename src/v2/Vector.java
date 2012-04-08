package v2;

public class Vector {
	private double x;
	private double y;
	private double z;
	
	public Vector(){
		
	}
	
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	
	//Setting information about this starling
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setZ(double z){
		this.z = z;
	}

	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	
	public double getMagnitude(){
		return Vector.vectorMagnitude(this);
	}
	
	public static Vector divideVectorsByScaler(Vector aVector, double d){
		Vector vector = new Vector();
		
		vector.setX(aVector.getX()/d);
		vector.setY(aVector.getY()/d);
		vector.setZ(aVector.getZ()/d);
		
		return vector;
	}
	
	public static Vector multiplyByScaler(Vector aVector, double speedCap){
		Vector vector = new Vector();
		
		vector.setX(aVector.getX()*speedCap);
		vector.setY(aVector.getY()*speedCap);
		vector.setZ(aVector.getZ()*speedCap);
		
		return vector;
	}
	
	public static Vector addVectors(Vector vector1, Vector vector2){
		Vector vector = new Vector();
		
		vector.setX(vector1.getX() + vector2.getX());
		vector.setY(vector1.getY() + vector2.getY());
		vector.setZ(vector1.getZ() + vector2.getZ());
		
		return vector;
	}
	
	public static Vector subtractVectors(Vector vector1, Vector vector2){
		Vector vector = new Vector();
		
		vector.setX(vector1.getX() - vector2.getX());
		vector.setY(vector1.getY() - vector2.getY());
		vector.setZ(vector1.getZ() - vector2.getZ());
		
		return vector;
	}
	
	public static double vectorMagnitude(Vector v){
		double d = Math.sqrt((v.getX() * v.getX())+(v.getY()*v.getY())+(v.getZ()*v.getZ()));
		
		return d;
	}
}
