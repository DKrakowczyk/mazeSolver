/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import java.awt.*;
import mazesolver.alghoritms.AlgorithmFactory;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class Menu {

    int x, y;
    boolean hidden;

    public Menu(int x, int y) {
        this.x = x;
        this.y = y;
        this.hidden = true;
    }

    public void draw(Graphics g) {
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
            g.fillOval(-90, 340, 360, 360);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            if (GUI.mouseInUse) {
                g.setColor(new Color(61, 201, 7));
            }
            g.drawString("Mouse: add/remove walls", 15, 400);

            g.setColor(Color.WHITE);
            if (GUI.setStart) {
                g.setColor(new Color(7, 101, 238));
            }
            g.drawString("Mouse + S key: set start", 15, 430);

            g.setColor(Color.WHITE);
            if (GUI.setEnd) {
                g.setColor(new Color(238, 29, 7));
            }
            g.drawString("Mouse + E key: set end", 15, 460);
            
            g.setColor(Color.WHITE);
            g.drawString("C key: clear the grid", 15, 490);
            g.drawString("R key: generate random maze", 15, 520);
            
            if (GUI.running) {
                g.setColor(new Color(238, 29, 7));
            }
            g.drawString("Space: run/stop choosen algorithm", 15, 550);

            // Draw oval with alghoritms
            g.setColor(new Color(41, 43, 45, 160));
            g.fillOval(700, 420, 340, 340);
            
            g.setColor(Color.WHITE);
            if(AlgorithmFactory.getID() == 1) {
                g.setColor(new Color(106, 162, 244));
            }
            g.drawString("[1]  DFS alghoritm", 765, 490);
            
            g.setColor(Color.WHITE);
            if(AlgorithmFactory.getID() == 2) {
                g.setColor(new Color(106, 162, 244));
            }
            g.drawString("[2]  BFS alghoritm", 765, 520);
            
            g.setColor(Color.WHITE);
            if(AlgorithmFactory.getID() == 3) {
                g.setColor(new Color(106, 162, 244));
            }
            g.drawString("[3]  A* alghoritm", 765, 550);
        }
    }

    public void toggle() {
        this.hidden = !this.hidden;
    }
}
