
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
	
	public double computeRandomNumber(double low, double high) {
		return Math.random()*(high-low+1) + low; 
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 

		NumberOperations myObj = new NumberOperations();
		
		System.out.println("Number Operations Information: "); 
		System.out.println(); 
		
		////////////////////////////////////////////////////////////////////////////////////
		
		// enter your data below! 
		
		System.out.println("Factorial: " + myObj.computeFactorial(5)); 
		System.out.println("Random number: " + myObj.computeRandomNumber(0, 1000));
		
		////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println(); 
		System.out.println("-------"); 
		System.out.println(); 
		
		long after = System.nanoTime(); 
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////

}
