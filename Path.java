import java.util.ArrayList;
import java.util.List;

public class Path {
    private class Node {

        Move move;

        Node next;

    }

    private Node begin;

    /**
     * Constructs a new Path 
     */
    public Path() {
        begin = new Node();
    }


    /**
     * This method adds a Move.
     * @param move adds the given Move to this Path.
     */
    public void addMove( Move move ) {
        Node current = begin;
        Node prev = begin;
        if ( current.move == null )
            current.move = move;
        else {
            while ( current != null ) {
                prev = current;
                current = current.next;
            }
            current = new Node();
            current.move = move;
            prev.next = current;
        }
    }

    /**
     * This method extracts all possible moves from the given list of Paths.
     * @param paths the list of Paths to extract Moves from
     * @return An ArrayList< Move > containing all of the possible moves that can be made.
     */
    public static List< Move > extractMoves( List< Path > paths ) {
        List< Move > out = new ArrayList<>();
        for ( Path p : paths )
            out.addAll( p.moves() );
        return out;
    }

    /**
     * This method returns all of the moves in this Path.
     * @return a List of Moves that that this Path contains.
     */
    public List< Move > moves() {
        List< Move > moves = new ArrayList<>();
        Node current = begin;
        while( current != null ) {
            if ( current.move != null )
                moves.add( current.move );
            current = current.next;
        }

        return moves;
    }

    /**
     * This method returns the first Move in this path.
     * @return the first Move in this Path.
     */
    public Move head() {
        return begin.move;
    }

    /**
     * Returns the next Move in this Path after the given Move.
     * @param from the Move from which to retrieve the next Move from.
     * @return the next Move in this Path.
     */
    public Move next( Move from ) {
        Node current = begin;
        while ( current != null && current.move != null && !current.move.equals( from ) )
            current = current.next;

        return current == null ? null : current.next == null ? null : current.next.move;
    }

    /**
     * Removes the given Move from this Path, including the Moves that are followed by the given Move.
     * Precondition: The Move is in this Path.
     * @param move the move which needs to be removed.
     */
    public void removeMove( Move move ) {
        //System.out.println( "Move to delete: " + move );
        //System.out.println( toString() );
        Node current = begin;
        Node prev = begin;
        while( current != null ) {
            if ( move.equals( current.move ) ) {
                current.move = null;
                prev.next = null;
            }
            prev = current;
            current = current.next;
        }
        //System.out.println( toString() );
        
    }

    /**
     * Returns a textual representation of this Path.
     */
    public String toString() {
        String s = "[ ";
        Node current = begin;
        while ( current != null && current.move != null ) {
            s += current.move.toString() + " ";
            current = current.next;
        }

        return s + "]";
    }
}
