package it.musialmarek.sudokusolver.GridUtil;

import org.springframework.stereotype.Service;

@Service
public class SectionClassNameSplitter {
    public static boolean assignCellToSection(int row, int col, int size) {
        int section = row - row % size + (col - col % size) / size;
        return (section / size + section % size) % 2 == 0;
    }
}
