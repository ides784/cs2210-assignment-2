public class nk_TicTacToe2 {

    /*
     * char gameboard
     *
     * The first [position] = rows The second [position] = columns
     */
    private char[][] gameBoard;
    // Necessary to transfer the size of the board throughout methods
    private int size;
    // Necessary to transfer the win condition throughout the methods
    private int to_win;

    /**
     * Constructor TicTacToe
     *
     * Creates the board with a set size, sets the rules of the winning
     * condition and the depth the computer's AI will go into searching the tree
     * of possibilities.
     *
     * @param size
     *            size * size = number of rows per column. Always a square board
     *
     * @param to_win
     *            integer that determines how many symbols after each other in a
     *            row, column, or diagonally are needed to declare a winner.
     *
     * @param depth
     *            the AI's level depth of it's dictionary game tree of moves,
     *            sort of like setting computer difficulty level. The larger the
     *            number, the more the program will take to compile.
     * */
    public nk_TicTacToe2(int size, int to_win, int depth) {
        this.size = size;
        this.to_win = to_win;
        gameBoard = new char [size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++)
                gameBoard[row][column] = ' ';
        }
    }


    public Dictionary createDictionary() {
        Dictionary configurations = new Dictionary(2437);
        return configurations;
    }


    public int repeatedConfig(Dictionary configurations) {
        return configurations.get(gameBoard_asString());
    }


    public void insertConfig(Dictionary configurations, int score) {
        // The entry going into the dictionary
        Record entry = new Record(gameBoard_asString(), score);
        try {
            configurations.insert(entry);
        } catch (DictionaryException e) {
            e.getMessage();
        }
    }

    /**
     * Method storePlay
     *
     * Store the symbol 'X' or 'O' in the specified space
     * */
    public void storePlay(int row, int column, char symbol) {
        gameBoard[row][column] = symbol;
    }

    /**
     * Method squareIsEmpty
     *
     * @return true if gameBoard[row][column] is ' ', false if otherwise
     * */
    public boolean squareIsEmpty(int row, int column) {
        if (gameBoard[row][column] == ' ')
            return true;
        else
            return false;
    }

    /**
     * Method wins
     *
     * @param symbol
     *            The symbol being checked for the win ('X' if human, 'O' if
     *            computer)
     *
     * @return true if there are k adjacent occurrences of symbol in the same
     *         row, column, or diagonal of gameBoard, where ke is the number of
     *         required symbols in-line needed to win the game.
     * @return false if otherwise.
     * */
    public boolean wins(char symbol) {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                // only check squares that are have the entered symbol
                if (gameBoard [row][column] == symbol) {
                    if (checkColumn(column, row, symbol))
                        return true;
                    if (checkRow(column, row, symbol))
                        return true;
                    if (checkDiagonalRight(row,symbol))
                        return true;
                    if (checkDiagonalLeft(column,symbol))
                        return true;
                }// end if gameBoard (row, column) has symbol
            }// end for column to size
        }// end for row to size
        return false;
    }

    private boolean checkDiagonalLeft(int cr, char symbol)
    {
        int count = 0;
        // Check diagonal left
        //if ((size - row) >= to_win && (size-column)>= to_win) {
        for (int i = size-1; i >= cr; i--) {
            if (gameBoard[i][i] == symbol)
                count++;
            else
                break;
        }//end for column increase
        //}//end check diagonal left
        return (count>= to_win);
    }

    private boolean checkDiagonalRight(int cr, char symbol)
    {
        int count = 0;
        // Check diagonal right
        //if ((size - row) >= to_win && (size-column)>= to_win) {
        for (int i = cr/*row or column*/; i < size; i++) {
            if (gameBoard[i][i] == symbol)
                count++;
            else break;
        }// end for row and column increase
        //}// end check diagonal right
        return (count>= to_win);
    }

    private boolean checkColumn(int column, int row, char symbol)
    {// Check columns first
        int count=0;
        //if ((size - column) >= to_win) {
        for (int i = column; i < size; i++) {
            if (gameBoard[row][i] == symbol)
                count ++;
            else
                break;
        }// end for column check
        //}//end if (size - column) is greater than win condition
        return (count >= to_win);
    }

    private boolean checkRow(int column, int row, char symbol)
    {// Check columns first
        int count=0;
        // Check rows second
        //if ((size - row) >= to_win) {
        for (int i = row; i < size; i++) {
            if (gameBoard[i][column] == symbol)
                count++;
            else break;
        }// end for row check
        //}//end if (size - row) is greater than win condition
        return (count>= to_win);
    }

    public int evalBoard() {
        if (wins('X'))
            return 0;
        else if (wins('O'))
            return 3;
        else if (isDraw())
            return 2;
        else
            return 1; // Undecided since the board isn't full
    }

    /**
     * Method isDrawy
     *
     * @return true if there are no empty positions and no player has won the
     *         game
     * @return false otherwise
     * */
    public boolean isDraw() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                // only check squares that are not empty
                if (squareIsEmpty(row, column)) {
                    return false; // no draw until the board is full
                }
            }
        }
        return true;
    }

    /**
     * Method gameBoard_asString
     *
     * @return representation of the gameBoard as a String
     * */
    private String gameBoard_asString() {
        // String representation the gameBoard
        String gameBoardRepresentation = "";
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                gameBoardRepresentation += gameBoard[row][column];
            }
        }
        return gameBoardRepresentation;
    }
}










