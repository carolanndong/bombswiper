import java.util.*;

/**
 * Board is a class to make a board of a certain size for the BombSwiper game.
 * This class utilizes a 2D array to represent the tiles on the board.
 *
 * @author Neel Kolhe, Michelle Zhou, Carolann Dong
 * @version May 17, 2022
 * @author Period 5
 * @author Assignment - AP CS Final Project - BombSwiper
 * @author Sources - None
 */
public class Board
{
    private int      numFlags;
    private int      rows;
    private int      cols;
    private int      flagsCount;
    private Tile[][] board;

    /**
     * This constructor constructs a new 12 by 18 (medium difficulty) Board,
     * with 40 flags
     */
    public Board()
    {
        numFlags = 40;
        rows = 12;
        cols = 18;
        flagsCount = numFlags;
        board = new Tile[rows][cols];
    }


    /**
     * This constructor constructs a new Board with a given difficulty level.
     *
     * @param level
     *            A difficulty level to use; easy, medium, or hard.
     */
    public Board(String level)
    {
        if (level.equalsIgnoreCase("easy"))
        {
            numFlags = 10;
            rows = 8;
            cols = 10;
        }
        if (level.equalsIgnoreCase("medium"))
        {
            numFlags = 40;
            rows = 12;
            cols = 18;
        }
        if (level.equalsIgnoreCase("hard"))
        {
            numFlags = 99;
            rows = 20;
            cols = 24;
        }
        flagsCount = numFlags;
        board = new Tile[rows][cols];
    }


    /**
     * This method initializes the Board, creating a new Tile in each required
     * spot.
     */
    public void initializeBoard()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                board[i][j] = new Tile();
            }
        }
    }


    /**
     * This method sets up board with random mines with a given start point from
     * which it leaves a 3 by 3 square empty.
     *
     * @param row
     *            Row for which tile was clicked.
     * @param col
     *            Column for which tile was clicked.
     */
    public void setUpBoard(int row, int col)
    {
        // flags
        initializeBoard();
        for (int f = 0; f < numFlags; f++)
        {
            int r = (int)(Math.random() * rows);
            int c = (int)(Math.random() * cols);
            while (board[r][c].getValue() == 9 || Math.abs(r - row) < 2 && Math.abs(c - col) < 2)
            {
                r = (int)(Math.random() * (rows));
                c = (int)(Math.random() * (cols));
            }
            board[r][c].setValue(9);
        }
        // non flags
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                if (board[r][c].getValue() == 9)
                {
                    increment(r, c);
                }
            }
        }
    }


    /**
     * This method adds 1 to all neighboring tiles of a mine.
     *
     * @param row
     *            Row of mine
     * @param col
     *            Column of mine
     */

    public void increment(int row, int col)
    {
        for (int r = row - 1; r <= row + 1; r++)
        {
            for (int c = col - 1; c <= col + 1; c++)
            {
                if (r >= 0 && c >= 0 && r < rows && c < cols && board[r][c].getValue() != 9)
                {
                    board[r][c].incrementValue();
                }
            }
        }
    }


    /**
     * Method to determine whether game is over or not.
     *
     * @return Boolean, whether or not game is over.
     */
    public boolean wonGame()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (!(board[i][j].getIsTriggered() || board[i][j].getIsFlagged()))
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Once a blank tile is clicked, this method will trigger all the adjacent
     * squares until it hits a mine at which point it stops before triggering
     * the mine.
     *
     * @param row
     *            Row of blank tile.
     * @param col
     *            Column of blank tile.
     */
    public void triggerAdjacentBlanks(int row, int col)
    {
        board[row][col].trigger();
        if (board[row][col].getValue() != 0)
        {
            return;
        }
        int dx[] = { 1, 0, -1, 0, 1, 1, -1, -1 };
        int dy[] = { 0, 1, 0, -1, 1, -1, 1, -1 };
        for (int i = 0; i < dx.length; i++)
        {
            int currX = row + dx[i];
            int currY = col + dy[i];
            if (currX < 0 || currX >= rows || currY < 0 || currY >= cols)
            {
                continue;
            }
            if (board[currX][currY].getIsTriggered())
            {
                continue;
            }
            triggerAdjacentBlanks(currX, currY);
        }
    }


    /**
     * A method to trigger all the mines on the board.
     */
    public void triggerAllMines()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (board[i][j].getValue() == 9)
                {
                    board[i][j].trigger();
                }
            }
        }
    }


    /**
     * A method to print the board.
     */
    public void printBoard()
    {
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                System.out.print(board[r][c].getValue() + " ");
            }
            System.out.println();
        }
    }


    /**
     * A method to decrease the flag count.
     *
     * @param dec
     *            negative number to decrease flag count by.
     */
    public void incrementFlagsCount(int dec)
    {
        flagsCount += dec;
    }


    /**
     * Sets number of flags to given number.
     *
     * @param flags
     *            Number of flags to put flags to.
     */
    public void setNumFlags(int flags)
    {
        numFlags = flags;
    }


    /**
     * Returns number of rows in a board.
     *
     * @return An integer, the number of rows.
     */
    public int getRows()
    {
        return rows;
    }


    /**
     * Gets number of columns in a board.
     *
     * @return an integer, the number of columns.
     */
    public int getCols()
    {
        return cols;
    }


    /**
     * Gets number of flags remaining.
     *
     * @return an integer, the number of flags remaining.
     */
    public int getNumFlags()
    {
        return numFlags;
    }


    /**
     * Gets number of flags that have been used.
     *
     * @return an integer, the number of flags that have been used.
     */
    public int getFlagsCount()
    {
        return flagsCount;
    }


    /**
     * returns the current board.
     *
     * @return A 2d array containing the tile information on the board.
     */
    public Tile[][] getBoard()
    {
        return board;
    }
}
