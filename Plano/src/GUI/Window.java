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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.text.DefaultEditorKit;
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
	private boolean isCartesianCoordinates, isCartesianPlane;
	char xSign, ySign;

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
		planeButton.setBounds(520, 25, 130, 25);
		panel.add(planeButton);
		isCartesianPlane = true;

		Button coordinatesButton = new Button("Cartesian Coordinates");
		coordinatesButton.setBounds(12, 25, 190, 25);//(210, 25, 190, 25);
		panel.add(coordinatesButton);
		isCartesianCoordinates = true;

		xSign = '+';
		ySign = '+';
		Label coordinateLabel = new Label("( " + xSign + "X , " + ySign + "Y )");
		coordinateLabel.setAlignment(Label.CENTER);
		coordinateLabel.setBounds(210, 3, 85, 21);
		panel.add(coordinateLabel);

		Label scaleLabel = new Label("Scale");
		scaleLabel.setAlignment(Label.CENTER);
		scaleLabel.setBounds(650, 5, 120, 21);
		panel.add(scaleLabel);

		JSlider slider = new JSlider(1,4,1);
		slider.setBounds(655, 30, 120, 16);
		panel.add(slider);

		Button enterButton = new Button("Enter");
		enterButton.setBounds(210, 50, 85, 19);
		panel.add(enterButton);


		Button clearButton = new Button("Clear");
		clearButton.setBounds(320, 27, 86, 23);
		panel.add(clearButton);

		Button originButton = new Button("New Trace");
		originButton.setBounds(405, 27, 86, 23);
		panel.add(originButton);

		MaskFormatter format = new MaskFormatter();
		format.setMask("( ## , ## )");
		format.setPlaceholderCharacter('0');
		JFormattedTextField formattedTextField = new JFormattedTextField(format);
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(210, 27, 85, 19);
		panel.add(formattedTextField);

		MaskFormatter formatPolar = new MaskFormatter();
		formatPolar.setMask("( ## , ### )");
		formatPolar.setPlaceholderCharacter('0');
		JFormattedTextField formattedTextFieldPolar = new JFormattedTextField(formatPolar);
		formattedTextFieldPolar.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextFieldPolar.setBounds(210, 27, 85, 19);
		formattedTextFieldPolar.disable();
		formattedTextFieldPolar.setVisible(false);
		panel.add(formattedTextFieldPolar);

		this.plane = new Plane();
		this.plane.setBackground(Color.WHITE);
		this.plane.setBounds(0, canvasY, width, height-(canvasY*2));
		getContentPane().add(this.plane);


		/*
		 * Add Actions to Components
		 */
		coordinatesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isCartesianCoordinates) {
					coordinatesButton.setLabel("Polar Coordinates");
					coordinateLabel.setText("( r , " + ySign + "θ )");
					isCartesianCoordinates = false;
					formattedTextFieldPolar.enable();
					formattedTextFieldPolar.setVisible(true);
					formattedTextField.setVisible(false);
					formattedTextField.disable();
				}
				else {
					coordinatesButton.setLabel("Cartesian Coordinates");
					coordinateLabel.setText("( " + xSign + "X , " + ySign + "Y )");
					isCartesianCoordinates = true;
					formattedTextFieldPolar.disable();
					formattedTextFieldPolar.setVisible(false);
					formattedTextField.setVisible(true);
					formattedTextField.enable();
				}
				plane.changeCoordinate();
			}
		});

		planeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isCartesianPlane) {
					planeButton.setLabel("Polar Plane");
					isCartesianPlane = false;
				}
				else {
					planeButton.setLabel("Cartesian Plane");
					isCartesianPlane = true;
				}
				plane.changePlane();
			}
		});	

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				plane.changeScale(slider.getValue());
			}
		});
		
		formattedTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(formattedTextField.getCaretPosition() > 1 && formattedTextField.getCaretPosition() < 5) {
					if(arg0.getKeyChar() == '-') {
						xSign = '-';
					}
					else if (arg0.getKeyChar() == '+') {
						xSign = '+';
					}
				}
				if(formattedTextField.getCaretPosition() > 5 && formattedTextField.getCaretPosition() < 10) {
					if(arg0.getKeyChar() == '-') {
						ySign = '-';
					}
					else if (arg0.getKeyChar() == '+') {
						ySign = '+';

					}
				}
				coordinateLabel.setText("( " + xSign + "X , " + ySign + "Y )");
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});	
		
		formattedTextFieldPolar.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				//Only angle can be negative
				if(formattedTextField.getCaretPosition() > 5 && formattedTextField.getCaretPosition() < 10) {
					if(arg0.getKeyChar() == '-') {
						ySign = '-';
					}
					else if (arg0.getKeyChar() == '+') {
						ySign = '+';

					}
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});

		formattedTextField.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				enterCartesianCoordinate(formattedTextField.getValue());
			}

		});

		formattedTextFieldPolar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {	
				enterPolarCoordinate(formattedTextFieldPolar.getValue());
			}

		});

		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isCartesianCoordinates)
					enterCartesianCoordinate(formattedTextField.getValue());
				else
					enterPolarCoordinate(formattedTextFieldPolar.getValue());
			}
		});

		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				plane.clearAll();
			}
		});

		originButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				plane.newLine();
			}
		});


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
	}

	private void enterCartesianCoordinate(Object value) {
		if(value != null) {
			int x = Integer.parseInt(xSign+value.toString().substring(2,4));
			int y = Integer.parseInt(ySign+value.toString().substring(7,9));
			plane.addCartesianCoordinateDisplacement(x, y);
		}
	}

	private void enterPolarCoordinate(Object value) {
		if(value != null) {
			int r = Integer.parseInt(value.toString().substring(2,4));
			int O = Integer.parseInt(ySign+value.toString().substring(7,10));
			plane.addPolarCoordinateDisplacement(r, O);
		}
	}


}
