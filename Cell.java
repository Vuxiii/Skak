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

    public String toString() {
        return "[" + col + "," + row + "]";
    }

    public int hashCode() {
        return col + row*31;
    }

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
