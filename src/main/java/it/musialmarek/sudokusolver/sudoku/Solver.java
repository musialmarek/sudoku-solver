package it.musialmarek.sudokusolver.sudoku;

import it.musialmarek.sudokusolver.model.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class Solver {
    static Integer[][] curr = new Integer[9][9];

    public static Sudoku solveSudoku(Sudoku sudoku) {
        Integer[][] sudokuArray = sudoku.getArray();
        curr = sudokuArray;
        if (solve(0, 0, sudokuArray)) {
            sudoku.setArray(curr);
            log.debug("{}", sudoku.toString());
        } else {
            log.debug("impossible to solve");
        }
        return sudoku;
    }

    static boolean isInsertPossible(Integer col, Integer row, Integer value) {
        for (int i = 0; i < 9; i++) {
            if (value.equals(curr[col][i]) || value.equals(curr[i][row]) ||
                    value.equals(curr[col / 3 * 3 + i % 3][row / 3 * 3 + i / 3])) return false;
        }
        return true;
    }

    static boolean next(Integer col, Integer row, Integer[][] sudokuArray) {
        if (col == 8 && row == 8) return true;
        else if (col == 8) return solve(0, row + 1, sudokuArray);
        else return solve(col + 1, row, sudokuArray);
    }

    static boolean solve(Integer col, Integer row, Integer[][] sudokuArray) {
        if (sudokuArray[col][row] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (isInsertPossible(col, row, i)) {
                    curr[col][row] = i;
                    if (next(col, row, sudokuArray)) {
                        return true;
                    }
                }
            }
            curr[col][row] = 0;
            return false;
        }
        return next(col, row, sudokuArray);
    }
}
