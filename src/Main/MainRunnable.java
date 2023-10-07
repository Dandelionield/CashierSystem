package Main;

import java.awt.EventQueue;

public class MainRunnable {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                
                Mecanics.getFile(true);
                Mecanics.getClient(true);
                Mecanics.getReceipt(true);
                Mecanics.getEmploye(true);
				//Mecanics.main(null);

                try {

                    new Runner();

                } catch (Exception e) {

                    e.printStackTrace();

                }//*/

            }

        });

    }

}
