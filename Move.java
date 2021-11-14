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
}
