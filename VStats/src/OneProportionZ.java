
public class OneProportionZ {
	
	String confidenceInterval;
	
	public OneProportionZ() {
		confidenceInterval = ""; 
	}
	
	public static class SinglePropZConfData {
		double successes;
		double sampleSize;
		double confidenceLevel; 
	}
	
	public static class SinglePropZTestData {
		double successes;
		double pNought;
		double sampleSize;
		double alpha;
		String alternativeHypothesis; 
	}
	
	public String computeOnePropZConfInt(SinglePropZConfData data) {
		
		double pHat = 1.0 * (data.successes / data.sampleSize);
		double qHat = pHat; 
		ZDistConfidence obj = new ZDistConfidence(); 
		double zStar = obj.computeZStar(data.confidenceLevel); 
		double standardError = Math.sqrt((pHat * qHat) / (data.sampleSize));
		double low = pHat - (zStar * standardError); 
		double high = pHat + (zStar * standardError); 
		
		return "(" + low + ", " + high + ")"; 
		
	}
	
	public String computeOnePropZSigTest(SinglePropZTestData data) {
		
		if (data.alternativeHypothesis.equals("p-nought greater than value")) {
			return computeOnePropZSigTestP0GreaterThanValue(data);
		} else if (data.alternativeHypothesis.equals("p-nought less than value")) {
			return computeOnePropZSigTestP0LessThanValue(data);
		} else if (data.alternativeHypothesis.equals("p-nought not equals value")) {
			return computeOnePropZSigTestP0NotEqualsValue(data);
		} else {
			return "ERROR in Ha statement - please check typing/syntax"; 
		}
		
	}
	
	public String computeOnePropZSigTestP0LessThanValue(SinglePropZTestData data) {
		
		double pHat = 1.0 * (data.successes / data.sampleSize); 
		double qNought = 1 - data.pNought; 
		
		double zCritical = (pHat - data.pNought) / (Math.sqrt((data.pNought * qNought) / (data.sampleSize))); 
		NormalDist obj = new NormalDist(); 
		
		double pValue = obj.computeFiniteZProbMidpointRiemann(-1000.0, zCritical); 
		
		if (pValue < data.alpha) {
			return "There is statistically signficant evidence that the true P0 is less than the given P0 - reject H0"; 
		} else if (pValue > data.alpha) {
			return "There is no statistically signficant evidence that the true P0 is less than the given P0 - fail to reject H0"; 
		} else { // pValue equals alpha 
			return ""; 
		}
		
	}
	
	public String computeOnePropZSigTestP0GreaterThanValue(SinglePropZTestData data) {
		
		double pHat = 1.0 * (data.successes / data.sampleSize); 
		double qNought = 1 - data.pNought; 
		
		double zCritical = (pHat - data.pNought) / (Math.sqrt((data.pNought * qNought) / (data.sampleSize))); 
		NormalDist obj = new NormalDist(); 
		
		double pValue = obj.computeFiniteZProbMidpointRiemann(zCritical, 1000.0); 
		
		if (pValue < data.alpha) {
			return "There is statistically signficant evidence that the true P0 is greater than the given P0 - reject H0"; 
		} else if (pValue > data.alpha) {
			return "There is no statistically signficant evidence that the true P0 is greater than the given P0 - fail to reject H0"; 
		} else { // pValue equals alpha 
			return ""; 
		}
		
	}
	
	public String computeOnePropZSigTestP0NotEqualsValue(SinglePropZTestData data) {
		
		double pHat = 1.0 * (data.successes / data.sampleSize); 
		double qNought = 1 - data.pNought; 
		
		double zCritical = (pHat - data.pNought) / (Math.sqrt((data.pNought * qNought) / (data.sampleSize))); 
		NormalDist obj = new NormalDist(); 
		
		double pValue = obj.computeFiniteZProbMidpointRiemann((Math.abs(zCritical)), 1000.0) * 2.0; 
		
		if (pValue < data.alpha) {
			return "There is statistically signficant evidence that the true P0 not equals the given P0 - reject H0"; 
		} else if (pValue > data.alpha) {
			return "There is no statistically signficant evidence that the true P0 not equals the given P0 - fail to reject H0"; 
		} else { // pValue equals alpha 
			return ""; 
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 
		
		OneProportionZ myObj = new OneProportionZ(); 
		SinglePropZConfData myConfIntData = new SinglePropZConfData(); 
		SinglePropZTestData myZTestData = new SinglePropZTestData(); 
		
		System.out.println("Single Proportion Z: "); 
		System.out.println(); 
		
		////////////////////////////////////////////////////
		
		// enter your confidence interval data below!
		
		myConfIntData.successes = 12;
		myConfIntData.sampleSize = 100;
		myConfIntData.confidenceLevel = 0.95;
		
		////////////////////////////////////////////////////
		
		// enter your significance test data below! 
		
		myZTestData.successes = 47;
		myZTestData.sampleSize = 500; 
		myZTestData.alpha = 0.10;
		myZTestData.pNought = 0.08;
		myZTestData.alternativeHypothesis = "p-nought greater than value"; 
		
		////////////////////////////////////////////////////
		
		System.out.println(myObj.computeOnePropZConfInt(myConfIntData)); 
		System.out.println(myObj.computeOnePropZSigTest(myZTestData)); 
		
		System.out.println(); 
		System.out.println("-------"); 
		System.out.println(); 
		
		long after = System.nanoTime(); 
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		

	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
