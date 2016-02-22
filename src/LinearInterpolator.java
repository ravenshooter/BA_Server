import java.util.Arrays;





public class LinearInterpolator {

	public static final double[] interpLinear(double[] x, double[] y, double[] xi) throws IllegalArgumentException {

        if (x.length != y.length) {
            throw new IllegalArgumentException("X and Y must be the same length");
        }
        if (x.length == 1) {
            throw new IllegalArgumentException("X must contain more than one value");
        }
        double[] dx = new double[x.length - 1];
        double[] dy = new double[x.length - 1];
        double[] slope = new double[x.length - 1];
        double[] intercept = new double[x.length - 1];

        // Calculate the line equation (i.e. slope and intercept) between each point
        for (int i = 0; i < x.length - 1; i++) {
            dx[i] = x[i + 1] - x[i];
            if (dx[i] == 0) {
                throw new IllegalArgumentException("X must be montotonic. A duplicate " + "x-value was found");
            }
            if (dx[i] < 0) {
                throw new IllegalArgumentException("X must be sorted");
            }
            dy[i] = y[i + 1] - y[i];
            slope[i] = dy[i] / dx[i];
            intercept[i] = y[i] - x[i] * slope[i];
        }

        // Perform the interpolation here
        double[] yi = new double[xi.length];
        for (int i = 0; i < xi.length; i++) {
            if ((xi[i] > x[x.length - 1]) || (xi[i] < x[0])) {
                yi[i] = Double.NaN;
            }
            else {
                int loc = Arrays.binarySearch(x, xi[i]);
                if (loc < -1) {
                    loc = -loc - 2;
                    yi[i] = slope[loc] * xi[i] + intercept[loc];
                }
                else {
                    yi[i] = y[loc];
                }
            }
        }

        return yi;
    }
	
	

	public static double[] getXi(double nOldValues, double nNewValues){
		//da beide bei null beginnen und nicht bei eins
		double dist = (nOldValues-1)/(nNewValues-1);
		
		double[] xI = new double[(int)nNewValues];
		for(int i = 0; i < nNewValues;i++){
			xI[i] = i*dist;
		}
		return xI;
	}
	
	
	
	
	public static void main(String[] args) {
		double[] x = {0.0, 1.0, 2.0, 3.0};
		double[] y = {0.0 ,1.0, 4.0, 9.0};

		
		double[] xI  = getXi(3, 15);
		for(double d: xI){
			System.out.print(padRight(""+d,5)+"  ");
		}
		System.out.println();
		
		
		for(double d: interpLinear(x, y, xI)){
			System.out.print(padRight(""+d,5)+"  ");
		}
		
	}
	
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
}
