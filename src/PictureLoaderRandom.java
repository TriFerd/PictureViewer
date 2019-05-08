import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class PictureLoaderRandom extends PictureLoader {

    private ArrayList<Integer> pos = new ArrayList<>();

    private final long baseSeed = System.currentTimeMillis();

    private int index = 0;

    public PictureLoaderRandom(ArrayList<String> imgPathList) {
        super(imgPathList);
    }

    public void nextRandom() {
        index++;
        currentPos = indexFromSeed(index);
    }

    public void prevRandom() {
        index = index > 0 ? index - 1 : 0;
        currentPos = indexFromSeed(index);
    }

    private int indexFromSeed(int input) {
        Random r = new Random(baseSeed + input * 100000);
        return r.nextInt(imageCount);
    }

    @Override
    public void setImgPathList(ArrayList<String> imgPathList) {
        super.setImgPathList(imgPathList);
        index = 0;
        currentPos = 0;
    }
}
