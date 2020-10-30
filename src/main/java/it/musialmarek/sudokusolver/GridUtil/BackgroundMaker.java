package it.musialmarek.sudokusolver.GridUtil;

public class BackgroundMaker {
    public static boolean makeBackground(int row, int col, int size) {
        int section = row - row % size + (col - col % size) / size;
        return (section / size + section % size) % 2 == 0;
    }
}
