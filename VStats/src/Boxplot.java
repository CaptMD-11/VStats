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
        
        // 5-number summary 
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
        int windowIndexer = windowIncrement/4;
        
        ArrayList<Double> incrementingVals = new ArrayList<Double>(); 
        
        Font currFont = new Font("Monospaced", Font.PLAIN, 10); 
        g.setFont(currFont); 
        
        for (double i = min; i <= max; i += numberIncrement) {
        	i = Math.round(i * 100.0) / 100.0; 
        	g.drawString((i + ""), windowIndexer, 300);
        	incrementingVals.add(i); 
        	windowIndexer += windowIncrement; 
        }
        
        // draw rectangles 
        
		
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
		
///////////////////////////////////////////////////////////////
		
		// enter your data here! 
		double[] data = {1,2,3}; 
		
///////////////////////////////////////////////////////////////
		
		Boxplot boxplot = new Boxplot(data);
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
