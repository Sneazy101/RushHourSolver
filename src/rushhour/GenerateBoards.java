package rushhour;

import java.util.ArrayList;

public class GenerateBoards {
     public static ArrayList<BitBoard> allPossibleBoards(BitBoard board){
        ArrayList<Integer> startIndexes = board.getAllStartIndex();
         return produceBoards(board, startIndexes);
     }

    private static ArrayList<BitBoard> produceBoards(BitBoard board, ArrayList<Integer> startIndexes) {
        ArrayList<BitBoard> generatedBoards = new ArrayList<>();
        boolean carSize3 ;
        for (Integer startIndex : startIndexes) {
            carSize3 = false;
            if (board.getIndex(board.getHorizontal(), startIndex)) {
                if ((!board.getIndex(board.getStartBoard(), startIndex + 2)) && (board.getIndex(board.getHorizontal(), startIndex + 2))) {
                    carSize3 = true;
                }
                generatedBoards.addAll(generateAllHorizontalBoard(board, startIndex, carSize3));
            } else{
                if ((!board.getIndex(board.getStartBoard(), startIndex + 12)) && (board.getIndex(board.getVertical(), startIndex + 12))) {
                    carSize3 = true;
                }
                generatedBoards.addAll(generateAllVerticalBoard(board, startIndex, carSize3));
            }
        }
        return generatedBoards;
    }


    public static ArrayList<BitBoard> generateAllHorizontalBoard(BitBoard board, int start, boolean carsize3){
        ArrayList<BitBoard> generatedBoardsHorizontal = new ArrayList<>();
        BitBoard newBoard = board.clone();
        newBoard.setStartIndexZero(start);
        newBoard.setHorizontalIndexZero(start);
        newBoard.setHorizontalIndexZero(start+1);
        if(carsize3) {
            newBoard.setHorizontalIndexZero(start + 2);
        }
        int indexStart = start;
        int indexEnd = start;
        while(indexStart%6 !=0){
            if(newBoard.getIndex(newBoard.getFreeSpaceBoard(),indexStart-1)){
                indexStart--;
            }else{
                break;
            }
        }
        if(!carsize3){
            while(((indexEnd%6)-4) != 0){
                if(newBoard.getIndex(newBoard.getFreeSpaceBoard(),indexEnd+2)){
                    indexEnd++;
                }else{
                    break;
                }
            }
        }else{
            while(((indexEnd%6)-3) != 0){
                if(newBoard.getIndex(newBoard.getFreeSpaceBoard(),indexEnd+3)){
                    indexEnd++;
                }else{
                    break;
                }
            }
        }
        for(int i = indexStart; i<=indexEnd;i++){
            if(i == start){
                continue;
            }
            newBoard = board.clone();
            newBoard.setStartIndexZero(start);
            newBoard.setHorizontalIndexZero(start);
            newBoard.setHorizontalIndexZero(start+1);
            if(carsize3) {
                newBoard.setHorizontalIndexZero(start + 2);
            }
            newBoard.setHorizontalIndexOne(i);
            newBoard.setHorizontalIndexOne(i+1);
            newBoard.setStartIndexOne(i);
            if(carsize3) {
                newBoard.setHorizontalIndexOne(i + 2);
            }
            newBoard.setMove(new Moves(start, i));
            generatedBoardsHorizontal.add(newBoard);
        }
        return generatedBoardsHorizontal;
    }

    public static ArrayList<BitBoard> generateAllVerticalBoard(BitBoard board, int start, boolean carsize3){
        ArrayList<BitBoard> generatedBoardsVertical = new ArrayList<>();
        BitBoard newBoard = board.clone();
        newBoard.setStartIndexZero(start);
        newBoard.setVerticalIndexZero(start);
        newBoard.setVerticalIndexZero(start+6);
        newBoard.setStartIndexZero(start);
        if(carsize3) {
            newBoard.setVerticalIndexZero(start + 12);
        }
        int indexStart = start;
        int indexEnd = start;
        while((indexStart-6)>-1){
            if(newBoard.getIndex(newBoard.getFreeSpaceBoard(),indexStart-6)){
                indexStart-=6;
            }else{
                break;
            }
        }
        if(!carsize3){
            while((indexEnd+12)<36){
                if(newBoard.getIndex(newBoard.getFreeSpaceBoard(),indexEnd+12)){
                    indexEnd+=6;
                }else{
                    break;
                }
            }
        }else{
            while((indexEnd+18)<36){
                if(newBoard.getIndex(newBoard.getFreeSpaceBoard(),indexEnd+18)){
                    indexEnd+=6;
                }else{
                    break;
                }
            }
        }
        for(int i = indexStart; i<=indexEnd;i+=6){
            if(i == start){
                continue;
            }
            newBoard = board.clone();
            newBoard.setStartIndexZero(start);
            newBoard.setVerticalIndexZero(start);
            newBoard.setVerticalIndexZero(start+6);
            if(carsize3) {
                newBoard.setVerticalIndexZero(start + 12);
            }
            newBoard.setVerticalIndexOne(i);
            newBoard.setVerticalIndexOne(i+6);
            newBoard.setStartIndexOne(i);
            if(carsize3) {
                newBoard.setVerticalIndexOne(i + 12);
            }
            newBoard.setMove(new Moves(start, i));
            generatedBoardsVertical.add(newBoard);
        }
        return generatedBoardsVertical;
    }


}
