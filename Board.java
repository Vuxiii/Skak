import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Board {

    private String boardWhiteCell = "  "; //"\u2B1C"

    private Map< Character, Integer > charToInt; 

    private Piece[][] board;

    private boolean dbg = false; // debug.

    public Board() {
        initCharToInt();
        
        board = new Piece[8][8];
        initPieces();

        
        //List< Move > moves = getLegalMoves();
        //printMoves( moves );
    }

    private void debug( String msg ) {
        if (dbg)
            System.out.print( msg );
    }

    public void printMoves( List< Move > moves ) {
        for ( Move m : moves )
            System.out.println( m );
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
        for ( char i = 'A'; i <= 'H'; i++ )
            setPiece( new Cell( i, color.equals( "black" ) ? 2 : 7 ), new Pawn( color ) );
    }
    
    /**
     * This method simply places the bishops on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initBishops( String color ) {
        setPiece( new Cell( 'C', color.equals( "black" ) ? 1 : 8 ), new Bishop( color ) );
        setPiece( new Cell( 'F', color.equals( "black" ) ? 1 : 8 ), new Bishop( color ) );
    }
    
    /**
     * This method simply places the knights on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initKnights( String color ) {
        setPiece( new Cell( 'B', color.equals( "black" ) ? 1 : 8 ), new Knight( color ) );
        setPiece( new Cell( 'G', color.equals( "black" ) ? 1 : 8 ), new Knight( color ) );
    }
    
    /**
     * This method simply places the rooks on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initRooks( String color ) {
        setPiece( new Cell( 'A', color.equals( "black" ) ? 1 : 8), new Rook( color ) );
        setPiece( new Cell( 'H', color.equals( "black" ) ? 1 : 8), new Rook( color ) );
    }

    /**
     * This method simply places the queens on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initQueen( String color ) {
        setPiece( new Cell( 'D', color.equals( "black" ) ? 1 : 8 ), new Queen( color ) );
    }

    /**
     * This method simply places the kings on the board at the correct position.
     * @param color indicates what color this piece is.
     */
    private void initKing( String color ) {
        setPiece( new Cell( 'E', color.equals( "black" ) ? 1 : 8 ), new King( color ) );
    }

    /**
     * This method returns the Piece at the given Cell.
     * @param cell the Cell on the Board where the Piece is located.
     * @return the Piece in the given Cell
     */
    private Piece getPiece( Cell cell ) {
        return board[ cell.row - 1 ][ charToInt.get( cell.col ) - 1 ];
    }

    /**
     * This method places the given Piece into the given Cell on the Board.
     * @param cell the Cell to place the given Piece.
     * @param newPiece the Piece to be places in the Cell
     * @return the Piece that was previously in the Cell. Null if none.
     */
    private Piece setPiece( Cell cell, Piece newPiece ) {
        Piece ret = getPiece( cell );
        board[ cell.row - 1 ][ charToInt.get( cell.col ) - 1 ] = newPiece;
        return ret;
    }

    /**
     * This method returns a List of legal moves that are possible based on the current Board.
     */
    public List< Move > getLegalMoves() {
        
        List< Path > possiblePaths = retrieveAllPaths();

        removeIllegalMoves( possiblePaths );

        return Path.extractMoves( possiblePaths );
    }

    /**
     * This method computes all possible paths for each Piece on the Board.
     * It does not do any sorting.
     * @return a List with all possible path for each Piece on the Board.
     */
    private List< Path > retrieveAllPaths() {
        List< Path > possiblePaths = new ArrayList<>();

        for ( int row = 0; row < board.length; row++ ) {
            for ( int col = 0; col < board[0].length; col++ ) {
                Piece p = board[ row ][ col ];
                if ( p != null ) {
                    Cell pos = new Cell( (char) ( col + 'A' ), 1 + row );
                    possiblePaths.addAll( p.getMoves( pos ) );
                }
            }
        }
        return possiblePaths;
    }

    /**
     * This method removes all illegal Paths from the given List of Paths.
     * This method modifies the given List.
     */
    private void removeIllegalMoves( List< Path > possiblePaths ) {
        // Check for same color && Pieces protected by another Piece.. Remove these.
        for ( Path p : possiblePaths ) {
            Move currentMove = p.head();
            Move prev = currentMove;
            
            while ( currentMove != null ) {
                
                Piece piece = getPiece( currentMove.from );
                Piece target = getPiece( currentMove.to );
                if ( target != null && target.color().equals( piece.color() ) ) { // It is a friendly piece.
                    p.removeMove( currentMove );
                    currentMove = p.next( prev );
                } else if ( piece instanceof Pawn ) {
                    // Check infront.
                    if ( target != null && currentMove.to.col == currentMove.from.col && 
                            currentMove.to.row - currentMove.from.row == ( piece.color().equals( "white" ) ? -1 : 1 ) ) {
                        p.removeMove( currentMove ); // The piece infront this Pawn blocks it's path.   
                    // Diagonal check
                    } else if ( currentMove.to.col != currentMove.from.col && (target == null || !target.color().equals( piece.color() ) ) ) {
                        p.removeMove( currentMove );
                    }
                    currentMove = null;
                } else if ( target != null ) { // We know it is an enemy
                    Move next = p.next( currentMove );
                    
                    if ( next != null )                
                        p.removeMove( next );
                        
                    currentMove = null; // There can't possibly be any Moves behind this enemy, because the Piece blocks the Path.           
                } else
                    currentMove = p.next( currentMove ); 
                prev = currentMove;
            }
        }
    }

    /**
     * Checks if the given Cell has any legal moves.
     * @return true if the Piece in the given cell can perform a legal move. 
     */
    public boolean pieceCanBeMoved( Cell cell ) {
        Piece piece = getPiece( cell );
        if ( piece == null )
            return false;

        List< Path > paths = piece.getMoves( cell );
        removeIllegalMoves( paths );
        List< Move > moves = new ArrayList<>();
        for ( Path p : paths )
            moves.addAll( p.moves() );

        // debug( cell + " has " + moves.size() + " moves!" );
        return moves.size() > 0;
    }

    /**
     * Checks if the given move is valid.
     * Precondition: Cell from != null
     */
    public boolean isLegalMove( Cell from, Cell to ) {
        Piece piece = getPiece( from );
        List< Path > paths = piece.getMoves( from );
        removeIllegalMoves( paths );

        List< Move > moves = new ArrayList<>();
        for ( Path p : paths )
            moves.addAll( p.moves() );
        
        Move m = new Move( from, to );

        for ( Move move : moves )
            if ( move.equals( m ) ) {
                //debug( "The move " + m + " is legal." );
                return true;
            }
                

        //debug( "The move " + m + " is not legal." );
        return false;

    }

    /**
     * This method returns the color of the Piece in the given Cell
     * @return the color of the given Piece or null if the Cell is empty.
     */
    public String color( Cell cell ) {
        Piece piece = getPiece( cell );
        return piece == null ? null : piece.color();
    }

    /**
     * Moves the piece given at the location move.from to the location move.to
     * Precondition: The move is valid.
     */
    public void move( Move move ) {
        Cell from = move.from;
        Cell to = move.to;

        setPiece( to, getPiece( from ) );
        setPiece( from, null );
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
                    s += "" + p.toString() + " ";
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