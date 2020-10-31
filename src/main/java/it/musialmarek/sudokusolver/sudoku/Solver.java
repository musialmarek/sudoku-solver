package it.musialmarek.sudokusolver.sudoku;

import it.musialmarek.sudokusolver.model.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class Solver {
    private static Integer[][] tempArray;
    private static int size;
    private static int length;

    public static Sudoku solveSudoku(Sudoku sudoku) {
        if (isSudokuCorrect(sudoku)) {
            tempArray = sudoku.getArray().clone();
            size = sudoku.getSize();
            length = size * size;
            if (solve(0, 0, tempArray)) {
                sudoku.setArray(tempArray);
                log.debug("solved sudoku: {}", sudoku.toString());
            } else {
                log.debug("impossible to solve");
            }
        }
        return sudoku;
    }

    public static boolean isSudokuCorrect(Sudoku sudoku) {
        Integer[][] sudokuArray = sudoku.getArray();
        size = sudoku.getSize();
        for (int row = 0; row < sudokuArray.length; row++) {
            for (int col = 0; col < sudokuArray.length; col++) {
                Integer value = sudokuArray[row][col];
                if (value != null)
                    for (int i = 0; i < sudokuArray.length; i++) {
                        int sectionRow = row / size * size + i % size;
                        int sectionCol = col / size * size + i / size;
                        boolean rowContain = value.equals(sudokuArray[row][i]) && i != col;
                        boolean colContain = value.equals(sudokuArray[i][col]) && i != row;
                        boolean sectionContain = value.equals(sudokuArray[sectionRow][sectionCol]) && sectionCol != col && sectionRow != row;
                        if (rowContain || colContain || sectionContain) {
                            return false;
                        }
                    }
            }
        }
        return true;
    }


    private static boolean isInsertPossible(Integer row, Integer col, Integer value) {
        for (int i = 0; i < length; i++) {
            if (value.equals(tempArray[row][i]) || value.equals(tempArray[i][col]) ||
                    value.equals(tempArray[row / size * size + i % size][col / size * size + i / size])) return false;
        }
        return true;
    }

    private static boolean next(Integer col, Integer row, Integer[][] sudokuArray) {
        if (col == length - 1 && row == length - 1) return true;
        else if (col == length - 1) return solve(0, row + 1, sudokuArray);
        else return solve(col + 1, row, sudokuArray);
    }

    private static boolean solve(Integer col, Integer row, Integer[][] sudokuArray) {
        if (sudokuArray[col][row] == null) {
            for (int i = 1; i <= length; i++) {
                if (isInsertPossible(col, row, i)) {
                    tempArray[col][row] = i;
                    if (next(col, row, sudokuArray)) {
                        return true;
                    }
                }
            }
            tempArray[col][row] = null;
            return false;
        }
        return next(col, row, sudokuArray);
    }
}
