package mountainrange;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class MountainRange extends JComponent
{

    final int SLOTS = 1024;
    int[] heights = new int[SLOTS + 1];
    Random r = new Random();

    public static void main(String[] args)
    {
        MountainRange game = new MountainRange();
        JFrame window = new JFrame("Mountain Range");
        window.add(game);
//        window.addMouseListener(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MountainRange()
    {
        for (int i = 0; i < heights.length; i++)
        {
            heights[i] = -1;
        }

        heights[0] = r.nextInt(600);
        heights[SLOTS / 2] = r.nextInt(600);
        heights[SLOTS] = r.nextInt(600);

        int range = 400;
        for (int jump = SLOTS / 4; jump >= 1; jump /= 2)
        {

            for (int i = 0; i < SLOTS; i += jump)
            {
                
                if (heights[i] == -1)
                {
                    
                    final int average = (heights[i - jump] + heights[i + jump]) / 2;
                    heights[i] = average + r.nextInt(2 * range) - range;
                    
                }
            }
            
            range = (int) (range *.5);
            if (range <= 0) {
                range = 1;
            }

        }

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(SLOTS + 1, 600);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.black);
        int lastX = 0;
        int lastHeight = heights[0];
        for (int x = 0; x <= SLOTS; x++)
        {
            int height = heights[x];
            int y = 600 - 1 - height;

//            g.drawLine(x, 600 - 1 - height, lastX, 600 - 1 - lastHeight);
            g.drawLine(x, 600, x, y);

            lastX = x;
            lastHeight = height;
        }
    }
}
