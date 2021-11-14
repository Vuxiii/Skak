import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece {
    private final String color;

    public Knight( String color ) {
        this.color = color;
    }

    public String color() {
        return color;
    }
    
    public static List< Path > getPossibleMoves( Cell pos ) {
        // List< Move > moves = new ArrayList<>();
        
        List< Path > paths = new ArrayList<>();

        // Check left bound
        if ( pos.col > 'B' ) {
            if ( pos.row > 1 ) {
                // We can make left2 up jump
                Cell left2Up = new Cell( (char) (pos.col - 2), pos.row - 1 ); 
                // moves.add( new Move( pos, left2Up ) );
                
                paths.add( new Path() );
                paths.get( paths.size() - 1 ).addMove( new Move( pos, left2Up ) );
            }
            
            if ( pos.row < 8 ) {
                // We can make left2 down jump
                Cell left2Down = new Cell( (char) (pos.col - 2), pos.row + 1 ); 
                // moves.add( new Move( pos, left2Down ) );
                
                paths.add( new Path() );
                paths.get( paths.size() - 1 ).addMove( new Move( pos, left2Down ) );
            }
            
        }
        // CHeck left bound
        if ( pos.col > 'A' ) {
            
            if ( pos.row < 7 ) {
                // We can make left2 down jump
                Cell down2left = new Cell( (char) (pos.col - 1), pos.row + 2 ); 
                // moves.add( new Move( pos, down2left ) );
                
                paths.add( new Path() );
                paths.get( paths.size() - 1 ).addMove( new Move( pos, down2left ) );
            }
            if ( pos.row > 2 ) {
                // We can make up2 left jump
                
                Cell up2Left = new Cell( (char) (pos.col - 1), pos.row - 2 );
                // moves.add( new Move( pos, up2Left ) );
                paths.add( new Path() );
                paths.get( paths.size() - 1 ).addMove( new Move( pos, up2Left ) );
            }
        }
        // Check right bound
        if ( pos.col < 'G' ) {
            if ( pos.row > 1 ) {
                // We can make right2 up jump
                Cell right2Up = new Cell( (char) (pos.col + 2), pos.row - 1 ); 
                // moves.add( new Move( pos, right2Up ) );
                paths.add( new Path() );
                paths.get( paths.size() - 1 ).addMove( new Move( pos, right2Up ) );
            }
            
            if ( pos.row < 8 ) {
                // We can make right2 down jump
                Cell right2Down = new Cell( (char) (pos.col + 2), pos.row + 1 ); 
                // moves.add( new Move( pos, right2Down ) );
                paths.add( new Path() );
                paths.get( paths.size() - 1 ).addMove( new Move( pos, right2Down ) );
            }
        }
        if ( pos.col < 'H' ) {
            if ( pos.row > 2 ) {
                // We can make up2 right jump
                Cell up2Right = new Cell( (char) (pos.col + 1), pos.row - 2 );
                // moves.add( new Move( pos, up2Right ) );
                paths.add( new Path() );
                paths.get( paths.size() - 1 ).addMove( new Move( pos, up2Right ) );
            }
            if ( pos.row < 7 ) {
                // We can make down2 right jump
                Cell down2Right = new Cell( (char) (pos.col + 1), pos.row + 2 ); 
                // moves.add( new Move( pos, down2Right ) );
                paths.add( new Path() );
                paths.get( paths.size() - 1 ).addMove( new Move( pos, down2Right ) );
            }
        }

        return paths;
    }
    
    public List< Path > getMoves( Cell pos ) {
        return Knight.getPossibleMoves( pos );
    }
    
    public String toString() {
        return color == "black" ? "♞" : "♘";
    }
}
