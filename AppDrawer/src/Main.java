import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class Main {
	static String theme = "light";
	static JFrame mainFrame;
	static JPanel softwarePanel, titlePanel;
	static GridLayout gridLayout;
	static JLabel title;
	static JButton exitButton, themeButton;

	static String softwareName, softwareLocation, softwareIcon;
	static Image icon = null;
	static Scanner input;
	static Point mousePoint;
	static Border border;
	static Color mainBackground, softwareBackground, textColor;

	public static void main(String[] args) {

		setLookAndFeel();

		try {
			input = new Scanner(new File("C:\\Users\\Matan\\AppData\\Roaming\\AppDrawer\\softwareinfo.txt"));
		} catch (FileNotFoundException e1) {
		}

		// Read the theme of the software
		theme = input.nextLine();
		if (theme.equals("dark")) {
			border = BorderFactory.createLineBorder(Color.WHITE);
			mainBackground = new Color(32, 34, 37);
			softwareBackground = new Color(47, 49, 54);
			textColor = Color.WHITE;
		} else if (theme.equals("light")) {
			border = BorderFactory.createLineBorder(Color.BLACK);
			mainBackground = new Color(240, 240, 240);
			softwareBackground = Color.WHITE;
			textColor = Color.BLACK;
		}

		// Create the frame with software paenl
		mainFrame = new JFrame("Softwares");
		softwarePanel = new JPanel();
		gridLayout = new GridLayout(input.nextInt(), input.nextInt(), 15, 15);
		softwarePanel.setLayout(gridLayout);
		softwarePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		softwarePanel.setBackground(mainBackground);
		mainFrame.add(softwarePanel, BorderLayout.CENTER);


		try {
			mainFrame.setIconImage(ImageIO.read(Main.class.getResource("/icon.png")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// Read softwares from file
		input.nextLine();
		while (input.hasNextLine()) {
			softwareName = input.nextLine();
			softwareLocation = input.nextLine();
			softwareIcon = input.nextLine();

			File file = new File("C:\\Users\\Matan\\AppData\\Roaming\\AppDrawer\\images\\" + softwareIcon);
			try {
				icon = ImageIO.read(file).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			} catch (IOException e) {
			}
			input.nextLine();

			SoftWare software = new SoftWare(new ImageIcon(icon), softwareName, softwareLocation, softwareBackground,
					textColor);
			software.setPreferredSize(new Dimension(150, 150));
			software.setFocusPainted(false);
			// Run software on click
			software.addActionListener(e -> {
				software.runSoftware();
			});

			// Set border on hover
			software.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent evt) {
					software.setBorder(border);
				}

				public void mouseExited(MouseEvent evt) {
					software.setBorder(BorderFactory.createEmptyBorder());
				}
			});
			softwarePanel.add(software);
		}

		// Title bar
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		titlePanel.setBackground(mainBackground);
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		GridBagConstraints c = new GridBagConstraints();

		title = new JLabel("Softwares", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 16));
		title.setForeground(textColor);

		exitButton = new JButton("X");
		exitButton.setFont(new Font("Arial", Font.BOLD, 16));
		exitButton.setBackground(new Color(244, 67, 54));
		exitButton.setForeground(Color.WHITE);
		exitButton.setContentAreaFilled(false);
		exitButton.setOpaque(true);
		exitButton.setFocusPainted(false);
		exitButton.addActionListener(e -> {
			System.exit(0);
		});

		themeButton = new JButton("Theme");
		themeButton.setFont(new Font("Arial", Font.BOLD, 16));
		themeButton.setBackground(softwareBackground);
		themeButton.setForeground(textColor);
		themeButton.setContentAreaFilled(false);
		themeButton.setOpaque(true);
		themeButton.setFocusPainted(false);
		themeButton.addActionListener(e -> {
			if (theme.equals("dark")) {
				try {
					replaceLine(1, "light");
				} catch (IOException e1) {
				}
				mainFrame.dispose();
				main(null);

			} else if (theme.equals("light")) {
				try {
					replaceLine(1, "dark");
				} catch (IOException e1) {
				}
				mainFrame.dispose();
				main(null);
			}
		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
	
		c.gridx = 0;
		c.weightx = 0;
		titlePanel.add(themeButton, c);
		
		c.gridx = 1;
		c.weightx = 3;
		c.insets = new Insets(0, 0, 0, 50);
		titlePanel.add(title, c);
		
		c.insets = new Insets(0, 0, 0, 0); 
		c.gridx = 2;
		c.weightx = 0;
		c.gridwidth = 2;
		titlePanel.add(exitButton, c);
		
		//Move window
		mainFrame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mousePoint = e.getPoint();
				mainFrame.getComponentAt(mousePoint);
			}
		});
		mainFrame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				// get location of Window
				int thisX = mainFrame.getLocation().x;
				int thisY = mainFrame.getLocation().y;

				// Determine how much the mouse moved since the initial click
				int xMoved = e.getX() - mousePoint.x;
				int yMoved = e.getY() - mousePoint.y;

				// Move window to this position
				int X = thisX + xMoved;
				int Y = thisY + yMoved;
				mainFrame.setLocation(X, Y);
			}
		});
		

		mainFrame.setUndecorated(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}
	}

	public static void replaceLine(int lineNumber, String data) throws IOException {
		Path path = Paths.get("C:\\Users\\Matan\\AppData\\Roaming\\AppDrawer\\softwareinfo.txt");
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		lines.set(lineNumber - 1, data);
		Files.write(path, lines, StandardCharsets.UTF_8);
	}
}