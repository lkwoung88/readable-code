package cleancode.studycafe.my.io;

import cleancode.studycafe.my.exception.AppException;
import cleancode.studycafe.my.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

/**
 * inputHandler
 * 사용자로부터 입력을 받는다.
 * 값을 검증한다.
 * 값을 전달한다.
 */
public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if(isInvalidPassTypeSelection(userInput)) {
            throw new AppException("잘못된 입력입니다.");
        }

        return userInput;
    }

    public int getSelectPassIndex() {
        String userInput = SCANNER.nextLine();

        try{
            return Integer.parseInt(userInput) - 1;
        }catch (NumberFormatException e){
            throw new AppException("잘못된 입력입니다.");
        }
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();

        return "1".equals(userInput);
    }

    private boolean isInvalidPassTypeSelection(String userInput) {
        List<String> selections = StudyCafePassType.getSelections();
        return !selections.contains(userInput);
    }
}
