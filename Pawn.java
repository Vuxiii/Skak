import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private final String color;

    public Pawn( String color ) {
        this.color = color;
    }

    public String color() {
        return color;
    }

    public String toString() {
        return color == "black" ? "♟" : "♙";
    }
    
    public static List< Move > getPossibleMoves( Cell pos, String color ) {
        List< Move > moves = new ArrayList<>();

        // Adds the move two cells forward.
        if ( ( color == "black" && pos.row != 7 ) || pos.row != 2 ) {
            Cell to = new Cell( pos.col, pos.row + ( color == "black" ? -2 : 2 ) );
            moves.add( new Move( pos, to ) );
        }

        // Add move one cell forward.
        Cell to = new Cell( pos.col, pos.row + ( color == "black" ? -1 : 1 ) );
        moves.add( new Move( pos, to ) );
        
        // Add kill left.
        if ( pos.col > 'A' ) {
            to = new Cell( (char) (pos.col - 1), pos.row + ( color == "black" ? -1 : 1 ) );
            moves.add( new Move( pos, to ) );
        }
        
        // Add kill right.
        if ( pos.col < 'H' ) {
            to = new Cell( (char) (pos.col + 1), pos.row + ( color == "black" ? -1 : 1 ) );
            moves.add( new Move( pos, to ) );
        }

        return moves;
    }
    
    public List< Move > getMoves( Cell pos ) {
        return Pawn.getPossibleMoves( pos, color );
    }
}
