
public class SingleProportionZConfidence {
	
	String confidenceInterval;
	
	public SingleProportionZConfidence() {
		confidenceInterval = ""; 
	}
	
	public static class SinglePropZConfData {
		double successes;
		double sampleSize;
		double confidenceLevel; 
	}
	
	public String computeSingleProportionZConfidenceInterval(SinglePropZConfData data) {
		
		double pHat = 1.0 * (data.successes / data.sampleSize);
		double qHat = pHat; 
		ZDistConfidence obj = new ZDistConfidence(); 
		double zStar = obj.computeZStar(data.confidenceLevel); 
		double standardError = Math.sqrt((pHat * qHat) / (data.sampleSize));
		double low = pHat - (zStar * standardError); 
		double high = pHat + (zStar * standardError); 
		
		return "(" + low + ", " + high + ")"; 
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SingleProportionZConfidence myObj = new SingleProportionZConfidence(); 
		SinglePropZConfData myData = new SinglePropZConfData(); 
		
		////////////////////////////////////////////////////
		
		// enter your data below!
		
		myData.successes = 12;
		myData.sampleSize = 100;
		myData.confidenceLevel = 0.95;
		
		////////////////////////////////////////////////////
		
		System.out.println(myObj.computeSingleProportionZConfidenceInterval(myData)); 

	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
