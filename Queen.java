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
    
    public static List< Path > getPossibleMoves( Cell pos ) {
        // List< Move > moves = new ArrayList<>();
        List< Path > paths = new ArrayList<>();
        paths.addAll( Rook.getPossibleMoves( pos ) );
        paths.addAll( Bishop.getPossibleMoves( pos ) );

        return paths;
    }
    
    public List< Path > getMoves( Cell pos ) {
        return Queen.getPossibleMoves( pos );
    }

    public String toString() {
        return color == "black" ? "♛" : "♕";
    }
}
