import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	static JFrame mainFrame;
	static GridLayout gridLayout;

	static String softwareName, softwareLocation, softwareIcon;
	static Image img = null;

	static Image imgItunes, imgEclipse, imgVS2019, imgCrucial, imgCcleaner, imgDefraggler;

	public static void main(String[] args) throws FileNotFoundException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		mainFrame = new JFrame("Softwares");
		gridLayout = new GridLayout(1, 5);
		mainFrame.setLayout(gridLayout);

		Scanner input = new Scanner(new File("C:\\Users\\Matan\\AppData\\Roaming\\AppDrawer\\softwareinfo.txt"));
		while (input.hasNextLine()) {
			softwareName = input.nextLine();
			softwareLocation = input.nextLine();
			softwareIcon = input.nextLine();

			File file = new File("C:\\Users\\Matan\\AppData\\Roaming\\AppDrawer\\images\\"+softwareIcon);
			try {
				img = ImageIO.read(file).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			} catch (IOException e) {
			}
			input.nextLine();

			SoftWare soft = new SoftWare(new ImageIcon(img), softwareName, softwareLocation);
			soft.addActionListener(new AL(soft));
			mainFrame.add(soft);
		}

		

		mainFrame.setLocationRelativeTo(null);
		mainFrame.setBackground(Color.WHITE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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