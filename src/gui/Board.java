package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import main.KeyboardInput;

import org.xml.sax.SAXException;

import quest.InvalidQuestFileException;
import quest.Quest;
import worldMap.Map;


public class Board extends javax.swing.JPanel implements ActionListener {

	
	private Map map;
	private Quest quest;
	private BufferedImage background;
        private KeyboardInput keys;
        private Timer timer;
	
	public Board() {
            keys = new KeyboardInput();
		addKeyListener(keys);
		setFocusable(true);
                
                        timer = new Timer(5, this);
        timer.start();

		
		map = new Map(575, 426, new ArrayList<int[]>());
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Quest file", "que");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		while (true) {
			if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				try {
					System.out.println("Loading from " + chooser.getSelectedFile().getName());
					quest = new Quest(chooser.getSelectedFile().getName());
					break;
				} catch (IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Invalid quest file, please don't do that again.");
				} catch (ParserConfigurationException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Invalid quest file, please don't do that again.");
				} catch (SAXException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Invalid quest file, please don't do that again.");
				} catch (InvalidQuestFileException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Invalid quest file, please don't do that again.");
				}
			} else {
				System.exit(0);
			}
		}
		try {
			background = ImageIO.read(new File("background.jpg"));
			System.out.println("Background loaded");
		} catch (IOException e) {
			System.exit(1);
		}
		
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
            System.out.println("action performed!");
		repaint();
	}
	
        @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Painting");

        Graphics2D g2d = (Graphics2D) g;
        char[][] info = map.getView();
		for (int x = 0; x < info.length; x++) {
			for (int y = 0; y < info.length; y++) {
				switch(info[x][y]) {
				case 'p':	g2d.drawImage(quest.getPartyPic(), x, y, this); break;
				case 'q':	g2d.drawImage(quest.getMarkerPic(), x, y, this); break;
				case 'r':	g2d.drawImage(quest.getMarkerPic(), x, y, this); g2d.drawImage(quest.getPartyPic(), x, y, this); break;
				default:	break;
				}
			}
		}

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
            class KeyboardInput implements KeyListener {
	
            private boolean[] direction;

            public KeyboardInput() {
                    // TODO Auto-generated constructor stub
                    direction = new boolean[4];
            }

            @Override
            public void keyPressed(KeyEvent e) {
                    // TODO Auto-generated method stub
                    switch(e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:	map.moveParty(270d, 1); System.out.println("moving DOWN"); break;
                    case KeyEvent.VK_LEFT:	map.moveParty(180d, 1); System.out.println("moving LEFT"); break;
                    case KeyEvent.VK_RIGHT:	map.moveParty(0d, 1); System.out.println("moving RIGHT"); break;
                    case KeyEvent.VK_UP:	map.moveParty(90d, 1); System.out.println("moving UP"); break;
                    default: break;
                    }
    //               System.out.println("Key pressed, moving " + e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub
                    switch(e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:	direction[0] = false; break;
                    case KeyEvent.VK_LEFT:	direction[1] = false; break;
                    case KeyEvent.VK_RIGHT:	direction[2] = false; break;
                    case KeyEvent.VK_UP:	direction[3] = false; break;
                    default: break;
                    }
            }

            public boolean[] getDirection() {
                    return direction;
            }

        @Override
        public void keyTyped(KeyEvent ke) {
             switch(ke.getKeyCode()) {
                    case KeyEvent.VK_DOWN:	map.moveParty(270d, 1); break;
                    case KeyEvent.VK_LEFT:	map.moveParty(180d, 1); break;
                    case KeyEvent.VK_RIGHT:	map.moveParty(0d, 1); break;
                    case KeyEvent.VK_UP:	map.moveParty(90d, 1); break;
                    default: break;
                    }
        }
    }
}
