/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class BacktrackGenerator implements IGenerator {

    Grid grid;
    Stack<Node> stack;

    public BacktrackGenerator(Grid grid) {
        this.grid = grid;
        this.stack = new Stack<>();

        //Fill grid with walls
        for (int y = 0; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                Node n = grid.getNode(x, y);
                n.setType(Types.WALL);
            }
        }
    }

    private Node getRandomNeighbor(int x, int y) {
        // Make a list of neighbors
        List<Node> neighbors = new LinkedList<>();

        Node neighbor;
        // Searching in X axis for unvisited neighbors
        for (int xoff = -2; xoff <= 2; xoff += 4) {
            neighbor = grid.getNode(x + xoff, y);
            if (neighbor != null && neighbor.getType() == Types.WALL) {
                neighbors.add(neighbor);
            }
        }
        // Searching in Y axis for unvisited neighbors
        for (int yoff = -2; yoff <= 2; yoff += 4) {
            neighbor = grid.getNode(x, y + yoff);
            if (neighbor != null && neighbor.getType() == Types.WALL) {
                neighbors.add(neighbor);
            }
        }

        // If any avaialbe neighbor, return null
        if (neighbors.isEmpty()) {
            return null;
        }

        // Else return random neighbor
        Random random = new Random();
        return neighbors.get(random.nextInt(neighbors.size()));
    }

    private void removeWalls(Node current, Node next) {
        // Calculating position of wall to remove
        int x = current.getX() + (next.getX() - current.getX()) / 2;
        int y = current.getY() + (next.getY() - current.getY()) / 2;

        // Get node and if it exist remove wall
        Node toEmpty = grid.getNode(x, y);
        if (toEmpty != null) {
            toEmpty.setType(Types.EMPTY);
        }
    }

    @Override
    public void generate() {
        // Select current node and push it to stack
        Node current = grid.getNode(0,0);
        stack.push(current);

        // While stack is not empty
        while (!stack.isEmpty()) {
            // Remove current wall
            current.setType(Types.EMPTY);
            // Pick a random neighbor
            Node next = getRandomNeighbor(current.getX(), current.getY());
            if (next != null) {
                // Push neighbor to stack
                stack.push(next);
                // Remove walls
                removeWalls(current, next);
                // Set neighbor as current node
                current = next;
            } else {
                // Else pick node from stack
                current = stack.pop();
            }
        }
    }

}
