package Main;

import java.awt.EventQueue;

import GUI.Window;

public class Launch {
	/*
	 * Launch Application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window(800,600);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
