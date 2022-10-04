import java.util.*;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI3
    extends JFrame
    implements ActionListener
{
    private Board                    board;
    private int                      rows, cols;
    private JFrame                   mainFrame, levelsFrame;
    private JLabel                   flags;
    private JButton                  newGame, flagMode;
    private JButton                  easy, medium, hard;
    private HashMap<Coords, JButton> tiles;
    private int                      dimensions;
    private boolean                  isFirstTriggered;
    private boolean                  isFlagMode;
    private ImageIcon                newGameIcon;
    private ImageIcon                easyIcon, mediumIcon, hardIcon;
    // private ImageIcon flagModeOffIcon, flagModeOnIcon;
    private ImageIcon                grassIcon, flagIcon, mineIcon, dirtIcon, oneIcon, twoIcon,
        threeIcon, fourIcon, fiveIcon, sixIcon, sevenIcon, eightIcon;

    /**
     * Calls initGame to initialize BombSwiper game.
     */
    public GUI3()
    {
        initGame();
    }


    /**
     * Initializes BombSwiper game by initializing a new board represented by
     * Board class, main frame, levels frame, icons, new game button, flags
     * counter, flag mode button, and levels pop up. isFirstTriggered and
     * isFlagMode are set to false;
     */
    public void initGame()
    {
        board = new Board();

        mainFrame = new JFrame();
        levelsFrame = new JFrame();

        initIcons();
        initBoard();
        newGameButton();
        flagsCounter();
        flagModeButton();

        mainFrame.setSize(460, 460);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        levelsPopUp();

        isFirstTriggered = false;
        isFlagMode = false;

    }


    /**
     * Initializes the icons in BombSwiper.
     */
    public void initIcons()
    {
        // newGameIcon = new ImageIcon("newgame.png");
        easyIcon = new ImageIcon("easy.png");
        mediumIcon = new ImageIcon("medium.png");
        hardIcon = new ImageIcon("hard.png");
        // flagModeOffIcon = new ImageIcon("flagmodeoff.png");
        // flagModeOnIcon = new ImageIcon("flagmodeon.png");
        grassIcon = new ImageIcon("grass.png");
        flagIcon = new ImageIcon("flag.png");
        mineIcon = new ImageIcon("mine.png");
        dirtIcon = new ImageIcon("dirt.png");
        oneIcon = new ImageIcon("one.png");
        twoIcon = new ImageIcon("two.png");
        threeIcon = new ImageIcon("three.png");
        fourIcon = new ImageIcon("four.png");
        fiveIcon = new ImageIcon("five.png");
        sixIcon = new ImageIcon("six.png");
        sevenIcon = new ImageIcon("seven.png");
        eightIcon = new ImageIcon("eight.png");
    }


    /**
     * Updates the given icon dimensions with the given width and height. If
     * width is -1, the height is scaled propotionately with the same aspect
     * ratio. If height is -1, the width is scaled propotionately with the same
     * aspect ratio.
     *
     * @param icon
     *            Icon to resize.
     * @param width
     *            Given width to resize to.
     * @param height
     *            Given height to rezise to.
     */
    public void updateIconDimensions(ImageIcon icon, int width, int height)
    {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        if (width == -1)
        {
            w = w * height / h;
        }
        else if (height == -1)
        {
            h = h * width / w;
        }
        else
        {
            w = width;
            h = height;
        }

        Image img = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        icon.setImage(img);
    }


    /**
     * Updates all of the tile icon dimensions. Tiles include grassIcon,
     * flagIcon, mineIcon, dirtIcon, and one-eightIcon.
     */
    public void updateTileIconDimensions()
    {
        ImageIcon[] icons = { grassIcon, flagIcon, mineIcon, dirtIcon, oneIcon, twoIcon, threeIcon,
            fourIcon, fiveIcon, sixIcon, sevenIcon, eightIcon };

        for (ImageIcon icon : icons)
        {
            updateIconDimensions(icon, dimensions, dimensions);
        }
    }


    /**
     * Creates and displays the flag counter label.
     */
    public void flagsCounter()
    {
        flags = new JLabel();
        flags.setBounds(200, 25, 60, 40);
        flags.setText("Flags: 40");
        mainFrame.add(flags);
    }


    /**
     * Creates and displays the new game button. Once clicked, the levels pop up
     * is displayed.
     */
    public void newGameButton()
    {
        newGame = new JButton("New game");
        // newGame = new JButton();
        newGame.setBounds(25, 25, 120, 40);
        // updateIconDimensions(newGameIcon, 120, 40);
        // newGame.setIcon(newGameIcon);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                levelsPopUp();
            }
        });

        mainFrame.add(newGame);
    }


    /**
     * Creates and displays the flag mode button. Once clicked, the flag mode
     * button icon changes to on if off, and off if on.
     */
    public void flagModeButton()
    {
        flagMode = new JButton("Flag mode: Off");
        // flagMode = new JButton();
        flagMode.setBounds(315, 25, 120, 40);
        // updateIconDimensions(flagModeOffIcon, 120, 40);
        // updateIconDimensions(flagModeOnIcon, 120, 40);
        // flagMode.setIcon(flagModeOffIcon);
        flagMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (isFlagMode)
                {
                    flagMode.setText("Flag Mode: Off");
                    // flagMode.setIcon(flagModeOffIcon);
                }
                else
                {
                    flagMode.setText("Flag Mode: On");
                    // flagMode.setIcon(flagModeOnIcon);
                }
                isFlagMode = !isFlagMode;
            }
        });

        mainFrame.add(flagMode);
    }


    /**
     * Initializes a new board with the given level.
     *
     * @param level
     *            Level to initialize board with.
     */
    public void initNewLevel(String level)
    {
        board = new Board(level);
        clearBoard();
        initBoard();
        flags.setText("Flags: " + board.getNumFlags());
        flagMode.setText("Flag Mode: Off");
        // flagMode.setIcon(flagModeOffIcon);
        isFirstTriggered = false;
        isFlagMode = false;
        levelsFrame.setVisible(false);
    }


    /**
     * Creates and displays the levels pop up frame. Contains buttons easy,
     * medium, and hard. Once cliked, a new board is initialized with the given
     * level.
     */
    public void levelsPopUp()
    {
        // levels
        // easy = new JButton("Easy");
        easy = new JButton();
        easy.setBounds(25, 60, 100, 40);
        updateIconDimensions(easyIcon, -1, 40);
        easy.setIcon(easyIcon);
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                initNewLevel("easy");
            }
        });

        // medium = new JButton("Medium");
        medium = new JButton();
        medium.setBounds(130, 60, 140, 40);
        updateIconDimensions(mediumIcon, -1, 40);
        medium.setIcon(mediumIcon);
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                initNewLevel("medium");
            }
        });

        // hard = new JButton("Hard");
        hard = new JButton();
        hard.setBounds(275, 60, 100, 40);
        updateIconDimensions(hardIcon, -1, 40);
        hard.setIcon(hardIcon);
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                initNewLevel("hard");
            }
        });

        levelsFrame.add(easy);
        levelsFrame.add(medium);
        levelsFrame.add(hard);

        levelsFrame.setSize(400, 200);
        levelsFrame.setLayout(null);

        if (levelsFrame.isVisible())
        {
            levelsFrame.toFront();
            levelsFrame.repaint();
        }
        else
        {
            levelsFrame.setVisible(true);
        }
    }


    /**
     * Initializes the collection of tiles represented by JButton class. The
     * dimensions depend on the number of rows and columns on the board which
     * varies with level. The tile icon dimensions ae updated and displayed.
     */
    public void initBoard()
    {
        rows = board.getRows();
        cols = board.getCols();

        tiles = new HashMap<>();

        dimensions = 360 / cols;
        updateTileIconDimensions();

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                JButton b = new JButton();
                tiles.put(new Coords(r, c), b);
                b.addActionListener(this);
                // b.setText("");
                b.setIcon(grassIcon);
                b.setBounds(50 + c * dimensions, 100 + r * dimensions, dimensions, dimensions);
                mainFrame.add(b);
            }
        }

        mainFrame.repaint();
    }


    /**
     * Clears all of the tiles on the board and the collection of tiles
     */
    public void clearBoard()
    {
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                mainFrame.remove(tiles.get(new Coords(r, c)));
                tiles.remove(new Coords(r, c));
            }
        }
    }


    /**
     * Performs (an) action(s) depending on the tile. If the first tile is not
     * yet triggered, the board is initialized with values depending on the tile
     * clicked. If a tile is flagged, unflags the tile and updates flag counter
     * text. If the tile is in flag mode, a flag tile is displayed if the tile
     * is not already triggered. If none of the above apply, the tile value is
     * triggered and displayed.
     */
    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();
        Coords coords = getKey(tiles, b);
        int r = coords.getRow();
        int c = coords.getCol();

        if (!isFirstTriggered)
        {
            board.setUpBoard(r, c);
            // board.printBoard();
            board.getBoard()[r][c].trigger();
            board.triggerAdjacentBlanks(r, c);
            updateMultipleTriggered();
            isFirstTriggered = true;
        }

        int value = board.getBoard()[r][c].getValue();

        if (board.getBoard()[r][c].getIsFlagged())
        {
            board.getBoard()[r][c].unflag();
            b.setIcon(grassIcon);
            board.incrementFlagsCount(1);
            flags.setText("Flags: " + board.getFlagsCount());
            return;
        }

        if (isFlagMode)
        {
            if (b.getIcon() == grassIcon)
            {
                board.getBoard()[r][c].flag();
                b.setIcon(flagIcon);
                board.incrementFlagsCount(-1);
                flags.setText("Flags: " + board.getFlagsCount());
            }
        }
        else
        {
            if (value == 0)
            {
                board.getBoard()[r][c].trigger();
                board.triggerAdjacentBlanks(r, c);
                updateMultipleTriggered();
            }
            else if (value >= 1 && value <= 8)
            {
                board.getBoard()[r][c].trigger();
                updateSingleTriggered(b, value);
            }
            else if (value == 9)
            {
                board.triggerAllMines();
                updateMultipleTriggered();
                loseGame();
            }
        }

        if (board.wonGame())
        {
            winGame();
        }

        mainFrame.repaint();
    }


    /**
     * Gets the key for the given value.
     *
     * @param <K>
     *            Coordniate key represented by Coords class.
     * @param <V>
     *            Tile value represented by JButton class.
     * @param map
     *            Collection of tiles.
     * @param value
     *            Tile value represented by JButton class.
     * @return The key for the given value
     */
    public <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Entry<K, V> entry : map.entrySet())
        {
            if (entry.getValue().equals(value))
            {
                return entry.getKey();
            }
        }
        return null;
    }


    /**
     * Updates a single triggered tile depending on the given tile value
     *
     * @param b
     *            Tile represented by JButton class to update.
     * @param value
     *            Tile value represented by Integer.
     */
    public void updateSingleTriggered(JButton b, int value)
    {
        if (value == 0)
        {
            b.setIcon(dirtIcon);
        }
        else if (value == 1)
        {
            b.setIcon(oneIcon);
        }
        else if (value == 2)
        {
            b.setIcon(twoIcon);
        }
        else if (value == 3)
        {
            b.setIcon(threeIcon);
        }
        else if (value == 4)
        {
            b.setIcon(fourIcon);
        }
        else if (value == 5)
        {
            b.setIcon(fiveIcon);
        }
        else if (value == 6)
        {
            b.setIcon(sixIcon);
        }
        else if (value == 7)
        {
            b.setIcon(sevenIcon);
        }
        else if (value == 8)
        {
            b.setIcon(eightIcon);
        }
        else if (value == 9)
        {
            b.setIcon(mineIcon);
        }
    }


    /**
     * Updates multiple triggered tiles.
     */
    public void updateMultipleTriggered()
    {
        for (Coords coord : tiles.keySet())
        {
            int r = coord.getRow();
            int c = coord.getCol();
            if (board.getBoard()[r][c].getIsTriggered())
            {
                JButton b = tiles.get(coord);
                int value = board.getBoard()[r][c].getValue();

                updateSingleTriggered(b, value);
            }
        }
    }


    /**
     * Wins BombSwiper by displaying a new window saying "You Won".
     */
    private void winGame()
    {
        JFrame won = new JFrame();

        JLabel youWon = new JLabel();
        youWon.setText("You won!!! :)");
        youWon.setBounds(60, 20, 80, 40);

        won.add(youWon);

        won.setSize(200, 100);
        won.setLayout(null);
        won.setVisible(true);
    }


    /**
     * Loses BombSwiper by displaying a new window saying "You Lost".
     */
    private void loseGame()
    {

        JFrame lost = new JFrame();

        JLabel youLost = new JLabel();
        youLost.setText("You lost!!! :(");
        youLost.setBounds(60, 20, 80, 40);

        lost.add(youLost);

        lost.setSize(200, 100);
        lost.setLayout(null);
        lost.setVisible(true);
    }


    /**
     * Main class.
     *
     * @param args
     *            Arguments.
     */
    public static void main(String[] args)
    {
        new GUI3();
    }
}
