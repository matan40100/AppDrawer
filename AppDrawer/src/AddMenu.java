import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;

public class AddMenu extends JDialog {

    public AddMenu(Color mainBackground, Color tileBackground, Color textColor) {
        setLayout(new BorderLayout());
        getRootPane().setBorder(BorderFactory.createLineBorder(textColor));

        JPanel topPanel = new JPanel(new GridBagLayout());
        JLabel title = new JLabel("Add Software", SwingConstants.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        topPanel.setBackground(mainBackground);
        title.setFont(new Font("Arial", Font.PLAIN, 16));
        title.setForeground(textColor);

        JButton exitButton = new JButton("X");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBackground(new Color(244, 67, 54));
        exitButton.setForeground(Color.WHITE);
        exitButton.setContentAreaFilled(false);
        exitButton.setOpaque(true);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> {
            dispose();
        });

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        topPanel.add(title, c);

        c.gridx = 1;
        c.weightx = 0;
        topPanel.add(exitButton, c);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(mainBackground);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel name = new JLabel("Display name");
        JTextField displayNameField = new JTextField(20);
        name.setFont(new Font("Arial", Font.PLAIN, 16));
        name.setForeground(textColor);
        displayNameField.setBackground(tileBackground);
        displayNameField.setForeground(textColor);
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(0, 0, 5, 0);
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        centerPanel.add(name, c);

        c.gridy = 1;
        c.gridx = 0;
        c.weightx = 1;
        centerPanel.add(displayNameField, c);

        c.insets = new Insets(10, 0, 0, 0);

        JLabel location = new JLabel("Location");
        JTextField locationField = new JTextField(20);
        location.setFont(new Font("Arial", Font.PLAIN, 16));
        location.setForeground(textColor);
        locationField.setBackground(tileBackground);
        locationField.setForeground(textColor);
        c.insets = new Insets(10, 0, 0, 0);
        c.gridy = 2;
        c.gridx = 0;
        c.weightx = 1;
        centerPanel.add(location, c);

        c.insets = new Insets(5, 0, 0, 0);
        c.gridy = 3;
        c.gridx = 0;
        c.weightx = 1;
        centerPanel.add(locationField, c);

        c.insets = new Insets(10, 0, 0, 0);

        JButton iconChooser = new JButton("Choose icon");
        iconChooser.setFont(new Font("Arial", Font.PLAIN, 16));
        iconChooser.setBackground(tileBackground);
        iconChooser.setForeground(textColor);
        iconChooser.setContentAreaFilled(false);
        iconChooser.setOpaque(true);
        iconChooser.setFocusPainted(false);
        c.gridy = 4;
        c.gridx = 0;
        c.weightx = 1;
        centerPanel.add(iconChooser, c);

        iconChooser.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog((Frame) null, "Select image to add");
            fileDialog.setVisible(true);

            try {
                Files.move(Paths.get(fileDialog.getDirectory() + fileDialog.getFile()),
                        Paths.get("C:\\Users\\" + System.getProperty("user.name")
                                + "\\AppData\\Roaming\\AppDrawer\\images\\" + fileDialog.getFile()));
            } catch (IOException e1) {
            }

            try {
                FileWriter fileWritter = new FileWriter("C:\\Users\\" + System.getProperty("user.name")
                        + "\\AppData\\Roaming\\AppDrawer\\softwaredata.txt", true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.newLine();
                bufferWritter.write(displayNameField.getText());
                bufferWritter.newLine();
                bufferWritter.write(locationField.getText());
                bufferWritter.newLine();
                bufferWritter.write(fileDialog.getFile());
                bufferWritter.newLine();
                bufferWritter.close();
                fileWritter.close();
            } catch (Exception e2) {
            }
            Image icon = null;
            try {
                icon = ImageIO
                        .read(new File("C:\\Users\\" + System.getProperty("user.name")
                                + "\\AppData\\Roaming\\AppDrawer\\images\\" + fileDialog.getFile()))
                        .getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            } catch (IOException e1) {
            }

            SoftWare software = new SoftWare(new ImageIcon(icon), displayNameField.getText(), locationField.getText(),
                    tileBackground, textColor);
            Main.softwarePanel.add(software);
            Main.softwarePanel.revalidate();
            Main.softwarePanel.repaint();
            Main.mainFrame.pack();
            dispose();

        });
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}