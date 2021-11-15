import java.util.Scanner;

public class Client {
    static Scanner in = new Scanner( System.in );
    static boolean whiteTurn = true;

    static boolean runGame = true;

    static Board board;

    public static void main( String[] args ) {

        board = new Board();

        while( runGame ) {
            System.out.println( board );
            System.out.println( "It is " + (whiteTurn ? "White" : "Black") + " player's turn!" );
            
            Cell fromCell = getFromMove();
            Cell toCell = getToCell( fromCell );
            Move move = new Move( fromCell, toCell );
            board.move( move );
            whiteTurn = !whiteTurn;
        }
    }

    public static String currentPlayer() {
        return whiteTurn ? "white" : "black";
    }

    public static Cell getToCell( Cell from ) {
        Cell toCell = null;
        String cellColor = null;
        do {
            if ( toCell != null )
                if ( cellColor != null && cellColor.equals( currentPlayer() ) )
                    System.out.println( "You can not kill your own Pieces!" );
                else
                    System.out.println( "You can not move " + from + " to cell " + toCell );
                
            toCell = getCell( "Where do you want yo move " + from + " to?\n> " );
            cellColor = board.color( toCell );
        } while( !(cellColor == null || !cellColor.equals( currentPlayer()) ) || !board.isLegalMove( from, toCell ) );

        return toCell;
    }

    public static Cell getFromMove() {
        Cell fromCell = null;
        String cellColor = null;
        do {
            if ( fromCell != null )
                if ( cellColor == null )
                    System.out.println( "You selected a Cell with no Pieces in it." );
                else if ( !cellColor.equals( currentPlayer() ) )
                    System.out.println( "You selected your opponent's Piece. Make sure to select your own!" );
                else
                    System.out.println( "You have selected a Piece with no valid Moves!" );
                
            fromCell = getCell( "What Piece do you wish to move? E.g (B7)\n> " );
            cellColor = board.color( fromCell );
        } while( !(cellColor != null && cellColor.equals( currentPlayer() ) ) || !board.pieceCanBeMoved( fromCell ) ); // Check color. Check can move
        return fromCell;
    }

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
