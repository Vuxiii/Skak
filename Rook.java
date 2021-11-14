import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private final String color;

    public Rook( String color ) {
        this.color = color;
    }

    public String color() {
        return color;
    }
    
    public static List< Move > getPossibleMoves( Cell pos ) {
        List< Move > moves = new ArrayList<>();
        
        // Adds the vertical moves
        for ( int i = 1; i <= 8; i++ ) {
            if ( i != pos.row ) {
                Cell to = new Cell( pos.col, i );
                moves.add( new Move( pos, to ) );
            }
        }

        // Adds the horizontal moves
        for ( int i = 'A'; i <= 'H'; i++ ) {
            if ( i != pos.col ) {
                Cell to = new Cell( (char) i, pos.row );
                moves.add( new Move( pos, to ) );
            }
        }

        return moves;
    }
    
    public List< Move > getMoves( Cell pos ) {
        return Rook.getPossibleMoves( pos );
    }

    public String toString() {
        return color == "black" ? "♜" : "♖";
    }
}