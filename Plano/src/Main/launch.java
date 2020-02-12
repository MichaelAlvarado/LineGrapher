package Main;

import java.awt.EventQueue;

import GUI.Window;

public class launch {
	/*
	 * Launch Application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window(1200,1200);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
