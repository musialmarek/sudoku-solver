package it.musialmarek.sudokusolver.service;

import it.musialmarek.sudokusolver.model.Sudoku;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Solver {
    public static Sudoku solveSudoku(Sudoku sudoku) {

        int numberOfNullsStart = 1;
        int numberOfNullEnd = 0;

        while (numberOfNullEnd < numberOfNullsStart) {
            numberOfNullsStart = getNumberOfNulls(sudoku);
            fillEmptiesBySingle(sudoku);
            fillEmptiesByOnlys(sudoku);
            numberOfNullEnd = getNumberOfNulls(sudoku);
        }
        return sudoku;
    }

    private static void fillEmptiesByOnlys(Sudoku sudoku) {
        fillEmptiesByRowOnlys(sudoku);
        fillEmptiesByColOnlys(sudoku);
        fillEmptiesBySectionOnlys(sudoku);
    }

    private static void fillEmptiesBySectionOnlys(Sudoku sudoku) {
        for (int i = 0; i < sudoku.getArray().length; i++) {
            Integer[][][] possibilities = getPossibilities(sudoku);
            Integer[] onlys = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9};
            for (int j = 0; j < sudoku.getArray().length; j++) {
                onlys = ArrayUtils.removeElements(onlys, possibilities[j / 3 + 3 * (i / 3)][j % 3 + 3 * (i % 3)]);
            }
            for (int j = 0; j < sudoku.getArray().length; j++) {
                for (Integer only : onlys) {
                    int row = j / 3 + 3 * (i / 3);
                    int col = j % 3 + 3 * (i % 3);
                    if (sudoku.getArray()[row][col] == null && ArrayUtils.contains(possibilities[row][col], only)) {
                        sudoku.getArray()[row][col] = only;
                    }
                }
            }
        }
    }

    private static void fillEmptiesByColOnlys(Sudoku sudoku) {
        for (int col = 0; col < sudoku.getArray().length; col++) {
            Integer[][][] possibilities = getPossibilities(sudoku);
            Integer[] onlys = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9};
            for (int row = 0; row < sudoku.getArray().length; row++) {
                onlys = ArrayUtils.removeElements(onlys, possibilities[row][col]);
            }
            for (int row = 0; row < sudoku.getArray().length; row++) {
                for (Integer only : onlys) {
                    if (sudoku.getArray()[row][col] == null && ArrayUtils.contains(possibilities[row][col], only)) {
                        sudoku.getArray()[row][col] = only;
                    }
                }
            }
        }
    }

    private static void fillEmptiesByRowOnlys(Sudoku sudoku) {
        for (int row = 0; row < sudoku.getArray().length; row++) {
            Integer[][][] possibilities = getPossibilities(sudoku);
            Integer[] onlys = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9};
            for (int col = 0; col < sudoku.getArray().length; col++) {
                onlys = ArrayUtils.removeElements(onlys, possibilities[row][col]);
            }
            for (int col = 0; col < sudoku.getArray().length; col++) {
                for (Integer only : onlys) {
                    if (sudoku.getArray()[row][col] == null && ArrayUtils.contains(possibilities[row][col], only)) {
                        sudoku.getArray()[row][col] = only;
                    }
                }
            }
        }
    }

    private static void fillEmptiesBySingle(Sudoku sudoku) {
        Integer[][][] possibilities = getPossibilities(sudoku);
        for (int i = 0; i < sudoku.getArray().length; i++) {
            for (int j = 0; j < sudoku.getArray()[i].length; j++) {
                if (possibilities[i][j] != null && possibilities[i][j].length == 1 && sudoku.getArray()[i][j] == null) {
                    sudoku.getArray()[i][j] = possibilities[i][j][0];
                }
            }

        }
    }

    private static Integer getNumberOfNulls(Sudoku sudoku) {
        Integer numberOfNulls = 0;
        for (Integer[] integers : sudoku.getArray()) {
            for (Integer integer : integers) {
                if (integer == null) {
                    numberOfNulls++;
                }
            }
        }
        return numberOfNulls;
    }

    private static Integer[][][] getPossibilities(Sudoku sudoku) {
        Integer[] set = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[][][] possibilities = new Integer[9][9][];
        for (int row = 0; row < sudoku.getArray().length; row++) {
            for (int col = 0; col < sudoku.getArray()[row].length; col++) {
                if (sudoku.getArray()[row][col] == null) {
                    Set<Integer> notAvailableSet = new HashSet<>();
                    notAvailableSet.addAll(List.of(sudoku.getRows()[row]));
                    notAvailableSet.addAll(List.of(sudoku.getCols()[col]));
                    notAvailableSet.addAll(List.of(sudoku.getSections()[calculateSectionIndex(row, col)]));
                    Integer[] notAvailableNumbers = notAvailableSet.toArray(new Integer[0]);
                    possibilities[row][col] = ArrayUtils.removeElements(set, notAvailableNumbers);
                } else {
                    possibilities[row][col] = ArrayUtils.add(new Integer[0], sudoku.getArray()[row][col]);
                }
            }
        }
        return possibilities;
    }

    private static int calculateSectionIndex(int row, int col) {
        return (row - row % 3) + (col - col % 3) / 3;
    }

}
