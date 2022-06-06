import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class DrawValues {
	
	private double value;
	private int xLoc; 
	private int yLoc; 
	
	public DrawValues(double inputValue, int inputXLoc, int inputYLoc) {
		value = inputValue; 
		xLoc = inputXLoc; 
		yLoc = inputYLoc; 
	}
	
	public double getValue() {
		return value; 
	}
	
	public int getXLoc() {
		return xLoc; 
	}
	
	public int getYLoc() {
		return yLoc; 
	}
	
}

public class Boxplot extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	int frameWidth = 700;
	int frameHeight = 400; 
	double[] inputData;
	
	
	public void paint(Graphics g) {
		
		g.fillRect(0, 0, frameWidth, frameHeight); 
		
		g.setColor(Color.white);
		
        Font smallFont = new Font("Monospaced", Font.PLAIN, 20);
        g.setFont(smallFont);
        g.drawString("Boxplot", 300, 30);
        
        OneVar oneVarObj = new OneVar(inputData); 
        
        // initializing 5-number summary 
        double min = oneVarObj.computeMinimum();
        double q1 = oneVarObj.computeQuartile1();
        double median = oneVarObj.computeMedian();
        double q3 = oneVarObj.computeQuartile3();
        double max = oneVarObj.computeMaximum();
        double range = oneVarObj.computeRange(); 
        
        // graph scaling 
        // need 15 "increments" 
        double numberIncrement = (range / 15.0); 
        int windowIncrement = (frameWidth / 16); 
        int windowIndexer = (windowIncrement/4) + 5;
        
        ArrayList<Double> incrementingVals = new ArrayList<Double>(); 
        
        ArrayList<DrawValues> drawValuesTracker = new ArrayList<DrawValues>(); 
        
        Font currFont = new Font("Monospaced", Font.PLAIN, 10); 
        g.setFont(currFont); 
        
        // draw scaling axis & axis tick marks 
        for (double i = min; i <= max; i += numberIncrement) {
        	i = Math.round(i * 100.0) / 100.0; 
        	String temp = i+""; 
        	if (temp.length() < 4)
        		temp += "0"; 
        	g.drawString(temp, windowIndexer, 300);
        	drawValuesTracker.add(new DrawValues(i, windowIndexer, 300)); 
        	g.fillRect(windowIndexer+10, 270, 1, 10); 
        	incrementingVals.add(i); 
        	windowIndexer += windowIncrement; 
        }
        // now, drawValuesTracker is filled incrementing values & their respective "locations" 
        
        // raw incrementingVals is sorted from least to greatest 
        // adding 5-number summary to respective indexes in incrementingVals 
        
        ArrayList<Double> fiveNumberSummary = new ArrayList<Double>(5); 
        
        fiveNumberSummary.add(min);
        fiveNumberSummary.add(q1);
        fiveNumberSummary.add(median); 
        fiveNumberSummary.add(q3); 
        fiveNumberSummary.add(max); 
        
//        for (int j = 0; j < fiveNumberSummary.size(); j++) {
//        	for (int i = 0; i < incrementingVals.size(); i++) {
//            	if ((i == 0) && (fiveNumberSummary.get(j) < incrementingVals.get(i+1))) {
//            		incrementingVals.add(i, fiveNumberSummary.get(j)); 
//            	} else if ((i == incrementingVals.size()-1) && (fiveNumberSummary.get(j) > incrementingVals.get(i-1))) {
//            		incrementingVals.add(i, fiveNumberSummary.get(j)); 
//            	} else if (((i > 0) && (i < incrementingVals.size()-1)) && ((fiveNumberSummary.get(j) > incrementingVals.get(i-1)) && (fiveNumberSummary.get(j) < incrementingVals.get(i+1)))) {
//            		incrementingVals.add(i, fiveNumberSummary.get(j)); 
//            	}
//            }
//        }
        // now the 5-number summary is placed in the correct indexes in incrementingVals 
        
        // now, need to transfer 5-number summary information to drawValuesTracker 
        for (int j = 0; j < fiveNumberSummary.size(); j++) {
        	for (int i = 0; i < incrementingVals.size(); i++) {
            	if ((i == 0) && (fiveNumberSummary.get(j) < incrementingVals.get(i+1))) {
            		drawValuesTracker.add(i, new DrawValues(fiveNumberSummary.get(j), drawValuesTracker.get(i+1).getXLoc()-5, 50)); 
            	} else if ((i == incrementingVals.size()-1) && (fiveNumberSummary.get(j) > incrementingVals.get(i-1))) {
            		drawValuesTracker.add(i, new DrawValues(fiveNumberSummary.get(j), drawValuesTracker.get(i-1).getXLoc()+5, 50)); 
            	} else if (((i > 0) && (i < incrementingVals.size()-1)) && ((fiveNumberSummary.get(j) > incrementingVals.get(i-1)) && (fiveNumberSummary.get(j) < incrementingVals.get(i+1)))) {
            		int temp = ((drawValuesTracker.get(i+1).getXLoc()) + (drawValuesTracker.get(i-1).getXLoc())) / 2; 
            		drawValuesTracker.add(i, new DrawValues(fiveNumberSummary.get(j), temp, 50)); 
            	}
            }
        }
        
        
        //for (int j = 0; j < )
        
        g.setColor(Color.blue); 
        
        for (int i = drawValuesTracker.size()-1; i >= 0; i--) {
        	if ((drawValuesTracker.get(i).getValue() != q1) || (drawValuesTracker.get(i).getValue() != median) || (drawValuesTracker.get(i).getValue() != q3)) {
        		drawValuesTracker.remove(i); 
        	}
        }
        
        for (int i = 0; i < drawValuesTracker.size(); i++) {
        	g.fillRect(drawValuesTracker.get(i).getXLoc(), drawValuesTracker.get(i).getYLoc(), 3, 10); 
        }
		
	}

	public Boxplot(double[] data) {
		inputData = data; 
		JFrame f = new JFrame("Boxplot");
		f.setSize(new Dimension(frameWidth, frameHeight));
		f.setBackground(Color.black); 
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long before = System.nanoTime();
		System.out.println("Boxplot: "); 
///////////////////////////////////////////////////////////////
		
		// enter your data here! 
		double[] data = {1,2,3}; 
		
///////////////////////////////////////////////////////////////
		
		Boxplot boxplot = new Boxplot(data);
		
		System.out.println(); 
		System.out.println("-------");  
		System.out.println(); 
		long after = System.nanoTime();
		System.out.println("Time for completion: " + (after - before) + " nanoseconds"); 
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Timer t;
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

}
