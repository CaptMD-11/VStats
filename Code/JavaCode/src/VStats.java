/*
 * This is VStats, a Java library made by Vignesh Nydhruva, that computes various statistics. 
    Copyright (C) 2022  Vignesh Nydhruva

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public final class VStats {

	private VStats() {

	}

	/**
	 * Returns the mean (average) of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
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
	 * Returns the mean absolute deviation (MAD) of a <code>double</code> array, in
	 * a <code>double</code> format.
	 * <p>
	 * The MAD of an array is the average distance (absolute value) from each value
	 * in the array to the mean.
	 * 
	 * @param inputData a <code>double</code> array.
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

	private static double[] sortHELPER(double[] inputData) { // this method is only a helper method

		double[] res = new double[inputData.length];

		for (int j = 1; j < inputData.length; j++) {
			double temp = inputData[j];
			int possibleIndex = j;
			while (possibleIndex > 0 && temp < inputData[possibleIndex - 1]) {
				inputData[possibleIndex] = inputData[possibleIndex - 1];
				possibleIndex--;
			}
			inputData[possibleIndex] = temp;
		}

		res = inputData;
		return res;

	}

	/**
	 * Returns the median (second quartile) of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the median of <strong>inputData</strong>.
	 */
	public static double computeMedian(double[] inputData) { // this code computes median using the insertion sortHELPER
																// algorithm

		int middleIndex = 0;
		inputData = VStats.sortHELPER(inputData);

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

	/**
	 * Returns the minimum value of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the minimum value of <strong>inputData</strong>.
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
	 * Returns the maximum value of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the maximum value of <strong>inputData</strong>.
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
	 * Returns the range (maximum value - minimum value) of a <code>double</code>
	 * array, in a <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the range of <strong>inputData</strong>.
	 */
	public static double computeRange(double[] inputData) {
		return (computeMaximum(inputData) - computeMinimum(inputData)) * 1.0;
	}

	/**
	 * Returns the variance of a <code>double</code> array, in a <code>double</code>
	 * format.
	 * 
	 * @param inputData a <code>double</code> array.
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
	 * Returns the standard deviation of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the standard deviation of <strong>inputData</strong>.
	 */
	public static double computeStandardDeviation(double[] inputData) {
		// assert variance != 0;
		return Math.sqrt(computeVariance(inputData));
	}

	/**
	 * Returns the first quartile of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the first quartile of <strong>inputData</strong>.
	 */
	public static double computeQuartile1(double[] inputData) {
		int middleIndex = inputData.length / 2;
		int count = -1;
		double quartile1 = 0.0;
		inputData = VStats.sortHELPER(inputData);

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
	 * Returns the third quartile of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the third quartile of <strong>inputData</strong>.
	 */
	public static double computeQuartile3(double[] inputData) {
		int middleIndex = inputData.length / 2;
		int arrayEvenCounter = 0;
		int arrayOddCounter = 0;
		double quartile3 = 0.0;
		inputData = VStats.sortHELPER(inputData);

		for (int i = middleIndex + 1; i < inputData.length; i++) {
			arrayEvenCounter++;
		}

		if ((inputData.length % 2 != 0) && (arrayEvenCounter % 2 != 0)) {
			quartile3 = inputData[middleIndex + ((arrayEvenCounter / 2) + 1)]; // this if statement block is done
		}

		if ((inputData.length % 2 != 0) && (arrayEvenCounter % 2 == 0)) {
			quartile3 = (inputData[middleIndex + (arrayEvenCounter / 2)]
					+ inputData[middleIndex + (arrayEvenCounter / 2) + 1])
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
			quartile3 = (inputData[middleIndex + (arrayOddCounter / 2) - 1]
					+ inputData[middleIndex + (arrayOddCounter / 2)])
					/ 2.0;
		}

		return quartile3;

	}

	/**
	 * Returns the mode (most occurring value) of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the mode of <strong>inputData</strong>.
	 */
	public static double computeMode(double[] inputData) {

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

	/**
	 * Returns the sum of the values of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the sum of the values in <strong>inputData</strong>.
	 */
	public static double computeSumValues(double[] inputData) {
		double sumValues = 0.0;
		for (int i = 0; i < inputData.length; i++) {
			sumValues += inputData[i];
		}

		return sumValues;

	}

	/**
	 * Returns the interquartile range (IQR) of a <code>double</code> array, in a
	 * <code>double</code> format.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return the IQR of <strong>inputData</strong>.
	 */
	public static double computeIQR(double[] inputData) {

		return computeQuartile3(inputData) - computeQuartile1(inputData);

	}

	/**
	 * Returns a <code>double</code> <code>ArrayList</code> containing any possible
	 * outliers in a <code>double</code> array.
	 * 
	 * @param inputData a <code>double</code> array.
	 * @return an <code>ArrayList</code> which contains any possible outliers in
	 *         <strong>inputData</strong>.
	 */
	public static ArrayList<Double> computeOutliers(double[] inputData) {

		double lowBound = computeQuartile1(inputData) - (1.5 * computeIQR(inputData));
		double highBound = computeQuartile3(inputData) + (1.5 * computeIQR(inputData));
		ArrayList<Double> res = new ArrayList<Double>();

		for (int i = 0; i < inputData.length; i++) {
			if ((inputData[i] < lowBound) || (inputData[i] > highBound))
				res.add(inputData[i]);
		}

		return res;

	}

	/**
	 * Returns the factorial (in <code>int</code> format) of an <code>int</code>.
	 * 
	 * @param inputVal an <code>int</code>.
	 * @return the factorial of <strong>inputVal</strong>.
	 */
	public static int computeFactorial(int inputVal) {

		int resFactorial = 1;

		for (int i = inputVal; i >= 1; i--) {
			resFactorial = resFactorial * i;
		}
		return resFactorial;
	}

	/**
	 * Returns the number of combinations (in <code>int</code> format) possible when
	 * given the n-value and r-value (with respect to the format: nCr).
	 * 
	 * @param n the n-value in the format "nCr."
	 * @param r the r-value in the format "nCr."
	 * @return the number of combinations possible, with respect to
	 *         <strong>n</strong> and <strong>r</strong>.
	 */
	public static int computeCombinations(int n, int r) {

		return (int) ((computeFactorial(n)) / ((computeFactorial(r)) * (computeFactorial(n - r)))
				* 1.0);
	}

	/**
	 * Returns the binomial probability of a single x-value, in a
	 * <code>double</code> format.
	 * 
	 * @param numTrials the number of trials.
	 * @param xVal      the x-value of interest.
	 * @param pSuccess  the probability of success.
	 * @return the probability of <strong>xVal</strong> occurring.
	 */
	public static double computeBinomialPdfProb(int numTrials, int xVal, double pSuccess) {
		return ((computeCombinations(numTrials, xVal)) * (Math.pow(pSuccess, xVal))
				* (Math.pow((1 - pSuccess), (numTrials - xVal))) * 1.0);
	}

	/**
	 * Returns the binomial probability of an interval of x-values, in a
	 * <code>double</code> format.
	 * 
	 * @param numTrials   the number of trials.
	 * @param inputLBound the low bound x-value.
	 * @param inputHBound the high bound x-value.
	 * @param pSuccess    the probability of success.
	 * @return the probability of obtaining an x-value between
	 *         <strong>inputLBound</strong> and <strong>inputHBound</strong>,
	 *         inclusive.
	 */
	public static double computeBinomialCdfProb(int numTrials, int inputLBound, int inputHBound, double pSuccess) {
		double sum = 0.0;
		for (int i = inputLBound; i <= inputHBound; i++) {
			sum += computeBinomialPdfProb(numTrials, i, pSuccess);
		}
		return sum;
	}

	/**
	 * Returns the geometric probability of a single x-value, in a
	 * <code>double</code> format.
	 * 
	 * @param xVal     the x-value of interest.
	 * @param pSuccess the probability of success.
	 * @return the probability of the event occurring on the <strong>xVal</strong>th
	 *         try.
	 */
	public static double computeGeometricPdfProb(int xVal, double pSuccess) {
		return (pSuccess) * (Math.pow((1 - pSuccess), (xVal - 1)));
	}

	/**
	 * Returns the geometric probability of an interval of x-values, in a
	 * <code>double</code> format.
	 * 
	 * @param pSuccess       the probability of success.
	 * @param inputLowBound  the low bound x-value.
	 * @param inputHighBound the high bound x-value.
	 * @return the probability of the event occurring on between the
	 *         <strong>inputLowBound</strong>th and
	 *         <strong>inputHighBound</strong>th try, inclusive.
	 */
	public static double computeGeometricCdfProb(double pSuccess, int inputLowBound, int inputHighBound) {
		double sum = 0.0;
		for (int i = inputLowBound; i <= inputHighBound; i++) {
			sum += computeGeometricPdfProb(i, pSuccess);
		}
		return sum;
	}

	/**
	 * Returns the output (in a <code>double</code> format) of the probability
	 * density function (for normal distributions).
	 * <p>
	 * This method assumes that µ=0 and σ=1.
	 * 
	 * @param inputZ the input value.
	 * @return the normal probability density function's output when
	 *         <strong>inputZ</strong> is the input.
	 */
	public static double computeNormalPDF(double inputZ) {

		double constant = (1) / (Math.sqrt(2 * Math.PI));
		double exponent = (Math.pow(inputZ, 2.0)) / (-2);

		return (constant) * (Math.pow(Math.E, exponent));

	}

	/**
	 * Returns the (highly accurate) probability approximation between two z-scores
	 * for the probability density function (for normal distributions) using a
	 * left-endpoint Riemann sum.
	 * <p>
	 * This method assumes that µ=0 and σ=1.
	 * <p>
	 * Enter large absolute value z-scores for the bounds, for improper integral
	 * approximation.
	 * 
	 * @param inputZLow  the low bound z-score.
	 * @param inputZHigh the high bound z-score.
	 * @return the probability between <strong>inputZLow</strong> and
	 *         <strong>inputZHigh</strong>.
	 */
	public static double computeZProbLeftRiemann(double inputZLow, double inputZHigh) {

		double sum = 0.0;

		// double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7));
		double increment = 1.0 / (Math.pow(10, 7));

		for (double i = inputZLow; i < inputZHigh; i += increment) {
			sum += ((computeNormalPDF(i)) * (increment));
		}

		return sum;
	}

	/**
	 * Returns the (highly accurate) probability approximation between two z-scores
	 * for the probability density function (for normal distributions) using a
	 * right-endpoint Riemann sum.
	 * <p>
	 * This method assumes that µ=0 and σ=1.
	 * <p>
	 * Enter large absolute value z-scores for the bounds, for improper integral
	 * approximation.
	 * 
	 * @param inputZLow  the low bound z-score.
	 * @param inputZHigh the high bound z-score.
	 * @return the probability between <strong>inputZLow</strong> and
	 *         <strong>inputZHigh</strong>.
	 */
	public static double computeZProbRightRiemann(double inputZLow, double inputZHigh) {

		double sum = 0.0;

		// double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7));
		double increment = 1.0 / (Math.pow(10, 7));

		for (double i = (inputZLow + increment); i <= inputZHigh; i += increment) {
			sum += (computeNormalPDF(i) * increment);
		}

		return sum;

	}

	/**
	 * Returns the (highly accurate) probability approximation between two z-scores
	 * for the probability density function (for normal distributions) using the
	 * average of a left-endpoint & right-endpoint Riemann sum.
	 * <p>
	 * This method assumes that µ=0 and σ=1.
	 * <p>
	 * Enter large absolute value z-scores for the bounds, for improper integral
	 * approximation.
	 * 
	 * @param inputZLow  the low bound z-score.
	 * @param inputZHigh the high bound z-score.
	 * @return the probability between <strong>inputZLow</strong> and
	 *         <strong>inputZHigh</strong>.
	 */
	public static double computeZProbAvgLeftRightRiemann(double inputZLow, double inputZHigh) {
		return ((computeZProbRightRiemann(inputZLow, inputZHigh))
				+ (computeZProbLeftRiemann(inputZLow, inputZHigh))) / 2;
	}

	/**
	 * Returns the probability approximation between two z-scores for the
	 * probability density function (for normal distributions) using a midpoint
	 * Riemann sum (accurate to about 6 decimal places).
	 * <p>
	 * This method assumes that µ=0 and σ=1.
	 * <p>
	 * Enter large absolute value z-scores for the bounds, for improper integral
	 * approximation.
	 * 
	 * @param inputZLow  the low bound z-score.
	 * @param inputZHigh the high bound z-score.
	 * @return the probability between <strong>inputZLow</strong> and
	 *         <strong>inputZHigh</strong>.
	 */
	public static double computeZProbMidpointRiemann(double inputZLow, double inputZHigh) {

		double sum = 0.0;

		double increment = (inputZHigh - inputZLow) / (Math.pow(10, 7));
		// double increment = 1.0 / (Math.pow(10, 6));

		for (double i = (inputZLow + (increment / 2)); i < inputZHigh; i += increment) {
			sum += (computeNormalPDF(i) * increment);
		}

		return sum;

	}

	/**
	 * Returns the probability approximation between two z-scores for the
	 * probability density function (for normal distributions) using a trapezoidal
	 * Riemann sum.
	 * <p>
	 * This method assumes that µ=0 and σ=1.
	 * <p>
	 * Enter large absolute value z-scores for the bounds, for improper integral
	 * approximation.
	 * 
	 * @param inputZLow  the low bound z-score.
	 * @param inputZHigh the high bound z-score.
	 * @return the probability between <strong>inputZLow</strong> and
	 *         <strong>inputZHigh</strong>.
	 */
	public static double computeZProbTrapezoidRiemann(double inputZLow, double inputZHigh) {

		double sum = 0.0;

		// double increment = (inputZHigh - inputZLow)/(Math.pow(10, 7));
		double increment = 1.0 / (Math.pow(10, 7));

		for (double i = inputZLow; i < (inputZHigh - increment); i += increment) {
			sum += ((0.5) * (computeNormalPDF(i) + computeNormalPDF(i + increment)) * (increment));
		}

		return sum;

	}

	/**
	 * Returns the corresponding z-score with respect to the left area under the
	 * probability density function (for normal distributions).
	 * <p>
	 * This method assumes that µ=0 and σ=1.
	 * <p>
	 * Method is only an approximation, and does not return an exact value.
	 * 
	 * @param input the area under the probability density function (to the left
	 *              of the desired z-score).
	 * @return the corresponding z-score to the <strong>input</strong> area.
	 */
	public static double computeInverseNormalApprox(double input) { // works, but highly inefficient
		// with respect to probability to the left of input value

		double res = 0.0;

		if ((input == 0) || (input == 1)) {
			return (Double) null;
		} else if (((input > 0) && (input < 0.01)) || ((input > 0.99) && (input < 1))) {

			res = Math.tan((Math.PI / 0.1) * (input - 0.95));

			return res;

		} else if ((input >= 0.01) && (input <= 0.99)) { // good

			res = Math.tan((Math.PI / 1.34) * (input - 0.5));

			return res;

		} else if ((input <= 0) || (input >= 1)) { // good
			return (Double) null;
		}

		return (Double) null;

	}

	/**
	 * Returns an <code>int</code> which represents the number of possible
	 * permutations, when the inputs are the n-value and the r-value.
	 * 
	 * @param n the n-value in the form "nPr."
	 * @param r the r-value in the form "nPr."
	 * @return the number of permutations possible, with respect to
	 *         <strong>n</strong> and <strong>r</strong>.
	 */
	public static int computePermutations(int n, int r) {
		return (computeFactorial(n)) / (computeFactorial(n - r));
	}

	/**
	 * Returns the expected value of a discrete random variable, in a
	 * <code>double</code> format.
	 * 
	 * @param inputDataArray     an array containing the possible outcomes of the
	 *                           random variable.
	 * @param probabilitiesArray an array containing the probabilities of the
	 *                           respective possible outcomes of the random
	 *                           variable.
	 * @return the expected value (µX) of the random variable.
	 */
	public static double computeDiscreteExpectedValue(double[] inputDataArray, double[] probabilitiesArray) {

		double sum = 0.0;

		for (int i = 0; i < inputDataArray.length; i++) {
			sum += (inputDataArray[i] * probabilitiesArray[i]);
		}

		return sum;

	}

	/**
	 * Returns the variance of a discrete random variable, in a <code>double</code>
	 * format.
	 * 
	 * @param inputDataArray     an array containing the possible outcomes of the
	 *                           random variable.
	 * @param probabilitiesArray an array containing the probabilities of the
	 *                           respective possible outcomes of the random
	 *                           variable.
	 * @return the variance (the square of σX) of the random variable.
	 */
	public static double computeDiscreteVariance(double[] inputDataArray, double[] probabilitiesArray) {

		double sum = 0.0;
		double meanOfX = computeDiscreteExpectedValue(inputDataArray, probabilitiesArray);

		for (int i = 0; i < inputDataArray.length; i++) {
			sum += (Math.pow(inputDataArray[i] - meanOfX, 2) * probabilitiesArray[i]);
		}

		return sum;

	}

	/**
	 * Returns the standard deviation of a discrete random variable, in a
	 * <code>double</code> format.
	 * 
	 * @param inputDataArray     an array containing the possible outcomes of the
	 *                           random variable.
	 * @param probabilitiesArray an array containing the probabilities of the
	 *                           respective possible outcomes of the random
	 *                           variable.
	 * @return the standard deviation (σX) of the random variable.
	 */
	public static double computeDiscreteStandardDeviation(double[] inputDataArray, double[] probabilitiesArray) {
		return Math.sqrt(computeDiscreteVariance(inputDataArray, probabilitiesArray));
	}

	/**
	 * Returns the sum of the elements of a row in a 2D array (0-based index), as a
	 * <code>double</code> format.
	 * 
	 * @param inputData a 2D array.
	 * @param row       the row of interest.
	 * @return the sum of the elements in <strong>row</strong>.
	 */
	public static double computeRowSum(double[][] inputData, int row) {

		double sum = 0;

		for (int i = 0; i < inputData[0].length; i++) {
			sum += inputData[row][i];
		}

		return sum;

	}

	/**
	 * Returns the sum of the elements of a column in a 2D array (0-based index), as
	 * a <code>double</code> format.
	 * 
	 * @param inputData a 2D array.
	 * @param col       the column of interest.
	 * @return the sum of the elements in <strong>col</strong>.
	 */
	public static double computeColumnSum(double[][] inputData, int col) {

		double sum = 0;

		for (int i = 0; i < inputData.length; i++) {
			sum += inputData[i][col];
		}

		return sum;

	}

	/**
	 * Returns the product of the elements of a row in a 2D array (0-based index),
	 * as a <code>double</code> format.
	 * 
	 * @param inputData a 2D array.
	 * @param row       the row of interest.
	 * @return the product of the elements in <strong>row</strong>.
	 */
	public static double computeRowProduct(double[][] inputData, int row) {

		double product = 1;

		for (int i = 0; i < inputData[0].length; i++) {
			product *= inputData[row][i];
		}

		return product;
	}

	/**
	 * Returns the product of the elements of a column in a 2D array (0-based
	 * index), as a <code>double</code> format.
	 * 
	 * @param inputData a 2D array.
	 * @param col       the column of interest.
	 * @return the product of the elements in <strong>col</strong>.
	 */
	public static double computeColumnProduct(double[][] inputData, int col) {

		double product = 1;

		for (int i = 0; i < inputData.length; i++) {
			product *= inputData[i][col];
		}

		return product;
	}

	/**
	 * Returns a <code>double</code> 2D array containing the sum of the 2 parameter
	 * matrices (2D arrays).
	 * <p>
	 * The matrices must have the same dimensions.
	 * 
	 * @param arr1 a 2D array.
	 * @param arr2 another 2D array.
	 * @return the sum of <strong>arr1</strong> and <strong>arr2</strong>, in a 2D
	 *         array.
	 */
	public static double[][] computeMatrixAddition(double[][] arr1, double[][] arr2) {
		// matrix1 & matrix2 must have the same dimensions
		double[][] res = new double[arr1.length][arr1[0].length];

		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				res[i][j] = (arr1[i][j] + arr2[i][j]);
			}
		}

		return res;

	}

	/**
	 * Returns a <code>double</code> 2D array containing the difference of the 2
	 * parameter matrices (2D arrays).
	 * <p>
	 * The matrices must have the same dimensions.
	 * 
	 * @param arr1 a 2D array.
	 * @param arr2 another 2D array.
	 * @return the difference of <strong>arr1</strong> and <strong>arr2</strong>, in
	 *         a 2D array.
	 */
	public static double[][] computeMatrixSubtraction(double[][] arr1, double[][] arr2) {

		double[][] res = new double[arr1.length][arr1[0].length];

		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				res[i][j] = (arr1[i][j] - arr2[i][j]);
			}
		}

		return res;

	}

	/**
	 * Returns a <code>double</code> 2D array containing the product of a matrix and
	 * a scalar.
	 * 
	 * @param arr    a 2D array.
	 * @param scalar the scalar.
	 * @return the product of <strong>arr</strong> and <strong>scalar</strong>.
	 */
	public static double[][] computeMatrixMultiplicationByScalar(double[][] arr, double scalar) {

		double[][] res = new double[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				res[i][j] = (scalar) * (arr[i][j]);
			}
		}

		return res;

	}

	/**
	 * Returns the standard error (Se) of the residuals, in a <code>double</code>
	 * format.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return Se with respect to <strong>indVar</strong> and
	 *         <strong>depVar</strong>.
	 */
	public static double computeSe(double[] indVar, double[] depVar) {

		return Math.sqrt((computeSumOfResidualsSquared(indVar, depVar)) / (depVar.length - 2));

	}

	/**
	 * Returns the sum of the residuals squared, in a <code>double</code> format.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return sum of the residuals squared with respect to <strong>indVar</strong>
	 *         and <strong>depVar</strong>.
	 */
	public static double computeSumOfResidualsSquared(double[] indVar, double[] depVar) {

		double sum = 0.0;

		double[] temp = computeResidualValues(indVar, depVar);

		for (int i = 0; i < temp.length; i++) {
			sum += (Math.pow(temp[i], 2));
		}

		return sum;

	}

	/**
	 * Returns a <code>double</code> array containing the residual values.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return an array containing the residual values with respect to
	 *         <strong>indVar</strong> and <strong>depVar</strong>.
	 */
	public static double[] computeResidualValues(double[] indVar, double[] depVar) {

		double[] res = new double[indVar.length];

		for (int i = 0; i < res.length; i++) {
			res[i] = (depVar[i] - computeLSRLOutput(indVar, depVar, indVar[i]));
		}

		return res;

	}

	/**
	 * Returns a <code>double</code> array containing the predicted y-values (ŷ).
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return an array containing the predicted y-values with respect to
	 *         <strong>indVar</strong> and <strong>depVar</strong>.
	 */
	public static double[] computeYPredictedValues(double[] indVar, double[] depVar) {

		double[] res = new double[indVar.length];

		for (int i = 0; i < res.length; i++) {
			res[i] = computeLSRLOutput(indVar, depVar, indVar[i]);
		}

		return res;

	}

	/**
	 * Returns a <code>double</code> which represents the predicted y-value (ŷ) when
	 * an x-value is inputted into the least-squares regression line (LSRL).
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @param input  a <code>double</code> which is the value to be inputted into
	 *               the LSRL function.
	 * @return the predicted y-value when <strong>input</strong> is the input
	 *         x-value to the LSRL function (with respect to <strong>indVar</strong>
	 *         and <strong>depVar</strong>).
	 */
	public static double computeLSRLOutput(double[] indVar, double[] depVar, double input) {

		return ((computeA(indVar, depVar)) + (computeB(indVar, depVar) * input));

	}

	/**
	 * Returns the least-squares regression line (LSRL) equation, in a
	 * <code>String</code> format.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return the LSRL equation with respect to <strong>indVar</strong> and
	 *         <strong>depVar</strong>.
	 */
	public static String displayLSRLEquation(double[] indVar, double[] depVar) {

		String res = "";

		res = "ŷ = " + (computeA(indVar, depVar)) + " + " + (computeB(indVar, depVar)) + "x";

		if (computeB(indVar, depVar) < 0) {
			res = "ŷ = " + (computeA(indVar, depVar)) + " - " + (-1 * computeB(indVar, depVar)) + "x";
		} else if (computeB(indVar, depVar) >= 0) {
			res = "ŷ = " + (computeA(indVar, depVar)) + " + " + (computeB(indVar, depVar)) + "x";
		}

		return res;

	}

	/**
	 * Returns the y-intercept of the least-squares regression line (LSRL) equation,
	 * in a <code>double</code> format.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return the y-intercept of the LSRL equation, with respect to
	 *         <strong>indVar</strong> and <strong>depVar</strong>.
	 */
	public static double computeA(double[] indVar, double[] depVar) {

		return (computeMean(depVar)) - ((computeB(indVar, depVar)) * (computeMean(indVar)));

	}

	/**
	 * Returns the slope of the least-squares regression line (LSRL) equation, in a
	 * <code>double</code> format.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return the slope of the LSRL equation, with respect to
	 *         <strong>indVar</strong> and <strong>depVar</strong>.
	 */
	public static double computeB(double[] indVar, double[] depVar) {

		double r = computeR(indVar, depVar);

		double b = r * (computeStandardDeviation(depVar) / computeStandardDeviation(indVar));

		return b;

	}

	/**
	 * Returns the correlation coefficient (r-value) of the relationship between the
	 * independent and dependent variables, in a <code>double</code> format.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return the r-value of the LSRL, with respect to <strong>indVar</strong> and
	 *         <strong>depVar</strong>.
	 */
	public static double computeR(double[] indVar, double[] depVar) {

		double sumOfProducts = 0.0;

		double arr1Bar = computeMean(indVar);
		double arr1Sigma = computeStandardDeviation(indVar);

		double arr2Bar = computeMean(depVar);
		double arr2Sigma = computeStandardDeviation(depVar);

		for (int i = 0; i < indVar.length; i++) { // both arrays must have the same length
			sumOfProducts += ((indVar[i] - arr1Bar) / (arr1Sigma))
					* ((depVar[i] - arr2Bar) / (arr2Sigma));
		}

		return sumOfProducts / (indVar.length - 1);

	}

	/**
	 * Returns the r^2-value of the relationship between the independent and
	 * dependent variables, in a <code>double</code> format.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar a <code>double</code> array containing the independent
	 *               variable values.
	 * @param depVar a <code>double</code> array containing the dependent variable
	 *               values.
	 * @return the r^2-value of the LSRL, with respect to <strong>indVar</strong>
	 *         and <strong>depVar</strong>.
	 */
	public static double computeRSquared(double[] indVar, double[] depVar) {

		return Math.pow((computeR(indVar, depVar)), 2.0);

	}

	/**
	 * Returns a <code>double</code> which represents the z-star (z-critical) value
	 * when a confidence level is inputted.
	 * <p>
	 * This method is only an approximation.
	 * 
	 * @param inputConfidenceLevel a <code>double</code> representing the input
	 *                             confidence level.
	 * @return the z-star value with respect to
	 *         <strong>inputConfidenceLevel</strong>.
	 */
	public static double computeZStar(double inputConfidenceLevel) {
		double invNormInput = inputConfidenceLevel + ((1 - inputConfidenceLevel) / (2));
		try {
			return computeInverseNormalApprox(invNormInput);
		} catch (Exception e) {
			System.out.println(e);
		}
		return invNormInput;

	}

	/**
	 * Returns a <code>String</code> which represents the confidence interval for a
	 * single mean, making use of the z-distribution.
	 * <p>
	 * This method assumes that µ=0 and σ=1.
	 * <p>
	 * This method is only an approximation.
	 * 
	 * @param mu              the mean of the sample.
	 * @param sigma           the standard deviation of the population.
	 * @param sampleSize      the size of the sample.
	 * @param confidenceLevel the input confidence level.
	 * @return the confidence interval for a single mean.
	 */
	public static String computeOneMeanZConfInt(double mu, double sigma, int sampleSize, double confidenceLevel) {

		String res = "";

		double lowBound = mu - (computeZStar(confidenceLevel) * ((sigma) / (Math.sqrt(sampleSize))));

		double highBound = mu + (computeZStar(confidenceLevel) * ((sigma) / (Math.sqrt(sampleSize))));

		res = "(" + lowBound + ", " + highBound + ")";

		return res;

	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for a single mean, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population mean is equal to a
	 * certain value.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that the population mean is greater
	 * than a certain value.</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param mu         the population mean to be tested.
	 * @param sigma      the population standard deviation.
	 * @param sampleMean the mean of the sample.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeOneMeanZTestHaGreaterThanValue(double mu, double sigma, double sampleMean,
			int sampleSize, double alpha) {

		double zCritical = (sampleMean - mu) / (sigma / (Math.sqrt(sampleSize)));

		double pValue = computeZProbMidpointRiemann(zCritical, 1000.0);

		if (pValue < alpha) {
			return "There is statistically significant evidence that Ha > H0... reject H0 - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that Ha > H0... fail to reject H0 - p-value: "
					+ pValue;
		} else if (pValue == alpha) {
			return "";
		}

		return "";

	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for a single mean, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population mean is equal to a
	 * certain value.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that the population mean is less
	 * than a certain value.</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param mu         the population mean to be tested.
	 * @param sigma      the population standard deviation.
	 * @param sampleMean the mean of the sample.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeOneMeanZTestHaLessThanValue(double mu, double sigma, double sampleMean, int sampleSize,
			double alpha) {

		double zCritical = (sampleMean - mu) / (sigma / (Math.sqrt(sampleSize)));

		double pValue = computeZProbMidpointRiemann(-1000.0, zCritical);

		if (pValue < alpha) {
			return "There is statistically significant evidence that Ha < H0... reject H0 - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that Ha < H0... fail to reject H0 - p-value: "
					+ pValue;
		} else if (pValue == alpha) {
			return "";
		}

		return "";

	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for a single mean, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population mean is equal to a
	 * certain value.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that the population mean is not
	 * equal to a certain value.</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param mu         the population mean to be tested.
	 * @param sigma      the population standard deviation.
	 * @param sampleMean the mean of the sample.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeOneMeanZTestHaNotEqualToValue(double mu, double sigma, double sampleMean,
			int sampleSize, double alpha) {

		double zCritical = (sampleMean - mu) / (sigma / (Math.sqrt(sampleSize)));

		double pValue = (computeZProbMidpointRiemann((Math.abs(zCritical)), 1000.0)) * 2.0;

		if (pValue < alpha) {
			return "There is statistically significant evidence that Ha ≠ H0... reject H0 - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that Ha ≠ H0... fail to reject H0 - p-value: "
					+ pValue;
		} else if (pValue == alpha) {
			return "";
		}

		return "";

	}

	/**
	 * Returns a <code>String</code> which represents the confidence interval for
	 * estimating a single population proportion.
	 * <p>
	 * This method is only an approximation.
	 * 
	 * @param pHat            the sample proportion.
	 * @param sampleSize      the size of the sample.
	 * @param confidenceLevel the confidence level needed.
	 * @return the confidence interval, where <strong>pHat</strong> is the center of
	 *         the interval.
	 */
	public static String computeOnePropZConfInt(double pHat, int sampleSize, double confidenceLevel) {

		double qHat = 1 - pHat;
		double zStar = computeZStar(confidenceLevel);
		double standardError = Math.sqrt((pHat * qHat) / (sampleSize));
		double low = pHat - (zStar * standardError);
		double high = pHat + (zStar * standardError);

		return "(" + low + ", " + high + ")";

	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for a single proportion, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population proportion is equal
	 * to a certain value.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that the population proportion is
	 * less than a certain value.</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param pHat       the sample proportion.
	 * @param pNought    the population proportion to be tested.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeOnePropZTestP0LessThanValue(double pHat, double pNought, double sampleSize,
			double alpha) {

		double qNought = 1 - pNought;

		double zCritical = (pHat - pNought) / (Math.sqrt((pNought * qNought) / (sampleSize)));

		double pValue = computeZProbMidpointRiemann(-1000.0, zCritical);

		if (pValue < alpha) {
			return "There is statistically significant evidence that the true P0 < given P0... reject H0 - p-value: "
					+ pValue;
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that the true P0 < given P0... fail to reject H0 - p-value: "
					+ pValue;
		} else { // pValue equals alpha
			return "";
		}

	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for a single proportion, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population proportion is equal
	 * to a certain value.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that the population proportion is
	 * greater than a certain value.</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param pHat       the sample proportion.
	 * @param pNought    the population proportion to be tested.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeOnePropZTestP0GreaterThanValue(double pHat, double pNought, double sampleSize,
			double alpha) {

		double qNought = 1 - pNought;

		double zCritical = (pHat - pNought) / (Math.sqrt((pNought * qNought) / (sampleSize)));

		double pValue = computeZProbMidpointRiemann(zCritical, 1000.0);

		if (pValue < alpha) {
			return "There is statistically significant evidence that the true P0 > given P0... reject H0 - p-value: "
					+ pValue;
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that the true P0 > given P0... fail to reject H0 - p-value: "
					+ pValue;
		} else { // pValue equals alpha
			return "";
		}

	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for a single proportion, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population proportion is equal
	 * to a certain value.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that the population proportion is
	 * not equal to a certain value.</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param pHat       the sample proportion.
	 * @param pNought    the population proportion to be tested.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeOnePropZTestP0NotEqualToValue(double pHat, double pNought, double sampleSize,
			double alpha) {

		double qNought = 1 - pNought;

		double zCritical = (pHat - pNought) / (Math.sqrt((pNought * qNought) / (sampleSize)));

		double pValue = computeZProbMidpointRiemann((Math.abs(zCritical)), 1000.0) * 2.0;

		if (pValue < alpha) {
			return "There is statistically significant evidence that the true P0 ≠ the given P0... reject H0 - p-value: "
					+ pValue;
		} else if (pValue > alpha) {
			return "There is no statistically significant evidence that the true P0 ≠ the given P0... fail to reject H0 - p-value: "
					+ pValue;
		} else { // pValue equals alpha
			return "";
		}
	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for 2 proportions, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population proportions are
	 * equal to each other.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that one population proportion (p1)
	 * is less than the other population proportion (p2).</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param successes1  the number of successes in the sample, with respect to
	 *                    p1.
	 * @param sampleSize1 the size of the sample, with respect to p1.
	 * @param successes2  the number of successes in the sample, with respect to
	 *                    p2.
	 * @param sampleSize2 the size of the sample, with respect to p2.
	 * @param alpha       the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeTwoPropZTestP1LessThanP2(int successes1, int sampleSize1, int successes2,
			int sampleSize2, double alpha) {
		double pHat1 = (successes1 * 1.0 / sampleSize1 * 1.0);
		double pHat2 = (successes2 * 1.0 / sampleSize2 * 1.0);
		double pHatPooled = ((successes1 * 1.0 + successes2 * 1.0) / (sampleSize1 * 1.0 + sampleSize2 * 1.0)) * 1.0;
		double qHatPooled = 1 - pHatPooled;

		double z = (pHat1 - pHat2) / (Math.sqrt(
				((pHatPooled * qHatPooled) / sampleSize1 * 1.0) + ((pHatPooled * qHatPooled) / sampleSize2 * 1.0)));

		double methodPValue = computeZProbMidpointRiemann(-1000.0, z);

		if (methodPValue < alpha) {
			return "There is statistically significant evidence that the true P1 < P2... reject H0 - p-value: "
					+ methodPValue;
		} else if (methodPValue > alpha) {
			return "There is no statistically significant evidence that the true P1 < P2... fail to reject H0 - p-value: "
					+ methodPValue;
		} else { // pvalue equals alpha
			return "";
		}
	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for 2 proportions, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population proportions are
	 * equal to each other.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that one population proportion (p1)
	 * is greater than the other population proportion (p2).</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param successes1  the number of successes in the sample, with respect to
	 *                    p1.
	 * @param sampleSize1 the size of the sample, with respect to p1.
	 * @param successes2  the number of successes in the sample, with respect to
	 *                    p2.
	 * @param sampleSize2 the size of the sample, with respect to p2.
	 * @param alpha       the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeTwoPropZTestP1GreaterThanP2(int successes1, int sampleSize1, int successes2,
			int sampleSize2, double alpha) {
		double pHat1 = (successes1 * 1.0 / sampleSize1 * 1.0);
		double pHat2 = (successes2 * 1.0 / sampleSize2 * 1.0);
		double pHatPooled = ((successes1 * 1.0 + successes2 * 1.0) / (sampleSize1 * 1.0 + sampleSize2 * 1.0));
		double qHatPooled = 1 - pHatPooled;

		double z = (pHat1 - pHat2) / (Math.sqrt(
				((pHatPooled * qHatPooled) / sampleSize1 * 1.0) + ((pHatPooled * qHatPooled) / sampleSize2 * 1.0)));

		double methodPValue = computeZProbMidpointRiemann(z, 1000.0);

		if (methodPValue < alpha) {
			return "There is statistically significant evidence that the true P1 > P2... reject H0 - p-value: "
					+ methodPValue;
		} else if (methodPValue > alpha) {
			return "There is no statistically significant evidence that the true P1 > P2... fail to reject H0 - p-value: "
					+ methodPValue;
		} else { // pvalue equals alpha
			return "";
		}
	}

	/**
	 * Returns the final decision (a <code>String</code>) of the significance test
	 * for 2 proportions, utilizing the z-distribution.
	 * <p>
	 * For this method:
	 * <p>
	 * <ul>
	 * <li>the null hypothesis should state that the population proportions are
	 * equal to each other.</li>
	 * <ul>
	 * <li>the alternate hypothesis should state that one population proportion (p1)
	 * is not equal to the other population proportion (p2).</li>
	 * </ul>
	 * </ul>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * 
	 * @param successes1  the number of successes in the sample, with respect to
	 *                    p1.
	 * @param sampleSize1 the size of the sample, with respect to p1.
	 * @param successes2  the number of successes in the sample, with respect to
	 *                    p2.
	 * @param sampleSize2 the size of the sample, with respect to p2.
	 * @param alpha       the significance level (α) of the test.
	 * @return the final decision of the significance test.
	 */
	public static String computeTwoPropZTestP1NotEqualToP2(int successes1, int sampleSize1, int successes2,
			int sampleSize2, double alpha) {
		double pHat1 = (successes1 * 1.0 / sampleSize1 * 1.0);
		double pHat2 = (successes2 * 1.0 / sampleSize2 * 1.0);
		double pHatPooled = ((successes1 * 1.0 + successes2 * 1.0) / (sampleSize1 * 1.0 + sampleSize2 * 1.0));
		double qHatPooled = 1 - pHatPooled;

		double z = (pHat1 - pHat2) / (Math.sqrt(
				((pHatPooled * qHatPooled) / sampleSize1 * 1.0) + ((pHatPooled * qHatPooled) / sampleSize2 * 1.0)));

		double methodPValue = 2.0 * computeZProbMidpointRiemann(Math.abs(z), 1000.0);

		if (methodPValue < alpha) {
			return "There is statistically significant evidence that the true P1 ≠ P2... reject H0 - p-value: "
					+ methodPValue;
		} else if (methodPValue > alpha) {
			return "There is no statistically significant evidence that the true P1 ≠ P2... fail to reject H0 - p-value: "
					+ methodPValue;
		} else { // pvalue equals alpha
			return "";
		}
	}

	/**
	 * Returns the an approximation of the gamma function when given an input.
	 * <p>
	 * This method makes use of Stirling's approximation of the gamma function.
	 * 
	 * @param inputZ the input value.
	 * @return the output of the gamma function when <strong>inputZ</strong> is
	 *         the
	 *         input.
	 */
	public static double computeGammaFunction(double inputZ) {
		double exponent = (inputZ * (Math.log(inputZ) - 1)) + (0.5 * (Math.log(1.0 / inputZ) + Math.log(2 * Math.PI)))
				+ (1.0 / (12.0 * inputZ)) - (1.0 / (360.0 * Math.pow(inputZ, 3.0)));
		return Math.pow(Math.E, exponent);
	}

	/**
	 * Returns the output of the chi-square function when provided a chi-square
	 * value and the degrees of freedom.
	 * 
	 * @param chiSqrValue the input chi-square value.
	 * @param degFree     the degrees of freedom.
	 * @return the output of the chi-square function when the inputs are
	 *         <strong>chiSqrValue</strong> and <strong>degFree</strong>.
	 */
	public static double computeChiSquarePDF(double chiSqrValue, int degFree) {
		if (chiSqrValue < 0)
			return 0;
		else {
			double numerator = (Math.pow(chiSqrValue, (degFree / 2.0) - 1.0))
					* (Math.pow(Math.E, -1.0 * chiSqrValue / 2.0));
			double denominator = (Math.pow(2, degFree / 2.0)) * computeGammaFunction(degFree / 2.0);
			return numerator / denominator;
		}
	}

	/**
	 * Returns the area (probability) under the chi-square function between 2
	 * chi-square values.
	 * 
	 * @param lowerBound the lower bound chi-square value.
	 * @param upperBound the upper bound chi-square value.
	 * @param degFree    the degrees of freedom.
	 * @return the area (probability) under the chi-square function between
	 *         <strong>lowerBound</strong> and <strong>upperBound</strong>.
	 */
	public static double computeChiSquareCDF(double lowerBound, double upperBound, int degFree) {
		double sum = 0.0;
		double increment = (upperBound - lowerBound) / (Math.pow(10, 2));
		for (double i = lowerBound + (increment / 2.0); i < upperBound; i += increment) {
			sum += (increment * computeChiSquarePDF(i, degFree));
		}
		return sum;
	}

	/**
	 * Returns the final decision and p-value of the chi-square goodness of fit
	 * (GOF) test.
	 * <p>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * <p>
	 * The degrees of freedom will be automatically registered as one less than the
	 * number of categories.
	 * 
	 * @param observed the array containing the observed values.
	 * @param expected the array containing the expected values.
	 * @param alpha    the significance level (α) of the test.
	 * @return the final decision and p-value of the GOF test.
	 */
	public static String computeChiSquareGOFTest(double[] observed, double[] expected, double alpha) {
		int degFree = observed.length - 1;
		double sum = 0;

		for (int i = 0; i < observed.length; i++) {
			sum += Math.pow(observed[i] - expected[i], 2) / expected[i];
		}

		double pValue = computeChiSquareCDF(sum, 100, degFree);
		if (pValue < alpha) {
			return "Reject null hypothesis - p-value: "
					+ pValue;
		} else if (pValue > alpha) {
			return "Fail to reject null hypothesis - p-value: "
					+ pValue;
		} else { // pvalue equals alpha
			return "";
		}
	}

	/**
	 * Returns the final decision and p-value of a two-way chi-square test.
	 * <p>
	 * The decision could be to either reject the null hypothesis or fail to
	 * reject the null hypothesis.
	 * <p>
	 * The degrees of freedom will automatically be registered as (# of rows - 1) *
	 * (# of columns - 1).
	 * 
	 * @param observed the observed matrix (do not include cells for the row totals
	 *                 and column
	 *                 totals).
	 * @param alpha    the significance level (α) of the test.
	 * @return the final decision and p-value of the two-way test.
	 */
	public static String computeChiSquareTwoWayTest(double[][] observed, double alpha) {
		double total = 0;
		for (int i = 0; i < observed.length; i++) {
			for (int j = 0; j < observed[i].length; j++) {
				total += observed[i][j];
			}
		}
		double[][] expected = new double[observed.length][observed[0].length];
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expected[i][j] = (computeRowSum(observed, i) * computeColumnSum(observed, j)) / total * 1.0;
			}
		}
		double sum = 0;
		for (int i = 0; i < observed.length; i++) {
			for (int j = 0; j < observed[i].length; j++) {
				sum += Math.pow(observed[i][j] - expected[i][j], 2) / expected[i][j];
			}
		}
		double pValue = computeChiSquareCDF(sum, 1000, (observed.length - 1) * (observed[0].length - 1));

		if (pValue < alpha) {
			return "Reject null hypothesis - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "Fail to reject null hypothesis - p-value: " + pValue;
		} else {
			return "";
		}
	}

	/**
	 * Returns the output of the Student's t-distribution function when given the
	 * input value
	 * and degrees of freedom.
	 * 
	 * @param inputVal the input value.
	 * @param degFree  the degrees of freedom.
	 * @return the output of the t-distribution function when given
	 *         <strong>inputVal</strong>
	 *         and <strong>degFree</strong>.
	 */
	public static double computeTPDF(double inputVal, int degFree) {
		double firstTermNumerator = computeGammaFunction((degFree + 1.0) / 2.0);
		double firstTermDenominator = computeGammaFunction(degFree / 2.0) * Math.sqrt(degFree * Math.PI);
		double secondTermBase = 1.0 + (Math.pow(inputVal, 2.0) / degFree);
		double exponent = (-1.0) * ((degFree + 1.0) / 2.0);
		return (firstTermNumerator / firstTermDenominator) * Math.pow(secondTermBase, exponent);
	}

	/**
	 * Returns the area (probability) under the t-distribution function between two
	 * t-values.
	 * 
	 * @param lowBound  the lower bound t-value.
	 * @param highBound the higher bound t-value.
	 * @param degFree   the degrees of freedom.
	 * @return the the area (probability) under the t-distribution function between
	 *         <strong>lowBound</strong> and <strong>highBound</strong>.
	 */
	public static double computeTCDF(double lowBound, double highBound, int degFree) {
		double sum = 0.0;
		double increment = (highBound - lowBound) / (Math.pow(10, 2));
		for (double i = lowBound + (increment / 2.0); i < highBound; i += increment) {
			sum += (increment * computeTPDF(i, degFree));
		}
		return sum;
	}

	/**
	 * Returns the final decision and p-value of a significance test, utilizing the
	 * t-distribution.
	 * <p>
	 * The alternate hypothesis must state that the population mean is less than a
	 * value.
	 * <p>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * <p>
	 * The degrees of freedom will automatically be registered as one less than the
	 * sample size.
	 * 
	 * @param mu         the population mean to be tested.
	 * @param sampleMean the mean of the sample.
	 * @param sampleSD   the standard deviation of the sample.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision and p-value of the significance test.
	 */
	public static String computeOneMeanTTestHaLessThanValue(double mu, double sampleMean, double sampleSD,
			int sampleSize, double alpha) {
		double t = (sampleMean - mu) / (sampleSD / Math.sqrt(sampleSize));
		double pValue = computeTCDF(-100, t, sampleSize - 1);

		if (pValue < alpha) {
			return "Reject null hypothesis - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "Fail to reject null hypothesis - p-value: " + pValue;
		} else {
			return "";
		}
	}

	/**
	 * Returns the final decision and p-value of a significance test, utilizing the
	 * t-distribution.
	 * <p>
	 * The alternate hypothesis must state that the population mean is greater than
	 * a value.
	 * <p>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * <p>
	 * The degrees of freedom will automatically be registered as one less than the
	 * sample size.
	 * 
	 * @param mu         the population mean to be tested.
	 * @param sampleMean the mean of the sample.
	 * @param sampleSD   the standard deviation of the sample.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision and p-value of the significance test.
	 */
	public static String computeOneMeanTTestHaGreaterThanValue(double mu, double sampleMean, double sampleSD,
			int sampleSize, double alpha) {
		double t = (sampleMean - mu) / (sampleSD / Math.sqrt(sampleSize));
		double pValue = computeTCDF(t, 100, sampleSize - 1);

		if (pValue < alpha) {
			return "Reject null hypothesis - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "Fail to reject null hypothesis - p-value: " + pValue;
		} else {
			return "";
		}
	}

	/**
	 * Returns the final decision and p-value of a significance test, utilizing the
	 * t-distribution.
	 * <p>
	 * The alternate hypothesis must state that the population mean is not equal to
	 * a value.
	 * <p>
	 * The decision could be to either reject the null hypothesis or fail to reject
	 * the null hypothesis.
	 * <p>
	 * The degrees of freedom will automatically be registered as one less than the
	 * sample size.
	 * 
	 * @param mu         the population mean to be tested.
	 * @param sampleMean the mean of the sample.
	 * @param sampleSD   the standard deviation of the sample.
	 * @param sampleSize the size of the sample.
	 * @param alpha      the significance level (α) of the test.
	 * @return the final decision and p-value of the significance test.
	 */
	public static String computeOneMeanTTestHaNotEqualToValue(double mu, double sampleMean, double sampleSD,
			int sampleSize, double alpha) {
		double t = (sampleMean - mu) / (sampleSD / Math.sqrt(sampleSize));
		double pValue = 2 * computeTCDF(Math.abs(t), 100, sampleSize - 1);

		if (pValue < alpha) {
			return "Reject null hypothesis - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "Fail to reject null hypothesis - p-value: " + pValue;
		} else {
			return "";
		}
	}

	/**
	 * Returns the standard error of the slope.
	 * <p>
	 * This method uses linear regression to model the relationship between the 2
	 * variables (least-squares regression line format: ŷ = a + bx).
	 * 
	 * @param indVar the independent variable.
	 * @param depVar the dependent variable.
	 * @return the standard error of the slope, with respect to
	 *         <strong>indVar</strong> and <strong>depVar</strong>.
	 */
	public static double computeSeb(double[] indVar, double[] depVar) {
		double xMean = computeMean(indVar);
		double sum = 0;
		for (int i = 0; i < indVar.length; i++) {
			sum += Math.pow(indVar[i] - xMean, 2);
		}
		return computeSe(indVar, depVar) / Math.sqrt(sum);
	}

	/**
	 * Returns the final decision and p-value of a linear regression t-test.
	 * <p>
	 * This method tests whether or not the population slope is negative.
	 * <p>
	 * The degrees of freedom will automatically be registered as 2 less than the
	 * number of data values.
	 * 
	 * @param indVar the independent variable.
	 * @param depVar the dependent variable.
	 * @param alpha  the significance level (α) of the test.
	 * @return the final decision and p-value of the test.
	 */
	public static String computeLinRegTTestNegativeSlope(double[] indVar, double[] depVar, double alpha) {
		double t = (computeB(indVar, depVar) - 0) / computeSeb(indVar, depVar);
		double pValue = computeTCDF(-100, t, indVar.length - 2);

		if (pValue < alpha) {
			return "Reject null hypothesis - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "Fail to reject null hypothesis - p-value: " + pValue;
		} else {
			return "";
		}
	}

	/**
	 * Returns the final decision and p-value of a linear regression t-test.
	 * <p>
	 * This method tests whether or not the population slope is positive.
	 * <p>
	 * The degrees of freedom will automatically be registered as 2 less than the
	 * number of data values.
	 * 
	 * @param indVar the independent variable.
	 * @param depVar the dependent variable.
	 * @param alpha  the significance level (α) of the test.
	 * @return the final decision and p-value of the test.
	 */
	public static String computeLinRegTTestPositiveSlope(double[] indVar, double[] depVar, double alpha) {
		double t = (computeB(indVar, depVar) - 0) / computeSeb(indVar, depVar);
		double pValue = computeTCDF(t, 100, indVar.length - 2);

		if (pValue < alpha) {
			return "Reject null hypothesis - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "Fail to reject null hypothesis - p-value: " + pValue;
		} else {
			return "";
		}
	}

	/**
	 * Returns the final decision and p-value of a linear regression t-test.
	 * <p>
	 * This method tests whether or not the population slope is not equal to 0.
	 * <p>
	 * The degrees of freedom will automatically be registered as 2 less than the
	 * number of data values.
	 * 
	 * @param indVar the independent variable.
	 * @param depVar the dependent variable.
	 * @param alpha  the significance level (α) of the test.
	 * @return the final decision and p-value of the test.
	 */
	public static String computeLinRegTTestUnequalSlope(double[] indVar, double[] depVar, double alpha) {
		double t = (computeB(indVar, depVar) - 0) / computeSeb(indVar, depVar);
		double pValue = 2 * computeTCDF(Math.abs(t), 100, indVar.length - 2);

		if (pValue < alpha) {
			return "Reject null hypothesis - p-value: " + pValue;
		} else if (pValue > alpha) {
			return "Fail to reject null hypothesis - p-value: " + pValue;
		} else {
			return "";
		}
	}

}