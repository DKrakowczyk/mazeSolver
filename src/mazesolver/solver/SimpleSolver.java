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

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class SimpleSolver implements ISolver {

    Grid grid;
    Map<Node, Node> solution;
    Queue<Node> stos;
    Node stanPoczatkowy, stanKoncowy;

    public SimpleSolver() {
        this.grid = null;

        this.solution = new LinkedHashMap<>();
        this.stos = new LinkedList<>();
    }

    @Override
    public boolean setGrid(Grid grid) {
        this.grid = grid;
        this.grid.clear();

        this.stanPoczatkowy = grid.getStart();
        this.stanKoncowy = grid.getEnd();

        if (stanPoczatkowy == null || stanKoncowy == null) {
            return false;
        }

        stos.add(stanPoczatkowy);
        solution.put(stanPoczatkowy, null);

        return true;
    }

    @Override
    public boolean isDone() {
        return stos.isEmpty();
    }

    @Override
    public void doStep() {
        Node stan = stos.poll(); //Pobiera element i zrzuca go z kolejki
        if (stan != grid.getEnd() && stan != grid.getStart()) {
            stan.setType(Types.VISITED);
        }
        List<Node> dzieci = grid.getNeighbors(stan.getX(), stan.getY());
        for (Node dziecko : dzieci) {
            if (stanKoncowy.equals(dziecko)) {
                solution.put(dziecko, stan);
            }
            if (!solution.containsKey(dziecko)) {
                stos.add(dziecko);
                solution.put(dziecko, stan);
            }
        }
    }

    @Override
    public Map<Node, Node> getSolution() {
        return solution;
    }
}
