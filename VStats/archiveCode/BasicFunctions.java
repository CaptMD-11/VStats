

public class BasicFunctions {
	private double xVar;
	private double yVar;
	
	public BasicFunctions() {
		xVar = 0.0;
		yVar = 0.0;
	}
	
	public double computeTraditionalLinearFunction(double XCoeff, double xVal, double constant) {
		
		double output = 0.0;
		
		output = (XCoeff)*(xVal) + (constant);
				
		return output; 
		
	}
	
	public double computeTraditionalQuadratic(double x2Coeff, double xCoeff, double xVal, double constant) {
		
		double output = 0.0;
		
		output = (x2Coeff)*(Math.pow(xVal, 2)) + (xCoeff)*(xVal) + constant;
		
		return output; 
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BasicFunctions myObj = new BasicFunctions();
		
		System.out.println("Traditional Linear Output: " + myObj.computeTraditionalLinearFunction(-0.5, 2, 3));
		
		System.out.println("Traditional Quadratic Output: " + myObj.computeTraditionalQuadratic(2, 4, -24, 100));

	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
