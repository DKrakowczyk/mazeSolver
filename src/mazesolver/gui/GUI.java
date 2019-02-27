/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mazesolver.grid.Grid;
import mazesolver.MazeSolver;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class GUI extends JPanel implements MouseListener, MouseMotionListener {

    private boolean setStart, setEnd, showMenu, mouseInUse, algorithmStarted;
    private int lastX, lastY;
    Grid grid;

    public GUI() {
        this.setStart = false;
        this.setEnd = false;
        this.showMenu = false;
        this.mouseInUse = false;
        this.algorithmStarted = false;
        this.lastX = -1;
        this.lastY = -1;
        this.grid = new Grid();
        
        setPreferredSize(new Dimension(MazeSolver.width, MazeSolver.height));
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(new keyboardHandler());
    }

    @Override
    public void paint(Graphics g) {
        grid.draw(g);
        
        if(!showMenu){
            drawMenuHidden(g);
        } else if(showMenu) {
            drawMenu(g);
        }
    }
    
    public void drawMenuHidden(Graphics g){
        g.setColor(new Color(41, 43, 45, 160));
        g.fillRect(20, 540, 40, 40);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("(M)", 25, 567);
    }
    
    public void drawMenu(Graphics g){
        g.setColor(new Color(41, 43, 45, 160));
        g.fillRect(20, 480, 460, 100);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.PLAIN, 16));
        if(setStart)
        g.setColor(new Color(7, 101, 238));
        g.drawString("Mouse + S key: set start", 25, 500);
        g.setColor(Color.white);
        if(setEnd)
        g.setColor(new Color(238, 29, 7));
        g.drawString("Mouse + E key: set end", 25, 520);
        g.setColor(Color.white);
        if(mouseInUse)
        g.setColor(new Color(61, 201, 7));
        g.drawString("Mouse left/right: add/remove walls", 25, 540);
        g.setColor(Color.white);
        if(algorithmStarted)
        g.setColor(new Color(173, 100, 52));
        g.drawString("Space: run/stop choosen algorithm", 140, 570);
        g.setColor(Color.white);
        g.drawLine(260, 480, 260, 540);
        
        // colors todo
        g.drawString("1: A STAR", 270, 500);
        g.drawString("2: BFS (Breadth first search)", 270, 520);
        g.drawString("3: DFS (Depth first search)", 270, 540);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = (me.getX()) / MazeSolver.nodeSize;
        int y = (me.getY()) / MazeSolver.nodeSize;

        if (SwingUtilities.isLeftMouseButton(me)) {
            if (setStart) {
                grid.setStart(x, y);
            } else if (setEnd) {
                grid.setEnd(x, y);
            } else {
                grid.setWall(x, y);
            }
        } else if (SwingUtilities.isRightMouseButton(me)) {
            grid.setEmpty(x, y);
        }
        
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
         mouseInUse = true;
         this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        mouseInUse = false;
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
        mouseInUse = false;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        // Get te cursor position
        int x = (me.getX()) / MazeSolver.nodeSize;
        int y = (me.getY()) / MazeSolver.nodeSize;

        // Return if mouse is over grid
        if (me.getX() < 0 || x >= grid.getCols()) {
            return;
        }
        if (me.getY() < 0 || y >= grid.getRows()) {
            return;
        }

        // Calculate velocity of mouse
        int vX = Math.abs(x - lastX);
        int vY = Math.abs(y - lastY);

        // If distance steps by 1 node
        if (vX >= 1 || vY >= 1) {
            if (SwingUtilities.isLeftMouseButton(me)) {
                grid.setWall(x, y);
            } else if (SwingUtilities.isRightMouseButton(me)) {
                grid.setEmpty(x, y);
            }
            this.repaint();

            //Update last position
            lastX = x;
            lastY = y;
        }
        mouseInUse = true;
        
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    private class keyboardHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    setStart = true;
                    repaint();
                    break;
                case KeyEvent.VK_E:
                    setEnd = true;
                    repaint();
                    break;
                case KeyEvent.VK_M:
                    showMenu = !showMenu;
                    repaint();
                    break;
                case KeyEvent.VK_SPACE:
                    algorithmStarted = !algorithmStarted;
                    repaint();
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    setStart = false;
                    repaint();
                    break;
                case KeyEvent.VK_E:
                    setEnd = false;
                    repaint();
                    break;

            }
        }

    }

}
