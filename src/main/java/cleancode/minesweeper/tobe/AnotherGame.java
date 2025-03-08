package cleancode.minesweeper.tobe;

public class AnotherGame implements Game {

    @Override
    public void initialize() {
        // ... 필요없는데..? -> ISP 위반
    }

    @Override
    public void run() {
        // 게임 진행
    }
}
