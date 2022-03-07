
public class ZDistSignificance {

	public static class ZSignificanceData {
		double mu;
		double sigma;
		double sampleMean;
		int sampleSize;
		double alpha;
		String alternateHypothesis;
	} 

	public String computeZSigTestHaGreaterThanH0(ZSignificanceData inputData) {

		double zCritical = (inputData.sampleMean - inputData.mu) / (inputData.sigma / (Math.sqrt(inputData.sampleSize)));

		NormalDist obj = new NormalDist();

		double pValue = obj.computeFiniteZProbMidpointRiemann(zCritical, 1000.0);

		if (pValue < inputData.alpha) {
			return "There is statistically significant evidence that Ha > H0... reject H0";
		} else if (pValue > inputData.alpha) {
			return "There is no statistically significant evidence that Ha > H0... fail to reject H0";
		} else if (pValue == inputData.alpha) {
			return "";
		}

		return "";

	} 

	public String computeZSigTestHaLessThanH0(ZSignificanceData inputData) {

		double zCritical = (inputData.sampleMean - inputData.mu) / (inputData.sigma / (Math.sqrt(inputData.sampleSize)));

		NormalDist obj = new NormalDist();

		double pValue = obj.computeFiniteZProbMidpointRiemann(-1000.0, zCritical);

		if (pValue < inputData.alpha) {
			return "There is statistically significant evidence that Ha < H0... reject H0";
		} else if (pValue > inputData.alpha) {
			return "There is no statistically significant evidence that Ha < H0... fail to reject H0";
		} else if (pValue == inputData.alpha) {
			return "";
		}

		return "";

	} 
	
	public String computeZSigTestHaNotEqualsH0(ZSignificanceData inputData) {
		
		double zCritical = (inputData.sampleMean - inputData.mu) / (inputData.sigma / (Math.sqrt(inputData.sampleSize)));
		
		NormalDist obj = new NormalDist(); 
		
		double pValue = (obj.computeFiniteZProbMidpointRiemann((Math.abs(zCritical)), 1000.0)) * 2.0; 
		
		if (pValue < inputData.alpha) {
			return "There is statistically significant evidence that Ha ≠ H0... reject H0"; 
		} else if (pValue > inputData.alpha) {
			return "There is no statistically significant evidence that Ha ≠ H0... fail to reject H0"; 
		} else if (pValue == inputData.alpha) {
			return ""; 
		}
		
		return ""; 
		
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZDistSignificance myObj = new ZDistSignificance();

		ZSignificanceData myData = new ZSignificanceData();
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// enter your data in this section! 
		
		myData.mu = 44;
		myData.sigma = 0.4767;
		myData.sampleMean = 46;
		myData.sampleSize = 157;
		myData.alpha = 0.05;
		myData.alternateHypothesis = "Ha not equals H0"; 
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		long before = System.nanoTime(); 
		
		System.out.println("Z-Significance Test: "); 
		System.out.println(); 
		
		if (myData.alternateHypothesis.equals("Ha > H0")) {
			System.out.println(myObj.computeZSigTestHaGreaterThanH0(myData));
			System.out.println(); 
			System.out.println("-------"); 
			System.out.println();
			long after = System.nanoTime();
			System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		} else if (myData.alternateHypothesis.equals("Ha < H0")) {
			System.out.println(myObj.computeZSigTestHaLessThanH0(myData)); 
			System.out.println(); 
			System.out.println("-------"); 
			System.out.println();
			long after = System.nanoTime();
			System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		} else if (myData.alternateHypothesis.equals("Ha not equals H0")) {
			System.out.println(myObj.computeZSigTestHaNotEqualsH0(myData)); 
			System.out.println(); 
			System.out.println("-------"); 
			System.out.println();
			long after = System.nanoTime();
			System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		}

	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
