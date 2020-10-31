package it.musialmarek.sudokusolver.sudoku;

import it.musialmarek.sudokusolver.GridUtil.SectionSplitter;
import it.musialmarek.sudokusolver.model.Sudoku;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
@RequiredArgsConstructor
public class SudokuController {
    private final SectionSplitter sectionSplitter;

    @ModelAttribute("sectionSplitter")
    public SectionSplitter getSectionSplitter() {
        return sectionSplitter;
    }

    @GetMapping()
    public String showEmptyGrid(Model model) {
        int size = 3;
        Sudoku sudoku = new Sudoku().setSize(size);
        log.debug("added empty sudoku {}", sudoku.toString());
        model.addAttribute("sudoku", sudoku);
        model.addAttribute("size", size);
        return "sudoku-solver";
    }

    @PostMapping()
    public String solveSudoku(HttpServletRequest request, Model model, Sudoku sudoku) {
        log.debug(sudoku.toString());
        if (Solver.isSudokuCorrect(sudoku)) {
            Sudoku solvedSudoku = Solver.solveSudoku(sudoku);
            model.addAttribute("solvedsudoku", solvedSudoku.getArray());
        } else {
            model.addAttribute("message", "INCORRECT SUDOKU");
        }
        model.addAttribute("sudoku", sudoku);
        int size = 3;
        model.addAttribute("size", size);
        return "sudoku-solver";
    }
}
