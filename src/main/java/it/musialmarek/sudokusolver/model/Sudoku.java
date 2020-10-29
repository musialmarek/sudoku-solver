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
        sb.append("\n");
        for (int i = 0; i < array.length; i++) {
            if(i%3==0){
                sb.append("---------------------\n");
            }
            for (int j = 0; j < array[i].length; j++) {
                if(j%3==0){
                    sb.append("|");
                }
                sb.append(array[i][j]).append(",");

            }
            sb.append("|\n");
        }
        return sb.toString();
    }
}
