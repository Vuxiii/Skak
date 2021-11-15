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
    
    public static List< Path > getPossibleMoves( Cell pos, String color ) {
        // List< Move > moves = new ArrayList<>();

        List< Path > paths = new ArrayList<>();

        Path forward = new Path(); 



        // Add move one cell forward.
        Cell to = new Cell( pos.col, pos.row + ( color == "black" ? 1 : -1 ) );
        // moves.add( new Move( pos, to ) );
        forward.addMove( new Move( pos, to ) );

        // Adds the move two cells forward.
        if ( ( color == "black" && pos.row != 7 ) || pos.row != 2 ) {
            to = new Cell( pos.col, pos.row + ( color == "black" ? 2 : -2 ) );
            // moves.add( new Move( pos, to ) );
            forward.addMove( new Move( pos, to ) );
        }
        paths.add( forward );
        Path killLeft = new Path();
        // Add kill left.
        if ( pos.col > 'A' ) {
            to = new Cell( (char) (pos.col - 1), pos.row + ( color == "black" ? 1 : -1 ) );
            // moves.add( new Move( pos, to ) );
            killLeft.addMove( new Move( pos, to ) );
        }
        Path killRight = new Path();
        // Add kill right.
        if ( pos.col < 'H' ) {
            to = new Cell( (char) (pos.col + 1), pos.row + ( color == "black" ? 1 : -1 ) );
            // moves.add( new Move( pos, to ) );
            killRight.addMove( new Move( pos, to ) );
        }
        paths.add( killLeft );
        paths.add( killRight );

        return paths;
    }
    
    public List< Path > getMoves( Cell pos ) {
        return Pawn.getPossibleMoves( pos, color );
    }
}
