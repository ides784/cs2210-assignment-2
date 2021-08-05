/**
 * @author Derek Liu
 *         A class that inplements the Tic Tac Toe Game using a dictionary
 */

public class nk_TicTacToe {
//initial gameboard with row and column input
    private final char[][] gameBoard;
    //size of the board
    private int board_size = 0;
    private int inline = 0;

    //constructor
    public nk_TicTacToe (int board_size, int inline, int max_levels){
        this.board_size =board_size;
        //create new board of board_size size
        gameBoard =new char [board_size][board_size];
        this.inline=inline;
        for (int i =0; i<board_size;i++){
            //empty the board
            for (int j =0; j <board_size;j++) {
                gameBoard[i][j] = ' ';
            }
        }
        //not given much instruction as to what to do with max_levels
    }

    /**
     *  create empty dictionary of size 6101, which is a prime
     */
    public Dictionary createDictionary(){
        return new Dictionary(6101);

    }

    /**
     * @param configurations the library to check
     * first represents the content of gameBoard as a string, then checks whether the string
     * representing the gameBoard is in the configurations dictionary, and returns it if so,
     * else return -1
     */
    public int repeatedConfig(Dictionary configurations){
        StringBuilder string = new StringBuilder();
        for (int i = 0;i < board_size; i++){
            for (int j = 0;j < board_size; j++){
                string.append(gameBoard[i][j]);
            }
        }
        return (configurations.get(string.toString())); //get returns -1 if not found

    }

    /**
     * @param configurations the library to insert into
     * first represents the content of gameBoard as a string, then inserts this string
     * and score in the dictionary
     */
    public void insertConfig(Dictionary configurations, int score){
        StringBuilder string = new StringBuilder();
        for (int i = 0; i <board_size; i++){
            for (int j = 0;j < board_size;j++){
                string.append(gameBoard[i][j]);
            }
        }
        Record insert = new Record(string.toString(), score);
        configurations.insert(insert);
    }

    /**
     * @param row the row to store in
     * @param col the column to store in
     * @param symbol the symbol to store
     * method that stores symbol into gameboard
     */
    public void storePlay(int row,int col,char symbol){
        gameBoard[row][col] = symbol;
    }

    /**
     * @param row the row to check
     * @param col the column to check
     * method that checks if location in gameboard is empty
     */
    public boolean squareIsEmpty(int row, int col){
        return (gameBoard[row][col]==' ');
    }

    /**
     * @param symbol the symbol to look for
     * method that returns if a win position is true; if there are inline number of symbols
     * in the vertical, horizontal, or diagonal line.
     */

    public boolean wins (char symbol){
        for (int i=0;i <board_size;i++) {
            for (int j =0; j <board_size;j++) {
                if (gameBoard[i][j] == symbol) {
                    //checks if a win is horizontal
                    int horizontalcount = 0;
                    for (int k =i; k <board_size;k++) {
                        if (gameBoard[k][j] ==symbol) {
                            horizontalcount++;
                        } else {
                            break;
                        }
                    }
                    if (horizontalcount >=inline)
                        return true;

                    //checks if a win is vertical
                    int verticalcount =0;
                    for (int k = j; k <board_size; k++) {
                        if (gameBoard[i][k] == symbol) {
                            verticalcount++;
                        } else {
                            break;
                        }
                    }
                    if (verticalcount >=inline)
                        return true;
                }

                //checks if a win is diagonal, both left and right
                //left diagnonal
                if (gameBoard[i][j]==symbol) {
                    int tempi=i;
                    int tempj=j;
                    //create temporary variables that can be modified without affecting i or j
                    int leftcounter=0;
                    while ((tempj>=0) && (tempi <=board_size-1)) {
                        if (gameBoard[tempi][tempj]==symbol) {
                            leftcounter++;
                        }
                        else leftcounter =0;//reset
                        if (leftcounter >=inline) return true;
                        else {
                            tempj--;
                            tempi++;
                        }
                    }
                }

                //right diagonal
                if (gameBoard[i][j] ==symbol) {
                    int tempj =j;
                    int tempi= i;
                    //create temporary varibles that can be modified without affecting i or j
                    int rightcounter =0;
                    while ((tempj <=board_size-1) && (tempi <=board_size-1)){
                        if (gameBoard[tempi][tempj] == symbol){
                            rightcounter++;
                        }
                        else rightcounter = 0;//reset

                        if (rightcounter >= inline) return true;
                        else{
                            tempj++;
                            tempi++;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * a method that returns true if the game ends in draw (no empty positions left and no winner)
     */
    public boolean isDraw(){
        for (int i =0; i <board_size;i++){
            for (int j =0; j <board_size; j++){
                if (squareIsEmpty(i, j)){
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * returns a score based on situation
     */
    public int evalBoard(){
        if (wins('O')) //computer win
            return 3;
        else if (wins('X')) //human win
            return 0;
        else if (isDraw()) //draw
            return 2;
        else
            return 1; //undecided
    }

}

