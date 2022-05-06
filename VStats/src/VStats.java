import java.util.ArrayList;

public class VStats {

	public VStats(double[] in) {

	}

	public double computeMean(double[] data) {
		double sum = 0;
		double count = 0.0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
			count++;
		}

		return ((sum * 1.0) / count);

	}

	public double computeMAD(double[] data) {
		double dataMean = computeMean(data); 
		ArrayList<Double> res = new ArrayList<Double>(); 
		for (int i = 0; i < data.length; i++) {
			res.add(Math.abs(data[i] - dataMean)); 
		}
		double sum = 0.0; 
		for (int i = 0; i < res.size(); i++) {
			sum += res.get(i); 
		}
		return sum / (data.length * 1.0); 
	}

	public void sort(double[] data) {

		for (int j = 1; j < data.length; j++) {
			double temp = data[j];
			int possibleIndex = j;
			while (possibleIndex > 0 && temp < data[possibleIndex - 1]) {
				data[possibleIndex] = data[possibleIndex - 1];
				possibleIndex--;
			}
			data[possibleIndex] = temp;
		}

	}

	public double computeMedian(double[] data) { // this code computes median using the insertion sort algorithm

		int middleIndex = 0;
		sort(data);

		// sorted.
		middleIndex = (data.length / 2);
		double temp = 0.0;
		if (data.length % 2 != 0) { // odd
			temp = data[middleIndex]; // median is good for odd number of values
			return temp;
		} else {
			temp = (data[middleIndex - 1] + data[middleIndex]) / 2.0;
			return temp;
		}

	}

	public double computeMinimum(double[] data) {
		double minTemp = data[0];

		for (int i = 0; i < data.length; i++) {
			if (data[i] < minTemp) {
				minTemp = data[i];
			}
		}

		return (minTemp * 1.0);

	}

	public double computeMaximum(double[] data) {
		double maxTemp = data[0];

		for (int i = 0; i < data.length; i++) {
			if (data[i] > maxTemp) {
				maxTemp = data[i];
			}
		}

		return (maxTemp * 1.0);

	}

	public double computeRange(double[] data) {
		return (computeMaximum(data) - computeMinimum(data)) * 1.0;
	}

	public double computeVariance(double[] data) {
		double sumDiff = 0.0;

		for (int i = 0; i < data.length; i++) {
			sumDiff += ((data[i] - computeMean(data)) * (data[i] - computeMean(data)));
		}

		return sumDiff / (data.length - 1);

	}

	public double computeStandardDeviation(double[] data) {
		// assert variance != 0;
		return Math.sqrt(computeVariance(data));
	}

	public double computeQuartile1(double[] data) {
		int middleIndex = data.length / 2;
		int count = -1;
		double quartile1 = 0.0;
		sort(data);

		for (int i = 0; i <= middleIndex - 1; i++) {
			count++;
		}

		if ((data.length % 2 == 0) && (count % 2 != 0)) {
			quartile1 = (data[count / 2] + data[(count / 2) + 1]) / 2.0;
		}

		if ((data.length % 2 == 0) && (count % 2 == 0)) {
			quartile1 = data[count / 2];
		}

		if ((data.length % 2 != 0) && (count % 2 == 0)) {
			quartile1 = data[count / 2];
		}

		if ((data.length % 2 != 0) && (count % 2 != 0)) {
			quartile1 = (data[count / 2] + data[(count / 2) + 1]) / 2.0;
		}

		return quartile1;

	}

	public double computeQuartile3(double[] data) {
		int middleIndex = data.length / 2;
		int arrayEvenCounter = 0;
		int arrayOddCounter = 0;
		double quartile3 = 0.0;
		sort(data);

		for (int i = middleIndex + 1; i < data.length; i++) {
			arrayEvenCounter++;
		}

		if ((data.length % 2 != 0) && (arrayEvenCounter % 2 != 0)) {
			quartile3 = data[middleIndex + ((arrayEvenCounter / 2) + 1)]; // this if statement block is done
		}

		if ((data.length % 2 != 0) && (arrayEvenCounter % 2 == 0)) {
			quartile3 = (data[middleIndex + (arrayEvenCounter / 2)] + data[middleIndex + (arrayEvenCounter / 2) + 1])
					/ 2.0;
		}

		for (int i = middleIndex; i < data.length; i++) {
			arrayOddCounter++;
		}

		if ((data.length % 2 == 0) && (arrayOddCounter % 2 != 0)) {
			quartile3 = data[middleIndex + (arrayOddCounter / 2)];
		}

		if ((data.length % 2 == 0) && (arrayOddCounter % 2 == 0)) {
			// quartile3 = (inputData[middleIndex + ((arrayOddCounter / 2) - 1)] +
			// inputData[middleIndex + (arrayOddCounter / 2)] ) / 2;
			quartile3 = (data[middleIndex + (arrayOddCounter / 2) - 1] + data[middleIndex + (arrayOddCounter / 2)])
					/ 2.0;
		}

		return quartile3;

	}

	public double computeMode(double[] data) {

		int[] modeFinder = new int[data.length];
		double mode = 0.0;

		for (int i = 0; i < modeFinder.length; i++) {
			modeFinder[i] = 1;
		}

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i] == data[j]) {
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

		mode = data[tempIndex];

		return mode;

	}

	public double computeNumValues(double[] data) {
		return (data.length * 1.0);
	}

	public double computeSumValues(double[] data) {
		double sumValues = 0.0;
		for (int i = 0; i < data.length; i++) {
			sumValues += data[i];
		}

		return sumValues;

	}

	public int computeLength(double[] data) {
		return data.length;
	}

	public double atIndex(double[] data, int i) {
		return data[i];
	}

	public double computeIQR(double[] data) {

		return computeQuartile3(data) - computeQuartile1(data);

	}

	public ArrayList<Double> computeOutliers(double[] data) {

		double lowBound = computeQuartile1(data) - (1.5 * computeIQR(data));
		double highBound = computeQuartile3(data) + (1.5 * computeIQR(data));
		ArrayList<Double> res = new ArrayList<Double>();

		for (int i = 0; i < data.length; i++) {
			if ((data[i] < lowBound) || (highBound > highBound))
				res.add(data[i]);
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

	public double computeDiscreteExpectedValue(double[] dataArray, double[] probabilitiesArray) {

		double sum = 0.0;

		for (int i = 0; i < computeLength(dataArray); i++) {
			sum += ((atIndex(dataArray, i)) * (atIndex(probabilitiesArray, i)));
		}

		return sum;

	}

	public double computeDiscreteVariance(double[] dataArray, double[] probabilitiesArray) {

		double sum = 0.0;
		double meanOfX = computeDiscreteExpectedValue(dataArray, probabilitiesArray);

		for (int i = 0; i < computeLength(dataArray); i++) {

			sum += ((Math.pow((atIndex(dataArray, i) - meanOfX), 2)) * (atIndex(probabilitiesArray, i)));

		}

		return sum;

	}

	public double computeDiscreteStandardDeviation(double[] dataArray, double[] probabilitiesArray) {
		return Math.sqrt(computeDiscreteVariance(dataArray, probabilitiesArray));
	}

	public double computeRowSum(double[][] data, int row) {

		double sum = 0;

		for (int i = 0; i < data[0].length; i++) {
			sum += data[row][i];
		}

		return sum;

	}

	public double computeColumnSum(double[][] data, int col) {

		double sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i][col];
		}

		return sum;

	}

	public double computeRowProduct(double[][] data, int row) {

		double sum = 0;

		for (int i = 0; i < data[0].length; i++) {
			sum *= data[row][i];
		}

		return sum;
	}

	public double computeColumnProduct(double[][] data, int col) {

		double sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum *= data[i][col];
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