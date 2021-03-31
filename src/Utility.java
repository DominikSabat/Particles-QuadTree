import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Utility {

    DataManager dm;

    Random direction;

    public Utility(DataManager dm){

        this.dm = dm;

        direction = new Random(System.currentTimeMillis());

    }

    Boolean checkBlack(Rectangle rect) {

        Boolean isBlack = false;

        for (int wi = rect.origin.x; wi < (rect.origin.x + rect.width); wi++) {
            for (int hi = rect.origin.y; hi < (rect.origin.y + rect.height); hi++) {

                Color readColor = new Color(dm.bgImg.getRGB(wi, hi));

                if (readColor.getRed() == 0 && readColor.getGreen() == 0 && readColor.getBlue() == 0)
                    isBlack = true;
            }
        }
        return isBlack;
    }

    Boolean checkInRange(Rectangle rect, Particle pa){

        return ((pa.x > rect.origin.x) && (pa.x < rect.origin.x+rect.width) && (pa.y > rect.origin.y) && (pa.y < rect.origin.y+rect.height));

    }

    void subdivide(Node root, ArrayList<Particle> pa){

        for(int i=0;i<pa.size();i++)
        {
            if(checkInRange(root.rect,dm.particles.get(i)))
            {
                root.nodeParticles.add(pa.get(i));
            }
        }

        if(root.nodeParticles.size()>root.capacity) {

            int width = root.rect.width / 2;
            int height = root.rect.height / 2;

                Node n1 = new Node(new Rectangle(new Point(root.rect.origin.x, root.rect.origin.y), width, height), null, null, null, null);
                Node n2 = new Node(new Rectangle(new Point(root.rect.origin.x + width, root.rect.origin.y), width, height), null, null, null, null);
                Node n3 = new Node(new Rectangle(new Point(root.rect.origin.x, root.rect.origin.y + height), width, height), null, null, null, null);
                Node n4 = new Node(new Rectangle(new Point(root.rect.origin.x + width, root.rect.origin.y + height), width, height), null, null, null, null);

                root.n1 = n1;
                root.n2 = n2;
                root.n3 = n3;
                root.n4 = n4;

                subdivide(root.n1, dm.particles);
                subdivide(root.n2, dm.particles);
                subdivide(root.n3, dm.particles);
                subdivide(root.n4, dm.particles);
        }
    }

    void constructQT(){

        int width = 700;
        int height = 500;

        Rectangle initialRectangle = new Rectangle(new Point(0,0), width, height);

        dm.rootNode = new Node(initialRectangle, null, null, null, null);

        subdivide(dm.rootNode, dm.particles);


    }

    void generateParticles(int amount){

        Random rand = new Random(System.currentTimeMillis());

        for(int i = 0; i < amount; i++){

            dm.particles.add(new Particle(rand.nextInt(dm.width), rand.nextInt(dm.height)));    //add particle randomly

        }

    }

    void moveParticle(Particle p){

        int dir = direction.nextInt(4);

        switch (dir){

            case 0:
                if(p.x > 0)
                    p.x--;
                break;

            case 1:
                if(p.x < dm.width)
                    p.x++;
                break;

            case 2:
                if(p.y > 0)
                    p.y--;
                break;

            case 3:
                if(p.y < dm.height)
                    p.y++;
                break;

        }

    }

}
