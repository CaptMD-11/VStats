import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {

        //System.out.println(VStats.computeZProbTrapezoidRiemann(0, 0.1)); 

        double[][] arr1 = {{1,2,6,7},{3,4,9,6}}; 
        double[][] arr2 = {{5,2,5,6},{5,3,7,4}}; 

        double[][] arr = VStats.computeMatrixMultiplicationByScalar(arr1, 3); 

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println(arr[i][j]); 
            }
        }

        //System.out.println(VStats.computeMatrixAddition(arr1, arr2)); 

        // double[] data = {2,2,6,4,5,4,1,7,3,5,1,6,7,8}; 

        // RecursiveMean obj = new RecursiveMean();

        // ArrayList<Double> test = obj.computeRecursiveMean(data); 

        // for (int i = 0; i < test.size(); i++) 
        //     System.out.println(test.get(i)); 

       //System.out.println(VStats.computeIQR(temp)); 

        // for (double i : arr) {
        //     System.out.println(i); 
        // }

        //System.out.println(mean); 

        //String output = VStats.computeZMeansTestHaGreaterThanValue(1, 2, 3, 4, 5); 

        //System.out.println(output); 

        //System.out.println(VStats.computeZProbMidpointRiemann(2.0, 10000));

       // System.out.println(VStats.computeGeometricPdfProb(5, 0.2)); 

        //System.out.println(VStats.computeZProbLeftRiemann(-1.0, 1.0)); 

        //System.out.println()
        
    }
    
}
