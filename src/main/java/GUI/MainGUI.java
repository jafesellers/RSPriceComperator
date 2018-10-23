package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainGUI {
	private JFrame view;
	private List<ImageIcon> icons = new ArrayList<ImageIcon>();

	public MainGUI() {
		this.view = new JFrame("Grand Exchange");
		this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.view.setLayout(new GridLayout(7,10));
		this.view.setBounds(0, 0, 800, 1000);
		this.view.setVisible(true);
	}

	public void addLabel(ImageIcon icon) {
		this.view.add(new JLabel(icon));
		this.view.pack();
		this.view.setVisible(true);
	}
	public void addLabel(ImageIcon icon,String text) {
		JLabel label = new JLabel(text);
		label.setIcon(icon);
		this.view.add(label);
		this.view.pack();
		this.view.setVisible(true);
	}
	public void addLabel(String text) {
		JLabel label = new JLabel(text);
		this.view.add(label);
		this.view.pack();
		this.view.setVisible(true);
	}
	public void addButton(ImageIcon icon,String label) {
	this.view.add(new JButton(label,icon));
	this.view.pack();
	this.view.setVisible(true);
	}
	public void displayImage() {
		Object[] ics = icons.toArray();
		System.out.println(ics.length);
		this.view.pack();
		this.view.setVisible(true);

	}
}
