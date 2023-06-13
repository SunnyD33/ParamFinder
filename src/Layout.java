import javax.swing.*;
import java.awt.*;

public class Layout {

    Layout() {
        String[] category = {"All", "Lab Conversions", "Parser", "Advia/Integra/ScatterGrams", "Reflab", "Robotics", "Micro",
                "Autoposting", "Olympus/bg200/clinitek", "Phoresis", "Pharmacia", "ABX/stks", "POCT", "astmGen",
                "Autodownloading", "HIS", "Interface Specific/Custom", "Multiple Categories"};

        //Create the frame
        JFrame frame = new JFrame("Param Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(425, 250);
        //frame.setLayout(new BorderLayout(20,15));

        //Create the components of the frame
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        JMenuItem fileItem1 = new JMenuItem("File Location");
        fileMenu.add(fileItem1);

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
        JLabel responseArea = new JLabel();
        responseArea.setText("Iparam response");

        searchPanel.add(searchArea);
        searchPanel.add(BorderLayout.SOUTH, responseArea);

        frame.getContentPane().add(BorderLayout.SOUTH, optionsPanel);
        frame.getContentPane().add(BorderLayout.NORTH, menubar);
        frame.getContentPane().add(BorderLayout.CENTER,searchPanel);
        frame.setVisible(true);
    }
}
