import java.util.ArrayList;
import java.util.List;


public interface Piece {

    /**
     * This metod returns the color of this piece.
     * @return The color of this Piece. "black" | "white"
     */
    public PieceColor color();

    /**
     * This method returns a copy of this Piece.
     * @return a copy of this Piece.
     */
    public Piece copy();

    /**
     * This method computes all of the possible moves for this Piece based on the current position and color.
     * This does not check if the move is valid, or if another Piece blocks it's way.
     * @param pos The Cell on the board where this Piece is located.
     * @return A list of moves where this Piece can possibly move.
     */
    public static List< Path > getPossibleMoves( Cell pos ) {
        return new ArrayList< Path >();
    }
    /**
     * This method computes all of the possible moves for this Piece based on the current position and color.
     * This does not check if the move is valid, or if another Piece blocks it's way.
     * @param pos The Cell on the board where this Piece is located.
     * @return A list of moves where this Piece can possibly move.
     */
    public List< Path > getMoves( Cell pos );

    /**
     * This method updates the the last tick that this Piece has been moved.
     * @param tick the total amount of moves made so far for the entire game.
     */
    public void lastUsed( int tick );

    /**
     * This method returns the last tick this Piece was moved.
     * If the Piece hasn't moved at all this game, returns 0
     * @return the tick this Piece was moved.
     */
    public int lastUsed();
}
