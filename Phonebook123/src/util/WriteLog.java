package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class WriteLog {

	
	public static void write(){

        Logger logger = Logger.getLogger(WriteLog.class.getName());
 
        
        FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("app.log", true);
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}       
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
 
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Information message");
        }
 
        if (logger.isLoggable(Level.WARNING)) {
            logger.warning("Warning message");
        }
}
}