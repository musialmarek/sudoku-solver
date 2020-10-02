package it.musialmarek.sudokusolver.model;


import lombok.Getter;

@Getter
public class Sudoku {
    private Integer[][] tab = new Integer[9][9];
    private Integer[][] cols = new Integer[9][];
    private Integer[][] rows = new Integer[9][];
    private Integer[][] sections = new Integer[9][];
}
