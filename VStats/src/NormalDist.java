
public class NormalDist {
// standard normal distribution 
	
	public double NormalPDF(double inputZ) {
		
		double constant = (1) / (Math.sqrt(2 * Math.PI)); 
		double exponent = (Math.pow(inputZ, 2.0)) / (-2); 
		
		return (constant) * (Math.pow(Math.E, exponent)); 
		
	} 
	
	public double computeFiniteZProbLeftRiemann(double inputZLow, double inputZHigh) {
		
		double sum = 0.0; 
		
		double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7)); 

		for (double i = inputZLow; i < inputZHigh; i += increment) {
			sum += ((NormalPDF(i)) * (increment)); 
		}
		
		return sum; 
	}
	
	public double computeFiniteZProbRightRiemann(double inputZLow, double inputZHigh) {
		
		double sum = 0.0; 
		
		double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7)); 
		
		for (double i = (inputZLow + increment); i <= inputZHigh; i += increment) {
			sum += (NormalPDF(i) * increment); 
		}
		
		return sum; 
		
	}
	
	public double computeFiniteZProbAvgLeftRightRiemann(double inputZLow, double inputZHigh) {
		return ((computeFiniteZProbRightRiemann(inputZLow, inputZHigh)) + 
											(computeFiniteZProbLeftRiemann(inputZLow, inputZHigh))) / 2; 
	}
	
	public double computeFiniteZProbMidpointRiemann(double inputZLow, double inputZHigh) {
		
		double sum = 0.0;
		
		double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7)); 
		
		for (double i = (inputZLow + (increment/2)); i < inputZHigh; i+= increment) {
			sum += (NormalPDF(i) * increment); 
		}
		
		return sum; 
		
	}

	public String computeInvNormApprox(double input) { // input confidence level 
		
		double res = 0.0; 
		
		if ( (input == 0) || (input == 1) ) {
			return "∞"; 
		} else if ( ((input > 0) && (input < 0.01)) || ((input > 0.99) && (input < 1)) ) {
			
			res = Math.tan((Math.PI / 0.1) * (input - 0.95)); 
			
			return res + ""; 
			
		} else if ( (input >= 0.01) && (input <= 0.99) ) { // good
			
			res = Math.tan((Math.PI / 1.34) * (input - 0.5)); 
			
			return res + ""; 
			
		} else if ( (input < 0) || (input > 1) ) { // good
			return "invalid input"; 
		}
		
		return ""; 
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NormalDist myObj = new NormalDist();  
		
		System.out.println("Probability Right: " + myObj.computeFiniteZProbRightRiemann(-1.5, 2.0337));
		System.out.println("Probability Left: " + myObj.computeFiniteZProbLeftRiemann(-1.5, 2.0337)); 
		System.out.println("Probability Avg: " + myObj.computeFiniteZProbAvgLeftRightRiemann(-1.5, 2.0337));
		System.out.println("Probability Midpoint: " + myObj.computeFiniteZProbMidpointRiemann(-1.5, 2.0337)); 
		System.out.println(myObj.computeInvNormApprox(0.001)); 
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////	
	

}
