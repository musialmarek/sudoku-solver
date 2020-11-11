package it.musialmarek.sudokusolver.model;

import org.junit.jupiter.api.Test;
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

//    @Test
//    void shouldReturn4WhenArrayLength16() {
//        //Given
//        Sudoku sudoku = buildSudokuByArrayLength(16);
//        //When
//        int size = sudoku.getSize();
//        //Then
//        assertThat(size).isEqualTo(4);
//    }
//
//    @Test
//    void shouldReturn2WhenArrayLength4() {
//        //Given
//        Sudoku sudoku = buildSudokuByArrayLength(4);
//        //When
//        int size = sudoku.getSize();
//        //Then
//        assertThat(size).isEqualTo(2);
//    }
//
//    @Test
//    void shouldReturnSudokuWithArrayLength4WhenSetSize2() {
//
//    }
//
//    @Test
//    void shouldReturnSudokuWithArrayLength9WhenSetSize3() {
//        //Given
//        Sudoku sudoku = new Sudoku();
//        //When
//        sudoku.setSize(3);
//        //Then
//        assertThat(sudoku.getArray().length).isEqualTo(9);
//    }
//
//    @Test
//    void shouldReturnSudokuWithArrayLength16WhenSetSize4() {
//        //Given
//        Sudoku sudoku = new Sudoku();
//        //When
//        sudoku.setSize(4);
//        //Then
//        assertThat(sudoku.getArray().length).isEqualTo(16);
//    }

    private Sudoku buildSudokuByArrayLength(int arrayLength) {
        return new Sudoku(new Integer[arrayLength][arrayLength]);
    }
}