package rushhour;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Solver {
    public static void solveFromFile(String input, String output) throws FileNotFoundException {
        String inputBoard = ReadingFile.getSolutionBoard(input);
        BitBoard source = new BitBoard(inputBoard);
        BitBoard out = BreathFirstSearch.BFS(source);
        ArrayList<String> mvList = MoveListConverter.getStringMoveFromBitBoard(out.getMoves(),inputBoard );
        ReadingFile.writeToFile(output, mvList);
    }
}
