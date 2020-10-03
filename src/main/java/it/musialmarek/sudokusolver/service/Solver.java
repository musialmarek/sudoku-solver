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
    public Sudoku solveSudoku(Sudoku sudoku) {
        return new Sudoku(new Integer[][]
                {
                        {7, 2, 5, 1, 6, 9, 3, 8, 4},
                        {6, 4, 9, 2, 3, 8, 1, 5, 7},
                        {8, 1, 3, 4, 5, 7, 2, 6, 9},
                        {1, 6, 2, 9, 4, 3, 5, 7, 8},
                        {9, 3, 4, 7, 8, 5, 6, 1, 2},
                        {5, 7, 8, 6, 1, 2, 9, 4, 3},
                        {2, 5, 1, 8, 9, 4, 7, 3, 6},
                        {3, 8, 7, 5, 2, 6, 4, 9, 1},
                        {4, 9, 6, 3, 7, 1, 8, 2, 5}
                });
    }

    private static Set<Integer>[][] getPossibilities(Sudoku sudoku) {
        Integer[] set = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[][][] possibilities = new Integer[9][9][];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.getArray()[i][j] == null) {
                    Set<Integer> notAvailableSet = new HashSet<>();
                    notAvailableSet.addAll(List.of(sudoku.getRows()[i]));
                    notAvailableSet.addAll(List.of(sudoku.getCols()[j]));
                    notAvailableSet.addAll(List.of(sudoku.getSections()[calculateSectionIndex(i, j)]));
                    Integer[] notAvailableNumbers = notAvailableSet.toArray(new Integer[0]);
                    possibilities[i][j] = ArrayUtils.removeElements(set, notAvailableNumbers);
                    System.out.println(i + "," + j + " - " + Arrays.toString(possibilities[i][j]));

                }
            }
        }
        return null;
    }

    private static int calculateSectionIndex(int x, int y) {
        return (x - x % 3) + (y - y % 3) / 3;
    }
}
