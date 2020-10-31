package it.musialmarek.sudokusolver.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class Sudoku {
    private Integer[][] array = new Integer[9][9];

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = this.getSize();
        sb.append("\n");
        for (int row = 0; row < array.length; row++) {
            if (row % size == 0) {
                sb.append("---------------------\n");
            }
            for (int col = 0; col < array[row].length; col++) {
                if (col % size == 0) {
                    sb.append("|");
                }
                sb.append(array[row][col]).append(",");

            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    public Sudoku setSize(int size) {
        int length = size * size;
        this.array = new Integer[length][length];
        return this;
    }

    public int getSize() {
        return (int) Math.pow(this.array.length, 0.5);
    }
}
