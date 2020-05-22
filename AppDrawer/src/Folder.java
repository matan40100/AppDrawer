import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class Folder extends JButton {
    private String name;
    private String location;
    private ImageIcon icon;
    private Color tileBackground, textColor;
    private Border border;


    public Folder(String name, String location, Color softwareBackground, Color textColor) {
        this.name = name;
        this.location = location;
        try {
            this.icon = new ImageIcon(ImageIO.read(Main.class.getResource("/folder.png")).getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tileBackground = softwareBackground;
        this.textColor = textColor;
        this.border = BorderFactory.createLineBorder(this.textColor);

        setIcon(this.icon);
        setText(this.name);
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
			openFolder();
        });
        
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setBorder(border);
            }

            public void mouseExited(MouseEvent evt) {
                setBorder(BorderFactory.createEmptyBorder());
            }
        });
    }
    public void openFolder()
    {
        try {
            Desktop.getDesktop().open(new File(this.location));
        } catch (IOException e) {
        }
    }
}