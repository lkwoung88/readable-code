package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.config.GameConfig;
import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

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
