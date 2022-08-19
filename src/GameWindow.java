import javax.swing.*;

import static javax.swing.JOptionPane.*;

public class GameWindow extends JFrame {
    public GameWindow(){
        int boardSize = showDialog();
        setTitle("TicTacToe");
        setSize(800,800);
        setContentPane(new GameView(this,boardSize));
        setResizable(false);
        setVisible(true);

    }

    private int showDialog(){
        String boardSize = showInputDialog(null,"Enter board size","GridMaker",INFORMATION_MESSAGE);
        int board = 0;
        boolean flag = true;
        while (flag){
            try{
                board = Integer.valueOf(boardSize);
                flag = false;

            }catch (NumberFormatException e){
                boardSize = showInputDialog(null,"Enter only number eg. 3 ","GridMaker",WARNING_MESSAGE);
            }
        }
        return board;
    }

}
