import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private final PieceColor color;
    private int lastMoved = 0;

    /**
     * Constructs a new Rook with the given color.
     * @param color the color of this Piece.
     */
    public Rook( PieceColor color ) {
        this.color = color;
    }

    public PieceColor color() {
        return color;
    }

    
    public static List< Path > getPossibleMoves( Cell pos ) {
        // List< Move > moves = new ArrayList<>();
        Path verticalUp = new Path();
        Path verticalDown = new Path();
        Path horizontalLeft = new Path();
        Path horizontalRight = new Path();
        List< Path > paths = new ArrayList<>();
        paths.add( verticalUp );
        paths.add( verticalDown );
        paths.add( horizontalLeft );
        paths.add( horizontalRight );

        // left
        for ( char i = (char) (pos.col - 1); i >= 'A'; i-- ) {
            Cell to = new Cell( i, pos.row );
            horizontalLeft.addMove( new Move( pos, to ) );
        }
        // right
        for ( char i = (char) (pos.col + 1); i <= 'H'; i++ ) {
            Cell to = new Cell( i, pos.row );
            horizontalRight.addMove( new Move( pos, to ) );
        }
        
        // up
        for ( int i = pos.row - 1; i >= 1; i-- ) {
            Cell to = new Cell( pos.col, i );
            verticalUp.addMove( new Move( pos, to ) );
        }
        // down
        for ( int i = pos.row + 1; i <= 8; i++ ) {
            Cell to = new Cell( pos.col, i );
            verticalDown.addMove( new Move( pos, to ) );
        }

        return paths;
    }
    
    public List< Path > getMoves( Cell pos ) {
        return Rook.getPossibleMoves( pos );
    }



    
    public Piece copy() {
        Rook rook = new Rook( color );
        rook.lastMoved = lastMoved;
        return rook;
    }

    public String toString() {
        return color != PieceColor.BLACK ? "♜" : "♖";
    }

    @Override
    public void lastUsed(int tick) {
        lastMoved = tick;
    }

    @Override
    public int lastUsed() {
        return lastMoved;
    }
}