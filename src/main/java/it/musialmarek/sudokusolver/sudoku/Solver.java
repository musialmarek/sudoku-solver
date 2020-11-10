package it.musialmarek.sudokusolver.sudoku;

import it.musialmarek.sudokusolver.model.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class Solver {
    List<List<Integer>> temp = new ArrayList<>();
    int currentRow;
    int currentCol;
    int size;
    int length;

    public Sudoku solveSudoku(Sudoku sudoku) throws IllegalArgumentException {
        if (isSudokuCorrect(sudoku)) {
            List<List<Integer>> origin = new ArrayList<>();
            for (Integer[] integers : sudoku.getArray()) {
                origin.add(Arrays.asList(integers));
            }
            List<List<Integer>> solve = solve(origin);
            for (List<Integer> list : solve) {
                Integer[] row = sudoku.getArray()[solve.indexOf(list)];
                list.toArray(row);
            }
            log.debug("solved sudoku: {}", sudoku.toString());
        }
        return sudoku;
    }


    public List<List<Integer>> solve(List<List<Integer>> origin) {
        createCopyOfSudokuBoard(origin);
        currentRow = 0;
        currentCol = 0;
        size = (int) Math.pow(temp.size(), 0.5);
        length = temp.size();
        while (currentRow < length && currentCol < length) {
            if (temp.get(currentRow).get(currentCol) == null) {
                int firstPossible = getFirstPossible();
                while (firstPossible == 0) {
                    if (isPreviousCell()) {
                        goToPreviousCell();
                        if (origin.get(currentRow).get(currentCol) == null) {
                            firstPossible = getFirstPossible();
                        }
                    } else {
                        //TODO throw exeption or some information that solving is impossible
                        log.error("EXEPTION");
                    }
                }
                temp.get(currentRow).set(currentCol, firstPossible);
            }
            nextCell();
        }
        return temp;
    }

    private void createCopyOfSudokuBoard(List<List<Integer>> origin) {
        temp = new ArrayList<>();
        for (List<Integer> list : origin) {
            List<Integer> row = new ArrayList<>(list);
            temp.add(row);
        }
    }

    private void goToPreviousCell() {
        if (currentCol > 0) {
            currentCol = currentCol - 1;
        } else if (currentCol == 0) {
            currentRow = currentRow - 1;
            currentCol = length - 1;
        }
    }

    private boolean isPreviousCell() {
        return currentRow != 0 || currentCol != 0;
    }

    private int getFirstPossible() {
        Integer currentValue = temp.get(currentRow).get(currentCol);
        if (currentValue == null) {
            currentValue = 0;
        }
        for (int value = currentValue + 1; value <= length; value++) {
            if (isInsertPossible(currentRow, currentCol, value)) {
                return value;
            }
        }
        temp.get(currentRow).set(currentCol, null);
        return 0;
    }

    private boolean isInsertPossible(Integer row, Integer col, Integer value) {
        for (int i = 0; i < length; i++) {
            if (value.equals(temp.get(row).get(i)) || value.equals(temp.get(i).get(col)) ||
                    value.equals(temp.get(row / size * size + i % size).get(col / size * size + i / size)))
                return false;
        }
        return true;
    }

    private void nextCell() {
        if (currentCol == length - 1) {
            currentRow = currentRow + 1;
            currentCol = 0;
        } else if (currentCol < length - 1) {
            currentCol = currentCol + 1;
        }
    }

    public boolean isSudokuCorrect(Sudoku sudoku) {
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
}
