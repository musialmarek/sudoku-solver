package it.musialmarek.sudokusolver.sudoku;

import it.musialmarek.sudokusolver.model.Sudoku;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class SolverTest {
    private Solver solver;

    @Before
    public void setUp() {
        solver = new Solver();
    }

    @Test
    public void shouldReturnSolvedSudoku() {
        //given
        Sudoku sudoku = new Sudoku(new Integer[][]{
                {7, 2, null, null, 6, 9, null, null, 4},
                {null, null, 9, null, 3, null, null, 5, null},
                {8, null, null, 4, null, null, 2, 6, null},
                {null, 6, null, 9, null, null, null, null, 8},
                {null, 3, null, null, 8, null, null, 1, null},
                {5, null, null, null, null, 2, null, 4, null},
                {null, 5, 1, null, null, 4, null, null, 6},
                {null, 8, null, null, 2, null, 4, null, null},
                {4, null, null, 3, 7, null, null, 2, 5}
        });
        //when
        Sudoku solvedSudoku = Solver.solveSudoku(sudoku);
        //then
        Assert.assertThat(solvedSudoku.getArray(), is(new Integer[][]{
                {7, 2, 5, 1, 6, 9, 3, 8, 4},
                {6, 4, 9, 2, 3, 8, 1, 5, 7},
                {8, 1, 3, 4, 5, 7, 2, 6, 9},
                {1, 6, 2, 9, 4, 3, 5, 7, 8},
                {9, 3, 4, 7, 8, 5, 6, 1, 2},
                {5, 7, 8, 6, 1, 2, 9, 4, 3},
                {2, 5, 1, 8, 9, 4, 7, 3, 6},
                {3, 8, 7, 5, 2, 6, 4, 9, 1},
                {4, 9, 6, 3, 7, 1, 8, 2, 5}
        }));
    }
}