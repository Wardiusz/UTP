package zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        Map<String, List<String>> map = reader.lines()
                .collect(Collectors.groupingBy(
                        word -> {
                            char[] chars = word.toCharArray();
                            Arrays.sort(chars);
                            return new String(chars);
                        }
                ));

        int max = map.values()
            .stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        map.values()
            .stream()
                .filter(list -> list.size() == max)
                .forEach(list -> {
                    System.out.println(String.join(" ", list));
                });
    }
}
