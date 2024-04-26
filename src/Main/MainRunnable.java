package Main;

import java.awt.EventQueue;

public class MainRunnable {
	
	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                
                try {

                    new Runner();
					
                } catch (Exception e) {

                    e.printStackTrace();
					
                }
				
            }
			
        });
		
    }

}
