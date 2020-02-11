package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.Canvas;
import javax.swing.JFormattedTextField;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.Label;
import javax.swing.JSlider;
import java.awt.Button;

/**
 * This was made using Window Builder's plug in in Eclipse IDE
 */

public class Window extends JFrame {
	private JTextField firstParameter; //this is either X or Magnitude
	private JTextField secondParameter; //this is either Y or Angle
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		int width = 1400; //width of the frame
		int height = 1200; //height of the frame
		int canvasY = 77; //this is the position in Y where the division is between plane and menu
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		panel.setBounds(0, 0, width, canvasY);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JToggleButton tglbtnCartasianCoordinates = new JToggleButton("Cartasian Coordinates");
		tglbtnCartasianCoordinates.setBounds(12, 25, 194, 25);
		tglbtnCartasianCoordinates.setHorizontalAlignment(SwingConstants.LEFT);
		tglbtnCartasianCoordinates.setSelected(true);
		panel.add(tglbtnCartasianCoordinates);
		
		JToggleButton tglbtnPolarCoordinates = new JToggleButton("Polar Coordinates");
		tglbtnPolarCoordinates.setBounds(204, 25, 162, 25);
		tglbtnPolarCoordinates.setHorizontalAlignment(SwingConstants.LEFT);
		tglbtnCartasianCoordinates.setSelected(false);
		panel.add(tglbtnPolarCoordinates);
		
		firstParameter = new JTextField();
		firstParameter.setHorizontalAlignment(SwingConstants.CENTER);
		firstParameter.setText("0");
		firstParameter.setBounds(409, 28, 42, 19);
		panel.add(firstParameter);
		firstParameter.setColumns(10);
		
		secondParameter = new JTextField();
		secondParameter.setHorizontalAlignment(SwingConstants.CENTER);
		secondParameter.setText("0");
		secondParameter.setBounds(452, 28, 42, 19);
		panel.add(secondParameter);
		secondParameter.setColumns(10);
		
		Label label = new Label("( X , Y )");
		label.setAlignment(Label.CENTER);
		label.setBounds(409, 3, 85, 21);
		panel.add(label);
		
		JSlider slider = new JSlider();
		slider.setBounds(788, 25, 200, 16);
		panel.add(slider);
		
		Label label_1 = new Label("Scale");
		label_1.setBounds(866, 3, 68, 21);
		panel.add(label_1);
		
		Button clear = new Button("Clear");
		clear.setBounds(539, 18, 86, 23);
		panel.add(clear);
		
		Button reset = new Button("Reset");
		reset.setBounds(649, 18, 86, 23);
		panel.add(reset);
		
		Canvas canvas = new Plane();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(0, canvasY, width, height-canvasY);
		getContentPane().add(canvas);
		
		/*
		 * Add Actions to the Buttons
		 */
		tglbtnCartasianCoordinates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglbtnPolarCoordinates.isSelected()) {
					tglbtnCartasianCoordinates.setSelected(true);
					tglbtnPolarCoordinates.setSelected(false);
				}
			}
		});
		tglbtnPolarCoordinates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglbtnCartasianCoordinates.isSelected()) {
					tglbtnCartasianCoordinates.setSelected(false);
					tglbtnPolarCoordinates.setSelected(true);
				}
			}
		});
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
	}
}
