import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataManager {

   int width = 675;
   int height = 475;

    BufferedImage bgImg;

    Node rootNode;
    int tolerance = 25;

    int particleSize = 7;

    //======================================

    ArrayList<Particle> particles;

    public DataManager(){ particles = new ArrayList<>(); }

}
