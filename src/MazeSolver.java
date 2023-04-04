/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Creates an array list of cells that contain the solution
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        Stack<MazeCell> stack = new Stack<MazeCell>();
        MazeCell currCell = maze.getEndCell();
        stack.push(currCell);
        //
        while (currCell != maze.getStartCell()){
            currCell = currCell.getParent();
            stack.push(currCell);
        }
        while(!stack.empty()){
            solution.add(stack.pop());
        }
        return solution;

        // Should be from start to end cells
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Stack
        Stack<MazeCell> stack = new Stack<MazeCell>();
        // First cell goes at the bottom
        stack.push(maze.getStartCell());
        maze.getStartCell().setExplored(true);
        // While the stack is not empty (once it's empty everything has been visited)
        while(!stack.empty()){
            // Takes the most recent one off so the child becomes the current cell
            MazeCell currCell = stack.pop();
            for(MazeCell m: findNeighbors(currCell)){
                stack.push(m);
                m.setExplored(true);
                m.setParent(currCell);
            }
        }
        return getSolution();
    }

    public ArrayList<MazeCell> findNeighbors(MazeCell m){
        ArrayList<MazeCell> neighbors = new ArrayList<MazeCell>();
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Check if cell north of current cell is valid and add to array
        if(maze.isValidCell(m.getRow() - 1, m.getCol())){
            neighbors.add(maze.getCell(m.getRow() - 1, m.getCol()));
            }
        // Check if cell east of current cell is valid and add to array
        if(maze.isValidCell(m.getRow(), m.getCol() - 1)){
            neighbors.add(maze.getCell(m.getRow(), m.getCol() - 1));
        }
        // Check if cell south of current cell is valid and add to array
        if(maze.isValidCell(m.getRow() + 1, m.getCol())){
            neighbors.add(maze.getCell(m.getRow() + 1, m.getCol()));
        }
        // Check if cell west of current cell is valid and add to array
        if(maze.isValidCell(m.getRow(), m.getCol() + 1)){
            neighbors.add(maze.getCell(m.getRow(), m.getCol() + 1));
        }
        return neighbors;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Queue
        // solve recursively
        Queue<MazeCell> queue = new LinkedList<MazeCell>();
        // First cell goes at the bottom
        queue.add(maze.getStartCell());
        maze.getStartCell().setExplored(true);
        // While the stack is not empty (once it's empty everything has been visited)
        while(!queue.isEmpty()){
            // Takes the most recent one off so the child becomes the current cell
            MazeCell currCell = queue.remove();
            for(MazeCell m: findNeighbors(currCell)){
                queue.add(m);
                m.setExplored(true);
                m.setParent(currCell);
            }
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
