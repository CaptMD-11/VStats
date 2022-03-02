
public class MatrixOperations {
	
	private int vars;
	
	public MatrixOperations() {
		vars = 0; 
	}
	
	public static class MatrixData {
		double[][] arr1;
		double[][] arr2; 
	}
	
	public double[][] computeMatrixAddition(MatrixData data) { // arr1 & arr2 must have same dimensions 
		
		double[][] res = new double[data.arr1.length][data.arr1[0].length]; 
		
		for (int i = 0; i < data.arr1.length; i++) {
			for (int j = 0; j < data.arr1[i].length; j++) {
				res[i][j] = (data.arr1[i][j] + data.arr2[i][j]);
			}
		}
		
		return res; 
		
	}
	
	public double[][] computeMatrixSubtraction(MatrixData data) {
		
		double[][] res = new double[data.arr1.length][data.arr1[0].length]; 
		
		for (int i = 0; i < data.arr1.length; i++) {
			for (int j = 0; j < data.arr1[i].length; j++) {
				res[i][j] = (data.arr1[i][j] - data.arr2[i][j]);
			}
		}
		
		return res; 
		
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime(); 
		
		System.out.println("Matrix Operations Information: "); 
		System.out.println(); 
		
		MatrixOperations myObj = new MatrixOperations(); 
		MatrixData myData = new MatrixData(); 
		
		///////////////////////////////////////////////////////
		
		// enter your data below! 
		
		myData.arr1 = new double[][]{{2, 3}, {3, 4}};
		myData.arr2 = new double[][]{{2,2}, {3,2}}; 
		
		///////////////////////////////////////////////////////
		
		double[][] tempAddition = myObj.computeMatrixAddition(myData); 
		System.out.println("Matrix addition result: "); 
		for (int i = 0; i < tempAddition.length; i++) {
			for (int j = 0; j < tempAddition[i].length; j++) {
				System.out.print(tempAddition[i][j] + " "); 
			}
			System.out.println(); 
		}
		
		System.out.println(); 
		
		double[][] tempSubtraction = myObj.computeMatrixSubtraction(myData); 
		System.out.println("Matrix subtraction result: "); 
		for (int i = 0; i < tempSubtraction.length; i++) {
			for (int j = 0; j < tempSubtraction[i].length; j++) {
				System.out.print(tempSubtraction[i][j] + " "); 
			}
			System.out.println(); 
		}
		
		System.out.println();
		
		//System.out.println("Matrix addition result: " + myObj.computeMatrixAddition(myData)); 
		
		System.out.println(); 
		System.out.println("-------"); 
		System.out.println(); 
		
		long after = System.nanoTime(); 
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 

	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
