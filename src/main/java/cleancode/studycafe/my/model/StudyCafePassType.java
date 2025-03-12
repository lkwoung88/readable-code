package cleancode.studycafe.my.model;

import java.util.Arrays;
import java.util.List;

public enum StudyCafePassType {

    HOURLY("1","시간 단위 이용권"),
    WEEKLY("2","주 단위 이용권"),
    FIXED("3","1인 고정석");

    private final String selection;
    private final String description;

    StudyCafePassType(String selection, String description) {
        this.selection = selection;
        this.description = description;
    }

    public static List<String> getSelections() {
        List<StudyCafePassType> cafePassTypes = Arrays.asList(StudyCafePassType.values());
        return cafePassTypes.stream()
                .map(StudyCafePassType::getSelection)
                .toList();
    }

    public static StudyCafePassType fromSelection(String selection) {
        return Arrays.stream(StudyCafePassType.values())
                .filter(type -> type.getSelection().equals(selection))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 좌석타입 입력입니다."));
    }

    private String getSelection() {
        return selection;
    }
}
