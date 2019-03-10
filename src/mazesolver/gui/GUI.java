/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import mazesolver.threads.BackgroundWorker;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mazesolver.grid.Grid;
import mazesolver.MazeSolver;
import mazesolver.generator.GeneratorFactory;
import mazesolver.alghoritms.AlgorithmFactory;
import mazesolver.threads.IConnectUI;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class GUI extends JPanel implements MouseListener, MouseMotionListener {

    static boolean setStart, setEnd, mouseInUse;

    public static boolean running;
    private int lastX, lastY;
    Grid grid;
    Menu menu;
    IConnectUI connetionLayer;

    public GUI(BackgroundWorker cl) {
        this.setStart = false;
        this.setEnd = false;
        mouseInUse = false;
        this.lastX = -1;
        this.lastY = -1;
        this.grid = new Grid(this);
        this.menu = new Menu(0, 0);
        this.connetionLayer = cl;

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
        Menu.showAlert = false;
        this.repaint();
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
                        GeneratorFactory.getGenerator(grid).generate();
                        repaint();
                    }
                    break;
                // Solver run/stop
                case KeyEvent.VK_SPACE:
                    if (grid.getStart() != null && grid.getEnd() != null) {
                        running = !running;
                        if (running) {
                            connetionLayer.setGrid(grid);
                            connetionLayer.setStarted(true);
                        }
                        Menu.alertMessage = 0;
                        Menu.showAlert = false;
                    } else {
                        Menu.alertMessage = 1;
                        Menu.showAlert = true;
                        repaint();
                    }

                    break;

                // Solver choose
                case KeyEvent.VK_1:
                    if (!running) {
                        AlgorithmFactory.setID(1);
                        repaint();
                    }
                    break;
                case KeyEvent.VK_2:
                    if (!running) {
                        AlgorithmFactory.setID(2);
                        repaint();
                    }
                    break;
                case KeyEvent.VK_3:
                    if (!running) {
                        AlgorithmFactory.setID(3);
                        repaint();
                    }
                    break;
                // Adjusting speed of the algorithm
                case KeyEvent.VK_LEFT:
                    if (Menu.algorithmSpeed < 50) {
                        Menu.algorithmSpeed++;
                        repaint();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (Menu.algorithmSpeed > 1) {
                        Menu.algorithmSpeed--;
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
