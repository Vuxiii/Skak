import java.util.ArrayList;
import java.util.List;

public class Queen implements Piece {
    private final String color;

    /**
     * Constructs a new Queen with the given color.
     * @param color the color of this Piece.
     */
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

    public Piece copy() {
        Queen queen = new Queen( color );
        return queen;
    }

    public String toString() {
        return !color.equals( "black" ) ? "♛" : "♕";
    }
}
