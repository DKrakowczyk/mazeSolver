/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import java.awt.*;

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
            g.fillOval(0, 540, 40, 40);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("(M)", 9, 565);
        } else {
            // Draw bigger box with text and data
            g.setColor(new Color(41, 43, 45, 160));
            g.fillOval(-90, 340, 360, 360);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            if(GUI.mouseInUse){
                g.setColor(new Color(61, 201, 7));
            }
            g.drawString("Mouse: add/remove walls", 5, 400);
            
            g.setColor(Color.WHITE);
            if(GUI.setStart){
                g.setColor(new Color(7, 101, 238));
            }
            g.drawString("Mouse + S key: set start", 5, 430);
            
            g.setColor(Color.WHITE);
            if(GUI.setEnd){
                g.setColor(new Color(238, 29, 7));
            }
            g.drawString("Mouse + E key: set end", 5, 460);
            
            g.setColor(Color.WHITE);
            g.drawString("C key: clear the grid", 5, 490);
            
            g.setColor(Color.WHITE);
            g.drawString("R key: generate random maze", 5, 520);
            
            g.setColor(Color.WHITE);
            if(GUI.running){
               g.setColor(new Color(106, 162, 244));
            }
            g.drawString("Space: run/stop choosen algorithm", 5, 550);
            
            
            
            // Draw bigger box with alghoritms
            g.setColor(new Color(41, 43, 45, 160));
            g.fillOval(710, 420, 260, 260);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            
            g.drawString("1: DFS alghoritm", 765, 490);
            
            g.drawString("2: BFS alghoritm", 765, 520);
            
            g.drawString("3: A* alghoritm", 765, 550);
            
            g.setColor(Color.WHITE);
            g.drawString("C key: clear the grid", 5, 490);
            
            g.setColor(Color.WHITE);
            g.drawString("R key: generate random maze", 5, 520);
            
            g.setColor(Color.WHITE);
            if(GUI.running){
               g.setColor(new Color(106, 162, 244));
            }
            g.drawString("Space: run/stop choosen algorithm", 5, 550);
            
            
            
            
            
        }
    }

    public void toggle() {
        this.hidden = !this.hidden;
    }
}
