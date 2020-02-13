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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;
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

	private Plane plane;
	private boolean isCartasianCoordinates, isCartasianPlane;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Window(int width, int height) throws ParseException {
		int canvasY = 77; //this is the position in Y where the division is between plane and menu

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		panel.setBounds(0, 0, width, canvasY);
		getContentPane().add(panel);
		panel.setLayout(null);

		Button planeButton = new Button("Cartesian Plane");
		planeButton.setBounds(12, 25, 190, 25);
		panel.add(planeButton);
		isCartasianPlane = true;

		Button coordinates = new Button("Cartesian Coordinates");
		coordinates.setBounds(210, 25, 190, 25);
		panel.add(coordinates);
		isCartasianCoordinates = true;

		Label coordinateLabel = new Label("( X , Y )");
		coordinateLabel.setAlignment(Label.CENTER);
		coordinateLabel.setBounds(409, 3, 85, 21);
		panel.add(coordinateLabel);

		Label scaleLabel = new Label("Scale");
		scaleLabel.setAlignment(Label.CENTER);
		scaleLabel.setBounds(850, 3, 68, 21);
		panel.add(scaleLabel);

		JSlider slider = new JSlider(1,10,1);
		slider.setBounds(789, 34, 200, 16);
		panel.add(slider);

		Button clear = new Button("Clear");
		clear.setBounds(539, 27, 86, 23);
		panel.add(clear);

		Button reset = new Button("Reset");
		reset.setBounds(649, 27, 86, 23);
		panel.add(reset);

		MaskFormatter format = new MaskFormatter();
		format.setMask("( ### , ### )");
		format.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextField = new JFormattedTextField(format);
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(409, 46, 85, 19);
		panel.add(formattedTextField);

		this.plane = new Plane();
		this.plane.setBackground(Color.WHITE);
		this.plane.setBounds(0, canvasY, width, height-canvasY);
		getContentPane().add(this.plane);
		
		Canvas listDisplay = new Canvas();
		listDisplay.setBackground(new Color(0,0,0,40));
		listDisplay.setBounds(this.getWidth()-100, canvasY, 100, 50);
		listDisplay.setVisible(true);
		getContentPane().add(listDisplay);
		

		/*
		 * Add Actions to Components
		 */
		coordinates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isCartasianCoordinates) {
					coordinates.setLabel("Cartesian Coordinates");
					coordinateLabel.setText("( X , Y )");
					isCartasianCoordinates = false;
				}
				else {
					coordinates.setLabel("Polar Coordinates");
					coordinateLabel.setText("( r , ϴ )");
					isCartasianCoordinates = true;
				}
			}
		});
		planeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isCartasianPlane) {
					planeButton.setLabel("Cartesian Plane");
					isCartasianPlane = false;
				}
				else {
					planeButton.setLabel("Polar Plane");
					isCartasianPlane = true;
				}
				plane.changePlane();
			}
		});	
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(slider.getValue());
				plane.changeScale(slider.getValue());
			}
		});
		formattedTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == (KeyEvent.VK_ENTER)){
					if(formattedTextField.getValue() != null) {
					int x = Integer.parseInt(formattedTextField.getValue().toString().substring(2,5));
					int y = Integer.parseInt(formattedTextField.getValue().toString().substring(8,11));
					System.out.println(x + " , " + y);
					plane.addCartesianCoordinateDisplacement(x, y);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
	}


}
