/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.alghoritms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;
import mazesolver.grid.Pair;
import mazesolver.gui.GUI;
import mazesolver.gui.Menu;
import mazesolver.threads.IConnectWorker;

/**
 *
 * @author Dawid
 */
public class AStarAlgorithm implements IAlgorithm {

    @Override
    public void solve(IConnectWorker worker, Grid grid) throws InterruptedException {
        Node startNode = grid.getStart();
        Node endNode = grid.getEnd();
        grid.clear();

        HashMap<Node, Node> parentMap = new HashMap<>();
        HashSet<Node> visited = new HashSet<>();
        Map<Node, Double> distances = new HashMap<>();

        for (Node n : grid.getNodes()) {
            distances.put(n, Double.POSITIVE_INFINITY);
        }

        Queue<Pair> priorityQueue = new PriorityQueue<>();

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
                    showSolution(solutionMap, grid);
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

    }

    @Override
    public void showSolution(Map<Node, Node> solutionMap, Grid grid) {
        Node state = grid.getEnd();
        while (state != grid.getStart()) {
            if (state != grid.getEnd() && state != grid.getStart()) {
                state.setType(Node.Types.SOLUTION);
            }
            Node rodzic = solutionMap.get(state);
            state = rodzic;
            grid.repaint();
        }
    }

    private double distance(Node a, Node b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

}
