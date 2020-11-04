package it.musialmarek.sudokusolver.GridUtil;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SectionClassNameSplitterTest {


    @Test
    public void shouldReturnTrueForRow3Col3Size2() {
        //Given
        int row = 3;
        int col = 3;
        int size = 2;
        //When
        boolean partOfSectionClassName = SectionClassNameSplitter.assignCellToSection(row, col, size);
        //Then
        assertThat(partOfSectionClassName).isTrue();
    }

    @Test
    public void shouldReturnFalseForRow3Col1Size2() {
        //When
        boolean partOfSectionClassName = SectionClassNameSplitter.assignCellToSection(3, 1, 2);
        //Then
        assertThat(partOfSectionClassName).isFalse();
    }

    @Test
    public void shouldReturnTrueForRow6Col2Size3() {
        //When
        boolean partOfSectionClassName = SectionClassNameSplitter.assignCellToSection(6, 2, 3);
        //Then
        assertThat(partOfSectionClassName).isTrue();
    }

    @Test
    public void shouldReturnFalseForRow3Col7Size3() {
        //When
        boolean partOfSectionClassName = SectionClassNameSplitter.assignCellToSection(3, 7, 3);
        //Then
        assertThat(partOfSectionClassName).isFalse();
    }

    @Test
    public void shouldReturnTrueForRow11Col10Size4() {
        //When
        boolean partOfSectionClassName = SectionClassNameSplitter.assignCellToSection(11, 10, 4);
        //Then
        assertThat(partOfSectionClassName).isTrue();
    }

    @Test
    public void shouldReturnFalseForRow13Col8Size4() {
        //When
        boolean partOfSectionClassName = SectionClassNameSplitter.assignCellToSection(13, 8, 4);
        //Then
        assertThat(partOfSectionClassName).isFalse();
    }

}