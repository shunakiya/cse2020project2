import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;

public class BreadthFirstSearch implements GraphSearchAlgorithm {

    public Path search(State start, State goal) {
        Queue<Path> queue = new LinkedList<>(); //queue that stores the paths
        HashSet<State> explored = new HashSet<>(); //keeps track of the explored states

        queue.add(new Path(start)); //starts the queue

        while (!queue.isEmpty()) { //while the queue is not empty, repeat this loop
            Path pathNow = queue.poll(); //gets the path at the front of the queue
            State stateNow = pathNow.getLastState();

            if (stateNow.equals(goal)) { //if the goal is reached then return the current path
                return pathNow; 
            }

            for (Action action: stateNow.getActions()) { //if not continue to iterate through  other states
                State stateAfter = action.getNextState();

                if (!explored.contains(stateAfter)) { //checks if the next state has been explored yet
                    Path newPath = new Path(pathNow, action); //creates new path by extending current path
                 
                    queue.add(newPath); //adds the new path to the end of the queue
                    explored.add(stateAfter); //marks next state as explored
                }
            }
        }
        return null; //if no path is found return null
    }
}
