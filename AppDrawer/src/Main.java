import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	static JFrame mainFrame;
	static GridLayout gridLayout;

	static String softwareName, softwareLocation, softwareIcon;
	static Image img = null;
	static Scanner input;

	public static void main(String[] args) {

		setLookAndFeel();

		mainFrame = new JFrame("Softwares");
		JPanel softwarePanel = new JPanel();
		gridLayout = new GridLayout(2, 4, 15, 15);
		softwarePanel.setLayout(gridLayout);
		softwarePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		mainFrame.add(softwarePanel, BorderLayout.CENTER);

		try {
			input = new Scanner(new File("C:\\Users\\Matan\\AppData\\Roaming\\AppDrawer\\softwareinfo.txt"));
		} catch (FileNotFoundException e1) {
		}
		while (input.hasNextLine()) {
			softwareName = input.nextLine();
			softwareLocation = input.nextLine();
			softwareIcon = input.nextLine();

			File file = new File("C:\\Users\\Matan\\AppData\\Roaming\\AppDrawer\\images\\" + softwareIcon);
			try {
				img = ImageIO.read(file).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			} catch (IOException e) {
			}
			input.nextLine();

			SoftWare software = new SoftWare(new ImageIcon(img), softwareName, softwareLocation);
			software.setPreferredSize(new Dimension(150, 150));
			software.addActionListener(new AL(software));
			software.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					// software.setBackground(Color.GREEN);
					software.setBorder(BorderFactory.createLineBorder(Color.black));
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					// .setBackground(UIManager.getColor("control"));
					software.setBorder(BorderFactory.createEmptyBorder());
				}
			});

			software.setFocusPainted(false);
			softwarePanel.add(software);
		}

		// mainFrame.setUndecorated(true);

		mainFrame.setLocationRelativeTo(null);
		mainFrame.setBackground(Color.WHITE);
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
			e.printStackTrace();
		}
	}

	static class AL implements ActionListener {
		SoftWare software;

		public AL(SoftWare software) {
			this.software = software;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			software.runSoftware();
			System.out.println("asas");

		}

	}
}