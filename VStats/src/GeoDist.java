

public class GeoDist {

	private double pGeoPdf;
    private double pGeoCdf;
    private double pSuccess;
    private int LowBound;
    private int HighBound;
    private int xVal;

public GeoDist(double inputPSuccess , int inputXVal) {
    pSuccess = inputPSuccess;
    xVal = inputXVal; 
    pGeoPdf = 0.0;
    pGeoCdf = 0.0;
    LowBound = 1;
    HighBound = 2;
}

public void computePGeoPdf() {
    pGeoPdf = (pSuccess) * (Math.pow((1-pSuccess), (xVal-1)));
}

public void ComputePGeoCdf() {
	pGeoPdf = (pSuccess) * (Math.pow((1-pSuccess), (xVal-1)));
	pGeoCdf += pGeoPdf;
}

public void computePGeoCdf(int inputLowBound , int inputHighBound) {
	for (int i = inputLowBound; i <= inputHighBound; i++) {
		setxVal(i);
		ComputePGeoCdf();
	}
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////


public static void main(String[] args) {
	// TODO Auto-generated method stub

	double PSuccess = 0.3;
	int XVal = 3;

	GeoDist myGeoDistObject = new GeoDist(PSuccess , XVal);

	System.out.println();
	myGeoDistObject.computePGeoPdf();
	System.out.println("GeometricPdf = " + myGeoDistObject.getpGeoPdf() + " (" + (myGeoDistObject.getpGeoPdf() * 100) + "%) ");

	System.out.println();
	myGeoDistObject.computePGeoCdf(1 , 4);
	System.out.println("GeometricCdf = " + myGeoDistObject.getpGeoCdf() + " (" + (myGeoDistObject.getpGeoCdf() * 100) + "%) ");
	
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////



public double getpGeoPdf() {
	return pGeoPdf;
}

public void setpGeoPdf(double pGeoPdf) {
	this.pGeoPdf = pGeoPdf;
}

public double getpGeoCdf() {
	return pGeoCdf;
}

public void setpGeoCdf(double pGeoCdf) {
	this.pGeoCdf = pGeoCdf;
}

public double getpSuccess() {
	return pSuccess;
}

public void setpSuccess(double pSuccess) {
	this.pSuccess = pSuccess;
}

public int getLowBound() {
	return LowBound;
}

public void setLowBound(int lowBound) {
	LowBound = lowBound;
}

public int getHighBound() {
	return HighBound;
}

public void setHighBound(int highBound) {
	HighBound = highBound;
}

public int getxVal() {
	return xVal;
}

public void setxVal(int xVal) {
	this.xVal = xVal;
}

public void computePGeoCdf() {
    for (int i = LowBound; i <= HighBound; i++) {
        computePGeoPdf();
    }
}

}
