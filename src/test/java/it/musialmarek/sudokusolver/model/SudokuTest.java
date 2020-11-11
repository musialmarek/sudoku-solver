package it.musialmarek.sudokusolver.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuTest {

    @ParameterizedTest(name = "should return size {0} when array length {1}")
    @CsvFileSource(resources = "/sizes-and-lengths.csv", numLinesToSkip = 1)
    void shouldReturnSizeByArrayLength(int size, int length) {
        //Given
        Sudoku sudoku = buildSudokuByArrayLength(length);
        //When
        int sudokuSize = sudoku.getSize();
        //Then
        assertThat(sudokuSize).isEqualTo(size);
    }

    @ParameterizedTest(name = "should set array length {1} when set size {0}")
    @CsvFileSource(resources = "/sizes-and-lengths.csv", numLinesToSkip = 1)
    void shouldSetArrayLengthWhenSudokuSetSize(int size, int length) {
        //Given
        Sudoku sudoku = new Sudoku();
        //When
        sudoku.setSize(size);
        //Then
        assertThat(sudoku.getArray().length).isEqualTo(length);
    }

    private Sudoku buildSudokuByArrayLength(int arrayLength) {
        return new Sudoku(new Integer[arrayLength][arrayLength]);
    }
}