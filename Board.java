import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Board {

    private String boardWhiteCell = "\u2B1C"; //2b1b

    private Map< Character, Integer > charToInt; 

    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        initPieces();

        initCharToInt();
        List< Move > moves = getLegalMoves();
        printMoves( moves );
    }

    public void printMoves( List< Move > moves ) {
        for ( Move m : moves )
            System.out.println( "[" + (char) m.from.col + "," + m.from.row + "] -> [" + (char) m.to.col + "," + m.to.row + "]" );
    } 

    /**
     * This method initializes the HashMap containing the mappings of the Cell's character to an Integer value usable for board.
     */
    private void initCharToInt() {
        charToInt = new HashMap< Character, Integer >();
        charToInt.put( 'A', 1 );
        charToInt.put( 'B', 2 );
        charToInt.put( 'C', 3 );
        charToInt.put( 'D', 4 );
        charToInt.put( 'E', 5 );
        charToInt.put( 'F', 6 );
        charToInt.put( 'G', 7 );
        charToInt.put( 'H', 8 );
        charToInt.put( 'a', 1 );
        charToInt.put( 'b', 2 );
        charToInt.put( 'c', 3 );
        charToInt.put( 'd', 4 );
        charToInt.put( 'e', 5 );
        charToInt.put( 'f', 6 );
        charToInt.put( 'g', 7 );
        charToInt.put( 'h', 8 );
    }

    /**
     * This method places all of the pieces on the board.
     */
    private void initPieces() {
        initBlackPieces();
        initWhitePieces();
    }

    /**
     * This method places all of the black pieces.
     */
    private void initBlackPieces() {
        String col = "black";
        initPawns( col );
        initBishops( col );
        initKnights( col );
        initRooks( col );
        initQueen( col );
        initKing( col );        
    }

    /**
     * This method places all of the white pieces.
     */
    private void initWhitePieces() {
        String col = "white";
        initPawns( col );
        initBishops( col );
        initKnights( col );
        initRooks( col );
        initQueen( col );
        initKing( col );       
    }
    
    /**
     * This method simply places the pawns on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initPawns( String color ) {
        for ( int i = 0; i < 8; i++ )
            board[ color.equals( "black" ) ? 1 : 6 ][ i ] = new Pawn( color );
    }
    
    /**
     * This method simply places the bishops on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initBishops( String color ) {
        board[ color.equals( "black" ) ? 0 : 7 ][ 2 ] = new Bishop( color );
        board[ color.equals( "black" ) ? 0 : 7 ][ 5 ] = new Bishop( color );
    }
    
    /**
     * This method simply places the knights on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initKnights( String color ) {
        board[ color.equals( "black" ) ? 0 : 7 ][ 1 ] = new Knight( color );
        board[ color.equals( "black" ) ? 0 : 7 ][ 6 ] = new Knight( color );
    }
    
    /**
     * This method simply places the rooks on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initRooks( String color ) {
        board[ color.equals( "black" ) ? 0 : 7 ][ 0 ] = new Rook( color );
        board[ color.equals( "black" ) ? 0 : 7 ][ 7 ] = new Rook( color );
    }

    /**
     * This method simply places the queens on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initQueen( String color ) {
        board[ color.equals( "black" ) ? 0 : 7 ][ 3 ] = new Queen( color );
    }

    /**
     * This method simply places the kings on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initKing( String color ) {
        board[ color.equals( "black" ) ? 0 : 7 ][ 4 ] = new King( color );
    }

    /**
     * This method returns a List of legal moves that are possible based on the current Board.
     */
    public List< Move > getLegalMoves() {
        List< Move > moves = new ArrayList<>();

        for ( int row = 0; row < board.length; row++ ) {
            for ( int col = 0; col < board[0].length; col++ ) {
                Piece p = board[ row ][ col ];
                if ( p != null ) {
                    Cell pos = new Cell( (char) ( col + 'A' ), 1 + row );
                    moves.addAll( p.getMoves( pos ) );
                }
            }
        }

        return moves;
    }

    /**
     * Moves the piece given at the location move.from to the location move.to
     * Precondition: The move is valid.
     */
    public void move( Move move ) {
        Cell from = move.from;
        Cell to = move.to;

        board[ to.row -1 ][ charToInt.get( to.col ) -1 ] = board[ from.row -1 ][ charToInt.get( from.col ) -1 ];
        board[ from.row -1 ][ charToInt.get( from.col ) -1 ] = null;
        // System.out.println( board[ charToInt.get( to.col ) ][ to.row ].toString() + charToInt.get( to.col ) + "   " + to.row);

    }

    /**
     * This method returns a textual representation of this board.
     */
    public String toString() {
        String s = "";
        int c = 0;

        int downCounter = 1;

        for ( Piece[] row : board ) {
            s += "+--+--+--+--+--+--+--+--+\n";
            s += "|";
            for ( Piece p : row ) {
                if ( p != null )
                    s += " " + p.toString();
                else
                    s += c % 2 == 0 ? boardWhiteCell : "  ";
                s += "|";
                c++;
            }
            c++;
            s += " " + downCounter++ + "\n";
        }
        s += "+--+--+--+--+--+--+--+--+\n";
        s += "  A  B  C  D  E  F  G  H\n";
        return s;
    }

}