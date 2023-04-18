package Group_Project_Chess_S2023.User_Layer;
import javax.swing.*;
import java.awt.*;
import Group_Project_Chess_S2023.Business_Layer.*;
public class GameUI extends JFrame {
  private JPanel[][] squares;
  private JPanel chessBoard;
  private Game game;
  public GameUI(Game game) {
    this.game = game;
    this.setTitle("Chess2");
    this.setSize(300, 300);
    this.setLayout(new FlowLayout());
    JLabel label = new JLabel("Chess");
    this.add(label);
    chessBoard = new JPanel();
    this.add(chessBoard);
    chessBoard.setLayout(new GridLayout(8, 8));
    chessBoard.setSize(200, 200);
    chessBoard.setBackground(Color.blue);
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        JPanel cell = new JPanel();
        cell.setSize(10, 10);
        if (j%2 == 0 && i%2 != 0) {
          cell.setBackground(Color.green);
        } else if (j%2 != 0 && i%2 ==0) {
          cell.setBackground(Color.green);
        } else {
          cell.setBackground(Color.white);
        }
        chessBoard.add(cell);
      }
    }
    this.setVisible(true);
  }
}