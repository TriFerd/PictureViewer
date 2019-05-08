import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    public static final int SCALE = 1;

    private BufferedImage image;
    private double imgRatio;

    public ImagePanel(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    public ImagePanel() {

    }

    public void setImage(BufferedImage image) {
        this.image = image;
        if (image != null)
            imgRatio = ((double) image.getWidth()) / image.getHeight();
        repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) {
            return;
        }
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double panelRatio = ((double) panelWidth) / panelHeight;

        if (imgRatio >= panelRatio) {
            imgWidth = panelWidth;
            imgHeight = (int) Math.round(imgWidth / imgRatio);
        } else {
            imgHeight = panelHeight;
            imgWidth = (int) Math.round(imgHeight * imgRatio);
        }

        g.drawImage(resize(image, imgHeight, imgWidth), panelWidth / 2 - imgWidth / 2, panelHeight / 2 - imgHeight / 2, this);

    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}