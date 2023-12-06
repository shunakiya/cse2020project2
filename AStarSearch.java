import java.util.HashSet;

public class AStarSearch implements GraphSearchAlgorithm {

    public Path search(State start, State goal) {
        PathPriorityQueue queue = new PathPriorityQueue(); //queue that stores the path (cost + heuristic)

        HashSet<State> explored = new HashSet<>(); //list to store explored path

        queue.add(new Path(start), start.heuristicTo(goal)); //begin the queue with start state

        while (!queue.isEmpty()) { //while loop until queue is empty
            Path pathNow = queue.poll(); //gets path with smallest cost and heuristic
            State stateNow = pathNow.getLastState();

            if (stateNow.equals(goal)) { //if the goal is reached, output the solution path
                return pathNow;
            }
            
            for (Action action: stateNow.getActions()) { //if not continue iterating through the states
                State stateNext = action.getNextState();

                if (!explored.contains(stateNext)) { //checks if next state has been explored or not
                    Path newPath = new Path(pathNow, action); //creates new path to extend the current path

                    int pathCost = newPath.getCost(); //calculates the newpath and heuristic
                    int heuristicEst = stateNext.heuristicTo(goal);

                    queue.add(newPath, pathCost + heuristicEst); //add new path to priority queue (cost + heuristic)
                }
            }
            explored.add(stateNow); //adds the current state to list
        }

        return null; //if no path is found, return null
    }
}
