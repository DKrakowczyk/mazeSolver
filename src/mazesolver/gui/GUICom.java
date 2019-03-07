/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import mazesolver.grid.Grid;

/**
 *
 * @author Marcin
 */
public interface GUICom {

    boolean isStart();

    void setAlgo(int algo);

    void setGrid(Grid grid);

    void setStopAlgo(boolean shouldStop);

    void setStart(boolean start);

    void setStopWorker(boolean stopWorker);
    
    boolean isFinished();
}
