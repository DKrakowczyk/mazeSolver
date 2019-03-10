/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.alghoritms;

import java.util.ArrayList;
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
import mazesolver.gui.Menu;
import mazesolver.threads.IConnectWorker;

/**
 *
 * @author Dawid
 */
public class DijkstraAlgorithm implements IAlgorithm {

    @Override
    public void solve(IConnectWorker worker, Grid grid) throws InterruptedException {
        
       
        Node startNode = grid.getStart();
        Node endNode = grid.getEnd();
 
        // setup for A*
        HashMap<Node,Node> parentMap = new HashMap<Node,Node>();
        HashSet<Node> visited = new HashSet<Node>();
        Map<Node, Double> distances = new HashMap<>();
        
        for(Node n : grid.getNodes()){
            distances.put(n,Double.POSITIVE_INFINITY);
        }
 
        Queue<Node> priorityQueue = new LinkedList<>();
 
        //  enque StartNode, with distance 0
        //startNode.setDistanceToStart(new Double(0));
        distances.put(startNode, new Double(0));
        priorityQueue.add(startNode);
        Node current = null;
        Map<Node, Node> solutionMap = new LinkedHashMap<>();

        solutionMap.put(startNode, null);
        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.remove();
            
            if (!visited.contains(current) ){
                visited.add(current);
                // if last element in PQ reached
                if (current.equals(endNode)){
                 
                    worker.stopRunning();
                    return;
                }
 
                List<Node> neighbors = grid.getNeighbors(current.getX(), current.getY());
                for (Node neighbor : neighbors) {
                    if (!visited.contains(neighbor) ){  
                        neighbor.setType(Types.VISITED);
                        Thread.sleep(Menu.getDelay());
                        grid.repaint();
                         //calculate predicted distance to the end node
                        double predictedDistance = distance(neighbor, endNode);
 
                        // 1. calculate distance to neighbor. 2. calculate dist from start node
                        double neighborDistance = distance(current, neighbor);
                        double totalDistance = distance(current,startNode) + neighborDistance + predictedDistance;
 
                        // check if distance smaller
                        if(totalDistance < distances.get(neighbor) ){
                            // update n's distance
                            distances.put(neighbor, totalDistance);
                            // used for PriorityQueue
                              //neighbor.setDistanceToStart(totalDistance);
                            //neighbor.setPredictedDistance(predictedDistance);
                            // set parent
                            parentMap.put(neighbor, current);
                            // enqueue
                            priorityQueue.add(neighbor);
                    
                        }
                    }
                }
            }
        }
    }
    @Override
    public void showSolution(Map<Node, Node> solutionMap, Grid grid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
  
  private double distance(Node a, Node b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }
   
}
