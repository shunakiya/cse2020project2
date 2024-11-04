import java.util.*;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Stack;

public class DepthFirstSearch implements GraphSearchAlgorithm {

  public Path search(State start, State goal) {

    Stack <Path> stack = new Stack<>(); //stack for path

    HashSet <State> visited = new HashSet<>(); //check if path was visited

    stack.push(new Path(start));

    while (!stack.isEmpty()) { 
      
      Path currPath = stack.pop();
      State currState = currPath.getLastState();

      if (currState.equals(goal)) { //checks to see if current path is equal to the expected goal
        return currPath;
      }

      if (!visited.contains(currState)) { //this if statement checks visited path is the current path
        
        visited.add(currState);
        
        for (Action action: currState.getActions()) {
          State stateNext = action.getNextState();
          
          if (!visited.contains(stateNext)) {
            Path newPath = new Path(currPath, action);
            stack.push(newPath); 
          }
        }
      }
    }
    return null;
  }
}
