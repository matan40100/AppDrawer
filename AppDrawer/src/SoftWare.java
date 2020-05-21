import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class SoftWare extends JButton {

	String name;
	String location;
	ImageIcon icon;
	Color softwareBackground,textColor;

	public SoftWare(ImageIcon imageIcon, String name, String location, Color softwareBackground,Color textColor) {
		this.name = name;
		this.location = location;
		this.icon = imageIcon;
		this.softwareBackground = softwareBackground;
		this.textColor = textColor;

		setIcon(this.icon);

		setText("<html><center>" + this.name.replace("\\n", "<br>") + "</center></html>");
		setPreferredSize(new Dimension(150, 150));
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);
		setBackground(softwareBackground);
		setForeground(textColor);
		setFont(new Font("Arial", Font.PLAIN, 16));
		setContentAreaFilled(false);
		setOpaque(true);

	}

	public void runSoftware() {
		try {
			new ProcessBuilder(this.location).start();
			System.exit(0);
		} catch (IOException e) {
			try {
				Runtime.getRuntime().exec("powershell.exe Start-Process -FilePath '" + this.location + "'  -verb RunAs");
				System.exit(0);
			} catch (IOException e1) {

			}
		}
	}
}
