/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.grid;

/**
 *
 * @author Dawid
 */
public class Pair implements Comparable{
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
       if(this.predictedDistance>((Pair) o).predictedDistance)
       return 1;
       else if(this.predictedDistance<((Pair) o).predictedDistance)
       return -1;
       else
       return 0;
    }
    
    public Node getNode(){
        return node;
    }
}
