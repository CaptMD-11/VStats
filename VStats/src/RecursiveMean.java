import java.util.ArrayList;

public final class RecursiveMean {

    private ArrayList<Double> meansList; 

    public RecursiveMean() {
        meansList = new ArrayList<Double>();
    }
         
    public ArrayList<Double> computeRecursiveMean(double[] data) {

        if (data.length < 2) 
            return meansList; 
        else {
            meansList.add(VStats.computeMean(data)); 
            ArrayList<Double> temp = new ArrayList<Double>(); 

            for (int i = 1; i < data.length-1; i++) 
                temp.add(data[i]); 
            
            double[] nextPass = new double[temp.size()]; 
            for (int i = 0; i < temp.size(); i++)
                nextPass[i] = temp.get(i); 
            
            return computeRecursiveMean(nextPass); 
            
        }

    }    
    
}