//java Group_Project_Chess_S2023/Business_Layer/Game.java
package Group_Project_Chess_S2023.User_Layer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;
import Group_Project_Chess_S2023.Business_Layer.*;
public class GameUI extends JFrame implements MouseListener, MouseMotionListener{
  private JPanel[][] squares = new JPanel[8][8];
  private JPanel chessBoard;
  private JLayeredPane mainPanel;
  private Game game;
  private int xadjust, yadjust;
  private JLabel statusBox;
  private JLabel draggedPiece;
  private Container draggedOldParent;
  private Map<String, JLabel> images = new HashMap<String, JLabel>();
  
  public GameUI() {
    game = new Game();
    this.setTitle("Chess");
    this.setSize(300, 300);
    this.mainPanel = new JLayeredPane();
    //mainPanel.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    JLabel label = new JLabel("Chess");
    label.setBounds(120, 0, 300, 25);
    mainPanel.add(label);
    chessBoard = new JPanel();
    mainPanel.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
    chessBoard.setLayout(new GridLayout(8, 8));
    chessBoard.setBounds(50, 40, 200, 200);
    chessBoard.setMaximumSize(new Dimension(200, 200));
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        JPanel cell = new JPanel();
        if (j%2 == 0 && i%2 != 0) {
          cell.setBackground(Color.green);
        } else if (j%2 != 0 && i%2 ==0) {
          cell.setBackground(Color.green);
        } else {
          cell.setBackground(Color.white);
        }
        //apparently we're doing column x row
        squares[j][i] = cell;
        chessBoard.add(cell);
      }
    }
    chessBoard.addMouseListener(this);
    chessBoard.addMouseMotionListener(this);
    statusBox = new JLabel("");
    statusBox.setBounds(50, 250, 300, 25);
    mainPanel.add(statusBox);
    this.setUpImages();
    this.updateBoard();
    if (game.getCurPlayer()) {
      statusBox.setText("White, it's your turn!");
    } else {
      statusBox.setText("Black, it's your turn!");
    }
    this.add(mainPanel);
    this.setVisible(true);
  }
  public void setUpImages() {
    //present images
    images.put("Pawn", new JLabel("P"));
    images.put("Bishop", new JLabel("B"));
    images.put("King", new JLabel("K"));
    images.put("Knight", new JLabel("N"));
    images.put("Queen", new JLabel("Q"));
    images.put("Rook", new JLabel("R"));
  }
  public void updateBoard() {
    Piece[][] pieces = game.getBoard().getBoard();
    for (int i = 0; i < pieces.length; i++) {
      for (int j = 0; j < pieces[i].length; j++) {
        System.out.println(i + " " + j);
        Piece temp = pieces[i][j];
        if (temp != null) {
          char tempName = temp.getSymbol();
          System.out.println(tempName);
          //add the icons
          JLabel tempLabel = new JLabel(String.valueOf(tempName));
          if (temp.getColor()) { //it's white
            tempLabel.setForeground(Color.YELLOW);
          } else {
            tempLabel.setForeground(Color.BLACK);
          }
          squares[i][j].removeAll();
          squares[i][j].add(tempLabel);
        }
      }
    }
  }
  public void mousePressed(MouseEvent e) {
    Component component = chessBoard.findComponentAt(e.getX(), e.getY());
   if (component instanceof JLabel) {
      draggedOldParent = component.getParent();
      xadjust = e.getX() - draggedOldParent.getLocation().x;
     yadjust = e.getY() - draggedOldParent.getLocation().y;
      draggedPiece = (JLabel)component;
     draggedPiece.setSize(draggedPiece.getWidth(), draggedPiece.getHeight());
      draggedPiece.setLocation(e.getX() - xadjust, e.getY() - yadjust);
     mainPanel.add(draggedPiece, JLayeredPane.DRAG_LAYER);
    }
  }
  public void mouseDragged(MouseEvent e) {
    if (draggedPiece != null) {
      draggedPiece.setLocation(e.getX() - xadjust, e.getY() - yadjust);
    }
  }
  public void mouseReleased(MouseEvent e) {
    if (draggedPiece != null) {
      Component component = chessBoard.findComponentAt(e.getX(), e.getY());
      if (component != null) {
        if (component instanceof JLabel) {
        Container parent = component.getParent();
          if (checkMove(parent)) {
            parent.removeAll();
            parent.add(draggedPiece);
            endTurn();
          } else {
            statusBox.setText("You made an invalid move! Try again!");
        draggedOldParent.add(draggedPiece);
          }
        } else {
          Container parent = (Container) component;
          if (checkMove(parent)) {
            parent.add(draggedPiece);
            endTurn();
          } else {
            statusBox.setText("You made an invalid move! Try again!");
        draggedOldParent.add(draggedPiece);
          }
        }
      } else {
        statusBox.setText("Please stay within the board!");
        draggedOldParent.add(draggedPiece);
      }
    }
    this.revalidate();
    this.repaint();
  }
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e){}
  public void mouseMoved(MouseEvent e){}
  private Boolean checkMove(Container c) {
    JPanel temp = (JPanel) c;
    int oldr = 0, oldc = 0, newr = 0, newc = 0;
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares[i].length; j++) {
        if (squares[i][j] == draggedOldParent) {
          oldc = i;
          oldr = j;
        } else if (squares[i][j] == temp) {
          newc = i;
          newr = j;
        }
      }
    }
    System.out.println("moving from row " + oldr + " col " + oldc + " to new row " + newr + " new col " + newc);
    return game.freeMove(oldc, oldr, newc, newr);
  }
  public void endTurn() {
    //if (game.checkmate()) {
      //statusBox.setText("Checkmate! Game over!");
    //} else if (game.stalemate()) {
      //statusBox.setText("Stalemate! Game over!");
    //} else {
      if (game.getCurPlayer()) {
        statusBox.setText("White, it's your turn!");
      } else {
        statusBox.setText("Black, it's your turn!");
      }
    }
 // }
}