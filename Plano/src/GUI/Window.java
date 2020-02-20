package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;

import java.awt.Color;
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
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.MaskFormatter;


import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.PopupMenu;

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
		panel.setBackground(new Color(70, 162, 195));
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
		slider.setBackground(new Color(65, 150, 185));
		panel.add(slider);

		Button enterButton = new Button("Enter");
		enterButton.setBounds(210, 50, 85, 19);
		panel.add(enterButton);

		Button clearButton = new Button("Clear all");
		clearButton.setBounds(320, 10, 86, 23);
		panel.add(clearButton);

		Button originButton = new Button("New Trace");
		originButton.setBounds(405, 10, 86, 23);
		panel.add(originButton);

		Button settingButton = new Button("Setting");
		settingButton.setBounds(405, 35, 86, 23);
		panel.add(settingButton);

		Button helpButton = new Button("Help");
		helpButton.setBounds(320, 35, 86, 23);
		panel.add(helpButton);

		MaskFormatter formatCartesian = new MaskFormatter();
		formatCartesian.setMask("(## , ##)");
		formatCartesian.setPlaceholderCharacter('0');
		JFormattedTextField formattedTextFieldCartesian = new JFormattedTextField(formatCartesian);
		formattedTextFieldCartesian.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextFieldCartesian.setBounds(210, 27, 85, 19);
		panel.add(formattedTextFieldCartesian);

		MaskFormatter formatPolar = new MaskFormatter();
		formatPolar.setMask("(## , ###)");
		formatPolar.setPlaceholderCharacter('0');
		JFormattedTextField formattedTextFieldPolar = new JFormattedTextField(formatPolar);
		formattedTextFieldPolar.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextFieldPolar.setBounds(210, 27, 85, 19);
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
					formattedTextFieldPolar.setVisible(true);
					formattedTextFieldCartesian.setVisible(false);
				}
				else {
					coordinatesButton.setLabel("Cartesian Coordinates");
					coordinateLabel.setText("( " + xSign + "X , " + ySign + "Y )");
					isCartesianCoordinates = true;
					formattedTextFieldPolar.setVisible(false);
					formattedTextFieldCartesian.setVisible(true);
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

		formattedTextFieldCartesian.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(formattedTextFieldCartesian.getCaretPosition() > 0 && formattedTextFieldCartesian.getCaretPosition() < 5) {
					if(arg0.getKeyChar() == '-') {
						if(xSign == '-')
							xSign = '+';
						else
							xSign = '-';
					}
					else if (arg0.getKeyChar() == '+') {
						xSign = '+';
					}
				}
				if(formattedTextFieldCartesian.getCaretPosition() > 5 && formattedTextFieldCartesian.getCaretPosition() < 10) {
					if(arg0.getKeyChar() == '-') {
						if(ySign == '-')
							ySign = '+';
						else
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
				if(arg0.getKeyCode() == arg0.VK_ENTER) {
					enterCartesianCoordinate(formattedTextFieldCartesian.getValue());
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});	

		formattedTextFieldPolar.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				//Only angle can be negative
				if(formattedTextFieldPolar.getCaretPosition() > 5 && formattedTextFieldPolar.getCaretPosition() < 11) {
					if(arg0.getKeyChar() == '-') {
						if(ySign == '-')
							ySign = '+';
						else
							ySign = '-';
					}
					else if (arg0.getKeyChar() == '+') {
						ySign = '+';
					}
				}
				coordinateLabel.setText("( r , " + ySign + "θ )");
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == arg0.VK_ENTER) {
					enterPolarCoordinate(formattedTextFieldPolar.getValue());
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});

		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isCartesianCoordinates)
					enterCartesianCoordinate(formattedTextFieldCartesian.getValue());
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

		settingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				settingScreen(e.getX()+settingButton.getX(), e.getY()+settingButton.getY()+canvasY);
			}
		});

		helpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				helpScreen();
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
	}

	private void enterCartesianCoordinate(Object value) {
		if(value != null) {
			int x = Integer.parseInt(xSign+value.toString().substring(1,3));
			int y = Integer.parseInt(ySign+value.toString().substring(6,8));
			plane.addCartesianCoordinateDisplacement(x, y);
		}
	}

	private void enterPolarCoordinate(Object value) {
		if(value != null) {
			int r = Integer.parseInt(value.toString().substring(1,3));
			int O = Integer.parseInt(ySign+value.toString().substring(6,9));
			plane.addPolarCoordinateDisplacement(r, O);
		}
	}

	private void settingScreen(int x, int y) {
		PopupMenu setting = new PopupMenu();

		MenuItem clear = new MenuItem("clear current line");
		MenuItem cpColor = new MenuItem("change current point Color");
		MenuItem clColor = new MenuItem("change current line Color");
		MenuItem ppColor = new MenuItem("change previous point Color");
		MenuItem plColor = new MenuItem("change previous line Color");

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.clear();
			}
		});

		cpColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"currentPoint");
			}
		});

		clColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"currentLine");
			}
		});

		ppColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"previousPoint");
			}
		});

		plColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"previousLine");
			}
		});

		setting.add(cpColor);
		setting.add(clColor);
		setting.add(ppColor);
		setting.add(plColor);
		setting.add(clear);

		this.add(setting);
		setting.show(this, x, y);
	}

	private void colorPopup(int x, int y, String component) {
		PopupMenu ColorPopup = new PopupMenu();

		MenuItem magenta = new MenuItem("Magenta");
		MenuItem green = new MenuItem("Green");
		MenuItem red = new MenuItem("Red");
		MenuItem blue = new MenuItem("Blue");


		magenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.currentPointColor(Color.MAGENTA); 
				else if(component.equals("currentLine"))
					plane.currentLineColor(Color.MAGENTA);
				else if(component.equals("previousPoint"))
					plane.previousPointColor(Color.MAGENTA);
				else if(component.equals("previousLine"))
					plane.previousLineColor(Color.MAGENTA);
			}
		});

		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color green = new Color(20,198,5); //Green
				if(component.equals("currentPoint"))
					plane.currentPointColor(green); 
				else if(component.equals("currentLine"))
					plane.currentLineColor(green);
				else if(component.equals("previousPoint"))
					plane.previousPointColor(green);
				else if(component.equals("previousLine"))
					plane.previousLineColor(green);
			}
		});

		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.currentPointColor(Color.RED); 
				else if(component.equals("currentLine"))
					plane.currentLineColor(Color.RED);
				else if(component.equals("previousPoint"))
					plane.previousPointColor(Color.RED);
				else if(component.equals("previousLine"))
					plane.previousLineColor(Color.RED);
			}
		});

		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.currentPointColor(Color.BLUE); 
				else if(component.equals("currentLine"))
					plane.currentLineColor(Color.BLUE);
				else if(component.equals("previousPoint"))
					plane.previousPointColor(Color.BLUE);
				else if(component.equals("previousLine"))
					plane.previousLineColor(Color.BLUE);
			}
		});

		ColorPopup.add(blue);
		ColorPopup.add(red);
		ColorPopup.add(green);
		ColorPopup.add(magenta);
		this.add(ColorPopup);
		ColorPopup.show(this, x, y);
	}

	private void helpScreen() {
		String Instructions = "Help \n Instructions:";
		JOptionPane.showMessageDialog(this, Instructions);
	}


}
