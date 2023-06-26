import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class About {

    About() {
        JFrame aboutFrame = new JFrame("About");
        aboutFrame.setDefaultCloseOperation(aboutFrame.DISPOSE_ON_CLOSE);
        aboutFrame.setSize(700, 150);

        JPanel infoPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        //Components for the About frame
        JLabel currentFile = new JLabel("Current File");
        JTextArea currentFileName = new JTextArea(new FileDestination().getFileDestination());
        currentFileName.setEnabled(false);
        JButton openFileButton = new JButton("Open File");

        infoPanel.add(currentFile);
        infoPanel.add(currentFileName);

        buttonPanel.add(openFileButton);

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDestination fd = new FileDestination();
                File file = new File(fd.getFileDestination());

                if(!Desktop.isDesktopSupported()){
                    showMessageDialog(null,"Desktop not supported. " +
                            '\n' + "You may have to open the file manually");
                    return;
                }

                Desktop desktop = Desktop.getDesktop();
                if(file.exists()) {
                    try {
                        desktop.open(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        aboutFrame.getContentPane().add(BorderLayout.CENTER, infoPanel);
        aboutFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        aboutFrame.setVisible(true);
    }
}
