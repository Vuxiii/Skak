import java.util.ArrayList;
import java.util.List;

public class Bishop implements Piece {
    private final PieceColor color;

    private int lastMoved = 0;

    /**
     * Constructs a new Bishop with the given color.
     * @param color the color of this Piece.
     */
    public Bishop( PieceColor color ) {
        this.color = color;
    }

    public PieceColor color() {
        return color;
    }
    
    public static List< Path > getPossibleMoves( Cell pos ) {
        // List< Move > moves = new ArrayList<>();
        
        List< Path > paths = new ArrayList<>();

        Path leftUp = new Path();
        // left up
        for ( int i = 1; i <= pos.col - 'A' && pos.row - i >= 1; i++ ) {
            Cell to = new Cell( (char) (pos.col - i), pos.row - i );
            // moves.add( new Move( pos, to ) );
            leftUp.addMove( new Move( pos, to ) );
        }
        Path leftDown = new Path();
        // left down
        for ( int i = 1; i <= pos.col - 'A' && pos.row + i <= 8; i++ ) {
            Cell to = new Cell( (char) (pos.col - i), pos.row + i );
            // moves.add( new Move( pos, to ) );
            leftDown.addMove( new Move( pos, to ) );
        }
        Path rightUp = new Path();
        // right up
        for ( int i = 1; i <= 'H' - pos.col && pos.row - i >= 1; i++ ) {
            Cell to = new Cell( (char) (pos.col + i), pos.row - i );
            // moves.add( new Move( pos, to ) );
            rightUp.addMove( new Move( pos, to ) );
        }
        Path rightDown = new Path();
        // right down
        for ( int i = 1; i <= 'H' - pos.col && pos.row + i <= 8; i++ ) {
            Cell to = new Cell( (char) (pos.col + i), pos.row + i );
            // moves.add( new Move( pos, to ) );
            rightDown.addMove( new Move( pos, to ) );
        }

        paths.add( leftDown );
        paths.add( leftUp );
        paths.add( rightUp );
        paths.add( rightDown );

        return paths;
    }
    
    public Piece copy() {
        Bishop bishop = new Bishop( color );
        return bishop;
    }

    public List< Path > getMoves( Cell pos ) {
        return Bishop.getPossibleMoves( pos );
    }

    public String toString() {
        return color != PieceColor.BLACK ? "♝" : "♗";
    }

    @Override
    public void lastUsed(int tick) {
        lastMoved = tick;
    }

    @Override
    public int lastUsed() {
        return lastMoved;
    }
}
