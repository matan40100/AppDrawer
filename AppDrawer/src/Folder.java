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
    
    private Border border;
    private Menu menu;

    public Folder(String name, String location, Color tileBackground, Color textColor) {
        this.name = name;
        this.location = location;
        try {
            this.icon = new ImageIcon(
                    ImageIO.read(Main.class.getResource("/folder.png")).getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        this.border = BorderFactory.createLineBorder(textColor);
        this.menu = new Menu(tileBackground, textColor);

        setName(this.name);
        setIcon(this.icon);
        setText(this.name);
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
            openFolder();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    menu.showPopup(e.getPoint(), e.getComponent());
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

    public void openFolder() {
        try {
            Desktop.getDesktop().open(new File(this.location));
        } catch (IOException e) {
        }
    }

    public void setBorderColor(Color color) {
        this.border = BorderFactory.createLineBorder(color);
    }

    public Menu getMenu() {
        return menu;
    }
}