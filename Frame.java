package billeterie;
import javax.swing.*;

public class Frame extends JFrame {
	Panel pan = new Panel();
	
	public Frame () {
		 this.setTitle("Billeterie");
		    this.setSize(1000, 625);
		    this.setLocationRelativeTo(null);
		    this.setContentPane(pan);
		    this.setBounds(100, 100, 730, 489);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.getContentPane().setLayout(null);					
		
		    this.setVisible(true);
	}
	
	public static void main(String[]args ) {
		Frame f = new Frame();
	}
}