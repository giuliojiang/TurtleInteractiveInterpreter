package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class ImagePanel extends JPanel
{

    private BufferedImage img;
    
    public ImagePanel()
    {
    }
    
    public void setImg(BufferedImage img)
    {
        this.img = img;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(500, 500);
    }
    
    public void paintPaper()
    {
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        // Draw Text
//        g.drawString("This is my custom Panel!", 10, 20);
    }
}
