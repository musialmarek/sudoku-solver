package it.musialmarek.sudokusolver.service;

import it.musialmarek.sudokusolver.model.Sudoku;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Solver {
    public static Sudoku solveSudoku(Sudoku sudoku) {

        Integer numberOfNullsStart = 1;
        Integer numberOfNullEnd = 0;
        while (numberOfNullEnd < numberOfNullsStart) {
            numberOfNullsStart = getNumberOfNulls(sudoku);
            fillEmptyBySingle(sudoku);
            numberOfNullEnd = getNumberOfNulls(sudoku);
        }
        return sudoku;
    }

    private static void fillEmptyBySingle(Sudoku sudoku) {
        Integer[][][] possibilities = getPossibilities(sudoku);
        for (int i = 0; i < sudoku.getArray().length; i++) {
            for (int j = 0; j < sudoku.getArray()[i].length; j++) {
                if (possibilities[i][j] != null && possibilities[i][j].length == 1) {
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
        for (int i = 0; i < sudoku.getArray().length; i++) {
            for (int j = 0; j < sudoku.getArray()[i].length; j++) {
                if (sudoku.getArray()[i][j] == null) {
                    Set<Integer> notAvailableSet = new HashSet<>();
                    notAvailableSet.addAll(List.of(sudoku.getRows()[i]));
                    notAvailableSet.addAll(List.of(sudoku.getCols()[j]));
                    notAvailableSet.addAll(List.of(sudoku.getSections()[calculateSectionIndex(i, j)]));
                    Integer[] notAvailableNumbers = notAvailableSet.toArray(new Integer[0]);
                    possibilities[i][j] = ArrayUtils.removeElements(set, notAvailableNumbers);
                }
            }
        }
        return possibilities;
    }

    private static int calculateSectionIndex(int x, int y) {
        return (x - x % 3) + (y - y % 3) / 3;
    }
}
