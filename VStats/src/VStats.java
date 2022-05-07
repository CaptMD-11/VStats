import java.util.ArrayList;

public final class VStats {

	public VStats(double[] in) { 

	} 

	/**
	 * Returns the mean (average) of a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the mean of <strong>inputData</strong>. 
	 */
	public static double computeMean(double[] inputData) {
		double sum = 0;

		for (int i = 0; i < inputData.length; i++) {
			sum += inputData[i];
		}

		return ((sum * 1.0) / inputData.length);

	}

	/**
	 * Returns the mean absolute deviation (MAD) of a <code>double</code> array, in a <code>double</code> format. 
	 * <p>
	 * The MAD of an array the absolute value of the average distance from each inputData value to the mean. 
	 * 
	 * @param inputData , a <code>double</code> array. 
	 * @return the mean absolute deviation of <strong>inputData</strong>. 
	 */
	public static double computeMAD(double[] inputData) {
		double inputDataMean = computeMean(inputData); 
		ArrayList<Double> res = new ArrayList<Double>(); 
		for (int i = 0; i < inputData.length; i++) {
			res.add(Math.abs(inputData[i] - inputDataMean)); 
		}
		double sum = 0.0; 
		for (int i = 0; i < res.size(); i++) {
			sum += res.get(i); 
		}
		return sum / (inputData.length * 1.0); 
	}

	/**
	 * Returns the median (second quartile) of a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the median of <strong>inputData</strong>. 
	 */
	public static double computeMedian(double[] inputData) { // this code computes median using the insertion sort algorithm

		int middleIndex = 0;
		sort(inputData);

		// sorted.
		middleIndex = (inputData.length / 2);
		double temp = 0.0;
		if (inputData.length % 2 != 0) { // odd
			temp = inputData[middleIndex]; // median is good for odd number of values
			return temp;
		} else {
			temp = (inputData[middleIndex - 1] + inputData[middleIndex]) / 2.0;
			return temp;
		}

	}

	public static void sort(double[] inputData) {

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

	/**
	 * Returns the minimum value in a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the minimum value in <strong>inputData</strong>. 
	 */
	public static double computeMinimum(double[] inputData) {
		double minTemp = inputData[0];

		for (int i = 0; i < inputData.length; i++) {
			if (inputData[i] < minTemp) {
				minTemp = inputData[i];
			}
		}

		return (minTemp * 1.0);

	}

	/**
	 * Returns the maximum value in a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the maximum value in <strong>inputData</strong>. 
	 */
	public static double computeMaximum(double[] inputData) {
		double maxTemp = inputData[0];

		for (int i = 0; i < inputData.length; i++) {
			if (inputData[i] > maxTemp) {
				maxTemp = inputData[i];
			}
		}

		return (maxTemp * 1.0);

	}

	/**
	 * Returns the range (maximum value - minimum value) of a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the range of <strong>inputData</strong>. 
	 */
	public static double computeRange(double[] inputData) {
		return (computeMaximum(inputData) - computeMinimum(inputData)) * 1.0;
	}

	/**
	 * Returns the variance of a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the variance of <strong>inputData</strong>. 
	 */
	public static double computeVariance(double[] inputData) {
		double sumDiff = 0.0;

		for (int i = 0; i < inputData.length; i++) {
			sumDiff += ((inputData[i] - computeMean(inputData)) * (inputData[i] - computeMean(inputData)));
		}

		return sumDiff / (inputData.length - 1);

	}

	/**
	 * Returns the standard deviation of a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the standard deviation of <strong>inputData</strong>. 
	 */
	public static double computeStandardDeviation(double[] inputData) {
		// assert variance != 0;
		return Math.sqrt(computeVariance(inputData));
	}

	/**
	 * Returns the first quartile of a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the first quartile of <strong>inputData</strong>. 
	 */
	public static double computeQuartile1(double[] inputData) {
		int middleIndex = inputData.length / 2;
		int count = -1;
		double quartile1 = 0.0;
		sort(inputData);

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

	/**
	 * Returns the first quartile of a <code>double</code> array, in a <code>double</code> format. 
	 * @param inputData , a <code>double</code> array. 
	 * @return the third quartile of <strong>inputData</strong>. 
	 */
	public static double computeQuartile3(double[] inputData) {
		int middleIndex = inputData.length / 2;
		int arrayEvenCounter = 0;
		int arrayOddCounter = 0;
		double quartile3 = 0.0;
		sort(inputData);

		for (int i = middleIndex + 1; i < inputData.length; i++) {
			arrayEvenCounter++;
		}

		if ((inputData.length % 2 != 0) && (arrayEvenCounter % 2 != 0)) {
			quartile3 = inputData[middleIndex + ((arrayEvenCounter / 2) + 1)]; // this if statement block is done
		}

		if ((inputData.length % 2 != 0) && (arrayEvenCounter % 2 == 0)) {
			quartile3 = (inputData[middleIndex + (arrayEvenCounter / 2)] + inputData[middleIndex + (arrayEvenCounter / 2) + 1])
					/ 2.0;
		}

		for (int i = middleIndex; i < inputData.length; i++) {
			arrayOddCounter++;
		}

		if ((inputData.length % 2 == 0) && (arrayOddCounter % 2 != 0)) {
			quartile3 = inputData[middleIndex + (arrayOddCounter / 2)];
		}

		if ((inputData.length % 2 == 0) && (arrayOddCounter % 2 == 0)) {
			// quartile3 = (inputinputData[middleIndex + ((arrayOddCounter / 2) - 1)] +
			// inputinputData[middleIndex + (arrayOddCounter / 2)] ) / 2;
			quartile3 = (inputData[middleIndex + (arrayOddCounter / 2) - 1] + inputData[middleIndex + (arrayOddCounter / 2)])
					/ 2.0;
		}

		return quartile3;

	}

	public double computeMode(double[] inputData) {

		int[] modeFinder = new int[inputData.length];
		double mode = 0.0;

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

	public double computeNumValues(double[] inputData) {
		return (inputData.length * 1.0);
	}

	public double computeSumValues(double[] inputData) {
		double sumValues = 0.0;
		for (int i = 0; i < inputData.length; i++) {
			sumValues += inputData[i];
		}

		return sumValues;

	}

	public int computeLength(double[] inputData) {
		return inputData.length;
	}

	public double atIndex(double[] inputData, int i) {
		return inputData[i];
	}

	public double computeIQR(double[] inputData) {

		return computeQuartile3(inputData) - computeQuartile1(inputData);

	}

	public ArrayList<Double> computeOutliers(double[] inputData) {

		double lowBound = computeQuartile1(inputData) - (1.5 * computeIQR(inputData));
		double highBound = computeQuartile3(inputData) + (1.5 * computeIQR(inputData));
		ArrayList<Double> res = new ArrayList<Double>();

		for (int i = 0; i < inputData.length; i++) {
			if ((inputData[i] < lowBound) || (highBound > highBound))
				res.add(inputData[i]);
		}

		return res;

	}

	public int computeFactorial(int inputVal) {

		int resFactorial = 1;

		for (int i = inputVal; i >= 1; i--) {
			resFactorial = resFactorial * i;
		}
		return resFactorial;
	}

	public double computeCombination(int numTrials, int xVal) {

		return ((computeFactorial(numTrials)) / ((computeFactorial(xVal)) * (computeFactorial(numTrials - xVal)))
				* 1.0);
	}

	public double computeBinomialPdf(int numTrials, int xVal, double pSuccess) {
		return ((computeCombination(numTrials, xVal)) * (Math.pow(pSuccess, xVal))
				* (Math.pow((1 - pSuccess), (numTrials - xVal))) * 1.0);
	}

	public double computepBinomialPdf(int numTrials, int xVal, double pSuccess) {

		double pBinomialPdf = 0.0;

		pBinomialPdf = (computeCombination(numTrials, xVal)) * (Math.pow(pSuccess, xVal))
				* (Math.pow((1 - pSuccess), (numTrials - xVal)));

		return pBinomialPdf;

	}

	public void computeBinomialCdf(int numTrials, int xVal, double pSuccess, int inputLBound, int inputHBound) {

		for (int i = inputLBound; i <= inputHBound; i++) {
			xVal = i;
			computepBinomialPdf(numTrials, xVal, pSuccess);
		}

	}

	public double computePGeoPdf(double pSuccess, int xVal) {
		return (pSuccess) * (Math.pow((1 - pSuccess), (xVal - 1)));
	}

	public double computePGeoCdf(double pSuccess, int xVal) {
		return (pSuccess) * (Math.pow((1 - pSuccess), (xVal - 1)));
	}

	public double computePGeoCdf(double pSuccess, int xVal, int inputLowBound, int inputHighBound) {
		double sum = 0.0;
		for (int i = inputLowBound; i <= inputHighBound; i++) {
			xVal = i;
			sum += computePGeoCdf(pSuccess, xVal);
		}
		return sum;
	}

	public double NormalPDF(double inputZ) {

		double constant = (1) / (Math.sqrt(2 * Math.PI));
		double exponent = (Math.pow(inputZ, 2.0)) / (-2);

		return (constant) * (Math.pow(Math.E, exponent));

	}

	public double computeFiniteZProbLeftRiemann(double inputZLow, double inputZHigh) {

		double sum = 0.0;

		// double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7));
		double increment = 1.0 / (Math.pow(10, 7));

		for (double i = inputZLow; i < inputZHigh; i += increment) {
			sum += ((NormalPDF(i)) * (increment));
		}

		return sum;
	}

	public double computeFiniteZProbRightRiemann(double inputZLow, double inputZHigh) {

		double sum = 0.0;

		// double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7));
		double increment = 1.0 / (Math.pow(10, 7));

		for (double i = (inputZLow + increment); i <= inputZHigh; i += increment) {
			sum += (NormalPDF(i) * increment);
		}

		return sum;

	}

	public double computeFiniteZProbAvgLeftRightRiemann(double inputZLow, double inputZHigh) {
		return ((computeFiniteZProbRightRiemann(inputZLow, inputZHigh))
				+ (computeFiniteZProbLeftRiemann(inputZLow, inputZHigh))) / 2;
	}

	public double computeFiniteZProbMidpointRiemann(double inputZLow, double inputZHigh) {

		double sum = 0.0;

		// double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7));
		double increment = 1.0 / (Math.pow(10, 7));

		for (double i = (inputZLow + (increment / 2)); i < inputZHigh; i += increment) {
			sum += (NormalPDF(i) * increment);
		}

		return sum;

	}

	public double computeFiniteZProbTrapezoidRiemann(double inputZLow, double inputZHigh) {

		double sum = 0.0;

		// double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7));
		double increment = 1.0 / (Math.pow(10, 7));

		for (double i = inputZLow; i < (inputZHigh - increment); i += increment) {
			sum += ((0.5) * (NormalPDF(i) + NormalPDF(i + increment)) * (increment));
		}

		return sum;

	}

	public String computeInverseNormalApprox(double input) { // works, but highly inefficient
		// with respect to probability to the left of input value

		double res = 0.0;

		if ((input == 0) || (input == 1)) {
			return "∞";
		} else if (((input > 0) && (input < 0.01)) || ((input > 0.99) && (input < 1))) {

			res = Math.tan((Math.PI / 0.1) * (input - 0.95));

			return res + "";

		} else if ((input >= 0.01) && (input <= 0.99)) { // good

			res = Math.tan((Math.PI / 1.34) * (input - 0.5));

			return res + "";

		} else if ((input < 0) || (input > 1)) { // good
			return "invalid input";
		}

		return "";

	}

	public int computeRandomWholeNumber(int low, int high) {
		return (int) (Math.random() * (high - low + 1) + low);
	}

	public double computeRandomDecimalNumber(double low, double high) {
		return Math.random() * (high - low + 1) + low;
	}

	public double computePermutations(int numberOfObservations, int numberChoose) {
		return (computeFactorial(numberOfObservations)) / (computeFactorial(numberOfObservations - numberChoose));
	}

	public double computeDiscreteExpectedValue(double[] inputDataArray, double[] probabilitiesArray) {

		double sum = 0.0;

		for (int i = 0; i < computeLength(inputDataArray); i++) {
			sum += ((atIndex(inputDataArray, i)) * (atIndex(probabilitiesArray, i)));
		}

		return sum;

	}

	public double computeDiscreteVariance(double[] inputDataArray, double[] probabilitiesArray) {

		double sum = 0.0;
		double meanOfX = computeDiscreteExpectedValue(inputDataArray, probabilitiesArray);

		for (int i = 0; i < computeLength(inputDataArray); i++) {

			sum += ((Math.pow((atIndex(inputDataArray, i) - meanOfX), 2)) * (atIndex(probabilitiesArray, i)));

		}

		return sum;

	}

	public double computeDiscreteStandardDeviation(double[] inputDataArray, double[] probabilitiesArray) {
		return Math.sqrt(computeDiscreteVariance(inputDataArray, probabilitiesArray));
	}

	public double computeRowSum(double[][] inputData, int row) {

		double sum = 0;

		for (int i = 0; i < inputData[0].length; i++) {
			sum += inputData[row][i];
		}

		return sum;

	}

	public double computeColumnSum(double[][] inputData, int col) {

		double sum = 0;

		for (int i = 0; i < inputData.length; i++) {
			sum += inputData[i][col];
		}

		return sum;

	}

	public double computeRowProduct(double[][] inputData, int row) {

		double sum = 0;

		for (int i = 0; i < inputData[0].length; i++) {
			sum *= inputData[row][i];
		}

		return sum;
	}

	public double computeColumnProduct(double[][] inputData, int col) {

		double sum = 0;

		for (int i = 0; i < inputData.length; i++) {
			sum *= inputData[i][col];
		}

		return sum;
	}

	public double[][] computeMatrixAddition(double[][] arr1, double[][] arr2) { // matrix1 & matrix2 must have same
																				// dimensions

		double[][] res = new double[arr1.length][arr1[0].length];

		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				res[i][j] = (arr1[i][j] + arr2[i][j]);
			}
		}

		return res;

	}

	public double[][] computeMatrixSubtraction(double[][] arr1, double[][] arr2) {

		double[][] res = new double[arr1.length][arr1[0].length];

		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				res[i][j] = (arr1[i][j] - arr2[i][j]);
			}
		}

		return res;

	}

	public double[][] computeMatrixMultiplicationByScalar(double[][] arr, double scalar) {

		double[][] res = new double[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				res[i][j] = (scalar) * (arr[i][j]);
			}
		}

		return res;

	}

	public double computeSe(double[] indVar, double[] depVar) {

		return Math.sqrt((computeSumOfResidualsSquared(indVar, depVar)) / (computeLength(depVar)));

	}

	public double computeSumOfResidualsSquared(double[] indVar, double[] depVar) {

		double sum = 0.0;

		double[] temp = computeResidualValues(indVar, depVar);

		for (int i = 0; i < temp.length; i++) {
			sum += (Math.pow(temp[i], 2));
		}

		return sum;

	}

	public double[] computeResidualValues(double[] indVar, double[] depVar) {

		double[] res = new double[computeLength(indVar)];

		for (int i = 0; i < res.length; i++) {
			res[i] = (atIndex(depVar, i)) - (computeLSRLOutput(indVar, depVar, atIndex(indVar, i)));
		}

		return res;

	}

	public double[] computeYPredictedValues(double[] indVar, double[] depVar) {

		double[] res = new double[computeLength(indVar)];

		for (int i = 0; i < res.length; i++) {
			res[i] = computeLSRLOutput(indVar, depVar, atIndex(indVar, i));
		}

		return res;

	}

	public double computeLSRLOutput(double[] indVar, double[] depVar, double input) {

		return ((computeA(indVar, depVar)) + (computeB(indVar, depVar) * input));

	}

	public String displayLSRLEquation(double[] indVar, double[] depVar) {

		String res = "";

		res = "Ypredicted = " + (computeA(indVar, depVar)) + " + " + (computeB(indVar, depVar)) + "x";

		if (computeB(indVar, depVar) < 0) {
			res = "Ypredicted = " + (computeA(indVar, depVar)) + " - " + (-1 * computeB(indVar, depVar)) + "x";
		} else if (computeB(indVar, depVar) >= 0) {
			res = "Ypredicted = " + (computeA(indVar, depVar)) + " + " + (computeB(indVar, depVar)) + "x";
		}

		return res;

	}

	public double computeA(double[] indVar, double[] depVar) {

		return (computeMean(depVar)) - ((computeB(indVar, depVar)) * (computeMean(indVar)));

	}

	public double computeB(double[] indVar, double[] depVar) {

		double r = computeR(indVar, depVar);

		double b = r * (computeStandardDeviation(depVar) / computeStandardDeviation(indVar));

		return b;

	}

	public double computeR(double[] indVar, double[] depVar) {

		double sumOfProducts = 0.0;

		double arr1Bar = computeMean(indVar);
		double arr1Sigma = computeStandardDeviation(indVar);

		double arr2Bar = computeMean(depVar);
		double arr2Sigma = computeStandardDeviation(depVar);

		for (int i = 0; i < indVar.length; i++) { // both arrays must have the same length
			sumOfProducts += ((atIndex(indVar, i) - arr1Bar) / (arr1Sigma))
					* ((atIndex(depVar, i) - arr2Bar) / (arr2Sigma));
		}

		return sumOfProducts / (indVar.length - 1);

	}

	public double computeRSquaredValue(double[] indVar, double[] depVar) {

		return Math.pow((computeR(indVar, depVar)), 2.0);

	}

	public double computeZStar(double inputConfidenceLevel) {

		double invNormInput = inputConfidenceLevel + ((1 - inputConfidenceLevel) / (2));
		return Double.parseDouble(computeInverseNormalApprox(invNormInput));

	}

	public String computeZConfidenceInterval(double mu, double sigma, double sampleSize, double confidenceLevel) {

		String res = "";

		double lowBound = mu - (computeZStar(confidenceLevel) * ((sigma) / (Math.sqrt(sampleSize))));

		double highBound = mu + (computeZStar(confidenceLevel) * ((sigma) / (Math.sqrt(sampleSize))));

		res = "(" + lowBound + ", " + highBound + ")";

		return res;

	}

	public String computeZSigTestHaGreaterThanH0(double mu, double sigma, double sampleMean, int sampleSize,
			double alpha, String alternateHypothesis) {

		double zCritical = (sampleMean - mu) / (sigma / (Math.sqrt(sampleSize)));

		double pValue = computeFiniteZProbMidpointRiemann(zCritical, 1000.0);

		if (pValue < alpha) {
			return "There is statistically significant evidence that Ha > H0... reject H0";
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that Ha > H0... fail to reject H0";
		} else if (pValue == alpha) {
			return "";
		}

		return "";

	}

	public String computeZSigTestHaLessThanH0(double mu, double sigma, double sampleMean, int sampleSize, double alpha,
			String alternateHypothesis) {

		double zCritical = (sampleMean - mu) / (sigma / (Math.sqrt(sampleSize)));

		double pValue = computeFiniteZProbMidpointRiemann(-1000.0, zCritical);

		if (pValue < alpha) {
			return "There is statistically significant evidence that Ha < H0... reject H0";
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that Ha < H0... fail to reject H0";
		} else if (pValue == alpha) {
			return "";
		}

		return "";

	}

	public String computeZSigTestHaNotEqualToH0(double mu, double sigma, double sampleMean, int sampleSize,
			double alpha, String alternateHypothesis) {

		double zCritical = (sampleMean - mu) / (sigma / (Math.sqrt(sampleSize)));

		double pValue = (computeFiniteZProbMidpointRiemann((Math.abs(zCritical)), 1000.0)) * 2.0;

		if (pValue < alpha) {
			return "There is statistically significant evidence that Ha ≠ H0... reject H0";
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that Ha ≠ H0... fail to reject H0";
		} else if (pValue == alpha) {
			return "";
		}

		return "";

	}

	public String computeOnePropZConfInt(double successes, double sampleSize, double confidenceLevel) {

		double pHat = 1.0 * (successes / sampleSize);
		double qHat = pHat;
		double zStar = computeZStar(confidenceLevel);
		double standardError = Math.sqrt((pHat * qHat) / (sampleSize));
		double low = pHat - (zStar * standardError);
		double high = pHat + (zStar * standardError);

		return "(" + low + ", " + high + ")";

	}

	public String computeOnePropZSigTest(double successes, double pNought, double sampleSize, double alpha,
			String alternativeHypothesis) {

		if (alternativeHypothesis.equals("p-nought > value")) {
			return computeOnePropZSigTestP0GreaterThanValue(successes, pNought, sampleSize, alpha,
					alternativeHypothesis);
		} else if (alternativeHypothesis.equals("p-nought < value")) {
			return computeOnePropZSigTestP0LessThanValue(successes, pNought, sampleSize, alpha, alternativeHypothesis);
		} else if (alternativeHypothesis.equals("p-nought not equal to value")) {
			return computeOnePropZSigTestP0NotEqualToValue(successes, pNought, sampleSize, alpha,
					alternativeHypothesis);
		} else {
			return "ERROR in Ha statement - please check typing/syntax";
		}

	}

	public String computeOnePropZSigTestP0LessThanValue(double successes, double pNought, double sampleSize,
			double alpha, String alternativeHypothesis) {

		double pHat = 1.0 * (successes / sampleSize);
		double qNought = 1 - pNought;

		double zCritical = (pHat - pNought) / (Math.sqrt((pNought * qNought) / (sampleSize)));

		double pValue = computeFiniteZProbMidpointRiemann(-1000.0, zCritical);

		if (pValue < alpha) {
			return "There is statistically signficant evidence that the true P0 < given P0 - reject H0";
		} else if (pValue > alpha) {
			return "There is no statistically signficant evidence that the true P0 < given P0 - fail to reject H0";
		} else { // pValue equals alpha
			return "";
		}

	}

	public String computeOnePropZSigTestP0GreaterThanValue(double successes, double pNought, double sampleSize,
			double alpha, String alternativeHypothesis) {

		double pHat = 1.0 * (successes / sampleSize);
		double qNought = 1 - pNought;

		double zCritical = (pHat - pNought) / (Math.sqrt((pNought * qNought) / (sampleSize)));

		double pValue = computeFiniteZProbMidpointRiemann(zCritical, 1000.0);

		if (pValue < alpha) {
			return "There is statistically signficant evidence that the true P0 > given P0 - reject H0";
		} else if (pValue > alpha) {
			return "There is no statistically signficant evidence that the true P0 > given P0 - fail to reject H0";
		} else { // pValue equals alpha
			return "";
		}

	}

	public String computeOnePropZSigTestP0NotEqualToValue(double successes, double pNought, double sampleSize,
			double alpha, String alternativeHypothesis) {

		double pHat = 1.0 * (successes / sampleSize);
		double qNought = 1 - pNought;

		double zCritical = (pHat - pNought) / (Math.sqrt((pNought * qNought) / (sampleSize)));

		double pValue = computeFiniteZProbMidpointRiemann((Math.abs(zCritical)), 1000.0) * 2.0;

		if (pValue < alpha) {
			return "There is statistically signficant evidence that the true P0 ≠ the given P0 - reject H0";
		} else if (pValue > alpha) {
			return "There is no statistically signficant evidence that the true P0 ≠ the given P0 - fail to reject H0";
		} else { // pValue equals alpha
			return "";
		}

	}

	public double computeSampleMeansMu(double mu, double sigma, int sampleSize, double meanForComparison,
			String statement) {
		return mu;
	}

	public double computeSampleMeansStandardDeviation(double mu, double sigma, int sampleSize, double meanForComparison,
			String statement) {
		return ((sigma) / (Math.sqrt(sampleSize)));
	}

	public double computeSampleMeansProbabilityLessThan(double mu, double sigma, int sampleSize,
			double meanForComparison, String statement) {
		double z = (meanForComparison - mu)
				/ (computeSampleMeansStandardDeviation(mu, sigma, sampleSize, meanForComparison, statement));
		return computeFiniteZProbMidpointRiemann(-1000.0, z);
	}

	public double computeSampleMeansProbabilityGreaterThan(double mu, double sigma, int sampleSize,
			double meanForComparison, String statement) {
		double z = (meanForComparison - mu)
				/ (computeSampleMeansStandardDeviation(mu, sigma, sampleSize, meanForComparison, statement));
		return computeFiniteZProbMidpointRiemann(z, 1000.0);
	}

	public double computeSampleProportionsMu(double pHat, int sampleSize, double proportionForComparison,
			String statement) {
		return pHat;
	}

	public double computeSampleProportionsStandardDeviation(double pHat, int sampleSize, double proportionForComparison,
			String statement) {
		double numerator = (pHat) * (1 - pHat);
		return Math.sqrt(numerator / (sampleSize));
	}

	public double computeSampleProportionsProbabilityLessThanValue(double pHat, int sampleSize,
			double proportionForComparison, String statement) {

		double z = (proportionForComparison - pHat)
				/ (computeSampleProportionsStandardDeviation(pHat, sampleSize, proportionForComparison, statement));
		return computeFiniteZProbMidpointRiemann(-1000, z);

	}

	public double computeSampleProportionsProbabilityGreaterThanValue(double pHat, int sampleSize,
			double proportionForComparison, String statement) {

		double z = (proportionForComparison - pHat)
				/ (computeSampleProportionsStandardDeviation(pHat, sampleSize, proportionForComparison, statement));
		return computeFiniteZProbMidpointRiemann(z, 1000);

	}

	public double computeContinuousRandomVar1LessThanVar2(double mu1, double standardDeviation1, double mu2,
			double standardDeviation2, double valueForComparison) {
		double newMu = mu1 - mu2;
		double newVariance = (Math.pow(standardDeviation1, 2) + Math.pow(standardDeviation2, 2));
		double newSD = Math.sqrt(newVariance);

		double z = (0 - newMu) / (newSD);
		return computeFiniteZProbMidpointRiemann(-1000, z);
	}

	public double var1GreaterThanVar2(double mu1, double standardDeviation1, double mu2, double standardDeviation2,
			double valueForComparisona) {
		double newMu = mu1 - mu2;
		double newVariance = (Math.pow(standardDeviation1, 2) + Math.pow(standardDeviation2, 2));
		double newSD = Math.sqrt(newVariance);

		double z = (0 - newMu) / (newSD);
		return computeFiniteZProbMidpointRiemann(z, 1000);
	}

	public double computeContinuousRandomSumOfBothVarsGreaterThanValue(double mu1, double standardDeviation1,
			double mu2, double standardDeviation2, double valueForComparison) {
		double newMu = mu1 + mu2;
		double newVariance = (Math.pow(standardDeviation1, 2) + Math.pow(standardDeviation2, 2));
		double newSD = Math.sqrt(newVariance);

		double z = (valueForComparison - newMu) / (newSD);
		return computeFiniteZProbMidpointRiemann(z, 1000);
	}

	public double computeContinuousRandomSumOfBothVarsLessThanValue(double mu1, double standardDeviation1, double mu2,
			double standardDeviation2, double valueForComparison) {
		double newMu = mu1 + mu2;
		double newVariance = (Math.pow(standardDeviation1, 2) + Math.pow(standardDeviation2, 2));
		double newSD = Math.sqrt(newVariance);

		double z = (valueForComparison - newMu) / (newSD);
		return computeFiniteZProbMidpointRiemann(-1000, z);
	} 

}