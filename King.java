import java.util.ArrayList;
import java.util.List;

public class King implements Piece {
    private final String color;

    public King( String color ) {
        this.color = color;
    }

    public String color() {
        return color;
    }
    
    public static List< Move > getPossibleMoves( Cell pos ) {
        List< Move > moves = new ArrayList<>();
        
        // Check upper bound of board.
        if ( pos.row != 1 ) {
            Cell to = new Cell( pos.col, pos.row - 1 );
            moves.add( new Move( pos, to ) );
        }
        
        // Check lower bound of board.
        if ( pos.row != 8 ) {
            Cell to = new Cell( pos.col, pos.row + 1 );
            moves.add( new Move( pos, to ) );
        }
        // Check left bound of board.
        if ( pos.col != 'A' ) {
            Cell to = new Cell( (char) (pos.col - 1), pos.row );
            moves.add( new Move( pos, to ) );
        }
        // Check right bound of board.
        if ( pos.col != 'H' ) {
            Cell to = new Cell( (char) (pos.col + 1), pos.row );
            moves.add( new Move( pos, to ) );
        }

        return moves;
    }
    
    public List< Move > getMoves( Cell pos ) {
        return King.getPossibleMoves( pos );
    }

    public String toString() {
        return color == "black" ? "♚" : "♔";
    }
}
