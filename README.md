# Uniwersalne Techniki Programowania
To repozytorium zawiera moje rozwiązania ćwiczeń dla UTP (Universal Programming Techniques) podczas studiów inżynierskich na [PJATK](https://pja.edu.pl).

## Spis Treści
+ [Zadanie 1 - Generics - UTP1.1](#zadanie-1---generics)
+ [Zadanie 2 - Ceny przelotów 1 - UTP1.2](#zadanie-2---ceny-przelotów-1)
+ [Zadanie 3 - Ceny przelotów 2 - UTP1.3](#zadanie-3---ceny-przelotów-2)
+ [Zadanie 4 - Lambda wyrazenia (kompozycja funkcji) - UTP2.1](#zadanie-4---lambda-wyrażenia---kompozycja-funkcji)
+ [Zadanie 5 - Klasa Maybe - UTP2.2](#zadanie-5---klasa-maybe)
+ [Zadanie 6 - Klienci - UTP3.1](#zadanie-6---klienci)
+ [Zadanie 7 - Programmers - UTP3.2](#zadanie-7---programmers)
+ [Zadanie 8 - Dodatkowe operacje na listach (XList) - UTP3.3](#zadanie-8---dodatkowe-operacje-na-listach)
+ [Zadanie 9 - WalkFtree A - UTP4.1](#zadanie-9---walkftree-a)
+ [Zadanie 10 - Zliczanie instrukcji i napisu - UTP4.2](#zadanie-10---zliczanie-instrukcji-i-napisu)
+ [Zadanie 11 - AnagramsStream - UTP4.3](#zadanie-11---anagramsstream)
+ [Zadanie 12 - Uruchamianie i zatrzymywanie równoległego działania kodów - UTP5.1](#zadanie-12---uruchamianie-i-zatrzymywanie-równoległego-działania-kodów)
+ [Zadanie 13 - - - UTP5.2](#zadanie-13)
+ [Zadanie 14 - - - UTP5.3](#zadanie-14)
---

## Zadanie 1 - Generics

Stworzyć sparametryzowane interfejsy:
+ Selector - z metodą select, zwracającą true jesli argument spełnia warunek zapisany w metodzoe i false w przeciwnym razie
+ Mapper - z metodą map, będącą dowolną funkcją: argument -> wynik

oraz  sparametryzowaną klasę ListCreator, zawierającą:
statyczną metodę collectFrom (lista)
metodę when
metodę mapEvery
które działają w taki sposób, że symboliczny zapis:

```java
    collectFrom(list1).when(selektor).mapEvery(mapper)
```

spowoduje utworzenie listy wynikowej, której elementy stanowią wybrane przez selektor elementy listy list1, przekształacone za pomocą podanego mappera.

Działanie wyjasnia poniższy przykładowy program (którego plik może być modyfikowany tylko w meijscach oznaczonych przez /*<--    ....  */:

```java
      import java.util.*;
  
      public class Main {
        public Main() {
          List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
          System.out.println(test1(src1));

          List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" );
          System.out.println(test2(src2));
        }
  
        public List<Integer> test1(List<Integer> src) {
          Selector /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
          Mapper   /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
          return   /*<-- zwrot wyniku
            uzyskanego przez wywołanie statycznej metody klasy ListCreator:
           */  collectFrom(src).when(sel).mapEvery(map);
        }
  
        public List<Integer> test2(List<String> src) {
          Selector /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
          Mapper   /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
          return   /*<-- zwrot wyniku
            uzyskanego przez wywołanie statycznej metody klasy ListCreator:
           */  collectFrom(src).when(sel).mapEvery(map);
        }
  
        public static void main(String[] args) {
          new Main();
        }
      }
```

Gdy w metodzie test1 selektor wybiera z listy liczby < 10, a mapper zwraca liczbę-argument powiększoną o 10, to na konsoli powinniśmy zobaczyć:
```[11, 17, 19]```
Gdy w metodzie test2  selektor wybiera z listy napisy, których długiość jest  większa od 3 znakow, a mapper dzwraca dlugość przekazanego napisu, powiększoną o 10, to na konsoli zobaczymy:
```[14, 17]```
Należy obowiązkowo zapewnić takie właśnie działanie programu..

## Zadanie 2 - Ceny przelotów 1

Lista dest zawiera informacje o cenach przelotów w postaci napisów:

```port_wylotu port_przylotu cena_w_EUR```

Należy utworzyć listę wynikową, której elementy będą opisywać ceny przelotów do poszczególnych miejsc (tylko) z Warszawy w PLN i wypisać na konsoli jej kolejne elementy.

Aby rozwiązać to zadanie, należy utworzyć sparametryzowaną klasę ListCreator, zawierającą:
statyczną metodę collectFrom (lista)
metodę when
metodę mapEvery
które działają w taki sposób, że symboliczny zapis:

```java
collectFrom(list).when(lambda-1).mapEvery(lambda-2)
```

spowoduje utworzenie listy wynikowej, której elementy stanowią wybrane przez lambda-1 elementy listy list, przekształcone za pomocą podanego lambda-2.

Uwagi: 
w zadaniu nie wolno korzystać z własnych interfejsów,
klasa ListCreator i jej metody powinny działać dla list (źródłowej i docelowej) elementów dowolnego typu.

Następujący (niemodyfikowalny poza miejsami oznaczonymi /*<--*/) program:
```java
    import java.util.*;

    public class Main {

      static List<String> getPricesInPLN(List<String> destinations, double xrate) {
        return ListCreator.collectFrom(destinations)
                           .when(  /*<-- lambda wyrażenie
                                    *  selekcja wylotów z Warszawy (zaczynających się od WAW)
                                    */
                            )
                           .mapEvery( /*<-- lambda wyrażenie
                                       *  wyliczenie ceny przelotu w PLN
                                       *  i stworzenie wynikowego napisu
                                       */
                            );
      }

      public static void main(String[] args) {
        // Lista destynacji: port_wylotu port_przylotu cena_EUR 
        List<String> dest = Arrays.asList(
          "bleble bleble 2000",
          "WAW HAV 1200",
          "xxx yyy 789",
          "WAW DPS 2000",
          "WAW HKT 1000"
        );
        double ratePLNvsEUR = 4.30;
        List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
        for (String r : result) System.out.println(r);
      }
    }
```
ma wyprowadzić na konsolę napisy:

```
to HAV - price in PLN:	5160
to DPS - price in PLN:	8600
to HKT - price in PLN:	4300
```
Postać wydruku jest obowiązkowa.

## Zadanie 3 - Ceny przelotów 2

Lista dest zawiera informacje o cenach przelotów w postaci napisów:
```port_wylotu port_przylotu cena_w_EUR```

Należy utworzyć listę wynikową, której elementy będą opisywać ceny przelotów do poszczególnych miejsc (tylko) z Warszawy w PLN i wypisać na konsoli jej kolejne elementy, używając następującego programu:

```java
/*<-- niezbędne importy */

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = 
    /*<-- tu należy dopisać fragment
     * przy czym nie wolno używać żadnych własnych klas, jak np. ListCreator
     * ani też żadnych własnych interfejsów
     */

    for (String r : result) System.out.println(r);
  }
}
```

Plik Main.java wolno modyfikować tylko w miejscach oznaczonych /*<--  */, a program ma wyprowadzić na konsolę:

```
to HAV - price in PLN:	5160
to DPS - price in PLN:	8600
to HKT - price in PLN:	4300
```

## Zadanie 4 - Lambda-wyrażenia - kompozycja funkcji


Zbudować klasę InputConverter, która pozwala przekształcić dane wejściowe (ustalane w konstruktorze klasy) za pomocą funkcji, podanych jako argumenty metody convertBy.

Np. jeśli mamy zdefiniowane operacje (funkcje):
* flines - zwraca listę wierszy z pliku tekstowego

* join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)

* collectInts - zwraca listę liczb całkowitych zawartych w napisie

* sum - zwraca sumę elementów listy liczb całkowitych

to sumę liczb całkowitych, występujących w pliku o nazwie fname możemy uzyskać poprzez: 

```java
Integer s = new InputConverter<String>(fname).convertBy(flines, join, collectInts, sum); 
```

a listę liczb całkowitych z napisu txt tak:

```java
List<Integer> n = new InputConverter<String>(txt).convertBy(collectInts);
```

Istotą metody convertBy jest to, że pozwala ona w różny sposób kombinować różne operacje na różnych danych i łatwo uzyskiwać wyniki.

Zapewnić prawidłowe działanie następującego fragmentu kodu (dostarczonego w szablonie projektu, pliku Main.java nie wolno modyfikować):

```java
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

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
```

Dla następujących danych z pliku:
Cars:
```
- Fiat: 15, Ford: 20
- Opel: 8, Mitsubishi: 10
```

oraz  nastepujących argumentów wywołania metody main:

```Warszawa 100 Kielce 200 Szczecin 300```

program powinien wyprowadzić na konsolę:

```
[Cars:, - Fiat: 15, Ford: 20, - Opel: 8, Mitsubishi: 10]
Cars:- Fiat: 15, Ford: 20- Opel: 8, Mitsubishi: 10
[15, 20, 8, 10]
53
600
```

Uwaga: w klasie InputConverter musi występować tylko jedna metoda o nazwie convertBy (czyli w tym zadaniu nie dopuszczamy przeciążania metody convertBy).

## Zadanie 5 - Klasa Maybe

Zdefiniować klasę Maybe o następujących właściwościach.

Obiekty Maybe reprezentują kontenery, które mogą zawierać lub nie pojedynczą wartość. Motywacją do wprowadzenia takiej konstrukcji jest ułatwienie programowania w sytuacji, gdy zmienna może mieć wartość null, szczególnie kiedy wymagane jest jej dalsze bezpieczne przetwarzanie (na przykład za pomocą lambda-wyrażeń, oznaczających jakieś funkcje). Bezpieczne - to znaczy takie, które nie powoduje wyjątku NullPointerException.

Obiekty typu Maybe zawierają jakąś wartość lub są puste (nigdy nie powinny mieć wartości null). 
W klasie Maybe zdefiniować następujące metody:

* Maybe.of(x) - ta metoda statyczna zwraca obiekt Maybe, „opakowujący” wartość x, dowolnego typu referencyjnego.

* void ifPresent(Consumer cons)  - jeżeli w obiekcie Maybe znajduje się wartość, wykonywana jest operacja cons z tą wartością jako argumentem, w przeciwnym razie - gdy obiekt Maybe jest pusty - nic się nie dzieje.

* Maybe map(Function func) -  jeżeli w obiekcie  jest wartość, wykonywana jest funkcja func z tą wartością jako argumentem i zwracany jest jej wynik „zapakowany” w nowy obiekt klasy Maybe (to opakowanie jest niezbędne, bo wynik mógłby być null, a tego chcemy uniknąć w ewentualnym dalszym przetwarzaniu; jeśli wynikiem funkcji jest null, zwracany jest pusty obiekt klasy Maybe).

* T get() zwraca zawartość obiektu Maybe, ale jeśli jest on pusty, powinna zgłosić wyjątek NoSuchElementException.
boolean isPresent() - zwraca true jeśli w obiekcie Maybe zawarta jest wartośc, a false - gdy jest on pusty

* T orElse(T defVal) - zwraca zawartość obiektu Maybe lub domyślną wartosć defVal, jeśli obiekt Maybe jest pusty.

* Maybe filter(Predicate pred)  - zwraca  to Maybe, jeśli spełniony jest warunek pred lub to Maybe jest puste; zwraca puste Maybe, jeśli warunek pred jest niespełniony.

Klasę Maybe przetestować na przykładzie następującej klasy Main:

```java
        public class Main {

          public static void test() {
            // Metoda of(...)
            String s = "aaa";    
            Maybe<String> m1 = Maybe.of(s);
            System.out.println(m1);
            s = null;
            Maybe<String> m2 = Maybe.of(s);
            System.out.println(m2);

            // Metoda ifPresent(...)
            Integer num = null;
            Maybe<Integer> m4 = Maybe.of(num);
            // ZAMIAST
            if (num != null) System.out.println(num);
            // PISZEMY
            m4.ifPresent(n -> System.out.println(n));
            // A NAWET
            m4.ifPresent(System.out::println);

            Maybe<Integer> m5 = Maybe.of(10);
            m5.ifPresent(System.out::println);

            // Metoda map()
            Maybe<Integer> m6 = m5.map( n -> n +10 ); 
            System.out.println(m6);

            // Metoda get()
            System.out.println(m6.get());
            try {
              System.out.println(m4.get());
            } catch(Exception exc) {
              System.out.println(exc);
            }

            // Metoda orElse()
            // ZAMIAST
            String snum = null;
            if (num != null) snum = "Wartość wynosi: " + num;
            if (snum != null) System.out.println(snum);
            else System.out.println("Wartość niedostępna");

            //MOŻNA NAPISAĆ
            String res = Maybe.of(num).map(n -> "Wartość wynosi: "+n)
                              .orElse("Wartość niedostępna");
            System.out.println(res);

            // I filter(...)

            String txt = "Pies";
            String msg = "";

            //ZAMIAST
            if (txt != null && txt.length() > 0) {
              msg = txt;
            } else {
              msg = "Txt is null or empty";
            }

            //MOŻNA NAPISAĆ
            msg = Maybe.of(txt)
                       .filter(t -> t.length() > 0)
                       .orElse("Txt is null or empty"); 
            System.out.println(msg);
          }

          public static void main(String[] args) {
            test();
          }
        }
        // Wynik na konsoli:
        /*    
          Maybe has value aaa
          Maybe is empty
          10
          Maybe has value 20
          20
          java.util.NoSuchElementException:  maybe is empty
          Wartość niedostępna
          Wartość niedostępna
          Pies
        */
```

## Zadanie 6 - Klienci

W pliku customers.txt umieszczonym w katalogu {user.home} znajdują się dane o zakupach klientów w postaci:

```
id_klienta; nazwisko i imię; nazwa_towaru;cena;zakupiona_ilość
```

Identyfikator klienta ma postać
```cNNNNN```
gdzie N cyfra ze zbioru [0...9]
np.
c00001;Kowalski Jan;bułka;2;100

Wczytać dane z pliku i wypisać na konsoli w kolejnych wierszach:

* poprzedzone napisem "Nazwiska" dane posortowane wg nazwisk w porządku rosnącym (porządek rekordów z tymi samymi nazwiskami jest określany przez identyfikatory klientów - rosnąco),

* poprzedzone napisem "Koszty" dane posortowane wg kosztów zakupów w porządku malejącym (porządek rekordów z tymi samymi kosztami jest określany przez identyfikatory klientów - rosnąco) z dodatkowym dopiskiem na końcu w nawiasach:  koszty:  kosztZakupu (np. (koszt: 200.0)),

* poprzedzone napisem "Klient c00001" dane o wszystkich zakupach  klienta  o identyfikatorze "c00001" (w odrębnych wierszach)

* poprzedzone napisem "Klient c00002" - w odrębnych wierszach -dane o wszystkich zakupach  klienta  o identyfikatorze "c00002"  (w odrebnych wierszach) (a więc uwaga: w pliku muszą być klienci o identyfikatorach c00001 i c00002)

Np. dla pliku w postaci:
```
c00004;Nowak Anna;banany;4.0;50.0
c00003;Kowalski Jan;mleko;4.0;5.0
c00001;Kowalski Jan;mleko;4.0;10.0
c00001;Kowalski Jan;mleko;5.0;2.0
c00002;Malina Jan;mleko;4.0;2.0
c00002;Malina Jan;chleb;3.0;5.0
c00001;Kowalski Jan;bulka;2.0;100.0
```

```
Nazwiska
c00001;Kowalski Jan;mleko;4.0;10.0
c00001;Kowalski Jan;mleko;5.0;2.0
c00001;Kowalski Jan;bulka;2.0;100.0
c00003;Kowalski Jan;mleko;4.0;5.0
c00002;Malina Jan;mleko;4.0;2.0
c00002;Malina Jan;chleb;3.0;5.0
c00004;Nowak Anna;banany;4.0;50.0

Koszty
c00001;Kowalski Jan;bulka;2.0;100.0 (koszt: 200.0)
c00004;Nowak Anna;banany;4.0;50.0 (koszt: 200.0)
c00001;Kowalski Jan;mleko;4.0;10.0 (koszt: 40.0)
c00003;Kowalski Jan;mleko;4.0;5.0 (koszt: 20.0)
c00002;Malina Jan;chleb;3.0;5.0 (koszt: 15.0)
c00001;Kowalski Jan;mleko;5.0;2.0 (koszt: 10.0)
c00002;Malina Jan;mleko;4.0;2.0 (koszt: 8.0)

Klient c00001
c00001;Kowalski Jan;mleko;4.0;10.0
c00001;Kowalski Jan;mleko;5.0;2.0
c00001;Kowalski Jan;bulka;2.0;100.0

Klient c00002
c00002;Malina Jan;mleko;4.0;2.0
c00002;Malina Jan;chleb;3.0;5.0
```
Uwaga: programy nie dające pokazanej formy wydruku otrzymują 0 punktów.

Niezbędne jest stworzenie klasy, opisującej zakupy klientów (Purchase) i operowanie na jej obiektach. Nie przyjmuję rozwiązań działających na "surowych" Stringach.

Aplikacja powinna zawierać klasy Purchase,  CustomersPurchaseSortFind oraz Main.
Ta ostatnia ma obowiązakową postać (nie wolno jej zmienić):
      public class Main {
```java  
        public static void main(String[] args)  {
          CustomersPurchaseSortFind cpsf = new CustomersPurchaseSortFind();
          String fname = System.getProperty("user.home") + "/customers.txt";
          cpsf.readFile(fname);
          cpsf.showSortedBy("Nazwiska");
          cpsf.showSortedBy("Koszty");
  
          String[] custSearch = { "c00001", "c00002" };
  
          for (String id : custSearch) {
            cpsf.showPurchaseFor(id);
          }
        }
  
      }
```
Generator projektów utworzy wymagane klasy.

Wykonanie programu rozpoczyna się od metody main(...) w klasie Main.

Uwaga: nazwa pliku jest obowiązkowe. Niespełnienie tego warunku skutkuje brakiem punktów.
Utworzona przez generator projektów klasa Main zawiera fragment pomocny dla uzyskania wymaganej nazwy pliku.

Uwaga: aby dowiedzieć się który  katalog jest  {user.home} i umieścić w nim plik testowy można z poziomu Javy użyć:
```System.getProperty("user.home");```
Np. jeśli identyfikatorem użytkownika jest Janek, to w Windows 7 katalog {user.home} to C:\Users\Janek.

Należy samodzielnie utworzyć testowy plikii umieścić je w katalogu {user.home}.

## Zadanie 7 - Programmers

Firma software'owa prowadzi projekty w różnych językach programowania.
Plik Prpgrammers.tsv z katalogu {user.home} zawiera informacje o programistach w postaci:

```
język1<TAB>nazwisko(1)<TAB>nazwisko(2)<TAB> itd
język2<TAB>nazwisko(1)<TAB>nazwisko(2)<TAB> itd
...
```

Stworzyć klasę ProgLang, mającą:

* konstruktor ProgLang(String nazwaPliku), w którym następuje wczytanie pliku o podanej nazwie,

* metodę getLangsMap() - zwracająca mapę, w której pod kluczem nazwa języka znajduje się kolekcja programistów tego języka,

* metodę getProgsMap() - zwracającą mapę, w której pod kluczem nazwisko programisty znajduje się kolekcja języków, w których programuje,

* metodę getLangsMapSortedByNumOfProgs()  - zwracającą mapę z wejściami  język -> kolekcja programistów. uporządkowaną malejąco według liczby osób znających poszczególne języki, w przypadku równej liczbu porządek jest alfabetyczny wg nazw języków,

* metodę getProgsMapSortedByNumOfLangs() - zwracającą mapę z wejścimi programista -> kolekcja językow, uporządkowaną malejąco wg liczby języków znanych programiści; w przypadku równej liczby porządek jest alfabetyczny wg nazwisk,

* metodę getProgsMapForNumOfLangsGreaterThan(int n) - zwracającą mapę z wejściami programista -> kolekcja języków, dla ktorych liczba języków jest większa od podanego n.

* metodę sorted(...), wołaną z argumentami mapa i lambda-wyrażenie. Metoda zwraca posortowaną wersję dowolnej mapy przekazanej jako piewrszy argument, a porządek sortowania jest określony przez lambda wyrażenia, podane jako drugi argument,

* metodę filtered(...) z argumentami: dowolna mapa i  lambda. Metoda zwraca  mapę, która zawiera tylko te wejścia z przekazanej jako pierwszy argument mapy, które spelniają warunek podany jako drugi argument (lambda z wynikiem typu boolean).

Metod sorted(...) lub filtered(...) użyć w oprogramowaniu innych, odpowiednich, metod klasy. Mają one jednak ogólniejsze znaczenia, bo mogą być używane dla dowolnych innych map  z warunkami sortowania czy filtrowania, zadawanymi przez własściwe w danych przypadkach lambdy.
Uwaga: uniwersalność metod sorted i filtered )możliwość ich zasobędzie sprawdzana


Poniższa klasa Main (ktorej w projekcie nie wolno modyfikować):

```java
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {
    ProgLang pl = null;
    try {
      pl =  new ProgLang(System.getProperty("user.home") + "/Programmers.tsv");
    } catch (Exception exc) {
      System.out.println("Wadliwy konstruktor: " + exc);
    }
    System.out.println("@1 Mapa językow:");
    pl.getLangsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    System.out.println("@2 Mapa programistów:");
    pl.getProgsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    System.out.println("@3 Języki posortowane wg liczby programistów:");
    pl.getLangsMapSortedByNumOfProgs()
      .forEach((k,v)->System.out.println(k+ " = " + v));
    System.out.println("@4 Programiści posortowani wg liczby języków:");
    pl.getProgsMapSortedByNumOfLangs()
      .forEach((k,v)->System.out.println(k+ " = " + v));
    System.out.println("@5 Oryginalna mapa języków niezmieniona:");
    pl.getLangsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    System.out.println("@6 Oryginalna mapa programistów niezmienione:");
    pl.getProgsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    System.out.println("@7 Mapa programistów znających więcej niż 1 język:");
    pl.getProgsMapForNumOfLangsGreaterThan(1)
      .forEach((k,v)->System.out.println(k+ " = " + v));
    System.out.println("@8 Oryginalna mapa programistów nie jest zmieniona:");
    pl.getProgsMap().forEach((k,v)->System.out.println(k+ " = " + v));
  }

}
```

dla nastepującej zawartości pliku Programmers.tsv:

```
Groovy	Z	Y	X	D
Java	V	B	C	D	A	Z
C++	G	J	H
C#	P	S	Q	V	D
Scala	A	D	A
```
(uwaga: sepratorami są znaki tabulacji)

wyprowadzi na konsolę wynik:

```
@1 Mapa językow:
Groovy = [Z, Y, X, D]
Java = [V, B, C, D, A, Z]
C++ = [G, J, H]
C# = [P, S, Q, V, D]
Scala = [A, D]
@2 Mapa programistów:
Z = [Groovy, Java]
Y = [Groovy]
X = [Groovy]
D = [Groovy, Java, C#, Scala]
V = [Java, C#]
B = [Java]
C = [Java]
A = [Java, Scala]
G = [C++]
J = [C++]
H = [C++]
P = [C#]
S = [C#]
Q = [C#]
@3 Języki posortowane wg liczby programistów:
Java = [V, B, C, D, A, Z]
C# = [P, S, Q, V, D]
Groovy = [Z, Y, X, D]
C++ = [G, J, H]
Scala = [A, D]
@4 Programiści posortowani wg liczby języków:
D = [Groovy, Java, C#, Scala]
A = [Java, Scala]
V = [Java, C#]
Z = [Groovy, Java]
B = [Java]
C = [Java]
G = [C++]
H = [C++]
J = [C++]
P = [C#]
Q = [C#]
S = [C#]
X = [Groovy]
Y = [Groovy]
@5 Oryginalna mapa języków niezmieniona:
Groovy = [Z, Y, X, D]
Java = [V, B, C, D, A, Z]
C++ = [G, J, H]
C# = [P, S, Q, V, D]
Scala = [A, D]
@6 Oryginalna mapa programistów niezmienione:
Z = [Groovy, Java]
Y = [Groovy]
X = [Groovy]
D = [Groovy, Java, C#, Scala]
V = [Java, C#]
B = [Java]
C = [Java]
A = [Java, Scala]
G = [C++]
J = [C++]
H = [C++]
P = [C#]
S = [C#]
Q = [C#]
@7 Mapa programistów znających więcej niż 1 język:
Z = [Groovy, Java]
D = [Groovy, Java, C#, Scala]
V = [Java, C#]
A = [Java, Scala]
@8 Oryginalna mapa programistów nie jest zmieniona:
Z = [Groovy, Java]
Y = [Groovy]
X = [Groovy]
D = [Groovy, Java, C#, Scala]
V = [Java, C#]
B = [Java]
C = [Java]
A = [Java, Scala]
G = [C++]
J = [C++]
H = [C++]
P = [C#]
S = [C#]
Q = [C#]
```


Ważne uwagi:

* zgodność informacji wyjściowej z oczekiwanym wynikiem (w tym kolejność pokazywania danych)  jest istotna - wynika z zastosowania odpowiednich map i innych klas kolekcyjnych, za niezgodność w którymkolwiek z punktów 1-8 będą odejmowane 2 punkty,

* uniwersalność metod sorted i filtered (możliwość ich zastosowania dla innych niż w zadaniu map) będzie sprawdzana; brak uniwersalności każdej z metod skutkuje odjęciem 4 punktów,

* w klasie ProgLang  nie wolno używac surowych typów.

## Zadanie 8 - Dodatkowe operacje na listach

Stworzyć klasę XList, dostarczającą dodatkowych możliwości tworzenia list i operowania na nich.
W klasie powinny znaleźć się odpowiednie konstruktory oraz statyczne metody of, umożliwiające tworzenie obiektów XList z innych kolekcji, tablic oraz argumentów podawanych przez przecinki.
Dodatkowo pomocnicze metody do tworzenia xlist z napisów: 
* ofChars(napis) - zwraca x-listę znaków napisu,

* ofTokens(napis, [ sep ]) - zwraca x-listę symboli napisu, rozdzielonych separatorami z sep (jesśi brak - to białymi znakami).

Oprócz tego dostarczyć metod: 

* union(dowolna_kolekcja)  -  zwraca  nową x-list z dołączaną do tej x-list  zawartością kolekcji,

* diff(dowolna_kolekcja) - zwraca x-list zawierającą te 
elementy tej x-list, które nie występują w kolekcji,

* unique() - zwraca nową x-list, która zawiera wszystkie niepowtarzające się elementy tej x-list

* combine() - zwraca x-listę list-kombinacji elementów z poszczególnych kolekcji, będących elementami tej x-listy

* collect(Function) - zwraca nową x-listę, której elemenatmi są wyniki funkcji stosowanej wobec elementów tej x-listy,

* join([sep]) - zwraca napis, będący połączeniem elementów tej x-listy, z ewentualnie wstawionym pomiędzy nie separatorem,

* forEachWithIndex(konsumer_z_dwoma argumentami: element, index) - do iterowania po liście z dostępem i do elementów i do ich indeksów.

Za realizację każdej z w/w właściwosci  uzyskuje się odrębne punkty, tak że można wykonac tylko część zadania.
Przy tym należy jednak uważać, aby przekazany w rozwiązaniu plik Main.java nie miał błędów w kompilacji.

Klasa Main zawarta w projekcie powinna dobrze wyjaśniać sposób realizacji zadania:

```java
import java.util.*;

// Plik Main.java może być dowolnie modyfikowany, 
// ale punkty uzyskuje się za właściwe dzialanie poszczególnych pokazanych tu metod klasy XList.

// Jeżeli nie oprogramujemy wszystkich metod, to z klasy Main nalezy usunąć te fragmenty,
// które powodują błędy w kompilacji - w przeciwnym razie nie uzyskamy punktów.

public class Main {
  public static void main(String[] args) {
    // Pewne dodatkowe zestawy danych
    Integer[] ints = { 100, 200, 300 };
    Set<Integer> set = new HashSet<>(Arrays.asList(3, 4, 5));
    
    // Sposoby tworzenia
    XList<Integer> list1 = new XList<>(1, 3, 9, 11);
    XList<Integer> list2 = XList.of(5, 6, 9);
    XList<Integer> list3 = new XList(ints);
    XList<Integer> list4 = XList.of(ints);
    XList<Integer> list5 = new XList(set);
    XList<Integer> list6 = XList.of(set);

    System.out.println(list1);
    System.out.println(list2);
    System.out.println(list3);
    System.out.println(list4);
    System.out.println(list5);
    System.out.println(list6);
    
    // --- i pomocnicze metody do tworzenia z napisów
    XList<String> slist1 = XList.charsOf("ala ma kota");
    XList<String> slist2 = XList.tokensOf("ala ma kota");
    XList<String> slist3 = XList.tokensOf("A-B-C", "-");

    System.out.println(slist1);
    System.out.println(slist2);
    System.out.println(slist3);

    // Metoda union - suma elementów 
    List<Integer> m1 = list1.union(list2);  // oczywiście, można podstawiać na List
    System.out.println(m1);
    // można wykonywać wszystkie operacje z interfejsu List, np:
    m1.add(11);
    System.out.println(m1);
    XList<Integer> m2 = (XList<Integer>) m1;
    XList<Integer> m3 = m2.union(ints).union(XList.of(4, 4));
    System.out.println(m2); // m2 się nie zmienia
    System.out.println(m3); // wynik jest w m3
    m3 = m3.union(set);
    System.out.println(m3);
    
    // Widzieliśmy metode union
    // Teraz metoda diff(dowolna kolekcja)
    System.out.println(m3.diff(set));  // wszystko z m3, co nie jest w set
    System.out.println(XList.of(set).diff(m3)); // co jest w set, czego nie ma w m3
    
    // Metoda unique -zwraca nową Xlist bez duplikatow   
    XList<Integer> uniq = m3.unique(); // lista, nie Set
    System.out.println(uniq);    
   
    // kombinacje (kolejność jest istotna)
    List<String> sa = Arrays.asList( "a", "b");
    List<String> sb = Arrays.asList( "X", "Y", "Z" );
    XList<String> sc = XList.charsOf( "12" );
    XList toCombine = XList.of(sa, sb, sc);  // czy można tu uniknąć użycia typu surowego?
    System.out.println(toCombine);
    XList<XList<String>> cres = toCombine.combine();
    System.out.println(cres);

    // collect i join
    XList<String> j1 = cres.collect( list -> list.join());
    System.out.println(j1.join(" "));
    XList<String> j2 =cres.collect( list -> list.join("-"));
    System.out.println(j2.join(" "));
    
    // forEachWithIndex
    XList<Integer> lmod = XList.of(1,2,8, 10, 11, 30, 3, 4);  
    lmod.forEachWithIndex( (e, i) -> lmod.set(i, e*2));
    System.out.println(lmod);
    lmod.forEachWithIndex( (e, i) -> { if (i % 2 == 0) lmod.remove(e); } );
    System.out.println(lmod);
    lmod.forEachWithIndex( (e, i) -> { if (i % 2 == 0) lmod.remove(i); } );
    System.out.println(lmod); // Pytanie: dlaczego mamy taki efekt? 

  }
}
```

Ten program wypisuje na konsoli:

```
[1, 3, 9, 11]
[5, 6, 9]
[100, 200, 300]
[100, 200, 300]
[3, 4, 5]
[3, 4, 5]
[a, l, a,  , m, a,  , k, o, t, a]
[ala, ma, kota]
[A, B, C]
[1, 3, 9, 11, 5, 6, 9]
[1, 3, 9, 11, 5, 6, 9, 11]
[1, 3, 9, 11, 5, 6, 9, 11]
[1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4]
[1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4, 3, 4, 5]
[1, 9, 11, 6, 9, 11, 100, 200, 300]
[]
[1, 3, 9, 11, 5, 6, 100, 200, 300, 4]
[[a, b], [X, Y, Z], [1, 2]]
[[a, X, 1], [b, X, 1], [a, Y, 1], [b, Y, 1], [a, Z, 1], [b, Z, 1], [a, X, 2], [b, X, 2], [a, Y, 2], [b, Y, 2], [a, Z, 2], [b, Z, 2]]
aX1 bX1 aY1 bY1 aZ1 bZ1 aX2 bX2 aY2 bY2 aZ2 bZ2
a-X-1 b-X-1 a-Y-1 b-Y-1 a-Z-1 b-Z-1 a-X-2 b-X-2 a-Y-2 b-Y-2 a-Z-2 b-Z-2
[2, 4, 16, 20, 22, 60, 6, 8]
[4, 16, 22, 60, 8]
[16, 22, 60, 8]
```

## Zadanie 9 - WalkFtree A

Katalog `{user.home}/UTP6dir`  zawiera pliki tekstowe (z rozszerzeniem .txt) umieszczone w różnych podkatalogach. Kodowanie plików to Cp1250. 
Przeglądając rekursywnie drzewo katalogowe, zaczynające się od `{user.home}/UTP6dir`,  wczytać wszystkie te pliki. i połączoną ich zawartość zapisać do pliku o nazwie UTP6res.txt, znadującym się w katalogu projektu. Kodowanie pliku 
UTP6res.txt winno być UTF-8.

Poniższy gotowy fragment winien wykonać całą robotę:

```java
public class Main {
    public static void main(String[] args) {
        String dirName = System.getProperty("user.home")+"/UTP6dir";
        String resultFileName = "UTP6res.txt";
        Futil.processDir(dirName, resultFileName);
    }
}
```

Uwagi:
* pliku Main.java nie wolno w żaden sposób modyfikować,
* trzeba dostarczyć definicji klasy Futil,
oczywiście, nazwa katalogu i pliku oraz ich położenie są obowiązkowe,
* należy zastosować FileVisitor do przeglądania katalogu,
* proszę nie stosować środkow przetwarzania strumieniowego, na to będa oddzielne zadania,,
nalezy zalożyć, że na starcie programu  wynikowego pliku nie ma.


## Zadanie 10 - Zliczanie instrukcji i napisu

Napisać program, który wczytuje plik o nazwie "Test.java" z katalogu {user.home} z kodem źródłowym Javy (poprawnym, czyli kompilującym się bez błędów) i wyszukuje w nim instrukcje if oraz napisy "wariant". Wyniki dzialania programu w postaci:

Liczba instrukcji if: n
Liczba napisow wariant: m

należy wypisać na konsoli.

Obowiązkowa klasa Main, służąca do testowania programu winna mieć następującą postać:
```java
public class Main {
    public static void main(String ... args) throws Exception  {
        String home = System.getProperty("user.home");
        Finder finder = new Finder(home + "/Test.java");
        int nif = finder.getIfCount();
        int nwar = finder.getStringCount("wariant");
        System.out.println("Liczba instrukcji if: " + nif);
        System.out.println("Liczba napisow wariant: " + nwar);
    }
}
```

Uwagi: 
* Wszystkie klasy w programiw winny być publiczne (i w oddzielnych plikach)
* Klasy Main (utworzonej przez generator projektów) nie wolno w żaden sposób modyfikować
* Plik winien znajdowaać się w  katalou {user.homn} czyli tym który zwróci wywolanie System.getProperty("user.home");
* Plik tworzymy sobie sami.
* Proszę nie dołączać pliku do projektu.
* Forma wydruku na konsoli jest obowiązkowa.

Pomoc:
* Instrukcja if ma swoje wymagania składniowe (ale zakładamy, że program jest poprawny). Nie jest instrukcją if coś co tak wygląda, ale jest umieszczone w komentarzu lub w literale napisowym.

## Zadanie 11 - AnagramsStream

Na liście słów z http://wiki.puzzlers.org/pub/wordlists/unixdict.txt znaleźć wszystkie anagramy.
Wypisać słowa z maksymalną liczbą anagramów oraz wszystkie ich anagramy w postaci:

```slowo anagram1 anagram2 ....```

Program ma być bardzo krótki , dzięki zastosowaniu przetwarzania strumieniowego (java.util.stream).

## Zadanie 12 - Uruchamianie i zatrzymywanie równoległego działania kodów

Zbudować klasę StringTask, symulująca długotrwałe obliczenia, tu polegające na konkatenacji napisow.
Konstruktor klasy otrzymuje jako argument napis do powielenia oraz liczbę oznaczającą ile razy ten napis ma być powielony.
Klasa winna implementować interfejs Runnable, a w jej metodzie run() wykonywane jest powielenia napisu, przy czym to powielenia ma się odbywac za pomoca operatora '+' stosowanego wobec zmiennych typu String (to właśnie długotrwała operacja). Użycie '+' jest warunkiem obowiązkowe.

Obiekt klasy StringTask traktujemy jako zadanie, które może się wykonywać równolegle z innymi.

Możliwe stany zadania to:
* CREATED  - zadanie utworzone, ale nie zaczęło się jeszcze wykonywać
  
* RUNNING - zadanie się wykonuje w odrebnym wątku
  
* ABORTED - wykonanie zadania zostało przerwane
  
* READY - zadanie zakończyło się pomyślnie i sa gotowe wyniki

W klasie StringTask zdefiniować metody:
* public String getResult()  - zwracającą wynik konkatenacji

* public TaskState getState()  - zwracającą stan zadania
  
* public void start() - uruchamiającą zadanie w odrębnym watku
  
* public void abort() - przerywającą wykonanie kodzu zadania i działanie watku
  
* public boolean isDone()  - zwracająca true, jeśli wykonanie zadania się zakończyło normalnie lub przez przerwanie, false w przeciwnym razie
  
Poniższy kod program:
```java
public class Main {
  public static void main(String[] args) throws InterruptedException {
    StringTask task = new StringTask("A", 70000);
    System.out.println("Task " + task.getState());
    task.start();
    if (args.length > 0 && args[0].equals("abort")) { 
    /*<- tu zapisać kod  przerywający działanie tasku po sekundzie 
         i uruchomic go w odrębnym wątku
    */
    }
    while (!task.isDone()) {
      Thread.sleep(500);
      switch(task.getState()) {
        case RUNNING: System.out.print("R."); break;
        case ABORTED: System.out.println(" ... aborted."); break;
        case READY: System.out.println(" ... ready."); break;
        default: System.out.println("uknown state");
      }
    }
    System.out.println("Task " + task.getState());
    System.out.println(task.getResult().length());
  }
}
```

uruchomiony bez argumentu powinien wyprowadzić coś w rodzaju:
```
Task CREATED
R.R.R.R.R.R.R.R.R. ... ready.
Task READY
70000
```
a uruchomiony z argumentem "abort" może wyprowadzić:
```
Task CREATED
R. ... aborted.
Task ABORTED
31700
```

Uwagi: 
* Plik Main.java może być modyfikowany tylko w miejscu oznaczonym /*<- */
* Nie wolno uzywac metody System.exit(...)
* W tym zadaniu nie należy stosować Executor/ExceutorService

## Zadanie 13
Treści nie pamiętam ale coś na zasadzie 12 zadania tylko przy użyciu Swing'a.

## Zadanie 14

