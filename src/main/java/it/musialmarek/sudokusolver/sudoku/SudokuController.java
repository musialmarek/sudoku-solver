package it.musialmarek.sudokusolver.sudoku;

import it.musialmarek.sudokusolver.model.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
public class SudokuController {
    @GetMapping()
    public String showEmptyTable(Model model) {
        int[] size = new int[9];
        for (int i = 0; i < size.length; i++) {
            size[i] = i;
        }
        model.addAttribute("size", size);
        return "sudoku-solver";
    }

    @PostMapping()
    public String solveSudoku(HttpServletRequest request, Model model) {
        Integer[][] userSudoku = new Integer[9][9];
        for (int row = 0; row < userSudoku.length; row++) {
            for (int col = 0; col < userSudoku[row].length; col++) {
                String cell = "sudoku" + row + col;
                log.debug("filling cell {}", cell);
                userSudoku[row][col] = Integer.parseInt(request.getParameter(cell));
            }
        }
        Sudoku sudoku = new Sudoku(userSudoku);
        Sudoku solvedSudoku = Solver.solveSudoku(sudoku);
        model.addAttribute("sudoku", sudoku.getArray());
        model.addAttribute("solvedsudoku", solvedSudoku.getArray());
        return "sudoku-solver";
    }
}
