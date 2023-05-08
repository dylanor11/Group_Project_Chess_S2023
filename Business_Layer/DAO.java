package Group_Project_Chess_S2023.Business_Layer;

import java.io.File;
import java.io.IOException;
import Group_Project_Chess_S2023.Business_Layer.Move;


public class DAO {

    
    public static saveMoves(ArrayList<Move> moves) {
        try {
            File moveFile = new File("moveList.txt");

            for(Move move : moves) {
                moveFile.write(move.toString() + );
                
            }

            moveFile.close();

        } catch( IOException e) {
            System.out.println("Error ocurred while saving moves to file");
        }
    }



}
