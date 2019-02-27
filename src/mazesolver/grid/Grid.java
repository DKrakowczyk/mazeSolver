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
import java.util.LinkedList;
import mazesolver.MazeSolver;
import mazesolver.grid.Node.Types;

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
            if (n.getType() == Types.START) {
                n.setType(Types.EMPTY);
            }
        }

        Node n = getNode(x, y);
        n.setType(Types.START);
    }

    public Node getStart() {
        for (Node n : nodes) {
            if (n.getType() == Types.START) {
                return n;
            }
        }
        return null;
    }

    public void setEnd(int x, int y) {
        // Remove old end
        for (Node n : nodes) {
            if (n.getType() == Types.END) {
                n.setType(Types.EMPTY);
            }
        }

        Node n = getNode(x, y);
        n.setType(Types.END);
    }

    public Node getEnd() {
        for (Node n : nodes) {
            if (n.getType() == Types.END) {
                return n;
            }
        }
        return null;
    }

    public void setWall(int x, int y) {
        Node n = getNode(x, y);
        n.setType(Types.WALL);
    }

    public void setEmpty(int x, int y) {
        Node n = getNode(x, y);
        n.setType(Types.EMPTY);
    }

    public void erase() {
        for (Node n : nodes) {
            n.setType(Types.EMPTY);
        }
    }

    public void clear() {
        for (Node n : nodes) {
            Types t = n.getType();
            if (t == Types.START || t == Types.END || t == Types.WALL) {
                continue;
            }
            n.setType(Types.EMPTY);
        }
    }

    public Node getNode(int x, int y) {
        if (x < 0 || x >= cols || y < 0 || y >= rows) {
            return null;
        }

        return nodes.get(x + y * cols);
    }

    public List<Node> getNeighbors(int x, int y) {
        List<Node> result = new LinkedList<>();

        Node neighbor;
        neighbor = getNode(x - 1, y);
        if (neighbor != null) {
            result.add(neighbor);
        }
        neighbor = getNode(x + 1, y);
        if (neighbor != null) {
            result.add(neighbor);
        }
        neighbor = getNode(x, y - 1);
        if (neighbor != null) {
            result.add(neighbor);
        }
        neighbor = getNode(x, y + 1);
        if (neighbor != null) {
            result.add(neighbor);
        }

        return result;
    }
}
