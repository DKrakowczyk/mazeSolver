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
public class GUI extends JPanel implements MouseListener {

    Grid grid;
    private boolean setStart, setEnd;

    public GUI() {
        this.grid = new Grid();

        setPreferredSize(new Dimension(MazeSolver.width, MazeSolver.height));
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(new keyboardHandler());
        setStart = false;
        setEnd = false;
    }

    @Override
    public void paint(Graphics g) {
        grid.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = (me.getX()) / MazeSolver.nodeSize;
        int y = (me.getY()) / MazeSolver.nodeSize;

        if (setStart) {
            grid.setStart(x, y);
        } else if (setEnd) {
            grid.setEnd(x, y);
        } else {
            grid.toggleWall(x, y);
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

    private class keyboardHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    setStart = true;
                    break;
                case KeyEvent.VK_E:
                    setEnd = true;
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
                    break;
                case KeyEvent.VK_E:
                    setEnd = false;
                    break;

            }
        }

    }

}
