package it.musialmarek.sudokusolver.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@NoArgsConstructor
public class Sudoku {
    private Integer[][] array = new Integer[9][9];
    private Integer[][] cols = new Integer[9][];
    private Integer[][] rows = new Integer[9][];
    private Integer[][] sections = new Integer[9][];

    public void setArray(Integer[][] array) {
        this.array = array;
    }

    public void fillCols() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Integer element = array[j][i];
                if (element != null) {
                    cols[i] = ArrayUtils.add(cols[i], element);
                }
            }
        }
    }

}
