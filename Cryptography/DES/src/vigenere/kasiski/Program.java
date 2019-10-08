package vigenere.kasiski;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import vigenere.util.StringUtils;
import vigenere.util.Util;

public class Program {

  private static final Map<Integer, Map<String, Integer>> lettersByPosition = new HashMap<>();
  private static HashMap<String, Set<Integer>> biGramsDistances = new HashMap<>();
  private static HashMap<String, Set<Integer>> triGramsDistances = new HashMap<>();

  private static void findNGrams(final String text, final int n, final String filename)
      throws IOException {
    final var nGrams = new HashMap<String, Integer>();
    final var distances = new HashMap<String, Set<Integer>>();
    for (var i = 0; i < text.length() - n + 1; i++) {
      final var substring = text.substring(i, i + n);
      if (!substring.matches("^\\w+$")) continue;
      final var dist = new TreeSet<Integer>();
      nGrams.put(substring, StringUtils.countMatches(text, substring, dist));
      distances.put(substring, dist);
    }
    var result =
        nGrams.entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry::getValue))
            .collect(Collectors.toList());
    Collections.reverse(result);
    try (final var writer =
        Files.newBufferedWriter(
            Paths.get(String.format("res/vigenere/kasiski/%s.txt", filename.toLowerCase())))) {
      writer.write(String.format("%s results: \n", filename));
      for (final var biGram : result) {
        writer.write(
            String.format(
                "\t%s : %d, Distances: %s\n",
                biGram.getKey(),
                biGram.getValue(),
                Arrays.toString(distances.get(biGram.getKey()).toArray(Integer[]::new))));
      }
    }
    if (n == 2) {
      biGramsDistances = distances;
    } else {
      triGramsDistances = distances;
    }
  }

  private static void findBiGrams(final String text) throws IOException {
    System.out.println("Finding repeating bigrams...");
    findNGrams(text, 2, "Bigrams");
  }

  private static void findTriGrams(final String text) throws IOException {
    System.out.println("Finding repeating trigrams...");
    findNGrams(text, 3, "Trigrams");
  }

  private static void findMostProbableLength() {
    System.out.println("Performing frequency analysis...");
    final var allNumbers = new ArrayList<Integer>();
    biGramsDistances.values().forEach(allNumbers::addAll);
    triGramsDistances.values().forEach(allNumbers::addAll);
    final var min = 4;
    final var max = 13;
    final var result = new TreeMap<Integer, Integer>();
    for (var i = min; i <= max; i++) {
      final var finalI = i;
      result.put(i, (int) allNumbers.stream().filter(n -> n % finalI == 0).count());
    }
    for (final var e : result.entrySet()) {
      System.out.println(String.format("\tDivides %d : %d numbers", e.getKey(), e.getValue()));
    }
    final var length =
        result.entrySet().stream()
            .max(Comparator.comparing(Map.Entry::getValue))
            .orElseThrow()
            .getKey();
    System.out.println(String.format("So, the most probable key length divides %d", length));
  }

  private static void print(final String text, final int n, final Integer th) throws IOException {
    final var split = new ArrayList<String>();
    for (var i = 0; i < text.length(); i += n) {
      split.add(text.substring(i, Math.min(i + n, text.length())));
    }
    final var x = 15;
    try (final var writer = Files.newBufferedWriter(Paths.get("res/vigenere/kasiski/split.txt"))) {
      final var letters = new HashMap<String, Integer>();
      for (var i = 0; i < split.size(); i += x) {
        var s = split.subList(i, Math.min(i + x, split.size()));
        if (th != null) {
          s =
              s.stream()
                  .map(str -> String.valueOf(str.charAt(Math.min(th - 1, str.length() - 1))))
                  .collect(Collectors.toList());
          s.forEach(l -> letters.put(l, letters.containsKey(l) ? letters.get(l) + 1 : 1));
        }
        writer.write(String.join("\t", s).toUpperCase());
        writer.newLine();
      }
      if (th != null) {
        writer.write(
            letters.entrySet().stream()
                .sorted((l, r) -> r.getValue().compareTo(l.getValue()))
                .map(e -> String.format("%s: %d", e.getKey(), e.getValue()))
                .collect(Collectors.joining("\n")));
        writer.newLine();
      }
    }
  }

  private static void analyze(final String text, final int n) {
    lettersByPosition.clear();
    final var result = new StringBuilder();
    final var split = new ArrayList<String>();
    for (var i = 0; i < text.length(); i += n) {
      split.add(text.substring(i, Math.min(i + n, text.length())));
    }
    for (var th = 0; th < n; th++) {
      final var letters = new HashMap<String, Integer>();
      final var finalTh = th;
      final var s =
          split.stream()
              .map(str -> String.valueOf(str.charAt(Math.min(finalTh, str.length() - 1))))
              .collect(Collectors.toList());
      s.forEach(l -> letters.put(l, letters.containsKey(l) ? letters.get(l) + 1 : 1));
      lettersByPosition.put(th, letters);
      result.append(
          letters.entrySet().stream()
              .max(Comparator.comparing(Map.Entry::getValue))
              .map(Map.Entry::getKey)
              .orElseThrow());
    }
    printPossibleKeyWord(result);
  }

  private static void printPossibleKeyWord(final StringBuilder result) {
    final var key = new StringBuilder();
    final var resultCh = result.toString().toCharArray();
    for (char ch : resultCh) {
      var diff = ch - 'e';
      if (diff < 0) diff += 26;
      key.append((char) ('a' + diff));
    }
    System.out.println(String.format("Possible key word: %s", key));
  }

  private static void go(final int n, final int letter) {
    final var result = new StringBuilder();
    for (var th = 0; th < n; th++) {
      final var sorted = new ArrayList<>(lettersByPosition.get(th).entrySet());
      sorted.sort((l, r) -> r.getValue().compareTo(l.getValue()));
      if (th == letter - 1) {
        if (lettersByPosition.get(th).entrySet().isEmpty()) {
          System.out.println("Nowhere more to move");
          return;
        }
        lettersByPosition
            .get(th)
            .entrySet()
            .removeIf(e -> e.getKey().equals(sorted.get(0).getKey()));
        sorted.remove(sorted.get(0));
      }
      result.append(sorted.get(0).getKey());
    }
    printPossibleKeyWord(result);
  }

  public static void main(final String[] args) throws IOException {
    var text =
        String.join(" ", Files.readAllLines(Paths.get("res/vigenere/kasiski/input.txt")))
            .toLowerCase()
            .replaceAll("[^a-z]", "");
    final var scanner = new Scanner(System.in);
    while (true) {
      var command = scanner.nextLine();
      if (command.equals("/f")) {
        findBiGrams(text);
        findTriGrams(text);
        findMostProbableLength();
      } else if (command.equals("/r")) {
        System.out.println("Text successfully reloaded");
        text =
            String.join(" ", Files.readAllLines(Paths.get("res/vigenere/kasiski/input.txt")))
                .toLowerCase()
                .replaceAll("[^a-z]", "");
      } else if (command.equals("/e")) {
        System.out.println("Encoded text with new keyword, press /r to reload file");
        Util.encodeWithRandomWord();
      } else if (command.startsWith("/p")) {
        System.out.println("Type nth letter or 'no'");
        final var answer = scanner.nextLine();
        print(
            text,
            Integer.parseInt(command.split(" ")[1]),
            answer.toLowerCase().equals("no") ? null : Integer.parseInt(answer));
      } else if (command.startsWith("/a")) {
        analyze(text, Integer.parseInt(command.split(" ")[1]));
      } else if (command.startsWith("/g")) {
        final var input = command.split(" ");
        go(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
      } else if (command.equals(":q")) {
        return;
      } else {
        System.out.println("Unknown command");
      }
    }
  }
}
