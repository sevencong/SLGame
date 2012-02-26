/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import gui.Board;
   import javax.swing.JFrame;
/**
 *
 * @author csuiei
 */
public class thing extends JFrame {

    public thing() {
        add(new Board());
        setTitle("thing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 280);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    public static void main(String[] args) {
        new thing();
    }
}
