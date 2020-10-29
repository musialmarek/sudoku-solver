package it.musialmarek.sudokusolver.model;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Sudoku {
    private Integer[][] array = new Integer[9][9];

    public Sudoku(Integer[][] array) {
        this.array = array;
    }

    public Integer[][] getArray() {
        return array;
    }

    public void setArray(Integer[][] array) {
        this.array = array;
    }
}
