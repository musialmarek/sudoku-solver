package it.musialmarek.sudokusolver.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class SudokuTest {
    private Integer[][] arrayOne;
    private Integer[][] arrayTwo;
    private Sudoku sudokuOne;
    private Sudoku sudokuTwo;

    @Before
    public void setUp() throws Exception {
        sudokuOne = new Sudoku();
        sudokuTwo = new Sudoku();
        arrayOne = new Integer[][]{
                {0, 1, 2, 3, 4, 5, 6, 7, 8},
                {10, 11, 12, 13, 14, 15, 16, 17, 18},
                {20, 21, 22, null, 24, 25, null, 27, 28},
                {30, 31, 32, 33, 34, 35, 36, 37, 38},
                {40, 41, 42, null, 44, 45, 46, 47, 48},
                {50, 51, 52, null, null, 55, 56, 57, 58},
                {60, 61, 62, 63, 64, 65, 66, 67, 68},
                {70, 71, 72, null, 74, 75, 76, 77, 78},
                {80, 81, 82, null, 84, 85, null, 87, 88}
        };
        arrayTwo = new Integer[][]{
                {1, 1, null, 2, 2, 2, 3, 3, 3},
                {1, 1, null, 2, 2, 2, 3, 3, 3},
                {1, 1, null, 2, 2, 2, 3, 3, 3},
                {4, 4, 4, 5, 5, 5, 6, 6, 6},
                {4, 4, 4, 5, 5, 5, 6, 6, 6},
                {4, 4, 4, 5, 5, 5, 6, 6, 6},
                {7, 7, null, 8, 8, 8, 9, 9, 9},
                {7, 7, null, 8, 8, 8, 9, 9, 9},
                {7, 7, null, 8, 8, 8, 9, 9, 9}
        };
    }

    @Test
    public void shouldFillColsOfSudokuWithDataFromArray() {
        //given
        sudokuOne.setArray(arrayOne);
        sudokuTwo.setArray(arrayTwo);
        //when
        sudokuOne.fillCols();
        sudokuTwo.fillCols();
        //then
        Integer[] colOne0 = {0, 10, 20, 30, 40, 50, 60, 70, 80};
        Integer[] colOne3 = {3, 13, 33, 63};
        Integer[] colTwo0 = {1, 1, 1, 4, 4, 4, 7, 7, 7};
        Integer[] colTwo2 = {4, 4, 4};
        Assert.assertThat(sudokuOne.getCols().length, is(9));
        Assert.assertThat(sudokuOne.getCols()[0], is(colOne0));
        Assert.assertThat(sudokuOne.getCols()[3], is(colOne3));
        Assert.assertThat(sudokuTwo.getCols().length, is(9));
        Assert.assertThat(sudokuTwo.getCols()[0], is(colTwo0));
        Assert.assertThat(sudokuTwo.getCols()[2], is(colTwo2));

    }

    @Test
    public void shouldFillRowsOfSudokuWithDataFromArray() {
        // given
        sudokuOne.setArray(arrayOne);
        sudokuTwo.setArray(arrayTwo);
        //when
        sudokuOne.fillRows();
        sudokuTwo.fillRows();
        //then
        Integer[] rowOne0 = {0,1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] rowOne8 = {80, 81, 82, 84, 85, 87, 88};
        Integer [] rowTwo0 =  {1, 1, 2, 2, 2, 3, 3, 3};
        Integer[] rowTwo5 ={4, 4, 4, 5, 5, 5, 6, 6, 6};
        Assert.assertThat(sudokuOne.getRows().length, is(9));
        Assert.assertThat(sudokuOne.getRows()[0], is(rowOne0));
        Assert.assertThat(sudokuOne.getRows()[8], is(rowOne8));
        Assert.assertThat(sudokuTwo.getRows().length, is(9));
        Assert.assertThat(sudokuTwo.getRows()[0], is(rowTwo0));
        Assert.assertThat(sudokuTwo.getRows()[5], is(rowTwo5));
    }
}