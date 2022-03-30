import java.util.ArrayList;

public class OneVar {

// this is the class that computes statistics
// data set must be 1D array

	private double[] inputData;
	private double mean;
	private double median; 
	private double mode;
	private double variance;
	private double stanDev;
	private double minimum;
	private double maximum;
	private double range;
	private double quartile1;
	private double quartile3;
	private double numValues;
	private double sumValues;
	
	
	public OneVar() {
		
	}


	public OneVar(double[] in) {
		inputData = in;
		mean = 0.0;
		median = 0.0;
		stanDev = 0.0;
		minimum = 0.0;
		maximum = 0.0;
		range = 0.0;
		mode = 0.0;
		quartile1 = 0.0;
		quartile3 = 0.0;
		numValues = 0.0;
		sumValues = 0.0;
		
		
		computeMean();
		computeVariance(); 
		computeStanDev(); 
		
	}

	public double computeMean() {
		double sum = 0;
		double count = 0.0;

		for (int i = 0; i < inputData.length; i++) {
			sum = sum + inputData[i];
			count++;
		}
		// sum is calculated
		// number of inputData in Array is calculated

		mean = sum / count;
		
		return mean; 

	}

	public void sort() {

		for (int j = 1; j < inputData.length; j++) {
			double temp = inputData[j];
			int possibleIndex = j;
			while (possibleIndex > 0 && temp < inputData[possibleIndex - 1]) {
				inputData[possibleIndex] = inputData[possibleIndex - 1];
				possibleIndex--;
			}
			inputData[possibleIndex] = temp;
		}

	}

	public double computeMedian() { // this code computes median using the insertion sort algorithm

		int middleIndex = 0;
		sort();

		// sorted.
		middleIndex = (inputData.length / 2);

		if (inputData.length % 2 != 0) { // odd
			median = inputData[middleIndex]; // median is good for odd number of values
		} else {
			median = (inputData[middleIndex - 1] + inputData[middleIndex]) / 2.0;
		}
		
		return median; 

	}

	public double computeMinimum() {
		double minTemp = inputData[0];

		for (int i = 0; i < inputData.length; i++) {
			if (inputData[i] < minTemp) {
				minTemp = inputData[i];
			}
		}

		minimum = minTemp * 1.0;
		
		return minimum; 

	}

	public double computeMaximum() {
		double maxTemp = inputData[0];

		for (int i = 0; i < inputData.length; i++) {
			if (inputData[i] > maxTemp) {
				maxTemp = inputData[i];
			}
		}

		maximum = maxTemp * 1.0;
		
		return maximum; 

	}

	public double computeRange() {
		computeMinimum();
		computeMaximum();
		range = (maximum - minimum) * 1.0;
		return range; 
	}

	public double computeVariance() {
		double sumDiff = 0.0;

		for (int i = 0; i < inputData.length; i++) {
			sumDiff += ((inputData[i] - mean) * (inputData[i] - mean));
		}

		variance = sumDiff / (inputData.length - 1);
		
		return variance; 

	}

	public double computeStanDev() {
		//assert variance != 0;
		stanDev = Math.sqrt(variance);
		
		return stanDev;
				
	}

	public double computeQuartile1() {
		int middleIndex = inputData.length / 2;
		int count = -1;
		sort();

		for (int i = 0; i <= middleIndex - 1; i++) {
			count++;
		}

		if ((inputData.length % 2 == 0) && (count % 2 != 0)) {
			quartile1 = (inputData[count / 2] + inputData[(count / 2) + 1]) / 2.0;
		}

		if ((inputData.length % 2 == 0) && (count % 2 == 0)) {
			quartile1 = inputData[count / 2];
		}

		if ((inputData.length % 2 != 0) && (count % 2 == 0)) {
			quartile1 = inputData[count / 2];
		}

		if ((inputData.length % 2 != 0) && (count % 2 != 0)) {
			quartile1 = (inputData[count / 2] + inputData[(count / 2) + 1]) / 2.0;
		}
		
		return quartile1; 

	}

	public double computeQuartile3() {
		int middleIndex = inputData.length / 2;
		int arrayEvenCounter = 0;
		int arrayOddCounter = 0;
		sort();

		for (int i = middleIndex + 1; i < inputData.length; i++) {
			arrayEvenCounter++;
		}

		if ((inputData.length % 2 != 0) && (arrayEvenCounter % 2 != 0)) {
			quartile3 = inputData[middleIndex + ((arrayEvenCounter / 2) + 1)]; // this if statement block is done
		}

		if ((inputData.length % 2 != 0) && (arrayEvenCounter % 2 == 0)) {
			quartile3 = (inputData[middleIndex + (arrayEvenCounter / 2)]
					+ inputData[middleIndex + (arrayEvenCounter / 2) + 1]) / 2.0;
		}

		for (int i = middleIndex; i < inputData.length; i++) {
			arrayOddCounter++;
		}

		if ((inputData.length % 2 == 0) && (arrayOddCounter % 2 != 0)) {
			quartile3 = inputData[middleIndex + (arrayOddCounter / 2)];
		}

		if ((inputData.length % 2 == 0) && (arrayOddCounter % 2 == 0)) {
			// quartile3 = (inputData[middleIndex + ((arrayOddCounter / 2) - 1)] +
			// inputData[middleIndex + (arrayOddCounter / 2)] ) / 2;
			quartile3 = (inputData[middleIndex + (arrayOddCounter / 2) - 1]
					+ inputData[middleIndex + (arrayOddCounter / 2)]) / 2.0;
		}
		
		return quartile3; 

	}

	public double computeMode() {

		int[] modeFinder = new int[inputData.length];

		for (int i = 0; i < modeFinder.length; i++) {
			modeFinder[i] = 1;
		}

		for (int i = 0; i < inputData.length; i++) {
			for (int j = 0; j < inputData.length; j++) {
				if (inputData[i] == inputData[j]) {
					modeFinder[i]++;
				}
			}
		}

			int cmaxTemp = modeFinder[0];
			
			for (int i = 0; i < modeFinder.length; i++) {
				if (modeFinder[i] > cmaxTemp) {
					cmaxTemp = modeFinder[i];
				}
			}

			int tempIndex = 0;
			for (int i = 0; i < modeFinder.length; i++) {
				if (modeFinder[i] == cmaxTemp) {
					tempIndex = i;
				}
			}
			
			mode = inputData[tempIndex];

			return mode; 

	}

	public double computeNumValues() {
		numValues = inputData.length;
		return numValues; 
	}

	public double computeSumValues() {

		for (int i = 0; i < inputData.length; i++) {
			sumValues += inputData[i];
		}
		
		return sumValues; 
		
	}
	
	public int computeLength() {
		
		int count = 0; 
		
		for (int i = 0; i < inputData.length; i++) {
			count++; 
		}
		
		return count; 
		
	}
	
	public double atIndex(int i) {
		
		return inputData[i]; 
		
	}
	
	public double computeIQR() {
		
		return computeQuartile3() - computeQuartile1(); 
		
	}
	
	public ArrayList<Double> computeOutliers() {
		
		double lowBound = computeQuartile1() - (1.5 * computeIQR()); 
		double highBound = computeQuartile3() + (1.5 * computeIQR()); 
		ArrayList<Double> res = new ArrayList<Double>(); 
		
		for (int i = 0; i < inputData.length; i++) {
			if ((inputData[i] < lowBound) || (highBound > highBound))
				res.add(inputData[i]); 
		}
		
		return res; 
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 
		
		double[] data = {1,2,3}; // input Array Data Set here! 
		OneVar myOneVarObject = new OneVar(data);
		
		
		System.out.println("One-Variable Statistics: ");
		System.out.println();
		System.out.println("number of data values = " + myOneVarObject.computeNumValues());
		System.out.println("sum of data values = " + myOneVarObject.computeSumValues());
		System.out.println("mean = " + myOneVarObject.computeMean());
		System.out.println("range = " + myOneVarObject.computeRange()); 
		System.out.println("mode = " + myOneVarObject.computeMode());
		System.out.println("variance = " + myOneVarObject.computeVariance());
		System.out.println("standard deviation = " + myOneVarObject.computeStanDev());
		System.out.println();
		
		System.out.println("5-Number Summary: ");
		System.out.println("minimum = " + myOneVarObject.computeMinimum());
		System.out.println("quartile 1 = " + myOneVarObject.computeQuartile1());
		System.out.println("median = " + myOneVarObject.computeMedian());
		System.out.println("quartile 3 = " + myOneVarObject.computeQuartile3());
		System.out.println("maximum = " + myOneVarObject.computeMaximum());
		
		System.out.println(); 
		System.out.println("-------"); 
		System.out.println(); 
		
		long after = System.nanoTime(); 
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	

	public double getMean() {
		return mean;
	}

	public double getMedian() {
		return median;
	}

	public double getMode() {
		return mode;
	}

	public double getStanDev() {

		return stanDev;
	}

	public double getVariance() {
		return variance;
	}

	public double getMinimum() {
		return minimum;
	}

	public double getMaximum() {
		return maximum;
	}

	public double getRange() {
		return range;
	}

	public double getQuartile1() {
		return quartile1;
	}

	public double getQuartile3() {
		return quartile3;
	}

	public double getNumValues() {
		return numValues;
	}

	public double getSumValues() {
		return sumValues;
	}
	

}
