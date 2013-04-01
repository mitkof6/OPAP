package gui;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * About frame.
 * 
 * @author STANEV DIMITAR
 */
public class Credits extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JLabel[] message = new JLabel[6];
	
	/**
	 * Constructor
	 */
	public Credits(){
		super("Credits");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(6, 1, 1, 1));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconImage.gif")));
		
		
		//Message
		message[0] = new JLabel("        Opap Statistics v1.3   ");
		message[1] = new JLabel("");
		message[2] = new JLabel("        All Rights Preserved   ");
		message[3] = new JLabel("         © CopyRights 2012    ");
		message[4] = new JLabel("");
		message[5] = new JLabel(" E-Mail: jimstanev@gmail.com ");
		for(int i = 0;i<6;i++){
			add(message[i]);
		}
	}
}
