public class Move {
    public final Cell from;
    public final Cell to;
    /**
     * This constructor constructs a new move representing moving a piece from 'from' to 'to'
     */
    public Move( Cell from, Cell to ) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a hashCode for this Object.
     */
    public int hashCode() {
        return from.hashCode() + to.hashCode();
    }

    /**
     * Checks whether other is equals to this Move.
     */
    public boolean equals( Object other ) {
        if ( other == null )
            return false;
        if ( other == this )
            return true;
        if ( !(other instanceof Move) )
            return false;
        Move o = (Move) other;
        return o.from.equals( from ) && o.to.equals( to );
    }

    public String toString() {
        return from + " -> " + to;
    }
}
