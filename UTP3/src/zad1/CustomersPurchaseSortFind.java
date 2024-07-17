package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomersPurchaseSortFind {
    List<Purchase> list = new ArrayList<>();
    public void readFile(String fname) {
        try(Scanner in = new Scanner(new File(fname))){
            while(in.hasNextLine()){
                list.add(Purchase.of(in.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String type) {
        System.out.println(type);

        if (type.equals("Nazwiska")) {
            list.stream().sorted((a, b) -> {
                int temp = a.lastName.compareTo(b.lastName);
                return temp == 0 ? a.id - b.id : temp;
            }).forEach(System.out::println);
        }

        if (type.equals("Koszty")) {
            list.stream()
                    .sorted((a, b) -> {
                        float temp = (b.amount * b.price) - (a.amount * a.price);
                        return temp == 0 ? a.id - b.id : (int) temp;
                    })
                    .forEach(c -> {
                        System.out.printf("%s (koszt: %.1f)%n", c, (c.price * c.amount));
                    });
        }

        System.out.println();
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);

        list.stream()
                .filter(c -> {
                    return c.id == Integer.parseInt(id.substring(1));
                })
                .forEach(System.out::println);

        System.out.println();
    }
}
