import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Surface extends JPanel{
    Manager manager;

    public Surface(Manager manager){
        this.manager=manager;
    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(new ImageIcon("blue-copy-space-digital-background_23-2148821698.jpg").getImage(),0,0,(int)InputProcessor.Yard_Width,(int)InputProcessor.Yard_Height,null);

        for(int i=0;i<manager.ballArrayList.size();i++){
            if (i%4==0){
                graphics2D.setColor(Color.YELLOW);
            } else if (i%4==1){
                graphics2D.setColor(Color.RED);
            } else if (i%4==2){
                graphics2D.setColor(Color.GREEN);
            } else if (i%4==3){
                graphics2D.setColor(Color.BLUE);
            }
            Ellipse2D.Double circle = new Ellipse2D.Double((int)manager.ballArrayList.get(i).getX_Position(),(int)manager.ballArrayList.get(i).getY_Position(),(int)manager.ballArrayList.get(i).getRadius(),(int)manager.ballArrayList.get(i).getRadius());
            graphics2D.fill(circle);
        }
    }
}