package cleancode.minesweeper.tobe;

public class BoardIndexConvertor {

    private static final char BASE_CHAR_FOR_COL = 'a';

    public int getSelectedColIndex(String cellInput, int boardColSize) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol, boardColSize);
    }

    public int getSelectedRowIndex(String cellInput, int boardRowSize) {
        String cellInputRow = cellInput.substring(1);
        return convertRowFrom(cellInputRow, boardRowSize);
    }

    private int convertColFrom(char cellInputCol, int boardColSize) {

        int colIndex = cellInputCol - BASE_CHAR_FOR_COL;

        if (colIndex < 0 || colIndex >= boardColSize) {
            throw new GameException("잘못된 입력입니다.");
        }

        return colIndex;
    }

    private int convertRowFrom(String cellInputRow, int boardRowSize) {

        int rowIndex = Integer.parseInt(cellInputRow) - 1;

        if(boardRowSize < 0 || rowIndex >= boardRowSize){
            throw new GameException("잘못된 입력입니다.");
        }

        return rowIndex;
    }
}
