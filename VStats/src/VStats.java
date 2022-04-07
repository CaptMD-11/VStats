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
			temp  = data[middleIndex]; // median is good for odd number of values
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

	public double computeStanDev(double[] data) {
		//assert variance != 0;
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
			quartile3 = (data[middleIndex + (arrayEvenCounter / 2)]
					+ data[middleIndex + (arrayEvenCounter / 2) + 1]) / 2.0;
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
			quartile3 = (data[middleIndex + (arrayOddCounter / 2) - 1]
					+ data[middleIndex + (arrayOddCounter / 2)]) / 2.0;
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
		
		int count = 0; 
		
		for (int i = 0; i < data.length; i++) {
			count++; 
		}
		
		return count; 
		
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

}
