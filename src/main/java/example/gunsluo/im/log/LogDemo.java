package example.gunsluo.im.log;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogDemo {
    public static void main(String[] args) throws IOException {
        System.setProperty("java.util.logging.config.file",
                "/Users/luoji/gopath/src/github.com/gunsluo/java-example/src/main/resources/logging.properties");

        LogManager.getLogManager().readConfiguration();
        Logger logger = Logger.getLogger(LogDemo.class.getName());
        logger.info("hello");
    }
}
