package zad1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    Function<String, List<String>> flines = path -> {
      List<String> list = new ArrayList<>();

      File file = new File(path);

      try (Scanner scan = new Scanner(file)) {

        while (scan.hasNextLine()){
          list.add(scan.nextLine());
        }

      } catch (IOException e) {
        e.printStackTrace();
      }

      return list;
    };

    Function<List<String>, String> join = list -> {
      StringBuilder txt = new StringBuilder();

      for (String s : list){
        txt.append(s);
      }

      return txt.toString();
    };

    Function<String, List<Integer>> collectInts = src -> {

      List<Integer> list = new ArrayList<>();

      Pattern.compile("\\D+")
              .splitAsStream(src)
              .filter(s -> !s.isEmpty())
              .map(Integer::parseInt)
              .forEach(list::add);

      return list;
    };

    Function<List<Integer>, Integer> sum = list -> {
      return list.stream().reduce(0, Integer::sum);
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
