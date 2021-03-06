/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.grid;

import java.awt.Color;
import java.awt.Graphics;
import mazesolver.MazeSolver;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class Node {

    final int x;
    final int y;
    Types type;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = Types.EMPTY;
    }

    public void setType(Types t) {
        this.type = t;
    }

    public Types getType() {
        return this.type;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        // Set propely color
        switch (type) {
            case EMPTY:
                g.setColor(new Color(70, 78, 91));
                break;
            case WALL:
                g.setColor(new Color(0, 0, 0));
                break;
            case START:
                g.setColor(new Color(7, 101, 238));
                break;
            case END:
                g.setColor(new Color(238, 29, 7));
                break;
            case VISITED:
                g.setColor(new Color(127, 130, 127));
                break;
            case SOLUTION:
                g.setColor(new Color(42, 170, 42));
                break;
          
        }

        // Draw square with nodeSize size
        g.fillRect(x * MazeSolver.nodeSize, y * MazeSolver.nodeSize, MazeSolver.nodeSize, MazeSolver.nodeSize);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.x;
        hash = 13 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    // Enum to recognize node by program (color for human)
    public static enum Types {
        EMPTY, WALL, START, END, VISITED, SOLUTION
    }
}
