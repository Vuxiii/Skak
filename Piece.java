import java.util.ArrayList;
import java.util.List;

public interface Piece {

    /**
     * This metod returns the color of this piece.
     * @return The color of this Piece. "black" | "white"
     */
    public String color();

    /**
     * This method computes all of the possible moves for this Piece based on the current position and color.
     * This does not check if the move is valid, or if another Piece blocks it's way.
     * @param pos The Cell on the board where this Piece is located.
     * @return A list of moves where this Piece can possibly move.
     */
    public static List< Move > getPossibleMoves( Cell pos ) {
        return new ArrayList< Move >();
    }
    /**
     * This method computes all of the possible moves for this Piece based on the current position and color.
     * This does not check if the move is valid, or if another Piece blocks it's way.
     * @param pos The Cell on the board where this Piece is located.
     * @return A list of moves where this Piece can possibly move.
     */
    public List< Move > getMoves( Cell pos );
}
