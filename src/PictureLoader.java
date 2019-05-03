import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PictureLoader {
    private ArrayList<String> imgPathList;
    private int currentPos = 0;
    private int imageCount = 0;

    public PictureLoader(ArrayList<String> imgPathList) {
        if (imgPathList == null) {
            imgPathList = new ArrayList<>();
        } else {
            this.imgPathList = imgPathList;
        }
        imageCount = imgPathList.size();
    }

    public void next() {
        currentPos = (currentPos + 1) % imageCount;
    }

    public void prev() {
        int newPos = currentPos - 1;
        currentPos = newPos < 0 ? newPos + imageCount : newPos;
    }

    public BufferedImage getCurrentImage() throws IOException {
        if (imageCount == 0)
            return null;
        return ImageIO.read(new File(imgPathList.get(currentPos)));
    }
}
