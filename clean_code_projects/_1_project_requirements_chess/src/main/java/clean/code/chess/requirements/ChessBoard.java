package clean.code.chess.requirements;

import javax.swing.text.Position;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;

    private Map<Position, Pawn> mapOfPositionAndPawn;
    private Map<Pawn, Position> mapOfPawnAndPosition;
    private ChessBoardValidator chessBoardValidator;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
        this.mapOfPositionAndPawn = new HashMap<>(MAX_BOARD_WIDTH * MAX_BOARD_HEIGHT);
        this.mapOfPawnAndPosition = new HashMap<>(MAX_BOARD_WIDTH * MAX_BOARD_HEIGHT);
        this.chessBoardValidator = new ChessBoardValidator();
    }


    public void Add(Pawn pawn, int xCoordonate, int yCoordonate, PieceColor pieceColor) {

        if(chessBoardValidator.isValidRow(xCoordonate, pawn.getPieceColor())){
            pawn.setChessBoard(this, xCoordonate, yCoordonate, pieceColor);
        }


    }


    public Pawn getPawn(Position position){
        return this.mapOfPositionAndPawn.get(position);
    }

        public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
            return chessBoardValidator.isValidCoordinates(xCoordinate, yCoordinate) && chessBoardValidator.isFreePosition(xCoordinate, yCoordinate);

        }

        public Position getPosition(Pawn pawn){
        return this.mapOfPawnAndPosition.get(pawn);
        }


    private class ChessBoardValidator {
        public boolean isValidCoordinates(int xCoordonate, int yCoordonate) {
            return this.isInsideTable(xCoordonate, MAX_BOARD_WIDTH) && this.isInsideTable(yCoordonate, MAX_BOARD_HEIGHT);
        }

        public boolean isValidRow(int xCoord, Color color){
            if(color.equals(Color.WHITE)){
                return xCoord == 0 || xCoord == 1;
            }else return xCoord == MAX_BOARD_HEIGHT -1 || xCoord == MAX_BOARD_HEIGHT;
        }
        public boolean isInsideTable(int coordonate, int limit) {
            return 0 <= coordonate && coordonate < limit;
        }

        public boolean isFreePosition(int xCoordonate, int yCoorddonate){
            return getPawn(new Position() {
                @Override
                public int getOffset() {
                    return 0;
                }
            }) == null;
        }
    }
}
