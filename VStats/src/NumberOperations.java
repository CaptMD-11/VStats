
public class NumberOperations {
	
	private double number;
	
	public NumberOperations() {
		number = 0; 
	}
	
	public double computeFactorial(double input) {
		if (input <= 1) {
			return 1;
		} else {
			return input * computeFactorial(input - 1); 
		}
	}
	
	public int computeRandomWholeNumber(int low, int high) {
		return (int) (Math.random()*(high-low+1) + low); 
	}
	
	public double computeRandomDecimalNumber(double low, double high) {
		return Math.random()*(high-low+1) + low; 
	}
	
	public double computeCombinations(int numberOfObservations, int numberChoose) {
		return (computeFactorial(numberOfObservations)) / (computeFactorial(numberChoose) * computeFactorial(numberOfObservations - numberChoose)); 
	}
	
	public double computePermutations(int numberOfObservations, int numberChoose) {
		return (computeFactorial(numberOfObservations)) / (computeFactorial(numberOfObservations - numberChoose)); 
	}
	
	public double computeAbsoluteValue(double number) {
		if (number < 0) {
			return number * -1;
		} else if (number == 0) {
			return 0;
		} else {
			return number; 
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 

		NumberOperations myObj = new NumberOperations();
		
		System.out.println("Number Operations Information: "); 
		System.out.println(); 
		
		////////////////////////////////////////////////////////////////////////////////////
		
		// enter your data here! 
		
		System.out.println("Factorial: " + myObj.computeFactorial(5)); 
		System.out.println("Random whole number: " + myObj.computeRandomWholeNumber(0, 100)); 
		System.out.println("Random decimal number: " + myObj.computeRandomDecimalNumber(0, 1000));
		System.out.println("Combinations: " + myObj.computeCombinations(10, 3)); 
		System.out.println("Permutations: " + myObj.computePermutations(10, 3)); 
		System.out.println("Absolute value: " + myObj.computeAbsoluteValue(1252)); 
		
		////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println(); 
		System.out.println("-------"); 
		System.out.println(); 
		
		long after = System.nanoTime(); 
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////

}
