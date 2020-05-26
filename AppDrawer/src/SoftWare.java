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


public class Software extends JButton {

	private String name;
	private String location;
	private ImageIcon icon;
	
	private Border border;
	private Menu menu;

	public Software(ImageIcon imageIcon, String name, String location, Color tileBackground,Color textColor) {
		this.name = name;
		this.location = location;
		this.icon = imageIcon;
		
		this.border = BorderFactory.createLineBorder(textColor);
		this.menu = new Menu(tileBackground,textColor);

		setName(this.name);
		setIcon(this.icon);
		setText("<html><center>" + this.name.replace("\\n", "<br>") + "</center></html>");
		setBackground(tileBackground);
		setForeground(textColor);
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
					menu.showPopup(e.getPoint(),e.getComponent());
						
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
	public void setBorderColor(Color color)
    {
        this.border = BorderFactory.createLineBorder(color);
    }

	public Menu getMenu() {
		return menu;
	}	
}
