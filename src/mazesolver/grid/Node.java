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
    Type type;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = Type.EMPTY;
    }

    public void setType(Type t) {
        this.type = t;
    }

    public Type getType() {
        return this.type;
    }

    public void draw(Graphics g) {
        switch (type) {
            case EMPTY:
                g.setColor(new Color(70, 78, 91));
                break;
            case WALL:
                g.setColor(new Color(255, 255, 255));
                break;
            case START:
                g.setColor(new Color(7, 101, 238));
                break;
            case END:
                g.setColor(new Color(238, 29, 7));
                break;
        }

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

    public static enum Type {
        EMPTY, WALL, START, END
    }
}
