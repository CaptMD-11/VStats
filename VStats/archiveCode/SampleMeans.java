
public class SampleMeans {
	
	private double m_mu;
	private double m_sigma;
	
	public SampleMeans() {
		m_mu = 0;
		m_sigma = 0;
	}
	
	public static class SampleMeansData {
		private double mu;
		private double sigma;
		private double sampleSize;
		private double meanForComparison; 
		private String statement; 
	}
	
	public double computeSampleMeansMu(SampleMeansData data) {
		return data.mu; 
	}
	
	public double computeSampleMeansStandardDeviation(SampleMeansData data) {
		return ((data.sigma) / (Math.sqrt(data.sampleSize))); 
	}
	
	public double computeProbabilityLessThan(SampleMeansData data) {
		double z = (data.meanForComparison - data.mu) / (computeSampleMeansStandardDeviation(data)); 
		NormalDist obj = new NormalDist(); 
		return obj.computeFiniteZProbMidpointRiemann(-1000.0, z);
	}
	
	public double computeProbabilityGreaterThan(SampleMeansData data) {
		double z = (data.meanForComparison - data.mu) / (computeSampleMeansStandardDeviation(data)); 
		NormalDist obj = new NormalDist(); 
		return obj.computeFiniteZProbMidpointRiemann(z, 1000.0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 

        SampleMeans myObj = new SampleMeans();
        SampleMeansData myData = new SampleMeansData(); 
		
		//////////////////////////////////////////////////////
		
		// enter your data here! 
		
		myData.mu = 30;
		myData.sigma = 50; 
        myData.sampleSize = 1000; 
        myData.meanForComparison = 33; 
        myData.statement = "xBar > meanForComparison"; 
				
		//////////////////////////////////////////////////////

        System.out.println("Sample Means Information: "); 
        System.out.println(); 
 
        if (myData.statement.equals("xBar < meanForComparison")) {
            System.out.println("Probability that xBar < meanForComparison: " + myObj.computeProbabilityLessThan(myData)); 
        } else if (myData.statement.equals("xBar > meanForComparison")) {
            System.out.println("Probability that xBar > meanForComparison: " + myObj.computeProbabilityGreaterThan(myData)); 
        } else {
            System.out.println("Probability that " + myData.statement + ": 0 - error in statement or not a continous random variable"); 
        }
		
		System.out.println(); 
		System.out.println("-------");  
		System.out.println(); 
		long after = System.nanoTime();
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 

	}

}
