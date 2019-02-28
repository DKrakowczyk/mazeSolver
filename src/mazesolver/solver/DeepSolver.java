/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class DeepSolver implements ISolver {

    Grid grid;
    List<Node> solution;

    public DeepSolver(Grid grid) {
        this.grid = grid;
        this.solution = null;
    }

    @Override
    public boolean solve() {
        grid.clear();
        Node stanPoczatkowy = grid.getStart();
        Node stanKoncowy = grid.getEnd();

        if (stanPoczatkowy == null || stanKoncowy == null) {
            return false;
        }

        Stack<Node> stos = new Stack<>();
        Map<Node, Node> mapaRozwiazan = new LinkedHashMap<>();

        //Stos do przeszukiwania
        stos.add(stanPoczatkowy);
        //Mapa stan - > rodzic
        mapaRozwiazan.put(stanPoczatkowy, null);

        while (stos.size() > 0) {
            Node stan = stos.peek(); //Pobierz element
            stos.pop();
            if (stan != grid.getEnd() && stan != grid.getStart()) stan.setType(Types.VISITED);
            List<Node> dzieci = grid.getNeighbors(stan.getX(), stan.getY());
            for (Node dziecko : dzieci) {
                if (stanKoncowy.equals(dziecko)) {
                    mapaRozwiazan.put(dziecko, stan);
                    showRozwiazanie(mapaRozwiazan);
                    return true;
                }
                if (!mapaRozwiazan.containsKey(dziecko)) {
                    stos.add(dziecko);
                    mapaRozwiazan.put(dziecko, stan);
                }
            }
        }

        return false;
    }

    public void showRozwiazanie(Map<Node, Node> mapaRozwiazan) {
        Node stan = grid.getEnd();
        while (stan != grid.getStart()) {
            if (stan != grid.getEnd() && stan != grid.getStart()) {
                stan.setType(Types.SOLUTION);
            }
            Node rodzic = mapaRozwiazan.get(stan);
            stan = rodzic;
        }
    }

}
