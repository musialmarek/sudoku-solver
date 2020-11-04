package it.musialmarek.sudokusolver.sudoku;

import it.musialmarek.sudokusolver.GridUtil.SectionClassNameSplitter;
import it.musialmarek.sudokusolver.model.Sudoku;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
@RequiredArgsConstructor
public class SudokuController {
    private final SectionClassNameSplitter sectionClassNameSplitter;

    @ModelAttribute("sectionSplitter")
    public SectionClassNameSplitter getSectionClassNameSplitter() {
        return sectionClassNameSplitter;
    }

    @GetMapping()
    public String showEmptyGrid(Model model, @RequestParam(name = "size", required = false) Integer size) {
        if (size == null) {
            size = 3;
        }
        Sudoku sudoku = new Sudoku().setSize(size);
        log.debug("added empty sudoku {}", sudoku.toString());
        model.addAttribute("sudoku", sudoku);
        model.addAttribute("size", size);
        return "sudoku-solver";
    }

    @PostMapping()
    public String solveSudoku(HttpServletRequest request, Model model) {
        log.debug("solving sudoku");
        int size = Integer.parseInt(request.getParameter("size"));
        Sudoku sudoku = getSudoku(request, size);
        log.debug(sudoku.toString());
        if (Solver.isSudokuCorrect(sudoku)) {
            Sudoku solvedSudoku = Solver.solveSudoku(sudoku);
            model.addAttribute("solvedsudoku", solvedSudoku);
        } else {
            model.addAttribute("message", "INCORRECT SUDOKU");
        }
        model.addAttribute("sudoku", sudoku);
        model.addAttribute("size", size);
        return "sudoku-solver";
    }

    private Sudoku getSudoku(HttpServletRequest request, int size) {
        Sudoku sudoku = new Sudoku().setSize(size);
        Integer[][] sudokuArray = sudoku.getArray();
        for (int row = 0; row < sudokuArray.length; row++) {
            for (int col = 0; col < sudokuArray[row].length; col++) {
                String cell = "sudoku" + row + "," + col;
                String value = request.getParameter(cell);
                if (value.isEmpty()) {
                    sudokuArray[row][col] = null;
                } else {
                    sudokuArray[row][col] = Integer.parseInt(value);
                }
            }
        }
        sudoku.setArray(sudokuArray);
        return sudoku;
    }
}
