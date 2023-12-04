package de.graefe.java.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {
        problem1();
        problem2();
    }


    private static void problem1() throws IOException {

        int result = 0;

        int rows = 0;
        int columns = 0;

        BufferedReader reader = new BufferedReader(new FileReader("day3.txt"));

        String line = reader.readLine();

        while (line != null) {
            columns = line.length();
            rows++;
            line = reader.readLine();
        }

        String[][] matrix = new String[rows][columns];

        reader = new BufferedReader(new FileReader("day3.txt"));

        line = reader.readLine();

        int row = 0;
        while (line != null) {
            for (int i = 0; i < columns; i++) {
                matrix[row][i] = line.substring(i, i + 1);
            }
            row++;
            line = reader.readLine();
        }
        char[][] matrix2 = new char[rows][columns];
        for (int r=0; r<rows; r++) {
            for (int c=0; c<columns; c++) {
                matrix2[r][c] = matrix[r][c].charAt(0);
            }
        }

        for (int r=0; r<rows; r++) {
            for (int c=0; c<columns; c++) {
                if (Character.isDigit(matrix2[r][c])) {
                    if (Character.isDigit(matrix2[r][c+1])) {
                        if (Character.isDigit(matrix2[r][c+2])) {
                            if (safeGet(matrix2, r-1, c-1) != '.' ||
                                    safeGet(matrix2, r, c-1) != '.' ||
                                    safeGet(matrix2, r+1, c-1) != '.' ||
                                    safeGet(matrix2, r-1, c) != '.' ||
                                    safeGet(matrix2, r+1, c) != '.' ||
                                    safeGet(matrix2, r-1, c+1) != '.' ||
                                    safeGet(matrix2, r-1, c+2) != '.' ||
                                    safeGet(matrix2, r-1, c+3) != '.' ||
                                    safeGet(matrix2, r, c+3) != '.' ||
                                    safeGet(matrix2, r+1, c+1) != '.' ||
                                    safeGet(matrix2, r+1, c+2) != '.' ||
                                    safeGet(matrix2, r+1, c+3) != '.') {
                                result += Integer.parseInt(matrix[r][c].concat(matrix[r][c+1]).concat(matrix[r][c+2]));
                                }
                            c += 2;
                        } else {
                            if (safeGet(matrix2, r-1, c-1) != '.' ||
                                    safeGet(matrix2, r, c-1) != '.' ||
                                    safeGet(matrix2, r+1, c-1) != '.' ||
                                    safeGet(matrix2, r-1, c) != '.' ||
                                    safeGet(matrix2, r-1, c+1) != '.' ||
                                    safeGet(matrix2, r-1, c+2) != '.' ||
                                    safeGet(matrix2, r, c+2) != '.' ||
                                    safeGet(matrix2, r+1, c) != '.' ||
                                    safeGet(matrix2, r+1, c+1) != '.' ||
                                    safeGet(matrix2, r+1, c+2) != '.') {
                                result += Integer.parseInt(matrix[r][c].concat(matrix[r][c+1]));
                                }
                            c++;
                        }
                    } else {
                        if (safeGet(matrix2, r-1, c-1) != '.' ||
                                safeGet(matrix2, r-1, c) != '.' ||
                                safeGet(matrix2, r-1, c+1) != '.' ||
                                safeGet(matrix2, r, c-1) != '.' ||
                                safeGet(matrix2, r, c+1) != '.' ||
                                safeGet(matrix2, r+1, c-1) != '.' ||
                                safeGet(matrix2, r+1, c) != '.' ||
                                safeGet(matrix2, r+1, c+1) != '.') {
                            result += Integer.parseInt(matrix[r][c]);
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static char safeGet(char[][] matrix, int r, int c) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length) {
            return '.';  // return default value
        }
        return matrix[r][c];
    }

    private static void problem2() throws IOException {
        int result = 0;

        BufferedReader reader = new BufferedReader(new FileReader("day3.txt"));
        List<String> lines = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }

        reader.close();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '*') {
                    List<String> numbers = new ArrayList<>();

                    for (int k = Math.max(0, i - 1); k <= Math.min(lines.size() - 1, i + 1); k++) {
                        for (int l = Math.max(0, j - 1); l <= Math.min(lines.get(k).length() - 1, j + 1); l++) {
                            if (Character.isDigit(lines.get(k).charAt(l))) {
                                int start = l;
                                while (start > 0 && Character.isDigit(lines.get(k).charAt(start - 1))) {
                                    start--;
                                }

                                int end = l;
                                while (end < lines.get(k).length() - 1 && Character.isDigit(lines.get(k).charAt(end + 1))) {
                                    end++;
                                }

                                String number = lines.get(k).substring(start, end + 1);
                                if (!numbers.contains(number)) {
                                    numbers.add(number);
                                }
                            }
                        }
                    }

                    if (numbers.size() == 2) {
                        result += Integer.parseInt(numbers.get(0)) * Integer.parseInt(numbers.get(1));
                    }
                }
            }
        }

        System.out.println(result);
    }
}
