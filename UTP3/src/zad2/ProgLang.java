package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProgLang {

    private final Map<String, List<String>> map;

    public ProgLang(String path) throws IOException {
        map = Files.lines(Paths.get(path))
                .collect(Collectors.toMap(
                        c -> c.substring(0, c.indexOf('\t')),
                        c -> Pattern.compile("\t").splitAsStream(c)
                                .skip(1)
                                .distinct()
                                .collect(Collectors.toList()),
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    public Map<String, List<String>> getLangsMap() {
        return map;
    }

    public Map<String, List<String>> getProgsMap() {
        Map<String, List<String>> result = new LinkedHashMap<>();
        map.forEach((key, value) -> {
            value.forEach(v -> {
                result.computeIfAbsent(v, k -> new ArrayList<>()).add(key);
            });
        });
        return result;
    }

    public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(map, Comparator.comparingInt(a -> a.getValue().size()));
    }

    public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(getProgsMap(), (a, b) -> {
            int sizeComparison = Integer.compare(b.getValue().size(), a.getValue().size());
            if (sizeComparison != 0) {
                return sizeComparison;
            }
            return a.getKey().compareTo(b.getKey());
        });
    }

    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(getProgsMap(), a -> a.getValue().size() > n);
    }

    private <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return map.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    private <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate) {
        return map.entrySet().stream()
                .filter(predicate)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new));
    }
}
