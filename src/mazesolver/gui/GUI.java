/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import java.awt.*;
import javax.swing.*;
import mazesolver.grid.Grid;
import mazesolver.MazeSolver;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class GUI extends JPanel {

    Grid grid;
    
    public GUI() {
        this.grid = new Grid();
        
        setPreferredSize(new Dimension(MazeSolver.width, MazeSolver.height));
        setFocusable(true);
        
        // KOD TESTUJĄCY PRZESTAWAINIE ŚCIANEK I USTAWIANIE TYPÓW
        
        //Rysowanie paska przez środek planszy
        for(int i = 0; i < grid.getRows(); i++) {
            grid.toggleWall(grid.getCols()/2, i);
        }
        //Powrotne togglowanie co drugiego
        for(int i = 0; i < grid.getRows(); i += 2) {
            grid.toggleWall(grid.getCols()/2, i);
        }
        
        //Kilkukrotne ustawienie startu
        grid.setStart(1, 1);
        grid.setStart(1, 0);
        grid.setStart(0, 0);
        
        grid.setEnd(1, 1);
        grid.setEnd(1, 0);
        grid.setEnd(grid.getCols()-1, grid.getRows()-1);
        // KONIEC KODU TESTUJĄCEGO
    }

    @Override
    public void paint(Graphics g) {
        grid.draw(g);
    }
}
