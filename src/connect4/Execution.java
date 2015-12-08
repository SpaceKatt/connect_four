/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.util.Scanner;

/**
 *
 * @author Jash
 */
public class Execution {
    public static void main(String[] args) {
        ConnectFourViewer gameRunner = new ConnectFourViewer();
        gameRunner.main(new String[]{"GO"});
//        Connect4 game = new Connect4();
//        int[] chipsInX = new int[7];
//        
//
//        int color = 1;
//        
//        //////////////// TESTIN //////////////////
////        int[][] corz = {{0,0},{1,1},{2,2},{3,3}};
////        int[][] corz = {{0,3},{1,2},{2,1},{3,0}};
//        int[][] corz = {{2,1},{3,1},{4,1},{5,1}};
//        for (int[] e : corz) {
//            game.placeChip(color, e);
//            try {
//                Thread.sleep(100);                 //1000 milliseconds is one second.
//            } catch(InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        }
//        
//        
    }
}
