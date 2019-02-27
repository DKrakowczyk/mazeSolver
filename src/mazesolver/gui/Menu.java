/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import java.awt.*;

/**
 *
 * @author M.Kucharskov
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
            g.setColor(new Color(41, 43, 45, 160));
            g.fillRect(20, 540, 40, 40);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("(M)", 29, 566);
        } else {
            g.setColor(new Color(41, 43, 45, 160));
            g.fillRect(20, 480, 260, 100);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Mouse + S key: set start", 30, 505);
            g.drawString("Mouse + E key: set end", 30, 525);
            g.drawString("Mouse left/right: add/remove walls", 30, 545);
            g.drawString("Space: run/stop choosen algorithm", 30, 565);
        }
    }

    public void toggle() {
        this.hidden = !this.hidden;
    }
}
