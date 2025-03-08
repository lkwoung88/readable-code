package cleancode.minesweeper.tobe;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

    private static final int LAND_MINE_COUNT = 10;

    private final Cell[][] board;

    public GameBoard(int row, int col){
        board = new Cell[row][col];
    }

    public void flag(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex, colIndex);
        cell.flag();
    }

    public void open(int selectedRowIndex, int selectedColIndex) {
        Cell cell = board[selectedRowIndex][selectedColIndex];
        cell.open();
    }

    public void openSurroundedCells(int row, int col) {
        if (row < 0 || row >= this.getColSize() || col < 0 || col >= this.getColSize()) {
            return;
        }

        if (isOpenedCell(row, col)) {
            return;
        }

        if (isMineCell(row, col)) {
            return;
        }

        this.open(row, col);
        if (doesCellHanLandMindeCount(row, col)) {
            return;
        }

        openSurroundedCells(row - 1, col - 1);
        openSurroundedCells(row - 1, col);
        openSurroundedCells(row - 1, col + 1);
        openSurroundedCells(row, col - 1);
        openSurroundedCells(row, col + 1);
        openSurroundedCells(row + 1, col - 1);
        openSurroundedCells(row + 1, col);
        openSurroundedCells(row + 1, col + 1);
    }

    private boolean doesCellHanLandMindeCount(int row, int col) {
        return this.findCell(row, col).hasLandMineCount();
    }

    private boolean isMineCell(int row, int col) {
        return this.isLandMineCell(row, col);
    }

    private boolean isOpenedCell(int row, int col) {
        return this.findCell(row, col).isOpened();
    }

    public boolean isLandMineCell(int selectedRowIndex, int selectedColIndex) {
        Cell cell = this.findCell(selectedRowIndex, selectedColIndex);
        return cell.isLandMine();
    }

    public boolean isAllCellChecked() {

        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .allMatch(cell -> cell.isChecked());
    }

    public void initializeGame() {

        int rowSize = this.getRowSize();
        int colSize = this.getColSize();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                board[row][col] = Cell.create();
            }
        }

        for (int i = 0; i < LAND_MINE_COUNT; i++) {
            int landMineCol = new Random().nextInt(colSize);
            int landMineRow = new Random().nextInt(rowSize);
            this.findCell(landMineRow, landMineCol).turnOnLandMine();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (isLandMineCell(row, col)) {
                    continue;
                }
                int count = countNearbyLandMines(row, col);
                this.findCell(row, col).updateNearbyLandMineCount(count);
            }
        }
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }

    public String getSign(int rowIndex, int colIndex) {
        Cell cell = this.findCell(rowIndex, colIndex);
        return cell.getSign();
    }

    private Cell findCell(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }

    private int countNearbyLandMines(int row, int col) {

        int rowSize = this.getRowSize();
        int colSize = this.getColSize();
        int count = 0;

        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMineCell(row - 1, col)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < colSize && isLandMineCell(row - 1, col + 1)) {
            count++;
        }
        if (col - 1 >= 0 && isLandMineCell(row, col - 1)) {
            count++;
        }
        if (col + 1 < colSize && isLandMineCell(row, col + 1)) {
            count++;
        }
        if (row + 1 < rowSize && col - 1 >= 0 && isLandMineCell(row + 1, col - 1)) {
            count++;
        }
        if (row + 1 < rowSize && isLandMineCell(row + 1, col)) {
            count++;
        }
        if (row + 1 < rowSize && col + 1 < colSize && isLandMineCell(row + 1, col + 1)) {
            count++;
        }
        return count;
    }

}
