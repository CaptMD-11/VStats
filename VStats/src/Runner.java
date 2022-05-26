public class Runner {

    public static void main(String[] args) {
    	double[] res = {3,4875,5,8,34}; 
        //VStats obj = new VStats(res); 
        
        double[] temp = {6,7,3,2}; 
        double mean = VStats.computeMean(temp); 

        //String output = VStats.computeZMeansTestHaGreaterThanValue(1, 2, 3, 4, 5); 

        //System.out.println(output); 

        System.out.println(VStats.computeZProbMidpointRiemann(2.0, 10000));

       // System.out.println(VStats.computeGeometricPdfProb(5, 0.2)); 

        //System.out.println(VStats.computeZProbLeftRiemann(-1.0, 1.0)); 

        //System.out.println()
        
    }
    
}
