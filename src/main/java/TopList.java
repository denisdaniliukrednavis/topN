import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopList {

    private static Integer N = 10;

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2,3,4,1,5,6,73,4,563763,0,23,5,1,1,null,123,-191239);

        list = topN(list.stream(), N);

        list.forEach(System.out::println);
    }

    /*
    What can be done to improve performance:
        use more faster sorting algorithm
        use threads for sorting
     */
    public static List<Integer> topN(Stream<Integer> input, Integer n) {
        if (input == null) return new ArrayList<>();
        if (n == null) n = N;

        return input
                .parallel()
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .limit(n)
                .skip(0)
                .collect(Collectors.toList());
    }
}
