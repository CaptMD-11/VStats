
public class MatrixOperations {
	
	private int vars;
	
	public MatrixOperations() {
		vars = 0; 
	}
	
	public static class MatrixData {
		double[][] matrix1;
		double[][] matrix2; 
		double[][] generalMatrix; 
		double scalar; 
	}
	
	public double[][] computeMatrixAddition(MatrixData data) { // matrix1 & matrix2 must have same dimensions 
		
		double[][] res = new double[data.matrix1.length][data.matrix1[0].length]; 
		
		for (int i = 0; i < data.matrix1.length; i++) {
			for (int j = 0; j < data.matrix1[i].length; j++) {
				res[i][j] = (data.matrix1[i][j] + data.matrix2[i][j]);
			}
		}
		
		return res; 
		
	}
	
	public double[][] computeMatrixSubtraction(MatrixData data) {
		
		double[][] res = new double[data.matrix1.length][data.matrix1[0].length]; 
		
		for (int i = 0; i < data.matrix1.length; i++) {
			for (int j = 0; j < data.matrix1[i].length; j++) {
				res[i][j] = (data.matrix1[i][j] - data.matrix2[i][j]);
			}
		}
		
		return res; 
		
	}
	
	public double[][] computeMatrixMultiplicationByScalar(MatrixData data) {
		
		double[][] res = new double[data.generalMatrix.length][data.generalMatrix[0].length]; 
		
		for (int i = 0; i < data.generalMatrix.length; i++) {
			for (int j = 0; j < data.generalMatrix[i].length; j++) {
				res[i][j] = (data.scalar) * (data.generalMatrix[i][j]); 
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
		
		myData.matrix1 = new double[][] {{2, 3}, {3, 4}};
		myData.matrix2 = new double[][] {{2,2}, {3,2}}; 
		myData.generalMatrix = new double[][] {{3,4} , {2,3}}; 
		myData.scalar = 2; 
		
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
		
		double[][] tempScalarMultiplication = myObj.computeMatrixMultiplicationByScalar(myData); 
		System.out.println("Matrix subtraction result: "); 
		for (int i = 0; i < tempScalarMultiplication.length; i++) {
			for (int j = 0; j < tempScalarMultiplication[i].length; j++) {
				System.out.print(tempScalarMultiplication[i][j] + " "); 
			}
			System.out.println(); 
		}
		
		//System.out.println("Matrix addition result: " + myObj.computeMatrixAddition(myData)); 
		
		System.out.println(); 
		System.out.println("-------"); 
		System.out.println(); 
		
		long after = System.nanoTime(); 
		
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 

	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
