package Group_Project_Chess_S2023;

import Group_Project_Chess_S2023.Business_Layer.Move;

public class Main {
    public static void main(String[] args) {
        for(int i = 0; i < 8; i++) {
            System.out.println(Move.fileToLetter(i));
        }
        
    }
}
