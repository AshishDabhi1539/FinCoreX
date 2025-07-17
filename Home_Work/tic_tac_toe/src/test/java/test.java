import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tss.model.Board;

public class test {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board(); // Initialize before each test
    }

    @Test
    public void testRowWin() {
        board.placeMark(0, 0, 'X');
        board.placeMark(0, 1, 'X');
        board.placeMark(0, 2, 'X');
        assertTrue(board.checkWin('X'), "Row win not detected");
    }

    @Test
    public void testColumnWin() {
        board.placeMark(0, 1, 'O');
        board.placeMark(1, 1, 'O');
        board.placeMark(2, 1, 'O');
        assertTrue(board.checkWin('O'), "Column win not detected");
    }

    @Test
    public void testDiagonalWin() {
        board.placeMark(0, 0, 'X');
        board.placeMark(1, 1, 'X');
        board.placeMark(2, 2, 'X');
        assertTrue(board.checkWin('X'), "Diagonal win not detected");
    }

    @Test
    public void testAntiDiagonalWin() {
        board.placeMark(0, 2, 'O');
        board.placeMark(1, 1, 'O');
        board.placeMark(2, 0, 'O');
        assertTrue(board.checkWin('O'), "Anti-diagonal win not detected");
    }

    @Test
    public void testNoWin() {
        board.placeMark(0, 0, 'X');
        board.placeMark(0, 1, 'O');
        board.placeMark(0, 2, 'X');
        assertFalse(board.checkWin('X'), "False positive win detected");
    }
}
