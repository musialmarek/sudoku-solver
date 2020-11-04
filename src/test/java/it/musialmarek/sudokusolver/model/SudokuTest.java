package it.musialmarek.sudokusolver.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuTest {

    @Test
    void shouldReturn3WhenArrayLength9() {
        //Given
        Sudoku sudoku = buildSudokuByArrayLength(9);
        //When
        int size = sudoku.getSize();
        //Then
        assertThat(size).isEqualTo(3);
    }

    @Test
    void shouldReturn4WhenArrayLength16() {
        //Given
        Sudoku sudoku = buildSudokuByArrayLength(16);
        //When
        int size = sudoku.getSize();
        //Then
        assertThat(size).isEqualTo(4);
    }

    @Test
    void shouldReturn2WhenArrayLength4() {
        //Given
        Sudoku sudoku = buildSudokuByArrayLength(4);
        //When
        int size = sudoku.getSize();
        //Then
        assertThat(size).isEqualTo(2);
    }

    @Test
    void shouldReturnSudokuWithArrayLength4WhenSetSize2() {
        //Given
        Sudoku sudoku = new Sudoku();
        //When
        sudoku.setSize(2);
        //Then
        assertThat(sudoku.getArray().length).isEqualTo(4);
    }

    @Test
    void shouldReturnSudokuWithArrayLength9WhenSetSize3() {
        //Given
        Sudoku sudoku = new Sudoku();
        //When
        sudoku.setSize(3);
        //Then
        assertThat(sudoku.getArray().length).isEqualTo(9);
    }

    @Test
    void shouldReturnSudokuWithArrayLength16WhenSetSize4() {
        //Given
        Sudoku sudoku = new Sudoku();
        //When
        sudoku.setSize(4);
        //Then
        assertThat(sudoku.getArray().length).isEqualTo(16);
    }

    private Sudoku buildSudokuByArrayLength(int arrayLength) {
        return new Sudoku(new Integer[arrayLength][arrayLength]);
    }
}