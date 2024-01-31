package Main;

import java.awt.EventQueue;

import Objects.*;

public class MainRunnable {

    public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                
                try {

                    new Runner();

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });//*/

    }

}
