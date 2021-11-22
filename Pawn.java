import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private final String color;
    private int totalMoves = 0;

    private Cell lastPos;

    /**
     * Constructs a new Pawn with the given color.
     * @param color the color of this Piece.
     */
    public Pawn( String color, Cell cell ) {
        this.color = color;
        lastPos = cell;
    }

    /**
     * Returns the last Cell this Pawn was located at.
     */
    public Cell lastPost() {
        return lastPos;
    }

    /**
     * This method updates this Pawn's last position. Used for En Passante Move.
     * Precondition: The given cell is the last move this Pawn made.
     * @param cell The Cell where this Pawn was last located.
     */
    public void updateLastPos( Cell cell ) {
        lastPos = cell;
    }

    /**
     * Increments how many times this Pawn has moved. Used for En Passante Move.
     */
    public void plusMove() {
        totalMoves++;
    }

    /**
     * Returns the total moves this Pawn has made. Used for En Passante Move.
     */
    public int totalMoves() {
        return totalMoves;
    }

    /**
     * Checks if this Pawn has made a move yet.
     * @param currentPosition the current Cell of this Pawn
     * @return Whether this Pawn has made any moves.
     */
    public boolean hasMoved() {
        return totalMoves > 0;
    }

    public String color() {
        return color;
    }

    public String toString() {
        return !color.equals( "black" ) ? "♟" : "♙";
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
    
    public Piece copy() {
        Pawn pawn = new Pawn( color, lastPos );
        pawn.totalMoves = totalMoves;
        return pawn;
    }

    public List< Path > getMoves( Cell pos ) {
        return Pawn.getPossibleMoves( pos, color );
    }
}
