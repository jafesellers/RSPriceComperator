package GUI;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainGUI {
	private JFrame view;
	private Image icon = null;
	private JLabel iconImage;
	public MainGUI(){
		this.view = new JFrame("Grand Exchange");
		this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.view.setVisible(true);
	}
	public void setIcon(URL location){
		try {
			this.icon = ImageIO.read(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.icon = null;
		}
	}
	public void displayImage(){
		this.iconImage = new JLabel(new ImageIcon(this.icon));
		this.view.add(this.iconImage);
		this.view.pack();
		this.view.setVisible(true);
	}
}
