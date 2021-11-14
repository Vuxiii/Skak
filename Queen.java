import java.util.ArrayList;
import java.util.List;

public class Queen implements Piece {
    private final String color;

    public Queen( String color ) {
        this.color = color;
    }

    public String color() {
        return color;
    }
    
    public static List< Move > getPossibleMoves( Cell pos ) {
        List< Move > moves = new ArrayList<>();
        
        moves.addAll( Rook.getPossibleMoves( pos ) );
        moves.addAll( Bishop.getPossibleMoves( pos ) );

        return moves;
    }
    
    public List< Move > getMoves( Cell pos ) {
        return Queen.getPossibleMoves( pos );
    }

    public String toString() {
        return color == "black" ? "♛" : "♕";
    }
}
