

public class SensorMesswert {

	
	double x, y , z;
	double length;
	long timestamp;
	String label;
	
	
	public SensorMesswert(double x, double y, double z,String label, long start) {
		this.x = x;
		this.y = y;
		this.z = z;
		length = Math.sqrt(x*x+y*y+z*z);
		this.label = label;
		timestamp = System.currentTimeMillis()-start;
	}
	
	
	public String toString(){
		return x+";"+y+";"+z;
	}
	
	public double getLength() {
		return length;
	}
}
