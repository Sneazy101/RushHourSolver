package rushhour;

import java.util.*;

public class BreathFirstSearch {

    public static BitBoard BFS(BitBoard st) {
        BitBoard source = st;
        HashSet<Long> visited = new HashSet<>(100000);
        Queue<BitBoard> queue = new LinkedList<>();
        LinkedList<BitBoard> genBoards = new LinkedList<>();
        source.setMove(null);
        queue.add(source);
        while (queue.size() != 0) {
            source = queue.remove();
            if (!visited.contains(source.getStartBoard())) {
                visited.add(source.getStartBoard());
                genBoards.addAll(GenerateBoards.allPossibleBoards(source));
                while (genBoards.size() != 0) {
                    BitBoard currentBoard = genBoards.pop();
                    currentBoard.getMoves().setParentMove(source.getMoves());
                    if (currentBoard.hasWon()) {
                        return currentBoard;
                    } else {
                        if (!(visited.contains(currentBoard.getStartBoard()))) {
                            queue.add(currentBoard);
                        }
                    }
                }
            }
        }
        System.out.println("No winner");
        return null;
    }
}
