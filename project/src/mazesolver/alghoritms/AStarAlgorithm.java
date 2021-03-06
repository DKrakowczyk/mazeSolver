/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.alghoritms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;
import mazesolver.gui.GUI;
import mazesolver.gui.Menu;
import mazesolver.threads.IConnectWorker;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class AStarAlgorithm implements IAlgorithm {

    @Override
    public void solve(IConnectWorker worker, Grid grid) throws InterruptedException {
        boolean solutionFound = false;
        Node startNode = grid.getStart();
        Node endNode = grid.getEnd();
        grid.clear();
        
        HashMap<Node, Node> parentMap = new HashMap<>();
        HashSet<Node> visited = new HashSet<>();
        Map<Node, Double> distances = new HashMap<>();
        Queue<Pair> priorityQueue = new PriorityQueue<>();
        for (Node n : grid.getNodes()) {
            distances.put(n, Double.POSITIVE_INFINITY);
        }

        distances.put(startNode, new Double(0));
        priorityQueue.add(new Pair(startNode, 0, 0));
        Node current = null;
        Map<Node, Node> solutionMap = new LinkedHashMap<>();
        solutionMap.put(startNode, null);
        
        while (!priorityQueue.isEmpty()) {
            if (!GUI.running) {
                return;
            }

            current = priorityQueue.poll().getNode();
            if (!visited.contains(current)) {
                visited.add(current);
                if (endNode.equals(current)) {
                    solutionFound = true;
                    Utils.checkForAlerts(solutionFound, grid);
                    Utils.showSolution(solutionMap, grid);
                    worker.stopRunning();
                    return;
                }

                List<Node> neighbors = grid.getNeighbors(current.getX(), current.getY());
                for (Node neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        if (neighbor != grid.getEnd() && neighbor != grid.getStart()) {
                            neighbor.setType(Types.VISITED);
                            Thread.sleep(Menu.getDelay());
                            grid.repaint();
                        }

                        double predictedDistance = distance(neighbor, endNode);
                        double neighborDistance = distance(current, neighbor);
                        double totalDistance = distance(current, startNode) + neighborDistance + predictedDistance;

                        if (totalDistance < distances.get(neighbor)) {
                            distances.put(neighbor, totalDistance);
                            parentMap.put(neighbor, current);
                            priorityQueue.add(new Pair(neighbor, totalDistance, predictedDistance));
                            solutionMap.put(neighbor, current);
                        }
                    }
                }
            }
        }
        Utils.checkForAlerts(solutionFound, grid);
        GUI.running = false;
    }

    private double distance(Node a, Node b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

}

class Pair implements Comparable {

    Node node;
    double totalDistance;
    double predictedDistance;

    public Pair(Node n, double totalDistance, double predictedDistance) {
        this.node = n;
        this.totalDistance = totalDistance;
        this.predictedDistance = predictedDistance;
    }

    @Override
    public int compareTo(Object o) {
        if (this.predictedDistance > ((Pair) o).predictedDistance) {
            return 1;
        } else if (this.predictedDistance < ((Pair) o).predictedDistance) {
            return -1;
        } else {
            return 0;
        }
    }

    public Node getNode() {
        return node;
    }
}
