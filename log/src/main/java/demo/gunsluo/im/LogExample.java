package demo.gunsluo.im;


import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogExample {
    public static void main(String[] args) throws IOException, InterruptedException {

        String path = LogExample.class.getResource("/logging.properties").getPath();
        System.setProperty("java.util.logging.config.file", path);

        LogManager.getLogManager().readConfiguration();
        Logger logger = Logger.getLogger(LogExample.class.getName());
        logger.info("hello");
    }
}