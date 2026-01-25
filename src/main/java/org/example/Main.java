package org.example;

import java.util.function.*;

public class Main {
    static void main() {
        //INTERFEJSY FUNKCYJNE
        //ktory ma dokladnie 1 metode abstrakcyjna

        //metoda abstrakcyjna = bez body, okresla tylko co ma byc zrobione ale nie mowi jak

        //int calculate(int x);

        //4 Najczesciej uzywane:
        //Function -> bierze cos i zwraca cos
        //Predicate -> sprawdza warunek i zwraca true/false
        //Consumer -> cos bierze i nic nie zwraca
        //Supplier -> nic nie bierze i cos zwraca

        //Function
        Function<Integer, Integer> multiplyNumberBy2 = number -> number * 2;
        System.out.println("Function: " + multiplyNumberBy2.apply(2));

        //Predicate
        Predicate<String> isLong = text -> text.length() > 3;
        System.out.println("Predicate Adrian " + isLong.test("Adrian"));
        System.out.println("Predicate Ada " + isLong.test("Ada"));

        //Consumera
        Consumer<String> print = text -> System.out.println("Consumer " + text);
        print.accept("Hello");

        //Suppiler
        Supplier<String> defaultName = () -> "Default name";
        System.out.println("Suppiler " + defaultName.get());


        //po co nam te interfejsy
        //zeby nie robic sciany ifow
        //zeby trybow/mode'ow (np 1 czy jakis string)
        //zeby nie pisac wielu podobnych metod
        System.out.println("\n");

        //stare podejscie
        handleTextOld("Hello", 1);
        handleTextOld("Hello", 2);
        handleTextOld("Hello", 3);
        System.out.println("\n");

        //nowe podejscie
        handleTextNew("Hello", text -> System.out.println(text));
        handleTextNew("Hello", text -> System.out.println("LOG: " + text));
        handleTextNew("Hello", text -> System.out.println("MAIL: " + text));

        System.out.println("\n");
        //stare podejscie
        System.out.println(checkTextOld("Adrian", 1));
        System.out.println(checkTextOld("Adrian", 2));

        //nowe podejscie
        System.out.println(" \n" + checkTextNew(n -> n.length() > 3, "Adrian"));
        System.out.println(checkTextNew(n -> n.startsWith("A"), "Adrian"));

        //stare podejscie
        System.out.println("\n" + processNumberOld(15, 1));
        System.out.println(processNumberOld(10, 2));

        //nowe podejscie
        System.out.println("\n" + processNumberNew(15, n -> n * 2));
        System.out.println(processNumberNew(10, n -> n + 10));

        //zadania start
        System.out.println("\nStart Zadan\n");
        System.out.println(przerabianieLiczby(5, n -> n * 2));
        System.out.println(przerabianieLiczby(5, n -> n + 10));
        System.out.println(przerabianieLiczby(5, n -> n - 2 * n));
        System.out.println("\n");

        String tekst = "Nic dwa razy się nie zdarza i nie zdarzy.";

        System.out.println(warunekNaTekscie(tekst, n -> n.length() >= 5));
        System.out.println(warunekNaTekscie(tekst, n -> n.startsWith("A")));
        System.out.println(warunekNaTekscie(tekst, n -> !n.contains(" ")));
        System.out.println("\n");

        akcjaNaTekscie(tekst, System.out::println);
        akcjaNaTekscie(tekst, n -> System.out.println("LOG: " + n));
        akcjaNaTekscie(tekst, n -> System.out.println(n.toUpperCase()));
        System.out.println("\n");

        System.out.println(generatorWartosciDomyslnej(null, () -> "Guest"));
        System.out.println(generatorWartosciDomyslnej("", () -> "Guest"));
        System.out.println(generatorWartosciDomyslnej("Adrian", () -> "Guest"));
        System.out.println("\n");

        System.out.println(operacjeNaDwoch(5, 5, (n, m) -> n + m));
        System.out.println(operacjeNaDwoch(5, 5, (n, m) -> n * m));
        System.out.println(operacjeNaDwoch(5, 6, (n, m) -> Math.max(n, m)));
        System.out.println("\n");

        System.out.println(sprawdzenieTekstu2(tekst, 5, (n, m) -> n.length() >= m));
        System.out.println("\n");

        imieWiek("Karol", 27, (n, m) -> System.out.println(n + " ma " + m + " lat"));
    }

    //metody po staremu
    static void handleTextOld(String text, int mode) {
        if (mode == 1) {
            System.out.println(text);
        } else if (mode == 2) {
            System.out.println("LOG: " + text);
        } else if (mode == 3) {
            System.out.println("MAIL: " + text);
        }
    }

    static boolean checkTextOld(String text, int mode) {
        if (mode == 1) {
            return text.length() > 3;
        } else if (mode == 2) {
            return text.startsWith("A");
        }
        return false;
    }

    static int processNumberOld(int number, int mode) {
        if (mode == 1) {
            return number * 2;
        } else if (mode == 2) {
            return number + 10;
        }
        return 0;
    }

    //metody nowe
    static void handleTextNew(String text, Consumer<String> consumer) {
        consumer.accept(text);
    }

    static Boolean checkTextNew(Predicate<String> predicate, String text) {
        return predicate.test(text);
    }

    static int processNumberNew(int number, Function<Integer, Integer> function) {
        return function.apply(number);
    }

//zadania start

    static int przerabianieLiczby(int liczba, Function<Integer, Integer> function) {
        return function.apply(liczba);
    }

    static boolean warunekNaTekscie(String tekst, Predicate<String> predicate) {
        return predicate.test(tekst);
    }

    static void akcjaNaTekscie(String tekst, Consumer<String> consumer) {
        consumer.accept(tekst);
    }

    static String generatorWartosciDomyslnej(String imie, Supplier<String> supplier) {
        if (imie != null && !imie.isEmpty()) {
            return imie;
        } else return supplier.get();
    }

    static Integer operacjeNaDwoch(Integer liczba1, Integer liczba2, BiFunction<Integer, Integer, Integer> function) {
        return function.apply(liczba1, liczba2);
    }

    static boolean sprawdzenieTekstu2(String tekst, Integer liczba, BiPredicate<String, Integer> predicate) {
        return predicate.test(tekst, liczba);
    }

    static void imieWiek(String imie, Integer wiek, BiConsumer<String, Integer> consumer) {
        consumer.accept(imie, wiek);
    }


    // - null -> ma zwrócić "Guest"
// - "" -> ma zwrócić "Guest"
// - "Adrian" -> ma zwrócić "Adrian"


    // Zasada ogólna:
// Masz pisać metody, które dostają "logikę" jako parametr.
// Czyli metoda ma być uniwersalna, a różnice mają wynikać z tego,
// co jej przekażesz przy wywołaniu.
//
// ------------------------------------------------------------
// 1) Przerabianie liczby (jedno wejście -> jedno wyjście)
// Napisz metodę, która:
// - przyjmuje liczbę
// - przyjmuje jako drugi parametr "operację"
// - zwraca wynik tej operacji
//
// Przetestuj w main na 3 różnych operacjach:
// - podwojenie liczby
// - dodanie 10
// - zamiana na liczbę ujemną
//
// ------------------------------------------------------------
// 2) Sprawdzanie warunku na tekście (jedno wejście -> true/false)
// Napisz metodę, która:
// - przyjmuje tekst
// - przyjmuje jako drugi parametr "regułę sprawdzającą"
// - zwraca wynik sprawdzenia (true/false)
//
// Przetestuj na 3 regułach:
// - tekst ma minimum 5 znaków
// - tekst zaczyna się od litery "A"
// - tekst nie zawiera spacji
//
// ------------------------------------------------------------
// 3) Akcja na tekście (jedno wejście -> brak wyniku)
// Napisz metodę, która:
// - przyjmuje tekst
// - przyjmuje jako drugi parametr "akcję do wykonania"
// - wykonuje tę akcję na tekście
//
// Przetestuj na 3 akcjach:
// - wypisz tekst normalnie
// - wypisz jako "LOG: <tekst>"
// - wypisz tekst wielkimi literami
//
// ------------------------------------------------------------
// 4) Wartość domyślna (może być kosztowna, więc ma się liczyć tylko gdy trzeba)
// Napisz metodę, która:
// - przyjmuje wartość (String)
// - przyjmuje jako drugi parametr "generator wartości domyślnej"
// - jeśli wartość jest OK (nie null i nie pusta), zwraca ją
// - jeśli nie jest OK, zwraca wartość z generatora
//
// Przetestuj:
// - null -> ma zwrócić "Guest"
// - "" -> ma zwrócić "Guest"
// - "Adrian" -> ma zwrócić "Adrian"
//
// ------------------------------------------------------------
// 5) Dwa argumenty naraz (2 wejścia -> wynik / decyzja / akcja)
// a) Napisz metodę, która:
// - przyjmuje dwie liczby
// - przyjmuje jako trzeci parametr "operację na dwóch liczbach"
// - zwraca wynik tej operacji
//
// Przetestuj na 3 operacjach:
// - dodawanie
// - mnożenie
// - większa z dwóch liczb
//
// b) Napisz metodę, która:
// - przyjmuje tekst i liczbę (np. minimalną długość)
// - przyjmuje jako kolejny parametr "regułę sprawdzającą te dwa parametry"
// - zwraca true/false
//
// Przetestuj regułę: czy tekst ma przynajmniej tyle znaków, ile wynosi minimalna długość
//
// c) Napisz metodę, która:
// - przyjmuje imię i wiek
// - przyjmuje jako kolejny parametr "akcję wypisującą/obsługującą te dane"
// - wykonuje tę akcję
//
// Przetestuj: wypisz "X ma Y lat"

}
