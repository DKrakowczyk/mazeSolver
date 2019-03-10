/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.alghoritms;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;
import mazesolver.gui.GUI;
import mazesolver.threads.IWorker;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class BFSAlgorithm implements ISolver {

    @Override
    public void solve(IWorker worker, Grid grid) throws InterruptedException {
        grid.clear();
        Node start = grid.getStart();
        Node end = grid.getEnd();

        if (start == null || end == null) {
            worker.stopRunning();
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> solutionMap = new LinkedHashMap<>();

        queue.add(start);

        solutionMap.put(start, null);

        while (queue.size() > 0) {
            Node state = queue.poll();
            if (state != grid.getEnd() && state != grid.getStart()) {
                state.setType(Types.VISITED);
                worker.getGrid().repaint();
                Thread.sleep(GUI.algorithmSpeed);
            }
            List<Node> childs = grid.getNeighbors(state.getX(), state.getY());
            for (Node chlid : childs) {
                if (end.equals(chlid)) {
                    solutionMap.put(chlid, state);
                    showSolution(solutionMap, grid);
                    worker.stopRunning();
                    return;
                }
                if (!solutionMap.containsKey(chlid)) {
                    queue.add(chlid);
                    solutionMap.put(chlid, state);
                }
            }
        }
    }

    @Override
    public void showSolution(Map<Node, Node> solutionMap, Grid grid) {
        Node state = grid.getEnd();
        while (state != grid.getStart()) {
            if (state != grid.getEnd() && state != grid.getStart()) {
                state.setType(Types.SOLUTION);
            }
            Node rodzic = solutionMap.get(state);
            state = rodzic;
            grid.repaint();
        }
    }
}
