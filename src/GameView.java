import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JPanel {

    private static int BOARD_SIZE;
    private JButton[][] buttons;
    private final JFrame frame;
    private boolean Xturn;
    public GameView(JFrame frame, int boardSize){
        BOARD_SIZE = boardSize;
        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        Xturn = true;
        this.frame = frame;
        setLayout(new GridLayout(BOARD_SIZE,BOARD_SIZE,1,1));
        createBlocks();
    }

    private void createBlocks() {
        Font font = new Font("MV Boli",1,150);
        for (int row = 0; row < BOARD_SIZE; row++){
            for (int col = 0; col < BOARD_SIZE; col++){
                JButton button = new JButton("");
                buttons[row][col] = button;
                button.setFont(font);
                button.setBackground(Color.white);
                addButtonListner((ActionEvent e)->{
                    move(button);
                    gameOver();
                },button);
                add(button);
            }
        }
    }
    private void move(JButton clickedBtn){

        String btnText = clickedBtn.getText();
        if(btnText.length() > 0){
            JOptionPane.showMessageDialog(this,"Invalid Move","Warning!",JOptionPane.WARNING_MESSAGE);
        }else{
            if (Xturn){
                clickedBtn.setText("X");
                Xturn = false;
            }else{
                clickedBtn.setText("O");
                Xturn = true;
            }
        }
    }
    private void gameOver() {
        GameStatus gameStatus = getGameStatus();
        if (gameStatus == GameStatus.Incomplete)
            return;
        winner(gameStatus);

        int choice = JOptionPane.showConfirmDialog(this,"Do you want to play again ?","Restart",JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            for (int row1 = 0; row1 < BOARD_SIZE; row1++){
                for (int col1 = 0; col1 < BOARD_SIZE; col1++){
                    buttons[row1][col1].setText("");
                }
            }
            Xturn = true;
        }else{
            frame.dispose();
        }
    }
    private GameStatus getGameStatus(){
        String status1 = "";
        String status2 = "";
        int row = 0;
        int col = 0;

        //rows
        row = 0;
        while (row < BOARD_SIZE){
            col = 0;
            while (col < BOARD_SIZE -1){
                status1 = buttons[row][col].getText();
                status2 = buttons[row][col+1].getText();
                if(!status1.equals(status2) || status1.length() == 0){
                    break;
                }
                col++;
            }
            if(col == BOARD_SIZE - 1){
                if (status1.equals("X"))
                    return GameStatus.Xwins;
                else
                    return GameStatus.Owins;
            }
            row++;
        }

        //cols
        col = 0;
        while (col < BOARD_SIZE){
            row = 0;
            while (row < BOARD_SIZE -1){
                status1 = buttons[row][col].getText();
                status2 = buttons[row+1][col].getText();
                if(!status1.equals(status2) || status1.length() == 0){
                    break;
                }
                row++;
            }
            if(row == BOARD_SIZE - 1){
                if (status1.equals("X"))
                    return GameStatus.Xwins;
                else
                    return GameStatus.Owins;
            }
            col++;
        }

        //Top-left to Bottom-right diagonal viceversa
        row = 0;
        col = 0;
        while (row < BOARD_SIZE - 1){
            status1 = buttons[row][col].getText();
            status2 = buttons[row+1][col+1].getText();
            if(!status1.equals(status2) || status1.length() == 0){
                break;
            }
            row++;
            col++;
        }

        if(row == BOARD_SIZE - 1){
            if(status1.equals("X"))
                return GameStatus.Xwins;
            else
                return GameStatus.Owins;
        }

        //Bottom-left to Top-right Diagonal & viceversa

        row = BOARD_SIZE - 1;
        col = 0;
        while (row > 0){
            status1 = buttons[row][col].getText();
            status2 = buttons[row-1][col+1].getText();
            if(!status1.equals(status2) || status1.length() == 0){
                break;
            }
            row--;
            col++;
        }

        if(row == 0){
            if(status1.equals("X"))
                return GameStatus.Xwins;
            else
                return GameStatus.Owins;
        }

        String status = "";
        for (row = 0; row < BOARD_SIZE; row++){
            for (col = 0; col < BOARD_SIZE; col++){
                status = buttons[row][col].getText();
                if (status.length() == 0)
                    return GameStatus.Incomplete;
            }
        }
        return GameStatus.Tie;
    }
    private void winner(GameStatus gameStatus){
        if(gameStatus == GameStatus.Xwins)
            JOptionPane.showMessageDialog(this,"X Wins");
        else if (gameStatus == GameStatus.Owins)
            JOptionPane.showMessageDialog(this,"O Wins");
        else
            JOptionPane.showMessageDialog(this,"Tie");
    }
    public void addButtonListner(ActionListener listener,JButton button){
        button.addActionListener(listener);
    }

}
