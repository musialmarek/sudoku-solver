package it.musialmarek.sudokusolver.GridUtil;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SectionClassNameSplitterTest {

    @ParameterizedTest(name = "should return {3} when size {0} row {1} col {2}")
    @CsvFileSource(resources = "/data-for-splitter.csv", numLinesToSkip = 1)
    public void shouldReturnMarkerOfSectionToDistinguishAdjoinSections(int size, int row, int col, boolean markerOfSection) {
        //When
        boolean tailOfSectionClassName = SectionClassNameSplitter.assignCellToSection(row, col, size);
        //Then
        assertThat(tailOfSectionClassName).isEqualTo(markerOfSection);
    }
}