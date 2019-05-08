import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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


    private boolean isRandomized = false;

    private File currDirectory;

    private ArrayList<String> imgPathList;

    private PictureLoaderRandom pictureLoader;

    public Frame() {
        currDirectory = new File(".");
        pictureLoader = new PictureLoaderRandom(null);
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

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRandomized) {
                    pictureLoader.nextRandom();
                } else {
                    pictureLoader.next();
                }
                try {
                    imagePanel.setImage(pictureLoader.getCurrentImage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRandomized) {
                    pictureLoader.prevRandom();
                } else {
                    pictureLoader.prev();
                }
                try {
                    imagePanel.setImage(pictureLoader.getCurrentImage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        cbRandom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                isRandomized = cbRandom.isSelected();
            }
        });
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
        pictureLoader.setImgPathList(getAllPicturesFromDirectory(currDirectory));
        try {
            imagePanel.setImage(pictureLoader.getCurrentImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
