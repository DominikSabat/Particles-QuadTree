import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainWind extends JFrame{

    private JPanel panel1;
    private JCanvasPanel canvas;

    DataManager dm;
    Utility util;

    public MainWind(String title){

        super(title);

        dm = new DataManager();
        util = new Utility(dm);

        //==============================================================================================================

        try {
            BufferedImage bg = ImageIO.read(new File("bg.jpg"));
            dm.bgImg = bg;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //==============================================================================================================

        canvas = new JCanvasPanel(dm);

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        panel1.add(BorderLayout.CENTER, canvas);

        //==============================================================================================================

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);

        this.setSize(new Dimension(750, 575));
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args){

        MainWind mw = new MainWind("QuadTreeMesh Generator for Particles");
        mw.setVisible(true);

        Utility util = new Utility(mw.dm);

        util.generateParticles(500);

        mw.canvas.repaint();

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                for (Particle p : mw.dm.particles)
                    util.moveParticle(p);

                util.constructQT();

                mw.canvas.repaint();


            }
        }, 0, 30, TimeUnit.MILLISECONDS);
    }

}
