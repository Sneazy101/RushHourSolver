package rushhour;

import java.util.ArrayList;

public class BitBoard {

    private long horizontalBoard;
    private long verticalBoard;
    private long startBoard;
    private Moves mv;

    public BitBoard(String s) {
        horizontalBoard = 0b0L;
        verticalBoard = 0b0L;
        String emptybit = "000000000000000000000000000000000000";
        String[] horizontalBinary = emptybit.split("");
        String[] verticalBinary = emptybit.split("");
        String[] startBinary = emptybit.split("");
        for (int i = 0; i < 36; i++) {
            if (s.charAt(i) != '.') {
                if (i != 35 && s.charAt(i) == s.charAt(i + 1)) {
                    horizontalBinary[i] = "1";
                    horizontalBinary[i + 1] = "1";
                    if ((i - 1) < 0 || s.charAt(i - 1) != s.charAt(i)) {
                        startBinary[i] = "1";
                    }
                } else if (i < 30 && s.charAt(i + 6) == s.charAt(i)) {
                    verticalBinary[i] = "1";
                    verticalBinary[i + 6] = "1";
                    if ((i - 6) < 0 || s.charAt(i - 6) != s.charAt(i)) {
                        startBinary[i] = "1";
                    }
                }
            }
        }
        StringBuilder Hb = new StringBuilder();
        StringBuilder Vb = new StringBuilder();
        StringBuilder Sb = new StringBuilder();
        for (int i = 0; i < 36; i++) {
            Hb.append(horizontalBinary[i]);
            Vb.append(verticalBinary[i]);
            Sb.append(startBinary[i]);
        }
        horizontalBoard = string2BitBoard(Hb.toString());
        verticalBoard = string2BitBoard(Vb.toString());
        startBoard = string2BitBoard(Sb.toString());

        horizontalBoard = horizontalBoard<< 28;
        verticalBoard = verticalBoard<< 28;
        startBoard = startBoard<< 28;
        mv = null;
    }

    public BitBoard(long  hor, long ver, long sta){
        horizontalBoard = hor;
        verticalBoard = ver;
        startBoard = sta;
        mv = null;
    }

    public long getHorizontal() {
        return horizontalBoard;
    }

    public long getVertical(){
        return verticalBoard;
    }

    public long getStartBoard(){return startBoard;}

    public long getFreeSpaceBoard(){
        long FreeSpaceBoard = horizontalBoard+verticalBoard;
        return ~FreeSpaceBoard;
    }

    public Moves getMoves(){
        return mv;
    }

    public int getMoveTo(){
        return mv.getMoveTo();
    }

    public int getMoveFrom(){
        return mv.getMoveFrom();
    }

    public void setMove(Moves m){
        mv = m;
    }

    public long string2BitBoard(String binary){
        return Long.parseLong(binary, 2);
    }

    public static String BitBoard2str(long number){
        String zero;
        String binary = Long.toBinaryString(number);
        while(binary.length() < 64){
            zero = "0";
            zero += binary;
            binary = zero;
        }
        return binary;
    }

    public static void printBitBoard(long board){
        System.out.println("---------------------------");
        String c = BitBoard2str(board);
        for(int i=0; i < 36; i+=6){
            System.out.println(c.substring(i, i+6));
        }
    }

    public static void printAll(BitBoard board){
        System.out.println("Start Board");
        printBitBoard(board.getStartBoard());
        System.out.println("Vertical Board");
        printBitBoard(board.getVertical());
        System.out.println("Horizontal Board");
        printBitBoard(board.getHorizontal());
    }

    public boolean getIndex(long board, int index){
        index = 63 - index;
        return ((board >> index)&1)==1;
    }

    public void setHorizontalIndexOne(int index){
        index = 63 - index;
        long setter = 1;
        setter = setter << index;
        horizontalBoard = horizontalBoard|setter;
    }

    public void setVerticalIndexOne(int index){
        index = 63 - index;
        long setter = 1;
        setter = setter << index;
        verticalBoard = verticalBoard|setter;
    }

    public void setStartIndexOne(int index){
        index = 63 - index;
        long setter = 1;
        setter = setter << index;
        startBoard = startBoard|setter;
    }

    public void setHorizontalIndexZero(int index){
        index = 63 - index;
        long setter = 1;
        setter = setter << index;
        setter = ~setter;
        horizontalBoard = horizontalBoard&setter;
    }

    public void setVerticalIndexZero(int index){
        index = 63 - index;
        long setter = 1;
        setter = setter << index;
        setter = ~setter;
        verticalBoard = verticalBoard&setter;
    }

    public void setStartIndexZero(int index){
        index = 63 - index;
        long setter = 1;
        setter = setter << index;
        setter = ~setter;
        startBoard = startBoard&setter;
    }

    public ArrayList<Integer> getAllStartIndex(){
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i=0; i < 36; i++){
            if(getIndex(startBoard, i)){
                indexes.add(i);
            }
        }
        return indexes;
    }

    public boolean hasWon(){
        return (((horizontalBoard >> 46)&1)==1);
    }

    public boolean isHorizontal(int index){
        return (getIndex(getHorizontal(), index));
    }

    public boolean isSize3(int startIndex){
        if(isHorizontal(startIndex)){
            return(getIndex(getHorizontal(), startIndex+2) && (!getIndex(getStartBoard(), startIndex+2)));
        }else{
            return(getIndex(getVertical(), startIndex+12) && (!getIndex(getStartBoard(), startIndex+12)));
        }
    }

    @Override
    public boolean equals(Object obj){
        final BitBoard board2 = (BitBoard)obj;
        return (board2.startBoard == this.startBoard);
    }

    @Override
    public BitBoard clone(){
        return new BitBoard(this.getHorizontal(), this.getVertical(), this.getStartBoard());
    }

    @Override
    public int hashCode(){
        return Long.hashCode(startBoard);
    }

}
