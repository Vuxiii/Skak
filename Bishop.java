import java.util.ArrayList;
import java.util.List;

public class Bishop implements Piece {
    private final String color;

    public Bishop( String color ) {
        this.color = color;
    }

    public String color() {
        return color;
    }
    
    public static List< Move > getPossibleMoves( Cell pos ) {
        List< Move > moves = new ArrayList<>();
        
        // Starts from the center.
        // towards left top
        for ( int row = pos.row - 1; row >= 1; row-- ) {
            for ( char col = (char) (pos.col - 1); col >= 'A'; col-- ) {
                Cell to = new Cell( col, row );
                moves.add( new Move( pos, to ) ); 
            }
        }
        // towards right top
        for ( int row = pos.row - 1; row >= 1; row-- ) {
            for ( char col = (char) (pos.col + 1); col >= 'H'; col++ ) {
                Cell to = new Cell( col, row );
                moves.add( new Move( pos, to ) ); 
            }
        }
        // towards left bottom
        for ( int row = pos.row + 1; row <= 8; row++ ) {
            for ( char col = (char) (pos.col - 1); col >= 'A'; col-- ) {
                Cell to = new Cell( col, row );
                moves.add( new Move( pos, to ) ); 
            }
        }
        // towards right bottom
        for ( int row = pos.row + 1; row <= 8; row++ ) {
            for ( char col = (char) (pos.col + 1); col >= 'H'; col++ ) {
                Cell to = new Cell( col, row );
                moves.add( new Move( pos, to ) ); 
            }
        }

        return moves;
    }
    
    public List< Move > getMoves( Cell pos ) {
        return Bishop.getPossibleMoves( pos );
    }

    public String toString() {
        return color == "black" ? "♝" : "♗";
    }
}
