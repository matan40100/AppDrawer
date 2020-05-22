import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;


public class SoftWare extends JButton {

	private String name;
	private String location;
	private ImageIcon icon;
	private Color tileBackground,textColor;
	private Border border;

	public SoftWare(ImageIcon imageIcon, String name, String location, Color softwareBackground,Color textColor) {
		this.name = name;
		this.location = location;
		this.icon = imageIcon;
		this.tileBackground = softwareBackground;
		this.textColor = textColor;
		this.border = BorderFactory.createLineBorder(this.textColor);
		
		setName(this.name);
		setIcon(this.icon);
		setText("<html><center>" + this.name.replace("\\n", "<br>") + "</center></html>");
		setBackground(this.tileBackground);
		setForeground(this.textColor);
		setPreferredSize(new Dimension(150, 150));
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);
		setFont(new Font("Arial", Font.PLAIN, 16));
		setContentAreaFilled(false);
		setOpaque(true);
		setFocusPainted(false);
		
		addActionListener(e -> {
			runSoftware();
		});

		addMouseListener(new MouseAdapter() {
		 	@Override
			public void mousePressed(MouseEvent e) {
			   if (e.getButton() == MouseEvent.BUTTON3) {
					new Menu(e.getPoint(),e.getComponent(),softwareBackground,textColor);
						
			   }
			} 

			public void mouseEntered(MouseEvent evt) {
				setBorder(border);
			}

			public void mouseExited(MouseEvent evt) {
				setBorder(BorderFactory.createEmptyBorder());
			}
		});

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
