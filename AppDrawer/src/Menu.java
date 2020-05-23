import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class Menu extends JPopupMenu {
    private JMenuItem deleteItem;

    public Menu(Color background, Color textColor) {

        deleteItem = new JMenuItem("Delete");
        deleteItem.setFont(new Font("Arial", Font.PLAIN, 16));
        deleteItem.setOpaque(true);
        deleteItem.setBackground(background);
        deleteItem.setForeground(textColor);
        deleteItem.setMargin(new Insets(0, 0, 0, 25));
        add(deleteItem);
        setBorder(BorderFactory.createLineBorder(textColor));
        

    }

    public void showPopup(Point point, Component component) {
        show(component, (int) point.getX(), (int) point.getY());
        deleteItem.addActionListener(e -> {
            try {
                deleteSoftware(component.getName(), component);
            } catch (IOException e1) {
            }
        });
    }

    public void deleteSoftware(String data, Component component) throws IOException {
        int i = 0;
        Path path;
        List<String> lines;
        if (component instanceof SoftWare) {
            path = Paths.get("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\AppDrawer\\softwaredata.txt");
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(data)) {
                    // if true - remove last app
                    if (i + 4 >= lines.size()) {
                        lines.remove(i);
                        lines.remove(i);
                        lines.remove(i);
                        if(lines.get(lines.size()-1).equals(""))
                        {
                            System.out.println("te");
                            lines.remove(lines.size()-1);
                        }        
                    } else {
                        lines.remove(i);
                        lines.remove(i);
                        lines.remove(i);
                        lines.remove(i);
                    }
                    break;
                }
            }
            Files.write(path, lines, StandardCharsets.UTF_8);
            Main.softwarePanel.remove(component);
            Main.softwarePanel.revalidate();
            Main.softwarePanel.repaint();

        } else {
            path = Paths.get("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\AppDrawer\\folderdata.txt");
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(data)) {
                    // if true - remove last app
                    if (i + 4 >= lines.size()) {
                        lines.remove(i);
                        lines.remove(i);

                        if(lines.get(lines.size()-1).equals(""))
                        {
                            System.out.println("te");
                            lines.remove(lines.size()-1);
                        }        
                    } else {
                        lines.remove(i);
                        lines.remove(i);
                        lines.remove(i);
                    }
                    break;
                }
            }
            Files.write(path, lines, StandardCharsets.UTF_8);
            Main.folderPanel.remove(component);
            Main.folderPanel.revalidate();
            Main.folderPanel.repaint();
        }

        Main.mainFrame.pack();

    }

    public JMenuItem getDeleteItem() {
        return deleteItem;
    }
}
