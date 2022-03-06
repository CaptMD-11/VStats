import java.util.ArrayList;

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
	
	public double getRowSum(double[][] data, int row) {
		
		double sum = 0; 
		
		for (int i = 0; i < data[0].length; i++) {
			sum += data[row][i]; 
		}
		
		return sum; 
		
	}
	
	public double getColumnSum(double[][] data, int col) {
		
		double sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum += data[i][col]; 
		}
		
		return sum; 
		
	}
	
	public double getRowProduct(double[][] data, int row) {
		
		double sum = 0; 
		
		for (int i = 0; i < data[0].length; i++) {
			sum *= data[row][i]; 
		}
		
		return sum; 
	}
	
	public double getColumnProduct(double[][] data, int col) {
		
		double sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum *= data[i][col]; 
		}
		
		return sum; 
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
	
	public double[][] computeMatrixMultiplicationBySquareMatrices(MatrixData data) { // matrix1 * matrix2 
		
		double[][] res = new double[data.matrix1.length][data.matrix1[0].length];
		ArrayList<Double> matrix1Products = new ArrayList<Double>(); 
		ArrayList<Double> matrix2Products = new ArrayList<Double>(); 
		
		for (int i = 0; i < data.matrix1.length; i++) {
			matrix1Products.add(getRowProduct(data.matrix1, i)); 
		}
		
		for (int i = 0; i < data.matrix2.length; i++) {
			matrix2Products.add(getColumnProduct(data.matrix2, i)); 
		}
		
		Stack<Double> infoStack1 = new Stack<Double>(); 
		
		for (int i = 0; i < matrix1Products.size(); i++) {
			for (int j = 0; j < matrix1Products.size(); i++) {
				infoStack1.push(matrix1Products.get(i) + matrix2Products.get(j));
			}
		} // all elements in stack - but in reverse order 
		
		Stack<Double> infoStack2 = new Stack<Double>();
		int infoStack1Size = infoStack1.size(); 
		
		for (int i = 0; i < infoStack1Size; i++) {
			infoStack2.push(infoStack1.pop());
		} // all elements - in "correct" order 
		
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				res[i][j] = infoStack2.pop(); 
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
		System.out.println("Matrix multiplicaion by scalar result: "); 
		for (int i = 0; i < tempScalarMultiplication.length; i++) {
			for (int j = 0; j < tempScalarMultiplication[i].length; j++) {
				System.out.print(tempScalarMultiplication[i][j] + " "); 
			}
			System.out.println(); 
		}
		
		System.out.println(); 
		
		double[][] tempMatrixMultiplicationBySquareMatrices = myObj.computeMatrixMultiplicationBySquareMatrices(myData); 
		System.out.println("Matrix multiplication by square matrices result: "); 
		for (int i = 0; i < tempMatrixMultiplicationBySquareMatrices.length; i++) {
			for (int j = 0; j < tempMatrixMultiplicationBySquareMatrices[i].length; j++) {
				System.out.print(tempMatrixMultiplicationBySquareMatrices[i][j] + " "); 
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
