package practice;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class StreamTest {

    @Test
        // 리스트
    void streamCollectList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<String> stringNumbers = numbers.stream()
                .map(number -> String.valueOf(number))
                .collect(Collectors.toList());

        assertThat(stringNumbers).containsExactly("1", "2", "3", "4", "5");
    }

    @Test
    void streamCollectSet() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 1, 2, 3, 4, 5);

        Set<Integer> numbersSet = numbers.stream()
                .collect(Collectors.toSet());

        assertThat(numbersSet).containsOnlyOnce(1, 2, 3, 4, 5);
    }

    @Test
    void streamCollectStringJoin() {
        List<String> words = Arrays.asList("Java", "Stream", "API");

        String joinedWords = words.stream()
                .collect(Collectors.joining(", "));

        assertThat(joinedWords).isEqualTo("Java, Stream, API");
    }

    @Test
    void StreamCollectGrouping() {
        List<String> names = Arrays.asList("김철수", "이영희", "박민수", "김영호", "이지은");

        Map<String, List<String>> groupedByLastName = names.stream()
                .collect(Collectors.groupingBy(name -> name.substring(0, 1)));

        assertThat(groupedByLastName).containsExactlyInAnyOrderEntriesOf(Map.of(
                "김", Arrays.asList("김철수", "김영호"),
                "박", Arrays.asList("박민수"),
                "이", Arrays.asList("이영희", "이지은")
        ));
    }

    @Test
    void streamCollectCount() {
        List<String> fruits = Arrays.asList("사과", "바나나", "오렌지", "키위", "망고");

        Long counts = fruits.stream()
                .collect(Collectors.counting());

        assertThat(counts).isEqualTo(5);
    }

    @Test
    void streamCollectAverage() {
        List<Integer> scores = Arrays.asList(85, 90, 78, 92, 88);

        Double average = scores.stream()
                .collect(Collectors.averagingInt(Integer::intValue));

        assertThat(average).isEqualTo(86.6);
    }

    @Test
    void streamCollectMinMax() {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);

        Optional<Integer> max = numbers.stream()
                .collect(Collectors.maxBy(Integer::compareTo));

        Optional<Integer> min = numbers.stream()
                .collect(Collectors.minBy(Integer::compareTo));

        assertThat(min).isEqualTo(Optional.of(1));
        assertThat(max).isEqualTo(Optional.of(9));
    }

    @Test
    void streamCollectMap() {
        List<String> people = Arrays.asList("김철수:30", "이영희:25", "박민수:35");

        Map<String, String> peopleMap = people.stream()
                .collect(Collectors.toMap(
                        peo -> peo.split(":")[0],
                        peo -> peo.split(":")[1]
                ));

        assertThat(peopleMap).containsExactlyInAnyOrderEntriesOf(Map.of(
                "김철수", "30",
                "이영희", "25",
                "박민수", "35"
        ));
    }

    @Test
        // stream의 filter는 주어진 조건에 맞는 요소만 선택합니다.
    void streamFilter() {
        List<String> names = Arrays.asList("김철수", "이영희", "박민수", "정지원", "최영미");

        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("김"))
                .collect(Collectors.toList());

        assertThat(filteredNames).containsExactly("김철수");
    }

    @Test
        // map은 각 요소를 변환합니다.
    void streamMap() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> squaredNumbers = numbers.stream()
                .map(number -> number * number)
                .collect(Collectors.toList());

        assertThat(squaredNumbers).containsExactly(1, 4, 9, 16, 25);
    }

    @Test
        // 중첩된 구조를 평면화 합니다.
    void streamFlatMap() {
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        List<Integer> flattedList = nestedList.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        assertThat(flattedList).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
        // 정렬합니다.
    void streamSort() {
        List<String> fruits = Arrays.asList("사과", "바나나", "오렌지", "키위", "망고");

        List<String> sortedFruits = fruits.stream()
                .sorted()
                .collect(Collectors.toList());

        assertThat(sortedFruits).containsExactly("망고", "바나나", "사과", "오렌지", "키위");
    }

}
