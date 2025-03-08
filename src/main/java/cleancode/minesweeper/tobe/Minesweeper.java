package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

public class Minesweeper {

    private final GameBoard gameBoard;
    private final BoardIndexConvertor boardIndexConvertor = new BoardIndexConvertor();

    private final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public Minesweeper(GameLevel gameLevel) {
        gameBoard = new GameBoard(gameLevel);
    }

    public void run() {

        consoleOutputHandler.showGameStartComments();
        gameBoard.initializeGame();

        while (true) {
            try{
                consoleOutputHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    consoleOutputHandler.printGameWinningComment();
                    break;
                }

                if (gameStatus == -1) {
                    consoleOutputHandler.printGameLosingComment();
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionIInput = getActionInputFromUser();
                actOnCell(cellInput, userActionIInput);
            }catch (GameException e){
                consoleOutputHandler.printExceptionMessage(e);
            }catch (Exception e){
                consoleOutputHandler.printSimpleMessage("프로그램에 문제가 생겼습니다");
//                e.printStackTrace(); anti-pattern
            }
        }
    }


    private void actOnCell(String cellInput, String userActionIInput) {
        int selectedColIndex = boardIndexConvertor.getSelectedColIndex(cellInput, gameBoard.getColSize());
        int selectedRowIndex = boardIndexConvertor.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

        if (doesUserChooseToPlantFlag(userActionIInput)) {
            gameBoard.flag(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();
            return;
        }

        if (doesUserChooseToOpenCell(userActionIInput)) {

            if (gameBoard.isLandMineCell(selectedRowIndex, selectedColIndex)) {
                gameBoard.open(selectedRowIndex, selectedColIndex);
                changeFameStatusToLose();
                return;
            }

            gameBoard.openSurroundedCells(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();
            return;
        }

        System.out.println("잘못된 번호를 선택하셨습니다.");
    }

    private void changeFameStatusToLose() {
        gameStatus = -1;
    }

    private boolean doesUserChooseToOpenCell(String userActionIInput) {
        return userActionIInput.equals("1");
    }

    private boolean doesUserChooseToPlantFlag(String userActionIInput) {
        return userActionIInput.equals("2");
    }

    private String getActionInputFromUser() {
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    private String getCellInputFromUser() {
        consoleOutputHandler.printCommentForSelectingCell();
        return consoleInputHandler.getUserInput();
    }

    private boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private void checkIfGameIsOver() {
        if (gameBoard.isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = 1;
    }
}
