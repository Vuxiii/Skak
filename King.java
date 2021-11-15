import java.util.ArrayList;
import java.util.List;

public class King implements Piece {
    private final String color;

    /**
     * Constructs a new King with the given color.
     * @param color the color of this Piece.
     */
    public King( String color ) {
        this.color = color;
    }

    public String color() {
        return color;
    }
    
    public static List< Path > getPossibleMoves( Cell pos ) {
        // List< Move > moves = new ArrayList<>();

        List< Path > paths = new ArrayList<>();
        Path up = new Path();
        Path down = new Path();
        Path left = new Path();
        Path right = new Path();
        // Check upper bound of board.
        if ( pos.row != 1 ) {
            Cell to = new Cell( pos.col, pos.row - 1 );
            up.addMove( new Move( pos, to ) );
        }
        
        // Check lower bound of board.
        if ( pos.row != 8 ) {
            Cell to = new Cell( pos.col, pos.row + 1 );
            down.addMove( new Move( pos, to ) );

        }
        // Check left bound of board.
        if ( pos.col != 'A' ) {
            Cell to = new Cell( (char) (pos.col - 1), pos.row );
            left.addMove( new Move( pos, to ) );
        }
        // Check right bound of board.
        if ( pos.col != 'H' ) {
            Cell to = new Cell( (char) (pos.col + 1), pos.row );
            right.addMove( new Move( pos, to ) );
        }
        paths.add( up );
        paths.add( down );
        paths.add( left );
        paths.add( right );
        return paths;
    }
    
    public List< Path > getMoves( Cell pos ) {
        return King.getPossibleMoves( pos );
    }

    public String toString() {
        return !color.equals( "black" ) ? "♚" : "♔";
    }
}
