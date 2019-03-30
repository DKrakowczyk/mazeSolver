/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import java.awt.*;
import mazesolver.MazeSolver;
import mazesolver.alghoritms.AlgorithmFactory;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class Menu {

    public static boolean showAlert;
    public static short speedMultipler, alertMessage;
    int x, y;
    boolean hidden;

    public Menu(int x, int y) {
        Menu.showAlert = false;
        Menu.speedMultipler = 100;
        Menu.alertMessage = 0;
        this.x = x;
        this.y = y;
        this.hidden = true;
    }

    public static int getDelay() {
        return MazeSolver.delay * (205 - speedMultipler) / 100;
    }

    public void draw(Graphics g) {
        drawAlertBox(g);

        if (hidden) {
            // Draw small square with "M"
            g.setColor(new Color(41, 43, 45, 160));
            g.fillOval(20, 520, 40, 40);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("(M)", 29, 545);
        } else {
            // Draw oval box with text and data
            g.setColor(new Color(41, 43, 45, 160));
            g.fillRect(0, 360, 260, 360);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            if (GUI.mouseInUse) {
                g.setColor(new Color(61, 201, 7));
            }
            g.drawString("Mouse: add/remove walls", 15, 385);

            g.setColor(Color.WHITE);
            if (GUI.setStart) {
                g.setColor(new Color(7, 101, 238));
            }
            g.drawString("Mouse + S key: set start", 15, 415);

            g.setColor(Color.WHITE);
            if (GUI.setEnd) {
                g.setColor(new Color(238, 29, 7));
            }
            g.drawString("Mouse + E key: set end", 15, 445);

            g.setColor(Color.WHITE);
            g.drawString("C key: clear the grid", 15, 475);
            g.drawString("R key: generate random maze", 15, 505);

            if (GUI.running) {
                g.setColor(new Color(238, 29, 7));
            }
            g.drawString("Space: run/stop choosen algorithm", 15, 535);
            g.setColor(Color.WHITE);
            g.drawString("Arrows: adjust speed: " + speedMultipler + "%", 15, 565);

            // Draw oval with alghoritms
            g.setColor(new Color(41, 43, 45, 160));
            g.fillRect(740, 460, 180, 340);

            g.setColor(Color.WHITE);
            if (AlgorithmFactory.getID() == 1) {
                g.setColor(new Color(42, 170, 42));
            }
            g.drawString("[1]  BFS alghoritm", 765, 490);

            g.setColor(Color.WHITE);
            if (AlgorithmFactory.getID() == 2) {
                g.setColor(new Color(42, 170, 42));
            }
            g.drawString("[2]  DFS alghoritm", 765, 520);

            g.setColor(Color.WHITE);
            if (AlgorithmFactory.getID() == 3) {
                g.setColor(new Color(42, 170, 42));
            }
            g.drawString("[3]  A* alghoritm", 765, 550);
        }
    }

    private void drawAlertBox(Graphics g) {
        // Draw oval box with text and data
        if (showAlert) {
            g.setColor(new Color(41, 43, 45, 240));
            g.fillRect(250, 0, 400, 50);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.setColor(Color.WHITE);
            switch (alertMessage) {
                case 1:
                    g.drawString("Please select start and end node", 320, 30);
                    break;
                case 2:
                    g.drawString("Solution could not be found", 340, 30);
                    break;
                case 3:
                    g.drawString("Solution has been found", 350, 30);
                    break;
                default:
                    break;
            }
        }
    }

    public void toggle() {
        this.hidden = !this.hidden;
    }
}
