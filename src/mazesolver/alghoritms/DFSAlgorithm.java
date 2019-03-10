package mazesolver.alghoritms;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;
import mazesolver.gui.GUI;
import mazesolver.threads.IConnectWorker;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class DFSAlgorithm implements IAlgorithm {

    @Override
    public void solve(IConnectWorker worker, Grid grid) throws InterruptedException {
        grid.clear();
        Node start = grid.getStart();
        Node end = grid.getEnd();

        if (start == null || end == null) {
            worker.stopRunning();
            return;
        }

        Stack<Node> stack = new Stack<>();
        Map<Node, Node> solutionMap = new LinkedHashMap<>();

        stack.add(start);
        solutionMap.put(start, null);

        while (stack.size() > 0) {
            Node state = stack.peek(); //Pobierz element
            stack.pop();

            List<Node> childs = grid.getNeighbors(state.getX(), state.getY());
            for (Node child : childs) {
                if (end.equals(child)) {
                    solutionMap.put(child, state);
                    showSolution(solutionMap, grid);
                    worker.stopRunning();
                    return;
                }
                if (!solutionMap.containsKey(child)) {
                    stack.add(child);
                    solutionMap.put(child, state);
                    if (child != grid.getEnd() && child != grid.getStart()) {
                        child.setType(Types.VISITED);
                        worker.getGrid().repaint();
                        Thread.sleep(GUI.algorithmSpeed);
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

}
