
public class LinearRegression {
	
	private OneVar m_indVar; 
	private OneVar m_depVar; 
	
	public LinearRegression(double[] indVarArr, double[] depVarArr) {
		
		m_indVar = new OneVar(indVarArr); 
		m_depVar = new OneVar(depVarArr); 
		
	}
	
	public double computeSe() {
		
		return Math.sqrt((computeSumOfResidualsSquared()) / (m_depVar.computeLength())); 
		
	}
	
	public double computeSumOfResidualsSquared() {
		
		double sum = 0.0; 

		double[] temp = computeResidualValues(); 
		
		for (int i = 0; i < temp.length; i++) {
			sum += (Math.pow(temp[i], 2)); 
		}
		
		return sum; 
		
	}
	
	public double[] computeResidualValues() {
		
		double[] res = new double[m_indVar.computeLength()]; 
		
		for (int i = 0; i < res.length; i++) {
			res[i] = (m_depVar.atIndex(i)) - (computeLSRLOutput(m_indVar.atIndex(i))); 
		}
		
		return res; 
		
	}
	
	public double[] computeYPredictedValues() {
		
		double[] res = new double[m_indVar.computeLength()]; 
		
		for (int i = 0; i < res.length; i++) {
			res[i] = computeLSRLOutput(m_indVar.atIndex(i)); 
		}
		
		return res; 
		
	} 
	
	public double computeLSRLOutput(double input) {
		
		return ((computeA()) + (computeB() * input)); 
		
	}
	
	public String displayLSRLEquation() {
		
		String res = ""; 
		
		res = "Ypredicted = "  + (computeA()) + " + " + (computeB()) + "x"; 
		
		if (computeB() < 0) {
			res = "Ypredicted = "  + (computeA()) + " - " + (-1 * computeB()) + "x"; 
		} else if (computeB() >= 0) {
			res = "Ypredicted = "  + (computeA()) + " + " + (computeB()) + "x"; 
		} 
		
		return res; 
		
	}
	
	public double computeA() {
		
		double a = (m_depVar.getMean()) - ((computeB()) * (m_indVar.getMean())); 
				
		return a; 
		
	}
	
	public double computeB() {
		
		
		double r = computeR(); 

		double b = r * (m_depVar.getStanDev() / m_indVar.getStanDev()); 
		
		return b; 
		
	}
	
	public double computeR() {
		
		double sumOfProducts = 0.0; 

		double arr1Bar = m_indVar.getMean();
		double arr1Sigma = m_indVar.getStanDev();

		
		double arr2Bar = m_depVar.getMean(); 
		double arr2Sigma = m_depVar.getStanDev();  
		
		
		for (int i = 0; i < m_indVar.computeLength(); i++) { // both arrays must have the same length 
			sumOfProducts += ((m_indVar.atIndex(i) - arr1Bar) / (arr1Sigma)) * ((m_depVar.atIndex(i) - arr2Bar) / (arr2Sigma)); 
		}
		
		return sumOfProducts / (m_indVar.computeLength()-1); 
		
	}
	
	public double computeRSquaredValue() {
		
		return Math.pow((computeR()), 2.0); 
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime();
		
		////////////////////////////////////////////////////////
		
		// enter your data lists here! 
		
		double[] arr1 = {4, 5, 6, 7}; // Independent-Variable
		double[] arr2 = {8, 29, 30, 40}; // Dependent-Variable 
		
		////////////////////////////////////////////////////////
		
		LinearRegression myObj = new LinearRegression(arr1, arr2); 
		
		System.out.println("LinearRegression: ");
		System.out.println();
		
		System.out.println("LSRL: " + myObj.displayLSRLEquation()); 
		System.out.println(); 
		System.out.println("r = " + myObj.computeR());
		System.out.println("r^2 = " + myObj.computeRSquaredValue()); 
		System.out.println(); 
		
		System.out.println("sum of residuals squared: " + myObj.computeSumOfResidualsSquared());
		System.out.println("Se: " + myObj.computeSe()); 
		System.out.println(); 
		
		System.out.println("-------");  
		
		System.out.println(); 
		
		long after = System.nanoTime();
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
