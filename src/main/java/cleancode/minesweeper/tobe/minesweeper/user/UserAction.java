package cleancode.minesweeper.tobe.minesweeper.user;

import javax.naming.OperationNotSupportedException;

public enum UserAction {
    OPEN("셀 열기"),
    FLAG("깃발 꽂기"),
    UNKNOWN("알수없음"),
    ;

    private final String description;

    UserAction(String description) {
        this.description = description;
    }
}
