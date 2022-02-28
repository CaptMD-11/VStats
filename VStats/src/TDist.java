
public class TDist {
	
	// need to create factorial & other related methods 
	
	public double factorial(double input) {
		
		if (input <= 1) {
			return 1;
		} else {
			return input * factorial(input - 1); 
		}
		
	}
	
	public double gammaFunction(double n) {
		
		return factorial(n-1); 
		
	}
	
	public double TPDF(double inputT, double inputDF) {
		
		double gammaExpression = ((inputDF + 1.0) / (2.0));
		
		double gammaNumerator = (gammaFunction(gammaExpression)); 
		
		double denominator = (Math.sqrt(inputDF * Math.PI)) * (gammaFunction((inputDF) / (2.0))); 
		
		double factor = (1 + ((Math.pow(inputT, 2.0)) / inputDF)); 
		
		double exponent = -1 * gammaExpression;
		
		return (gammaNumerator / denominator) * (Math.pow(factor, exponent)); 
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			long before = System.nanoTime(); 
		
			TDist myObj = new TDist();
			System.out.println("t-Distribution: "); 
			System.out.println(); 
			System.out.println(myObj.TPDF(0.5, 7.0));
			System.out.println(); 
			System.out.println("-------"); 
			System.out.println();
			
			long after = System.nanoTime(); 
			
			System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
			
	}
////////////////////////////////////////////////////////////////////////////////////////////////////

}
