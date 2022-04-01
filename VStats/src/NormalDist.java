
public class NormalDist {
// standard normal distribution 
	
	public double NormalPDF(double inputZ) {
		
		double constant = (1) / (Math.sqrt(2 * Math.PI)); 
		double exponent = (Math.pow(inputZ, 2.0)) / (-2); 
		
		return (constant) * (Math.pow(Math.E, exponent)); 
		
	} 
	
	public double computeFiniteZProbLeftRiemann(double inputZLow, double inputZHigh) {
		
		double sum = 0.0; 
		
		//double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7)); 
		double increment = 1.0 / (Math.pow(10, 7)); 

		for (double i = inputZLow; i < inputZHigh; i += increment) {
			sum += ((NormalPDF(i)) * (increment)); 
		}
		
		return sum; 
	}
	
	public double computeFiniteZProbRightRiemann(double inputZLow, double inputZHigh) {
		
		double sum = 0.0; 
		
		//double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7)); 
		double increment = 1.0 / (Math.pow(10, 7)); 
		
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
		
		//double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7)); 
		double increment = 1.0 / (Math.pow(10, 7)); 
		
		for (double i = (inputZLow + (increment/2)); i < inputZHigh; i+= increment) {
			sum += (NormalPDF(i) * increment); 
		}
		
		return sum; 
		
	}
	
	public double computeFiniteZProbTrapezoidRiemann(double inputZLow, double inputZHigh) {
		
		double sum = 0.0; 
		
		//double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7)); 
		double increment = 1.0 / (Math.pow(10, 7)); 
				
		for (double i = inputZLow; i < (inputZHigh-increment); i += increment) {
			sum += ((0.5) * (NormalPDF(i) + NormalPDF(i + increment)) * (increment)); 
		}
		
		return sum; 
		
	}

	public String computeInvNormApproxOBSOLETE(double input) { // input confidence level 
		
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
	
	public String computeInvNormApprox(double input) { // works, but highly inefficient 
		// with respect to probability to the left of input value 
		
		if ((input < 0) || (input > 1))
			return "invalid input"; 
		else if (input == 0)
			return "-∞"; 
		else if (input == 1)
			return "∞"; 
		else {
			double starter = 0.0; 
			
			while (starter <= 1) {
				if (computeFiniteZProbMidpointRiemann(-100000.0, starter) == input) 
					return starter + ""; 
				starter += 0.0000001;
			}

		}
		
		return ""; 
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 
		
		NormalDist myObj = new NormalDist();  
		
		System.out.println("Normal Distribution: "); 
		System.out.println(); 
		
		System.out.println("Probability Right: " + myObj.computeFiniteZProbRightRiemann(-1.5, 2.0337));
		System.out.println("Probability Left: " + myObj.computeFiniteZProbLeftRiemann(-1.5, 2.0337)); 
		System.out.println("Probability Avg: " + myObj.computeFiniteZProbAvgLeftRightRiemann(-1.5, 2.0337));
		System.out.println("Probability Midpoint: " + myObj.computeFiniteZProbMidpointRiemann(-1.5, 2.0337)); 
		System.out.println("Very approximate z-score: " + myObj.computeInvNormApprox(0.84));
		System.out.println(); 
		
		System.out.println("-------"); 
		System.out.println(); 
		
		long after = System.nanoTime(); 
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////	
	

}
