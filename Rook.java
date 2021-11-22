import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private final String color;
    private int totalMoves = 0;

    /**
     * Constructs a new Rook with the given color.
     * @param color the color of this Piece.
     */
    public Rook( String color ) {
        this.color = color;
    }

    /**
     * This method increments the counter that tracks how many times this King has moved. Used for Castleing.
     */
    public void plusMove() {
        totalMoves++;
    }

    /**
     * This method checks whether this King has moved. Used for Castleing.
     * @return true if this King has moved.
     */
    public boolean hasMoved() {
        return totalMoves > 0;
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



    public String color() {
        return color;
    }

    public Piece copy() {
        Rook rook = new Rook( color );
        rook.totalMoves = totalMoves;
        return rook;
    }

    public String toString() {
        return !color.equals( "black" ) ? "♜" : "♖";
    }
}