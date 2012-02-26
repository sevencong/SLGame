/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author csuiei
 */
public class ImagePanel extends JPanel {
    private BufferedImage image;
    
    public ImagePanel(BufferedImage image) {
        this.image = image;
    }
    
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters

    }

}
