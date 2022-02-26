
public class SampleProportions { 
    
    private double mu;
    private double sigma;

    public static class SampleProportionsData {
        private double pHat;
        private double sampleSize; 
        private double proportionForComparison; 
        private String statement; 
    }
	
	public SampleProportions() {
        mu = 0;
        sigma = 0;
	}
	
    public double computeSampleProportionsMu(SampleProportionsData data) {
        return data.pHat; 
    }

    public double computeSampleProportionsStandardDeviation(SampleProportionsData data) {
        double numerator = (data.pHat) * (1-data.pHat); 
        return Math.sqrt(numerator / (data.sampleSize)); 
    }

    public double probabilityLessThan(SampleProportionsData data) {
        
        double z = (data.proportionForComparison - data.pHat) / (computeSampleProportionsStandardDeviation(data)); 
        NormalDist obj = new NormalDist(); 
        return obj.computeFiniteZProbMidpointRiemann(-1000, z); 

    }

    public double probabilityGreaterThan(SampleProportionsData data) {

        double z = (data.proportionForComparison - data.pHat) / (computeSampleProportionsStandardDeviation(data)); 
        NormalDist obj = new NormalDist();
        return obj.computeFiniteZProbMidpointRiemann(z, 1000); 

    }
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 

        SampleProportions myObj = new SampleProportions();
        SampleProportionsData myData = new SampleProportionsData(); 
		
		//////////////////////////////////////////////////////
		
		// enter your data here! 
		
		myData.pHat = 0.22;
        myData.sampleSize = 200; 
        myData.proportionForComparison = 0.3; 
        myData.statement = "pHat > proportionForComparison"; 
				
		//////////////////////////////////////////////////////

        System.out.println("Sample Proportions Information: "); 
        System.out.println(); 
 
        if (myData.statement.equals("pHat < proportionForComparison")) {
            System.out.println("Probability that pHat < proportionForComparison: " + myObj.probabilityLessThan(myData)); 
        } else if (myData.statement.equals("pHat > proportionForComparison")) {
            System.out.println("Probability that pHat > proportionForComparison: " + myObj.probabilityGreaterThan(myData)); 
        } else {
            System.out.println("Probability that " + myData.statement + ": 0 - not a continous random variable"); 
        }
		
		System.out.println(); 
		System.out.println("-------");  
		System.out.println(); 
		long after = System.nanoTime();
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 

		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
