import java.awt.BorderLayout;
import java.awt.Color;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	static String theme = "light";
	static JFrame mainFrame;
	static JPanel softwarePanel, folderPanel, titlePanel;
	static JLabel title;
	static JButton exitButton, minimizeButton, themeButton, addButton;

	static String softwareName, softwareLocation, softwareIcon;
	static String folderName, folderLocation;
	static Image icon = null;
	static Scanner inputSoftware, inputFolder;
	static Point mousePoint;
	static Color mainBackground, tileBackground, textColor;

	public static void main(String[] args) {

		setLookAndFeel();
		// Check if the folder is exists
		if (!new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\AppDrawer").exists()) {
			new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\AppDrawer").mkdir();
			new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\AppDrawer\\images").mkdir();
			try {
				new File("C:\\Users\\" + System.getProperty("user.name")
						+ "\\AppData\\Roaming\\AppDrawer\\softwaredata.txt").createNewFile();
				new File("C:\\Users\\" + System.getProperty("user.name")
						+ "\\AppData\\Roaming\\AppDrawer\\folderdata.txt").createNewFile();
			} catch (Exception e) {
			}
			try {
				FileWriter softwareFile = new FileWriter("C:\\Users\\" + System.getProperty("user.name")
						+ "\\AppData\\Roaming\\AppDrawer\\softwaredata.txt", true);

				BufferedWriter bufferWritter = new BufferedWriter(softwareFile);
				bufferWritter.write(theme);
				bufferWritter.newLine();
				bufferWritter.write("5");
				bufferWritter.close();
				softwareFile.close();

				FileWriter folderFile = new FileWriter("C:\\Users\\" + System.getProperty("user.name")
						+ "\\AppData\\Roaming\\AppDrawer\\folderdata.txt", true);

				 bufferWritter = new BufferedWriter(folderFile);
				 bufferWritter.write("5");
				 bufferWritter.close();
				 folderFile.close();


			} catch (Exception e) {

			}

		}
		try {
			inputSoftware = new Scanner(new File("C:\\Users\\" + System.getProperty("user.name")
					+ "\\AppData\\Roaming\\AppDrawer\\softwaredata.txt"));
			inputFolder = new Scanner(new File(
					"C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\AppDrawer\\folderdata.txt"));
		} catch (FileNotFoundException e1) {
		}

		readTheme();

		// Create the frame with software paenl
		mainFrame = new JFrame("Softwares");

		softwarePanel = new JPanel();
		softwarePanel.setLayout(new GridLayout(0, inputSoftware.nextInt(), 15, 15));
		softwarePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		softwarePanel.setBackground(mainBackground);

		folderPanel = new JPanel();
		folderPanel.setLayout(new GridLayout(0, inputFolder.nextInt(), 15, 15));
		folderPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		folderPanel.setBackground(mainBackground);

		mainFrame.add(softwarePanel, BorderLayout.CENTER);
		mainFrame.add(folderPanel, BorderLayout.SOUTH);

		try {
			mainFrame.setIconImage(ImageIO.read(Main.class.getResource("/icon.png")));
		} catch (IOException e2) {
		}

		// Read Softwares & Folders
		readSoftwares();
		readFolders();

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

		minimizeButton = new JButton("—");
		minimizeButton.setFont(new Font("Arial", Font.BOLD, 16));
		minimizeButton.setBackground(tileBackground);
		minimizeButton.setForeground(textColor);
		minimizeButton.setContentAreaFilled(false);
		minimizeButton.setOpaque(true);
		minimizeButton.setFocusPainted(false);
		minimizeButton.addActionListener(e -> {
			mainFrame.setState(JFrame.ICONIFIED);
		});

		themeButton = new JButton("Theme");
		themeButton.setFont(new Font("Arial", Font.BOLD, 16));
		themeButton.setBackground(tileBackground);
		themeButton.setForeground(textColor);
		themeButton.setContentAreaFilled(false);
		themeButton.setOpaque(true);
		themeButton.setFocusPainted(false);
		themeButton.addActionListener(e -> {
			try {
				inputSoftware = new Scanner(new File("C:\\Users\\" + System.getProperty("user.name")
						+ "\\AppData\\Roaming\\AppDrawer\\softwaredata.txt"));
			} catch (FileNotFoundException e2) {
			}
			theme = inputSoftware.nextLine();
			if (theme.equals("dark")) {
				try {
					replaceLine(1, "light");
				} catch (IOException e1) {
				}
				mainBackground = new Color(240, 240, 240);
				tileBackground = Color.WHITE;
				textColor = Color.BLACK;
				changeTheme(mainBackground, tileBackground, textColor);
			} else if (theme.equals("light")) {
				try {
					replaceLine(1, "dark");
				} catch (IOException e1) {
				}
				mainBackground = new Color(32, 34, 37);
				tileBackground = new Color(47, 49, 54);
				textColor = Color.WHITE;
				changeTheme(mainBackground, tileBackground, textColor);
			}
		});

		addButton = new JButton("Add");
		addButton.setFont(new Font("Arial", Font.BOLD, 16));
		addButton.setBackground(tileBackground);
		addButton.setForeground(textColor);
		addButton.setContentAreaFilled(false);
		addButton.setOpaque(true);
		addButton.setFocusPainted(false);
		addButton.addActionListener(e -> {
			new AddMenu(mainBackground, tileBackground, textColor);

		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 0;
		titlePanel.add(themeButton, c);

		c.gridx = 1;
		c.weightx = 0;
		c.insets = new Insets(0, 10, 0, 0);
		titlePanel.add(addButton, c);

		
		c.insets = new Insets(0, 0, 0, 65);
		c.gridx = 2;
		c.weightx = 3;
		titlePanel.add(title, c);

		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 3;
		c.weightx = 0;
		titlePanel.add(minimizeButton, c);

		c.gridx = 4;
		c.weightx = 0;
		titlePanel.add(exitButton, c);

		// Move window
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

		System.gc();
		mainFrame.setUndecorated(true);
		if(softwarePanel.getComponentCount() >0)
		{
			mainFrame.pack();
			
		}else{
			mainFrame.setSize(840, 500);
		}
		
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

	public static void readTheme() {
		theme = inputSoftware.nextLine();
		if (theme.equals("dark")) {
			mainBackground = new Color(32, 34, 37);
			tileBackground = new Color(47, 49, 54);
			textColor = Color.WHITE;
		} else if (theme.equals("light")) {
			mainBackground = new Color(240, 240, 240);
			tileBackground = Color.WHITE;
			textColor = Color.BLACK;
		}
	}

	public static void readSoftwares() {
		if((inputSoftware.hasNextLine()))
		{
			inputSoftware.nextLine();
		}
		
		while (inputSoftware.hasNextLine()) {
			
			softwareName = inputSoftware.nextLine();
			softwareLocation = inputSoftware.nextLine();
			softwareIcon = inputSoftware.nextLine();

			File file = new File("C:\\Users\\" + System.getProperty("user.name")
					+ "\\AppData\\Roaming\\AppDrawer\\images\\" + softwareIcon);
			try {
				icon = ImageIO.read(file).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			} catch (IOException e) {
			}

			if (inputSoftware.hasNextLine()) {
				inputSoftware.nextLine();
			}

			Software software = new Software(new ImageIcon(icon), softwareName, softwareLocation, tileBackground,
					textColor);

			softwarePanel.add(software);
		}
	}

	public static void readFolders() {
		if((inputFolder.hasNextLine()))
		{
			inputFolder.nextLine();
		}
		while (inputFolder.hasNextLine()) {
			
			folderName = inputFolder.nextLine();
			folderLocation = inputFolder.nextLine();

			if (inputFolder.hasNextLine()) {
				inputFolder.nextLine();
			}
			Folder folder = new Folder(folderName, folderLocation, tileBackground, textColor);

			folderPanel.add(folder);
		}
	}

	public static void changeTheme(Color mainBackground, Color tileBackground, Color textColor) {

		softwarePanel.setBackground(mainBackground);
		folderPanel.setBackground(mainBackground);
		themeButton.setBackground(tileBackground);
		themeButton.setForeground(textColor);
		addButton.setBackground(tileBackground);
		addButton.setForeground(textColor);
		minimizeButton.setBackground(tileBackground);
		minimizeButton.setForeground(textColor);
		titlePanel.setBackground(mainBackground);
		title.setForeground(textColor);

		for (int i = 0; i < softwarePanel.getComponentCount(); i++) {

			softwarePanel.getComponent(i).setBackground(tileBackground);
			softwarePanel.getComponent(i).setForeground(textColor);
			((Software) softwarePanel.getComponent(i)).setBorderColor(textColor);
			((Software) softwarePanel.getComponent(i)).getMenu().getDeleteItem().setBackground(tileBackground);
			((Software) softwarePanel.getComponent(i)).getMenu().getDeleteItem().setForeground(textColor);
			((Software) softwarePanel.getComponent(i)).getMenu().setBorder(BorderFactory.createLineBorder(textColor));
		}

		for (int i = 0; i < folderPanel.getComponentCount(); i++) {
			folderPanel.getComponent(i).setBackground(tileBackground);
			folderPanel.getComponent(i).setForeground(textColor);
			((Folder) folderPanel.getComponent(i)).setBorderColor(textColor);
			((Folder) folderPanel.getComponent(i)).getMenu().getDeleteItem().setBackground(tileBackground);
			((Folder) folderPanel.getComponent(i)).getMenu().getDeleteItem().setForeground(textColor);
			((Folder) folderPanel.getComponent(i)).getMenu().setBorder(BorderFactory.createLineBorder(textColor));
		}
	}

	public static void replaceLine(int lineNumber, String data) throws IOException {

		Path path = Paths.get(
				"C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\AppDrawer\\softwaredata.txt");
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		lines.set(lineNumber - 1, data);
		Files.write(path, lines, StandardCharsets.UTF_8);
	}

}