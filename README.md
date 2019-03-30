## mazeSolver
| JAVA | SWING |
|--|--|

**mazeSolver**  is an application that allows to visualize the operation of various implemented pathfinding algorithms. User has the ability to draw a custom maze or generate one (mazes are randomly generated - backtrack algorithm).




##
### How to use
  
By default, after starting the program, we get an empty grid. To start pathfinding operations, it is necessary to define the start and end nodes as well as the labyrinth walls. 
To expand the context menu, press the **m** button

#### Drawing & generating maze
There are several ways to create a maze in the application:
 
 - Use **left mouse button** to place a wall.
 - Use **right mouse button** to remove a wall.
 - Press **R** key to randomly generate a maze.
 - Press **C** key to clear the grid 

  ![Maze drawing gif](https://github.com/DKrakowczyk/mazeSolver/blob/master/readme_img/drawing.gif?raw=true)
  ##
#### Selecting start & end nodes:
  
To mark the start or end node simply hold one of the available buttons described in the context menu.

 - **Mouse + S key** - set start node
 - **Mouse + E key** - set end node
 
![Start&end placing](https://github.com/DKrakowczyk/mazeSolver/blob/master/readme_img/start_end.gif?raw=true)
##
#### Running pathfinding algorithms
  
  To run selected algorithm press **Spacebar** button. The default choosen one is Breadth First Search Algorithm. You can also use **arrows** to determine the speed of the current working algorithm. You can also stop searching by pressing **Spacebar** again.

List of available algorithms:

 - Breadth First Search (BFS) **(1)**
 - Depth First Search (DFS) **(2)**
 - A* Algorithm **(3)**
 
![solvers](https://github.com/DKrakowczyk/mazeSolver/blob/master/readme_img/running.gif?raw=true)
##
#### Notifications:
  
There are few types of notifications in the application:

 - When you try to run algorithm when the start or end node is not selected you will get an alert (**"Please select start and end node"**).
 - You will also get a message when the algorithm:
	 - Found a solution (**"Solution has been found"**)
	 - Couldn't find a solution (**"Solution could not be found"**)

![alerts](https://github.com/DKrakowczyk/mazeSolver/blob/master/readme_img/alerts.gif?raw=true)
##
#### Scalability:
You can determine the size of the grid by changing **nodeSize** value which is located in **MazeSolver.java** class.

![grid size](https://github.com/DKrakowczyk/mazeSolver/blob/master/readme_img/dimension.gif?raw=true)

## Authors

[Michał Kucharski](https://github.com/Kucharskov)
[Dawid Krakowczyk](https://github.com/DKrakowczyk)
