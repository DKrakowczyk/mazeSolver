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
import mazesolver.generator.GeneratorFactory;
import mazesolver.generator.IGenerator;
import mazesolver.solver.SolverFactory;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class GUI extends JPanel implements MouseListener, MouseMotionListener {

    static boolean setStart, setEnd, mouseInUse, running;
    private int lastX, lastY;
    Grid grid;
    Menu menu;

    public GUI() {
        this.setStart = false;
        this.setEnd = false;
        mouseInUse = false;
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
        if (!running) {
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
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (!running) {
            mouseInUse = true;
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        mouseInUse = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (!running) {
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
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    private class keyboardHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            // If user press key
            switch (e.getKeyCode()) {
                // Start placing
                case KeyEvent.VK_S:
                    if (!running) {
                        setStart = true;
                        repaint();
                    }
                    break;
                // End placing
                case KeyEvent.VK_E:
                    if (!running) {
                        setEnd = true;
                        repaint();
                    }
                    break;
                // Menu toggling
                case KeyEvent.VK_M:
                    menu.toggle();
                    repaint();
                    break;
                // Grid clearing
                case KeyEvent.VK_C:
                    if (!running) {
                        grid.erase();
                        repaint();
                    }
                    break;
                // Maze randomizing
                case KeyEvent.VK_R:
                    if (!running) {
                        IGenerator generator = GeneratorFactory.getGenerator(grid);
                        generator.generate();
                        repaint();
                    }
                    break;
                // Solver run/stop
                case KeyEvent.VK_SPACE:
                    running = !running;
                    break;

                // Solver choose
                case KeyEvent.VK_1:
                    if (!running) {
                        SolverFactory.setID(1);
                        repaint();
                    }
                    break;
                case KeyEvent.VK_2:
                    if (!running) {
                        SolverFactory.setID(2);
                        repaint();
                    }
                    break;
                case KeyEvent.VK_3:
                    if (!running) {
                        SolverFactory.setID(3);
                        repaint();
                    }
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
