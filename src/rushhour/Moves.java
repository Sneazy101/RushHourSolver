package rushhour;

public class Moves {

    private  short movedFrom;
    private short movedTo;
    private Moves parentMove;

    public Moves(int i, int j){
        movedFrom = (short)i;
        movedTo = (short)j;
        parentMove = null;

    }

    public Moves getCurrentMove(){
        return this;
    }

    public int getMoveFrom(){
        return (int)movedFrom;
    }

    public int getMoveTo(){
        return (int)movedTo;
    }

    public void setParentMove(Moves m){
        parentMove = m;
    }

    public Moves getParentMove(){
        return parentMove;
    }

    public void setMoveFrom(int move){
        movedFrom = (short)move;
    }

    public void setMoveTo(int move){ movedTo = (short)move; }

}
