package de.graefe.java.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {

    public static void main(String[] args) throws IOException {
        problem1();
        problem2();
    }

    private static void problem1() throws IOException {
        int result = 0;
        BufferedReader reader = new BufferedReader(new FileReader("day4.txt"));
        String line = reader.readLine();

        while (line != null) {
            List<Integer> winningNumbers = new ArrayList<>();
            List<Integer> numbers = new ArrayList<>();

            String numbersString = line.split(":")[1].trim();

            for (String s : numbersString.split("\\|")[0].split(" ")) {
                if (!s.isEmpty()) {
                    winningNumbers.add(Integer.parseInt(s));
                }
            }
            for (String s : numbersString.split("\\|")[1].split(" ")) {
                if (!s.isEmpty()) {
                    numbers.add(Integer.parseInt(s));
                }
            }

            int matches = 0;

            for (Integer i : winningNumbers) {
                if (numbers.contains(i)) {
                    matches++;
                }
            }

            if (matches == 1) {
                result++;
            } else if (matches != 0) {
                result += (int) (Math.pow(2, matches-1));
            }

            line = reader.readLine();
        }
        System.out.println(result);
    }

    private static void problem2() throws IOException {
        int result = 0;
        BufferedReader reader = new BufferedReader(new FileReader("day4.txt"));
        String line = reader.readLine();

        List<String> lines = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        int index = 0;

        while (line!= null) {
            lines.add(line);
            map.put(index, 1);
            line = reader.readLine();
            index++;
        }

        for (int i = 0; i < map.size(); i++) {
            List<Integer> winningNumbers = new ArrayList<>();
            List<Integer> numbers = new ArrayList<>();

            String numbersString = lines.get(i).split(":")[1].trim();

            for (String s : numbersString.split("\\|")[0].split(" ")) {
                if (!s.isEmpty()) {
                    winningNumbers.add(Integer.parseInt(s));
                }
            }
            for (String s : numbersString.split("\\|")[1].split(" ")) {
                if (!s.isEmpty()) {
                    numbers.add(Integer.parseInt(s));
                }
            }

            int matches = 0;

            for (Integer j : winningNumbers) {
                if (numbers.contains(j)) {
                    matches++;
                }
            }

            while (matches > 0) {
                map.put(i+matches, map.get(i+matches) + map.get(i));
                matches--;
            }
        }

        for (Integer i : map.values()) {
            result += i;
        }

        System.out.println(result);
    }
}
