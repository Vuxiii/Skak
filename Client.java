import java.util.Scanner;

public class Client {
    static Scanner in = new Scanner( System.in );
    static boolean whiteTurn = true;

    static boolean runGame = true;

    static Board board;

    static boolean displayHelperMarkers = true;

    public static void main( String[] args ) {

        board = new Board();
        while( runGame ) {
            
            System.out.println( board );
            if ( board.isGameOver( currentPlayer() ) ) {
                System.out.println( currentPlayer() + " lost." );
                System.exit( 0 );
            }

            System.out.println( "It is " + currentPlayer() + " player's turn!" );
            Move move;
            if ( currentPlayer() == PieceColor.BLACK ) {
                move = Minimax.next( board, 3, currentPlayer() );
                System.out.println( "The AI has chosen\n> " + move );
            } else {
                Cell fromCell = getFromMove();
                if ( displayHelperMarkers ) {
                    board.helperMarkers( fromCell );
                    System.out.println( board );
                }
                Cell toCell = getToCell( fromCell );
                move = board.getMoveWithType( fromCell, toCell );
            }
            board.move( move );
            whiteTurn = !whiteTurn;
            
        }
    }

    /**
     * This method returns the color of the current player.
     * @return "black" if current player is black, otherwise "white"
     */
    public static PieceColor currentPlayer() {
        return whiteTurn ? PieceColor.WHITE : PieceColor.BLACK;
    }

    /**
     * This method asks the User where to move the selected Piece to.
     * @param from the cell containing the selected Piece
     * @return The target destination for this move.
     */
    public static Cell getToCell( Cell from ) {
        Cell toCell = null;
        PieceColor cellColor = null;
        do {
            if ( toCell != null )
                if ( cellColor != null && cellColor == currentPlayer() )
                    System.out.println( "You can not kill your own Pieces!" );
                else
                    System.out.println( "You can not move " + from + " to cell " + toCell );
                
            toCell = getCell( "Where do you want to move " + from + " to?\n> " );
            cellColor = board.color( toCell );
        } while( !( board.isLegalMove( from, toCell ) ) ); //  || !cellColor.equals( currentPlayer()) )
        // System.out.println( board.isLegalMove( from, toCell ) );
        return toCell;
    }

    /**
     * This method asks the User to select a Piece they want to move.
     * @return the Cell containing the selected Piece.
     */
    public static Cell getFromMove() {
        Cell fromCell = null;
        PieceColor cellColor = null;
        do {
            if ( fromCell != null )
                if ( cellColor == null )
                    System.out.println( "You selected a Cell with no Pieces in it." );
                else if ( cellColor != currentPlayer() )
                    System.out.println( "You selected your opponent's Piece. Make sure to select your own!" );
                else
                    System.out.println( "You have selected a Piece with no valid Moves!" );
                
            fromCell = getCell( "What Piece do you wish to move? E.g (B7)\n> " );
            cellColor = board.color( fromCell );
        } while( !(cellColor != null && cellColor.equals( currentPlayer() ) ) || !board.pieceCanBeMoved( fromCell ) ); // Check color. Check can move
        return fromCell;
    }

    /**
     * This method asks the user with the given msg and returns a correct Cell on the Board
     * @param msg the message to display to the user.
     * @return the Cell the user selected.
     */
    public static Cell getCell( String msg ) {
        String fromCell = null;
        do {
            if ( fromCell != null )
                System.out.println( "It looks like you mistyped. Please select a valid Piece!" );
            System.out.print( msg );
            fromCell = in.nextLine();
        } while( !isLegalInputFormat( fromCell ) );
        
        return new Cell( Character.toUpperCase(fromCell.charAt(0)), (int) (fromCell.charAt(1) - '0') );
    } 

    /**
     * This method checks whether the user inputted a correctly formatted cell.
     * @param cell The given String to check for formatation.
     * @return true if the String is formatted correctly.
     */
    public static boolean isLegalInputFormat( String cell ) {
        if ( cell.length() != 2 )
            return false;
        if ( Character.toLowerCase( cell.charAt( 0 ) ) < 'a' )
            return false;
        if ( Character.toLowerCase( cell.charAt( 0 ) ) > 'h' )
            return false;
        if ( cell.charAt( 1 ) < '1' )
            return false;
        if ( cell.charAt( 1 ) > '8' )
            return false;
        return true;
    }
}
