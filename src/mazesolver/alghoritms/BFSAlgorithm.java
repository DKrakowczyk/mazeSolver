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
import mazesolver.MazeSolver;
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
public class BFSAlgorithm implements IAlgorithm {

    @Override
    public void solve(IConnectWorker worker, Grid grid) throws InterruptedException {
        boolean solutionFound = false;
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
            if (!GUI.running) {
                return;
            }
            Node state = queue.poll();
            List<Node> childs = grid.getNeighbors(state.getX(), state.getY());
            for (Node chlid : childs) {
                if (worker.workerStopped()) {
                    return;
                }
                if (end.equals(chlid)) {
                    solutionFound = true;
                    checkForAlerts(solutionFound, grid);
                    solutionMap.put(chlid, state);
                    showSolution(solutionMap, grid);
                    worker.stopRunning();
                    return;
                }
                if (!solutionMap.containsKey(chlid)) {
                    queue.add(chlid);
                    solutionMap.put(chlid, state);
                    if (chlid != grid.getEnd() && chlid != grid.getStart()) {
                        chlid.setType(Types.VISITED);
                        worker.getGrid().repaint();
                        Thread.sleep(Menu.getDelay());
                    }
                }
            }
        }
        checkForAlerts(solutionFound, grid);
        return;
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

    private void checkForAlerts(boolean solved, Grid grid) {
        if (solved) {
            Menu.alertMessage = 3;
            Menu.showAlert = true;
            grid.repaint();
        } else {
            Menu.alertMessage = 2;
            Menu.showAlert = true;
            grid.repaint();
            GUI.running = false;
        }
    }
}
