import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

public class DrawPanel extends JPanel {

    private int WIDTH = 1280;
    private int HEIGHT = WIDTH * 9 / 16;
    private int NUMBER_OF_BARS = 200;
    private double BAR_WIDTH = (double) WIDTH / NUMBER_OF_BARS;
    private int[] array;

    public void setHeightOfArray(){
        this.array = new int[NUMBER_OF_BARS];
        int random;
        for(int i = 0; i < NUMBER_OF_BARS; i++){
            random = (int) (Math.random() * (HEIGHT - 1)) + 1;
            array[i] = random;
        }
    }

    public int[] getArray(){
        return array;
    }

@Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(new Color(112,128,144));
        for(int i = 0; i < array.length; i++){
            Rectangle2D.Double rect = new Rectangle2D.Double(i * BAR_WIDTH, 0, BAR_WIDTH, array[i]);
            g2d.draw(rect);
            g2d.fill(rect);
        }
        repaint();
  }



}
