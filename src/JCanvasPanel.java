import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class JCanvasPanel extends JPanel {

    DataManager dm;

    public JCanvasPanel(DataManager dm){

        this.dm = dm;

    }

    void drawSubdivision(Node root, Graphics2D g2){

        //Utility util = new Utility(dm);

        g2.setColor(Color.red);
        //if(root.n1 == null && root.n2 == null && root.n3 == null && root.n4 == null)
        g2.drawRect(root.rect.origin.x, root.rect.origin.y, root.rect.width, root.rect.height);

        if(root.n1 != null) //&& util.checkBlack(root.n1.rect))
            drawSubdivision(root.n1, g2);
        if(root.n2 != null) //&& util.checkBlack(root.n2.rect))
            drawSubdivision(root.n2, g2);
        if(root.n3 != null) //&& util.checkBlack(root.n3.rect))
            drawSubdivision(root.n3, g2);
        if(root.n4 != null) //&& util.checkBlack(root.n4.rect))
            drawSubdivision(root.n4, g2);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        dm.bgImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D pen = (Graphics2D) dm.bgImg.getGraphics();
        pen.setColor(Color.white);

        pen.fillRect(0,0, this.getWidth(), this.getHeight());

        pen.setColor(Color.black);

        if(dm.particles.size() > 0)
        {
            for(Particle p : dm.particles)
            {
                pen.fillOval(p.x, p.y, dm.particleSize, dm.particleSize);
            }

        }

        g2.drawImage(dm.bgImg, 0, 0, this);
        drawSubdivision(dm.rootNode, g2);

    }

    @Override
    public void repaint() {
        super.repaint();
    }

}
