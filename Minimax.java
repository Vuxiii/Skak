import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Minimax {
    
    private static class Tree {
        List< Move > moves;
        List< Double > scores;
        Double score;
        public Tree( Board board, int depth, boolean maximizing, PieceColor currentPlayer ) {
            if ( depth > 0 ) {
                PieceColor opponent = currentPlayer == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK;
                moves = board.getLegalMoves( currentPlayer );
                Queue< Move > moveQ = orderMoves( moves );
                scores = new ArrayList<>();

                for ( Move move = moveQ.peek(); !moveQ.isEmpty(); moveQ.poll() ) { // Order these moves.
                    Board bc = board.copy();
                    // System.out.println( bc.getPiece( move.from ) );
                    bc.move( move );
                    Tree t = new Tree( bc, depth - 1, !maximizing, opponent );
                    scores.add( t.score );
                    score = score == null ? t.score : ( maximizing 
                        ? Math.max( score, t.score )
                        : Math.min( score, t.score )
                    );
                }
                // System.out.println( score );
            } else {
                score = calcScore( board, currentPlayer );
            }

        }

        public Move next() {
            System.out.println( "Best score is " + score );
            for ( int i = 0; i < moves.size(); ++i ) {
                if ( scores.get( i ).equals( score ) ) {
                    
                    return moves.get( i );
                }
            }
            return moves.get(0);
        }
    }
    private static Board currentBoard;

    private static int moveVal( Move move ) {
        Piece p = currentBoard.getPiece( move.from );
        // if ( p != null ) System.out.println( currentBoard.getPiece( move.from ) );
        int score = 0;
        if ( p instanceof Queen )
            score += 20;
        if ( p instanceof Knight )
            score += 15;
        if ( p instanceof Rook )
            score += 10;
        if ( p instanceof Bishop )
            score += 10;
        if ( p instanceof Pawn )
            score += 5;
        
        if ( move.to.col >= 'C' && move.to.col <= 'F' )
            score += 30;
        else
            score += 10;
        
        return score;
    }

    private static Queue< Move > orderMoves( List< Move > moves ) {
        Queue< Move > q = new PriorityQueue<>( moves.size(), 
            ( Move move1, Move move2 ) -> moveVal( move2 ) - moveVal( move1 ) );
        
        q.addAll( moves );
        return q;
    }
    private static double calcScore( Board board, PieceColor currentPlayer ) {
        PieceColor opponent = currentPlayer == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK;
        // List< Move > currMoves = board.getLegalMoves( currentPlayer );
        // List< Move > oppoMoves = board.getLegalMoves( opponent );
        List< Piece > currPieces = board.getPieces( currentPlayer );
        List< Piece > oppoPieces = board.getPieces( opponent );
        
        

        Map< String, Integer > currentPieces = countPieces( currPieces );
        Map< String, Integer > opponentPieces = countPieces( oppoPieces );

        double score = 0;
        score += 200 * ( currentPieces.get( "king" ) - opponentPieces.get( "king" ) );
        score += 9 * ( currentPieces.get( "queen" ) - opponentPieces.get( "queen" ) );
        score += 5 * ( currentPieces.get( "rook" ) - opponentPieces.get( "rook" ) );
        score += 3 * ( currentPieces.get( "bishop" ) - opponentPieces.get( "bishop" ) );
        score += 3 * ( currentPieces.get( "knight" ) - opponentPieces.get( "knight" ) );
        score += 1 * ( currentPieces.get( "pawn" ) - opponentPieces.get( "pawn" ) );
        if ( score > 1 )
            System.out.println( "score\t" + score);
        return score;
    }

    private static Map< String, Integer > countPieces( List< Piece > pieces ) {
        Map< String, Integer > map = new HashMap<>();
        map.put( "king", 0 );
        map.put( "queen", 0 );
        map.put( "rook", 0 );
        map.put( "bishop", 0 );
        map.put( "knight", 0 );
        map.put( "pawn", 0 );

        for ( Piece p : pieces ) {
            if ( p instanceof King )
                map.put( "king",    map.get( "king")    + 1 );
            if ( p instanceof Queen )
                map.put( "queen",   map.get( "queen")   + 1 );
            if ( p instanceof Rook )
                map.put( "rook",    map.get( "rook")    + 1 );
            if ( p instanceof Bishop )
                map.put( "bishop",  map.get( "bishop")  + 1 );
            if ( p instanceof Knight )
                map.put( "knight",  map.get( "knight") + 1 );
            if ( p instanceof Pawn )
                map.put( "pawn",    map.get( "pawn")    + 1 );
        }
        return map;
    }

    public static Move next( Board board, int depth, PieceColor currentPlayer ) {
        currentBoard = board;
        return new Tree( board, depth, true, currentPlayer ).next();
    }
}
