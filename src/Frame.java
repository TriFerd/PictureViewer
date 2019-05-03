import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame{

    private JPanel mainPanel;
    private JPanel controlsPanel;
    private JPanel picturePanel;
    private JComboBox comboBoxMode;
    private JCheckBox cbRandom;
    private JButton btnSelectFolder;
    private JButton btnPrev;
    private JButton btnNext;

    public Frame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        Frame f = new Frame();
    }
}
