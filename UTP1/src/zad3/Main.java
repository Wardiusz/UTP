package zad3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
            .filter(x -> x.startsWith("WAW"))
            .map(x -> {
              String[] parts = x.split(" ");
              double pricePLN = Double.parseDouble(parts[2]) * ratePLNvsEUR;
              return "to " + parts[1] + " - price in PLN:\t" + Math.round(pricePLN);
            })
            .collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
