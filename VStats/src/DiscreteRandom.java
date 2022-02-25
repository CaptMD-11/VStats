
public class DiscreteRandom {
	
	private OneVar m_xValArr;
	private OneVar m_pOfX; 
	
	public DiscreteRandom(double[] arr1, double[] arr2) {
		
		m_xValArr = new OneVar(arr1); 
		m_pOfX = new OneVar(arr2); 
		
	}
	
	public double computeDiscreteExpectedValue() {
		
		double sum = 0.0; 
		
		for (int i = 0; i < m_xValArr.computeLength(); i++) {
			sum += ((m_xValArr.atIndex(i)) * (m_pOfX.atIndex(i))); 
		}
		
		return sum; 
		
	}
	
	public double computeDiscreteVariance() {
		
		double sum = 0.0; 
		double meanOfX = computeDiscreteExpectedValue(); 
		
		for (int i = 0; i < m_xValArr.computeLength(); i++) {
			
			sum += ( (Math.pow((m_xValArr.atIndex(i) - meanOfX), 2)) * (m_pOfX.atIndex(i)) ); 
			
		}
		
		return sum; 
		
	}
	
	public double computeDiscreteStandardDeviation() {
		
		return Math.sqrt(computeDiscreteVariance()); 
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 
		
		//////////////////////////////////////////////////////
		
		// enter your data here! 
		
		double[] xValuesArray = {10, 20, 137}; 
		double[] probabilitiesArray = {0.3, 0.2, 0.5}; 
				
		//////////////////////////////////////////////////////

		DiscreteRandom myObj = new DiscreteRandom(xValuesArray, probabilitiesArray);
		
		System.out.println("Discrete Random Variables Information: "); 
		System.out.println(); 
		
		System.out.println("Expected Value: " + myObj.computeDiscreteExpectedValue()); 
		System.out.println("Variance: " + myObj.computeDiscreteVariance()); 
		System.out.println("Standard Deviation: " + myObj.computeDiscreteStandardDeviation()); 
		
		System.out.println(); 
		System.out.println("-------");  
		System.out.println(); 
		long after = System.nanoTime();
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 

		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
