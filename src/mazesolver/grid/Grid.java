/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import mazesolver.MazeSolver;
import mazesolver.grid.Node.Type;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class Grid {

    final int rows;
    final int cols;
    List<Node> nodes;

    public Grid() {
        // Calculating dimension of grid
        this.rows = MazeSolver.height / MazeSolver.nodeSize;
        this.cols = MazeSolver.width / MazeSolver.nodeSize;
        this.nodes = new ArrayList<>();

        // Creating nodes (default type EMPTY)
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                nodes.add(new Node(x, y));
            }
        }
    }

    public void draw(Graphics g) {
        // Draw nodes
        for (Node n : nodes) {
            n.draw(g);
        }

        // Draw x axis
        g.setColor(new Color(16, 17, 17));
        for (int i = 1; i < cols; i++) {
            g.drawLine(i * MazeSolver.nodeSize, 0, i * MazeSolver.nodeSize, MazeSolver.height);
        }

        // Draw y axis
        g.setColor(new Color(16, 17, 17));
        for (int i = 1; i < rows; i++) {
            g.drawLine(0, i * MazeSolver.nodeSize, MazeSolver.width, i * MazeSolver.nodeSize);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setStart(int x, int y) {
        // Remove old start
        for (Node n : nodes) {
            if (n.getType() == Type.START) {
                n.setType(Type.EMPTY);
            }
        }

        Node n = getNode(x, y);
        n.setType(Type.START);
    }

    public void setEnd(int x, int y) {
        // Remove old end
        for (Node n : nodes) {
            if (n.getType() == Type.END) {
                n.setType(Type.EMPTY);
            }
        }

        Node n = getNode(x, y);
        n.setType(Type.END);
    }

    public void toggleWall(int x, int y) {
        Node n = getNode(x, y);
        switch (n.getType()) {
            case EMPTY:
                n.setType(Type.WALL);
                break;
            case WALL:
                n.setType(Type.EMPTY);
                break;
        }
    }
    
    public Node getNode(int x, int y) {
        if(x < 0 || x >= cols || y < 0 || y >= rows) {
            return null;
        }
        
        return nodes.get(x + y * cols);
    }

}
