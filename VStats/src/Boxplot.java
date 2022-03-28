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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Boxplot extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	int frameWidth = 700;
	int frameHeight = 400; 
	
	
	public void paint(Graphics g) {
		
		g.fillRect(0, 0, frameWidth, frameHeight); 
		
		g.setColor(Color.white);
		
        Font smallFont = new Font("Monospaced", Font.PLAIN, 20);
        g.setFont(smallFont);
        g.drawString("Boxplot (based on OneVar data)", 170, 30);
        
        OneVar oneVarObj = new OneVar(); 
        double median = oneVarObj.computeMedian();
        double q1 = oneVarObj.computeQuartile1();
        double q3 = oneVarObj.computeQuartile3();
        
        
		
	}

	public Boxplot(OneVar obj) {
		
		JFrame f = new JFrame("Boxplot (based on OneVar data)");
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] data = {1,2,3}; 
		OneVar myOneVarObj = new OneVar(data); 
		Boxplot boxplot = new Boxplot(myOneVarObj);
	}

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
