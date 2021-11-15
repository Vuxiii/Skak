public class Cell {
    public final char col;
    public final int row;

    /**
     * This constructor symbolizes a cell on the board
     */
    public Cell( char col, int row ) {
        this.col = col;
        this.row = row;
    }

    /**
     * Returns a textual representation of this Cell.
     */
    public String toString() {
        return "[" + col + "," + row + "]";
    }

    /**
     * Returns the hashCode for this instance.
     */
    public int hashCode() {
        return col + row*31;
    }

    /**
     * Checks if other is equals to this instance.
     */
    public boolean equals( Object other ) {
        if ( other == null )
            return false;
        if ( other == this )
            return true;
        if ( !(other instanceof Cell) )
            return false;
        Cell o = (Cell) other;
        return o.col == col && o.row == row;
    }
}
