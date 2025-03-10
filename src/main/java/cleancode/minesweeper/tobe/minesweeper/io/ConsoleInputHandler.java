package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    public static final Scanner SCANNER = new Scanner(System.in);

    private final BoardIndexConvertor boardIndexConvertor = new BoardIndexConvertor();

    @Override
    public UserAction getUserActionFromUser() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return UserAction.OPEN;
        } else if ("2".equals(userInput)) {
            return UserAction.FLAG;
        }

        return UserAction.UNKNOWN;
    }

    @Override
    public CellPosition getCellPositionFromUser() {
        String userInput = SCANNER.nextLine();

        int rowIndex = boardIndexConvertor.getSelectedRowIndex(userInput);
        int colIndex = boardIndexConvertor.getSelectedColIndex(userInput);
        return CellPosition.of(rowIndex, colIndex);
    }
}
