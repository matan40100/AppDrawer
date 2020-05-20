import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	static JFrame mainFrame;
	static GridLayout gridLayout;

	static Image imgItunes, imgEclipse, imgVS2019, imgCrucial, imgCcleaner, imgDefraggler;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		try {
			imgItunes = ImageIO.read(Main.class.getResource("itunes.png")).getScaledInstance(90, 90,
					Image.SCALE_SMOOTH);
			imgEclipse = ImageIO.read(Main.class.getResource("eclipse.png")).getScaledInstance(90, 90,
					Image.SCALE_SMOOTH);
			imgVS2019 = ImageIO.read(Main.class.getResource("vs2019.png")).getScaledInstance(90, 90,
					Image.SCALE_SMOOTH);
			imgCrucial = ImageIO.read(Main.class.getResource("crucial.png")).getScaledInstance(90, 90,
					Image.SCALE_SMOOTH);
			imgCcleaner = ImageIO.read(Main.class.getResource("ccleaner.png")).getScaledInstance(90, 90,
					Image.SCALE_SMOOTH);
			imgDefraggler = ImageIO.read(Main.class.getResource("defraggler.png")).getScaledInstance(90, 90,
					Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainFrame = new JFrame("Softwares");
		gridLayout = new GridLayout(2, 5);
		mainFrame.setLayout(gridLayout);

		SoftWare itunes = new SoftWare(new ImageIcon(imgItunes), "iTunes", "C:\\Program Files\\iTunes\\iTunes.exe");
		SoftWare eclipse = new SoftWare(new ImageIcon(imgEclipse), "Eclipse",
				"C:\\Users\\Matan\\eclipse\\jee-2019-12\\eclipse\\eclipse.exe");
		SoftWare visualStudio = new SoftWare(new ImageIcon(imgVS2019), "Visual Studio\n2019",
				"C:\\Program Files (x86)\\Microsoft Visual Studio\\2019\\Community\\Common7\\IDE\\devenv.exe");
		SoftWare crucial = new SoftWare(new ImageIcon(imgCrucial), "Crucial Storage\n Executive",
				"C:\\Program Files\\Crucial\\Crucial Storage Executive\\StorageExecutiveClient.exe");
		SoftWare ccleaner = new SoftWare(new ImageIcon(imgCcleaner), "CCleaner",
				"C:\\Program Files\\CCleaner\\CCleaner64.exe");
		SoftWare defraggler = new SoftWare(new ImageIcon(imgDefraggler), "Defraggler",
				"C:\\Program Files\\Defraggler\\Defraggler64.exe");

		itunes.addActionListener(new AL(itunes));
		eclipse.addActionListener(new AL(eclipse));
		visualStudio.addActionListener(new AL(visualStudio));
		crucial.addActionListener(new AL(crucial));
		ccleaner.addActionListener(new AL(ccleaner));
		defraggler.addActionListener(new AL(defraggler));

		mainFrame.add(itunes);
		mainFrame.add(eclipse);
		mainFrame.add(visualStudio);
		mainFrame.add(crucial);
		mainFrame.add(ccleaner);
		mainFrame.add(defraggler);

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