package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.Minesweeper;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutputHandler;

public class GameApplication {

    public static void main(String[] args) {

        GameLevel gameLevel = new Beginner();
        // GameLevel gameLevel = new Middle();
        // GameLevel gameLevel = new Advanced();
        // GameLevel gameLevel = new VeryBeginner();

        // DIP : 추상화로 분리된 객체를 사용
        InputHandler consoleInputHandler = new ConsoleInputHandler();
        OutputHandler consoleOutputHandler = new ConsoleOutputHandler();

        GameConfig gameConfig = new GameConfig(gameLevel, consoleInputHandler, consoleOutputHandler);

        // DI : 외부에서 의존성을 주입
        Minesweeper minesweeper = new Minesweeper(gameConfig);
        minesweeper.initialize();
        minesweeper.run();
    }
}
