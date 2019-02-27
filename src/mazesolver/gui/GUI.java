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

    private boolean setStart, setEnd;
    private int lastX, lastY;
    Grid grid;
    Menu menu;

    public GUI() {
        this.setStart = false;
        this.setEnd = false;
        this.lastX = -1;
        this.lastY = -1;
        this.grid = new Grid();
        this.menu = new Menu(0, 0);

        setPreferredSize(new Dimension(MazeSolver.width, MazeSolver.height));
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(new keyboardHandler());
    }

    @Override
    public void paint(Graphics g) {
        grid.draw(g);
        menu.draw(g);
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
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
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
                    menu.toggle();
                    repaint();
                    break;
                case KeyEvent.VK_SPACE:
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
