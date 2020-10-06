package it.musialmarek.sudokusolver.model;


import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@NoArgsConstructor
public class Sudoku {
    private Integer[][] array = new Integer[9][9];
    private Integer[][] cols = new Integer[9][];
    private Integer[][] rows = new Integer[9][];
    private Integer[][] sections = new Integer[9][];

    public Sudoku(Integer[][] array) {
        this.array = array;
        fillCols();
        fillRows();
        fillSections();
    }

    public Integer[][] getArray() {
        return array;
    }

    public Integer[][] getCols() {
        fillCols();
        return cols;
    }

    public Integer[][] getRows() {
        fillRows();
        return rows;
    }

    public Integer[][] getSections() {
        fillSections();
        return sections;
    }

    public void setArray(Integer[][] array) {
        this.array = array;
    }

    public void fillCols() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Integer element = this.array[j][i];
                if (element != null) {
                    this.cols[i] = ArrayUtils.add(this.cols[i], element);
                }
            }
        }
    }

    public void fillRows() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Integer element = this.array[i][j];
                if (element != null) {
                    this.rows[i] = ArrayUtils.add(this.rows[i], element);
                }
            }
        }
    }

    public void fillSections() {
        for (int bigRow = 0; bigRow < 3; bigRow++) {
            for (int bigCol = 0; bigCol < 3; bigCol++) {
                for (int smallRow = 0; smallRow < 3; smallRow++) {
                    for (int smallCol = 0; smallCol < 3; smallCol++) {
                        int section = bigRow * 3 + bigCol;
                        Integer element = this.array[bigRow * 3 + smallRow][bigCol * 3 + smallCol];
                        if (element != null) {
                            this.sections[section] = ArrayUtils.add(this.sections[section], element);
                        }
                    }
                }
            }
        }
    }
}
