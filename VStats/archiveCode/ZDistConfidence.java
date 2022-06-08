
public class ZDistConfidence {

	public static class ZConfidenceData {
		double mu;
		double sigma;
		double sampleSize;
		double confidenceLevel;
	}
	
	public double computeZStar(double inputConfidenceLevel) {
		
		double invNormInput = inputConfidenceLevel + ((1-inputConfidenceLevel) / (2)); 

		NormalDist obj = new NormalDist(); 
		
		return Double.parseDouble(obj.computeInvNormApprox(invNormInput));  
		
	}
	
	public String computeZConfidenceInterval(ZConfidenceData inputData) {
		
		String res = ""; 
		
		double lowBound = inputData.mu - (computeZStar(inputData.confidenceLevel) * ((inputData.sigma) / (Math.sqrt(inputData.sampleSize))));
		
		double highBound = inputData.mu + (computeZStar(inputData.confidenceLevel) * ((inputData.sigma) / (Math.sqrt(inputData.sampleSize))));
		
		res = "(" + lowBound + ", " + highBound + ")"; 
		
		return res; 
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 
		
		ZDistConfidence myObj = new ZDistConfidence();

		ZConfidenceData myData = new ZConfidenceData();

		////////////////////////////////////////////////////////////////////////////////////////////////////

		// enter your data in this section! 

		myData.mu = 44;
		myData.sigma = 0.4767;
		myData.sampleSize = 100;
		myData.confidenceLevel = 0.95; 

		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("Z-Confidence Interval: "); 
		System.out.println(); 
		System.out.println(myObj.computeZConfidenceInterval(myData)); 
		System.out.println(); 
		System.out.println("-------"); 
		System.out.println(); 
		
		long after = System.nanoTime();
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////

}