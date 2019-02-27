package mazesolver;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Dawid
 */
public class Maze extends JPanel implements MouseListener {

    // Window params
    public Frame frame;
    public final int WIDTH = 900;
    public final int HEIGHT = 600;
    public final int NODE_SIZE = 25;

    // Keyboard input handler
    private keyboardHandler key;

    // Node
    private Node startNode;
    private Node endNode;
    private ArrayList<Node> wallNodes;

    // Booleans for selecting walls to place
    private boolean placeStart;
    private boolean placeEnd;
    private boolean removeWall;
    private boolean placeWall;
    public Maze() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(new keyboardHandler());
        wallNodes = new ArrayList<>();

        placeStart = false;
        placeEnd = false;
        removeWall = false;
        placeWall = false;
    }

    public void paint(Graphics g) {
        // Clear
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // Fill background
        g.setColor(new Color(4607579));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw start node
        if (startNode != null) {
            g.setColor(new Color(5891392)); // hex green
            startNode.draw(g);
        }

        // Draw end node
        if (endNode != null) {
            g.setColor(new Color(13190714)); // hex red
            endNode.draw(g);
        }

        // Draw wall nodes
        if (wallNodes.size() > 0) {
            for (Node n : wallNodes) {
                g.setColor(new Color(1052945));
                n.draw(g);
            }
        }

        //-------------DRAW-X-AXIS-GRID-------------
        g.setColor(new Color(1052945));
        for (int i = 0; i < WIDTH / NODE_SIZE; i++) {
            g.drawLine(i * NODE_SIZE, 0, i * NODE_SIZE, HEIGHT);
        }
        //-------------DRAW-Y-AXIS-GRID-------------
        for (int i = 0; i < HEIGHT / NODE_SIZE; i++) {
            g.drawLine(0, i * NODE_SIZE, WIDTH, i * NODE_SIZE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (MouseInfo.getPointerInfo().getLocation().x) / NODE_SIZE;
        int y = (MouseInfo.getPointerInfo().getLocation().y) / NODE_SIZE;

        if (placeStart) {
            startNode = new Node(x, y - 1, NODE_SIZE);
            this.repaint();
        }

        if (placeEnd) {
            endNode = new Node(x, y - 1, NODE_SIZE);
            this.repaint();
        }
        
        if (removeWall) {
            Node n = new Node(x, y - 1, NODE_SIZE);
            if(wallNodes.size() > 0) {
                for(Node c: wallNodes) {
                    if(n.compareNodes(c)){
                        wallNodes.remove(c);
                        this.repaint();
                    }
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(placeWall){
            if (!placeStart && !placeEnd) {
                int x = (MouseInfo.getPointerInfo().getLocation().x) / NODE_SIZE;
                int y = (MouseInfo.getPointerInfo().getLocation().y) / NODE_SIZE;

                Node n = new Node(x, y - 1, NODE_SIZE);
                if (startNode != null && endNode != null) {
                    if (!n.compareNodes(startNode) && !n.compareNodes(endNode)) {
                        wallNodes.add(n);
                        this.repaint();
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private class keyboardHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    placeStart = !placeStart;
                    placeEnd = false;
                    System.out.println("Select start node");
                    break;
                case KeyEvent.VK_E:
                    placeStart = false;
                    placeEnd = !placeEnd;
                    System.out.println("Select end node");
                    break;
                case KeyEvent.VK_R:
                    if(!placeStart && !placeEnd){
                    removeWall = !removeWall;
                    placeWall = !placeWall;
                    }
                case KeyEvent.VK_W:
                    if(!removeWall && !placeStart && !placeEnd) {
                        placeWall = true;
                    }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}
