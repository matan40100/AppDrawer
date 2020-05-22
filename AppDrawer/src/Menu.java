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
    public Menu(Point point, Component component, Color background, Color textColor) {

        deleteItem = new JMenuItem("Delete");
        deleteItem.setFont(new Font("Arial", Font.BOLD, 16));
        deleteItem.setOpaque(true);
        deleteItem.setBackground(background);
        deleteItem.setForeground(textColor);
        deleteItem.setMargin(new Insets(0, 0, 0, 25));
        deleteItem.addActionListener(e -> {
            try {
                deleteSoftware(component.getName(), component);
            } catch (IOException e1) {
            }
        });

        add(deleteItem);
        setBorder(BorderFactory.createLineBorder(textColor));
        show(component, (int) point.getX(), (int) point.getY());
    }
    

    public void deleteSoftware(String data, Component component) throws IOException {
        int i = 0;
        Path path = Paths.get("C:\\Users\\Matan\\AppData\\Roaming\\AppDrawer\\softwaredata.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(data)) {
                //if true - remove last app
                if(i+4 >= lines.size()) {
                    lines.remove(lines.size()-1);
                    lines.remove(lines.size()-2);
                    lines.remove(lines.size()-3);
                    lines.remove(lines.size()-4);
                }else{
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
        Main.mainFrame.pack();
    }
   
   
}
