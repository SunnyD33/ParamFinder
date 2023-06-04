import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.HttpResponse;


public class Main {
    public static void main(String[] args) {

        String[] category = {"Lab Conversions"};

        //Create the frame
        JFrame frame = new JFrame("Param Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

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
        JPanel panel = new JPanel();
        JLabel categoryLabel = new JLabel("Categories");
        panel.add(categoryLabel);
        panel.add(categories);

        JTextArea textArea = new JTextArea();

        frame. getContentPane().add(BorderLayout.CENTER, textArea);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, menubar);
        frame.setVisible(true);
    }
}