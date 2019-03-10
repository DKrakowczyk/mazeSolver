/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;
import mazesolver.gui.IWorker;

/**
 *
 * @author Dawid
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
        Map<Node, Node> solution = new LinkedHashMap<>();

        queue.add(start);

        solution.put(start, null);

        while (queue.size() > 0) {
            Node state = queue.poll();
            if (state != grid.getEnd() && state != grid.getStart()) {
                state.setType(Types.VISITED);
                worker.getGrid().repaint();
                Thread.sleep(10);
            }
            List<Node> childs = grid.getNeighbors(state.getX(), state.getY());
            for (Node chlid : childs) {
                if (end.equals(chlid)) {
                    solution.put(chlid, state);
                    showRozwiazanie(solution, grid);
                    worker.stopRunning();
                    return;
                }
                if (!solution.containsKey(chlid)) {
                    queue.add(chlid);
                    solution.put(chlid, state);
                }
            }
        }
    }

    public void showRozwiazanie(Map<Node, Node> mapaRozwiazan, Grid grid) {
        Node stan = grid.getEnd();
        while (stan != grid.getStart()) {
            if (stan != grid.getEnd() && stan != grid.getStart()) {
                stan.setType(Types.SOLUTION);
            }
            Node rodzic = mapaRozwiazan.get(stan);
            stan = rodzic;
            grid.repaint();
        }
    }

}
