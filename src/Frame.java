import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Frame extends JFrame{

    private JPanel mainPanel;
    private JPanel controlsPanel;
    private JComboBox comboBoxMode;
    private JCheckBox cbRandom;
    private JButton btnSelectFolder;
    private JButton btnPrev;
    private JButton btnNext;
    private ImagePanel imagePanel;

    private File currDirectory;

    private ArrayList<String> imgPathList;

    private PictureLoader pl;

    public Frame() {
        currDirectory = new File(".");
        pl = new PictureLoader(null);
        imagePanel = new ImagePanel();
        mainPanel.add(imagePanel);
        refreshPathList();

        // Listeners
        btnSelectFolder.addActionListener(e -> {
            File tempDir = selectDirectory();
            if (tempDir != null)
                currDirectory = tempDir;
            refreshPathList();
        });



        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        Frame f = new Frame();
    }

    private File selectDirectory() {
        JFileChooser ch = new JFileChooser(currDirectory);
        ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ch.showOpenDialog(Frame.this);
        return ch.getSelectedFile();
    }

    private static ArrayList<String> getAllPicturesFromDirectory(File dir) {
        if (!dir.isDirectory()) {
            return null;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }

        ArrayList<String> imgPathList = new ArrayList<>();
        for (File f : files) {
            if (f.isDirectory()) {
                continue;
            }
            if(f.getAbsolutePath().endsWith(".jpg") || f.getAbsolutePath().endsWith(".jpeg")
                    || f.getAbsolutePath().endsWith(".png")) {
                imgPathList.add(f.getAbsolutePath());
            }
        }
        return imgPathList;
    }

    private void refreshPathList() {
        pl.setImgPathList(getAllPicturesFromDirectory(currDirectory));
        try {
            imagePanel.setImage(pl.getCurrentImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
