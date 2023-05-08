//java Group_Project_Chess_S2023/Business_Layer/Game.java
package Group_Project_Chess_S2023.User_Layer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;
import Group_Project_Chess_S2023.Business_Layer.*;
public class GameUI extends JFrame implements MouseListener, MouseMotionListener, ActionListener{
  private JPanel[][] squares = new JPanel[8][8];
  private JPanel chessBoard;
  private JLayeredPane mainPanel, startPanel;
  private Game game;
  private int xadjust, yadjust;
  private JLabel statusBox;
  private JLabel draggedPiece;
  private Container draggedOldParent;
  private ImageIcon whiteKing, blackKing, whiteKnight, blackKnight, whiteQueen, blackQueen, whitePawn, blackPawn, whiteRook, blackRook, blackBishop, whiteBishop;
  private JButton resetButton, saveButton, playButton;
  
  public GameUI() {
    game = new Game();
    this.setTitle("Chess");
    this.setSize(300, 300);
    
    this.startPanel = new JLayeredPane();
    this.startPanel.setBackground(Color.cyan);
    JLabel startLabel = new JLabel("Loading images...");
    startLabel.setBounds(120, 80, 300, 50);
    startPanel.add(startLabel);
    playButton = new JButton("Play");
    playButton.setBounds(90, 130, 110, 25);
    playButton.addActionListener(this);
    this.add(startPanel);
    this.setVisible(true);
    
    this.mainPanel = new JLayeredPane();
    JLabel label = new JLabel("Chess");
    label.setBounds(120, 0, 300, 25);
    mainPanel.add(label);
    
    chessBoard = new JPanel();
    mainPanel.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
    chessBoard.setLayout(new GridLayout(8, 8));
    chessBoard.setBounds(50, 20, 200, 200);
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
    statusBox.setBounds(50, 220, 300, 25);
    mainPanel.add(statusBox);
    resetButton = new JButton("New Game");
    resetButton.setBounds(20, 240, 110, 25);
    resetButton.addActionListener(this);
    saveButton = new JButton("Save");
    saveButton.setBounds(145, 240, 110, 25);
    saveButton.addActionListener(this);
    mainPanel.add(resetButton);
    mainPanel.add(saveButton);
    this.setUpImages();
    this.updateBoard();
    game.freeMove(0, 0, 0, 0);
    if (game.getCurPlayer()) {
      statusBox.setText("White, it's your turn!");
    } else {
      statusBox.setText("Black, it's your turn!");
    }
    startLabel.setText("Chess");
    startPanel.add(playButton);
  }
  public void setUpImages() {
    blackKing = new ImageIcon(new ImageIcon(GameUI.class.getResource("blackKing.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    whiteKing = new ImageIcon(new ImageIcon(GameUI.class.getResource("whiteKing.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    blackBishop = new ImageIcon(new ImageIcon(GameUI.class.getResource("blackBishop.jpg")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    whiteBishop = new ImageIcon(new ImageIcon(GameUI.class.getResource("whiteBishop.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    blackKnight = new ImageIcon(new ImageIcon(GameUI.class.getResource("blackKnight.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    whiteKnight = new ImageIcon(new ImageIcon(GameUI.class.getResource("whiteKnight.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    blackPawn = new ImageIcon(new ImageIcon(GameUI.class.getResource("blackPawn.jpg")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    whitePawn = new ImageIcon(new ImageIcon(GameUI.class.getResource("whitePawn.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    blackQueen = new ImageIcon(new ImageIcon(GameUI.class.getResource("blackQueen.jpg")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    whiteQueen = new ImageIcon(new ImageIcon(GameUI.class.getResource("whiteQueen.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    blackRook = new ImageIcon(new ImageIcon(GameUI.class.getResource("blackRook.jpg")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    whiteRook = new ImageIcon(new ImageIcon(GameUI.class.getResource("whiteRook.jpg")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
  }
  public ImageIcon getImageIcon(char symbol, Boolean color) {
    if (!color) {
      if (symbol == 'K') {
        return blackKing;
      } else if (symbol == 'N') {
        return blackKnight;
      } else if (symbol == 'Q') {
        return blackQueen;
      } else if (symbol == 'P') {
        return blackPawn;
      } else if (symbol == 'B') {
        return blackBishop;
      } else if (symbol == 'R') {
        return blackRook;
      }
    } else {
      if (symbol == 'K') {
        return whiteKing;
      } else if (symbol == 'N') {
        return whiteKnight;
      } else if (symbol == 'Q') {
        return whiteQueen;
      } else if (symbol == 'P') {
        return whitePawn;
      } else if (symbol == 'B') {
        return whiteBishop;
      } else if (symbol == 'R') {
        return whiteRook;
      }
    }
    return null;
  }
  public void updateBoard() {
    Piece[][] pieces = game.getBoard().getBoard();
    for (int i = 0; i < pieces.length; i++) {
      for (int j = 0; j < pieces[i].length; j++) {
        Piece temp = pieces[i][j];
        if (temp != null) {
          char tempName = temp.getSymbol();
          //add the icons
          JLabel tempLabel = new JLabel(getImageIcon(tempName, temp.getColor()));
          squares[i][j].removeAll();
          squares[i][j].add(tempLabel);
        } else {
          squares[i][j].removeAll();
        }
      }
    }
    System.out.println("Board updated!");
    if (game.getCurPlayer()) {
        statusBox.setText("White, it's your turn!");
      } else {
        statusBox.setText("Black, it's your turn!");
      }
    this.revalidate();
    this.repaint();
  }
  public void mousePressed(MouseEvent e) {
    Component component = chessBoard.findComponentAt(e.getX(), e.getY());
   if (component instanceof JLabel) {
      draggedOldParent = component.getParent();
      xadjust = e.getX() - draggedOldParent.getLocation().x;
     yadjust = e.getY() - draggedOldParent.getLocation().y;
      draggedPiece = (JLabel)component;
     draggedPiece.setSize(draggedPiece.getWidth(), draggedPiece.getHeight());
      draggedPiece.setLocation(e.getX() + 30 + xadjust, e.getY() - 10 + yadjust);
     mainPanel.add(draggedPiece, JLayeredPane.DRAG_LAYER);
    }
  }
  public void mouseDragged(MouseEvent e) {
    if (draggedPiece != null) {
      draggedPiece.setLocation(e.getX() + 30 + xadjust, e.getY() - 10 + yadjust);
    }
  }
  public void mouseReleased(MouseEvent e) {
    if (draggedPiece != null) {
      Component component = chessBoard.findComponentAt(e.getX(), e.getY());
      if (component != null) {
        if (component instanceof JLabel) {
        Container parent = component.getParent();
          //if (checkMove(parent)) {
            checkMove(parent);
            parent.removeAll();
            parent.add(draggedPiece);
            endTurn();
          //} else {
            //statusBox.setText("You made an invalid move! Try again!");
        //}
        } else {
          Container parent = (Container) component;
          //if (checkMove(parent)) {
            checkMove(parent);
            parent.add(draggedPiece);
            endTurn();
          //} else {
            //statusBox.setText("You made an invalid move! Try again!");
        //draggedOldParent.add(draggedPiece);
          //}
        }
      } else {
        statusBox.setText("Please stay within the board!");
        draggedOldParent.add(draggedPiece);
      }
    }
    this.updateBoard();
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
    return game.move(oldc, oldr, newc, newr);
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
  //}
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == resetButton) {
      int n = JOptionPane.showConfirmDialog(this, "Are you sure you want to start a new game?\nThis cannot be undone!", "Reset Game?", JOptionPane.YES_NO_OPTION);
      if (n == JOptionPane.YES_OPTION) {
        game = new Game();
        this.updateBoard();
        game.freeMove(0,0,0,0);
        JOptionPane.showMessageDialog(this, "Guys there's so many bugs.\nCheck and stalemate crashes the game\n, so I commented that out.\nThe move check acts weirdly; try moving pieces around\nand see for yourself!\nSomeone needs to implement DAO.\n-Fr john, the graphics programmer", "Message from John", JOptionPane.WARNING_MESSAGE);
    }
      
    } else if (e.getSource() == saveButton) {
      this.game.saveMoveToFile("gamemove.txt");
   //   JOptionPane.showMessageDialog(this, "Guys someone needs to implement DAO still.", "Bro please pay attention", JOptionPane.WARNING_MESSAGE);
    } else if (e.getSource() == playButton) {
      this.remove(startPanel);
      this.add(mainPanel);
      this.revalidate();
      this.repaint();
      JOptionPane.showMessageDialog(this, "Guys there's so many bugs.\nCheck and stalemate crashes the game\n, so I commented that out.\nThe move check acts weirdly; try moving pieces around\nand see for yourself!\nSomeone needs to implement DAO.\n-Fr john, the graphics programmer", "Message from John", JOptionPane.WARNING_MESSAGE);
    }
  }
}