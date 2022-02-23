

public class Derivative {
	
	private double xVar;
	
	public Derivative() {
		xVar = 0;
	}
	
	public String computePowerRule(double coeff, String variable, double exponent) {
		String output = "";
		
		double finalCoeff = coeff * exponent;
		double finalExponent = exponent-1;
		
		if (finalExponent != 1.0) {
			output = finalCoeff + variable + "^" + "(" + finalExponent + ")";
			return output;
		} else {
			output = finalCoeff + variable;
			return output; 
		}
 
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Derivative myDer = new Derivative();
		
		System.out.println("Derivative (Power Rule): " + myDer.computePowerRule(1, "x", 2));
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
