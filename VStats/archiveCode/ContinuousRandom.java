
public class ContinuousRandom {
	
	private double var1;
	private double var2;
	
	public ContinuousRandom() {
		var1 = 0;
		var2 = 0;
	}
	
	public static class ContinuousData {
		private double mu1; 
		private double standardDeviation1;
		private double mu2;
		private double standardDeviation2; 
		private double valueForComparison; 
	}
	
	public double var1LessThanVar2(ContinuousData data) { 
		double newMu = data.mu1 - data.mu2; 
		double newVariance = (Math.pow(data.standardDeviation1, 2) + Math.pow(data.standardDeviation2, 2)); 
		double newSD = Math.sqrt(newVariance); 
		
		double z = (0-newMu) / (newSD); 
		NormalDist obj = new NormalDist();
		return obj.computeFiniteZProbMidpointRiemann(-1000, z); 
	}
	
	public double var1GreaterThanVar2(ContinuousData data) {
		double newMu = data.mu1 - data.mu2; 
		double newVariance = (Math.pow(data.standardDeviation1, 2) + Math.pow(data.standardDeviation2, 2)); 
		double newSD = Math.sqrt(newVariance); 
		
		double z = (0-newMu) / (newSD); 
		NormalDist obj = new NormalDist();
		return obj.computeFiniteZProbMidpointRiemann(z, 1000); 
	}
	
	public double sumOfBothVarsGreaterThanValue(ContinuousData data) {
		double newMu = data.mu1 + data.mu2; 
		double newVariance = (Math.pow(data.standardDeviation1, 2) + Math.pow(data.standardDeviation2, 2));
		double newSD = Math.sqrt(newVariance); 
		
		double z = (data.valueForComparison - newMu) / (newSD); 
		NormalDist obj = new NormalDist();
		return obj.computeFiniteZProbMidpointRiemann(z, 1000); 
	}
	
	public double sumOfBothVarsLessThanValue(ContinuousData data) {
		double newMu = data.mu1 + data.mu2; 
		double newVariance = (Math.pow(data.standardDeviation1, 2) + Math.pow(data.standardDeviation2, 2));
		double newSD = Math.sqrt(newVariance); 
		
		double z = (data.valueForComparison - newMu) / (newSD); 
		NormalDist obj = new NormalDist();
		return obj.computeFiniteZProbMidpointRiemann(-1000, z); 
	}

////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 
		
		ContinuousRandom myObj = new ContinuousRandom(); 
		ContinuousData myData = new ContinuousData(); 
		
		System.out.println("Continuous Random Variables Information: "); 
		System.out.println(); 
		
		//////////////////////////////////////////////////
		
		// enter your data/methods here! 
		
		myData.mu1 = 110;
		myData.standardDeviation1 = 10;
		myData.mu2 = 100;
		myData.standardDeviation2 = 8; 
		myData.valueForComparison = 15; 
		
		System.out.println("Probability that Var1 is less than Var2: " + myObj.var1LessThanVar2(myData));
		
		//////////////////////////////////////////////////
		
		System.out.println(); 
		System.out.println("-------");  
		System.out.println(); 
		long after = System.nanoTime();
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 


	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////

}
