package rushhour;

import java.util.ArrayList;
import java.util.Stack;

public class MoveListConverter {
    public static ArrayList<String> getStringMoveFromBitBoard(Moves mv, String strBoard){
        ArrayList<String> moveList = new ArrayList<>();
        Stack<Moves> stack = new Stack<>();
        stack.push(mv);
        while(mv != null){
            stack.push(mv.getParentMove());
            mv = mv.getParentMove();
        }
        stack.pop();
        while(!stack.isEmpty()){
            mv = stack.pop();
            int startPoint = mv.getMoveFrom();
            int endPoint = mv.getMoveTo();
            moveList.add( int2stringmove(startPoint, endPoint, strBoard));
            strBoard = changeStringBoard(startPoint, endPoint, strBoard);
        }
        return moveList;
    }

    public static String int2stringmove(int a, int b, String board){

        char[] output = new char[3];
        output[0] = board.charAt(a);
        if(Math.abs(b - a) > 5){
            //Vertical Move
            if(b > a){
                output[1] = 'D';
                output[2] = (char) ('0'+ ((b-a)/6));
            }else {
                output[1]= 'U';
                output[2] = (char) ('0'+ ((a-b)/6));
            }
        }else{
            //Horizontal Move
            if(b > a){
                output[1] = 'R';
                output[2] = (char) ('0'+ (b-a));
            }else{
                output[1] = 'L';
                output[2] = (char) ('0'+ (a-b));
            }
        }
        return new String(output);
    }

    public static String changeStringBoard(int a, int b, String board){
        char charA = board.charAt(a);
        char charB = board.charAt(b);

        char[] charBoard = board.toCharArray();
        charBoard[b] = charA;
        charBoard[a] = charB;

        return new String(charBoard);
    }

}


