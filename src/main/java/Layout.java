import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class Layout {

    Layout() {
        String[] category = {"All", "Lab Conversions", "Parser", "Advia/Integra/ScatterGrams", "Reflab", "Robotics", "Micro",
                "Autoposting", "Olympus/bg200/clinitek", "Phoresis", "Pharmacia", "ABX/stks", "POCT", "astmGen",
                "Autodownloading", "HIS", "Interface Specific/Custom", "Multiple Categories"};

        //Create the frame
        JFrame frame = new JFrame("Param Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        //frame.setLayout(new BorderLayout(20,15));

        //Create the components of the menu
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");
        menubar.add(fileMenu);
        menubar.add(aboutMenu);

        //Add options to menu
        JMenuItem fileLocationMB = new JMenuItem("File Location");
        JMenuItem openFile = new JMenuItem("Open File");
        fileMenu.add(fileLocationMB);

        //Create category dropdown
        JComboBox<String> categories = new JComboBox<>(category);
        categories.setSelectedIndex(0);

        //Create bottom panel
        JPanel optionsPanel = new JPanel();

        JLabel categoryLabel = new JLabel("Categories");
        JButton searchButton = new JButton("Search");
        optionsPanel.add(categoryLabel);
        optionsPanel.add(categories);
        optionsPanel.add(searchButton);

        //Create search/response panel
        JPanel searchPanel = new JPanel();

        //Create text area for inputting an iparam and another for pulling the data
        //from the xls file
        JTextField searchArea = new JTextField(30);
        JTextArea responseArea = new JTextArea();
        responseArea.setSize(new Dimension(300,100));
        responseArea.setText("iparam details will display here");
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        //responseArea.setPreferredSize(new Dimension(400,50));

        searchPanel.add(searchArea);
        searchPanel.add(BorderLayout.SOUTH, responseArea);

        //Add functionality for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search query = new Search();
                query.search(searchArea.getText());
                if(query.getResponse().equals("")) {
                    responseArea.setText("iparam not found");
                    searchArea.grabFocus();
                } else {
                    responseArea.setText(query.getResponse());
                }
            }
        });

        //Add functionality for setting the file location
        fileLocationMB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDestination fd = new FileDestination();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int fileLocation = fileChooser.showOpenDialog(frame);

                if (fileLocation == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    fd.setFileDestination(file.toString());

                    showMessageDialog(null, "File changed!");

                    searchArea.setText("");
                    responseArea.setText("iparam details will display here");
                    searchArea.requestFocus();
                }
            }
        });

        //Add functionality for opening the file
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDestination fd = new FileDestination();
                File file = new File(fd.getFileDestination());

                //first check if Desktop is supported by Platform or not
                if(!Desktop.isDesktopSupported()){
                    System.out.println("Desktop is not supported");
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

        frame.getContentPane().add(BorderLayout.SOUTH, optionsPanel);
        frame.getContentPane().add(BorderLayout.NORTH, menubar);
        frame.getContentPane().add(BorderLayout.CENTER,searchPanel);
        frame.getRootPane().setDefaultButton(searchButton);
        frame.setVisible(true);
    }
}
