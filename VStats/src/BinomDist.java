

public class BinomDist {

	private double pBinomPdf;
	private double pBinomCdf;
 
	private int numTrials; // = n
	private double pSuccess;
	private int xVal; // = r
	private int resCombination; 


public BinomDist (int inputNumTrials, double inputPSuccess, int inputXVal) {
	numTrials = inputNumTrials;
	pSuccess = inputPSuccess;
	xVal = inputXVal;
	
	pBinomPdf = 0;
	pBinomCdf = 0;
	resCombination = 0;
	pBinomCdf = 0.0;
}

public int computeFactorial(int inputVal) {
	
	int resFactorial = 1;
	
    for (int i = inputVal; i >= 1; i--) {
    	resFactorial = resFactorial * i;
    }
    return resFactorial;
}

public void computeCombination() {
	
	resCombination = (computeFactorial(numTrials)) / ( (computeFactorial(xVal)) * (computeFactorial(numTrials - xVal)));
}

public void computeBinomPdf() {
	
	computeCombination();
	
	pBinomPdf = (resCombination) * (Math.pow(pSuccess, xVal)) * (Math.pow((1-pSuccess), (numTrials - xVal)));
	
	
}

public void ComputepBinomCdf() {
	
	computeCombination();
	
	pBinomPdf = (resCombination) * (Math.pow(pSuccess, xVal)) * (Math.pow((1-pSuccess), (numTrials - xVal)));
	
	pBinomCdf += pBinomPdf;
	
}


public void computeBinomCdf(int inputLBound , int inputHBound) {

	for (int i = inputLBound; i <= inputHBound; i++) {
		setxVal(i);
		ComputepBinomCdf();
	}
	
	
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	long before = System.nanoTime(); 

	int NumTrials = 10;
	double PSuccess = 0.87;
	int XVal = 4;
	
	
	BinomDist myBinomDistObject = new BinomDist(NumTrials,  PSuccess,  XVal);
	
	System.out.println("Binomial Distribution: "); 
	System.out.println(); 
	
	myBinomDistObject.computeBinomPdf();
	System.out.println("BinomialPdf = " + myBinomDistObject.getpBinomPdf() + " (" + (myBinomDistObject.getpBinomPdf() * 100) + "%) ");
	System.out.println();
	
	myBinomDistObject.computeBinomCdf(1 , 3);
	System.out.println("BinomialCdf = " + myBinomDistObject.getpBinomCdf() + " (" + (myBinomDistObject.getpBinomCdf() * 100) + "%) ");
	System.out.println();
	
	System.out.println("-------"); 
	System.out.println(); 
	
	long after = System.nanoTime(); 
	
	System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
	
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////


public double getpBinomPdf() {
	return pBinomPdf;
}

public void setpBinomPdf(double pBinomPdf) {
	this.pBinomPdf = pBinomPdf;
}

public double getpBinomCdf() {
	return pBinomCdf;
}

public void setpBinomCdf(double pBinomCdf) {
	this.pBinomCdf = pBinomCdf;
}

public int getNumTrials() {
	return numTrials;
}

public void setNumTrials(int numTrials) {
	this.numTrials = numTrials;
}

public double getpSuccess() {
	return pSuccess;
}

public void setpSuccess(double pSuccess) {
	this.pSuccess = pSuccess;
}

public int getxVal() {
	return xVal;
}

public void setxVal(int xVal) {
	this.xVal = xVal;
}

}
