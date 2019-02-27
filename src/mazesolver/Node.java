package mazesolver;

import java.awt.*;

/**
 *
 * @author Dawid
 */
public class Node {
    private int x;
    private int y;
    private int nodeSize;
    
    public Node(int x, int y, int nodeSize) {
        this.x = x;
        this.y =y;
        this.nodeSize = nodeSize;
    }
    
    public void draw(Graphics g){
        g.fillRect(x*nodeSize, y*nodeSize, nodeSize, nodeSize);
    }
    
    public boolean compareNodes(Node c) {
        if(this.x == c.x && this.y == c.y && this.nodeSize == c.nodeSize){
            return true;
        } else {
            return false;
        }
    }
}
