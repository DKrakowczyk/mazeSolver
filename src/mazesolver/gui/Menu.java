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
            g.fillRect(20, 520, 40, 40);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("(M)", 29, 545);
        } else {
            // Draw bigger box with text and data
            g.setColor(new Color(41, 43, 45, 160));
            g.fillRect(20, 420, 260, 140);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            if(GUI.mouseInUse){
                g.setColor(new Color(61, 201, 7));
            }
            g.drawString("Mouse: add/remove walls", 30, 445);
            
            g.setColor(Color.WHITE);
            if(GUI.setStart){
                g.setColor(new Color(7, 101, 238));
            }
            g.drawString("Mouse + S key: set start", 30, 465);
            
            g.setColor(Color.WHITE);
            if(GUI.setEnd){
                g.setColor(new Color(238, 29, 7));
            }
            g.drawString("Mouse + E key: set end", 30, 485);
            
            g.setColor(Color.WHITE);
            g.drawString("C key: clear the grid", 30, 510);
            
            g.setColor(Color.WHITE);
            g.drawString("R key: generate random maze", 30, 530);
            
            g.setColor(Color.WHITE);
            if(GUI.running){
               g.setColor(new Color(106, 162, 244));
            }
            g.drawString("Space: run/stop choosen algorithm", 30, 550);
        }
    }

    public void toggle() {
        this.hidden = !this.hidden;
    }
}
