package interviews.microsoft.design.logger.appenders;

import interviews.microsoft.design.logger.models.File;
import interviews.microsoft.design.logger.models.LogMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileAppender extends Appender<File> {

    public FileAppender(File file) {
        super(file);
    }

    @Override
    public void publish(LogMessage logMessage) {
        Path path = Paths.get(sink.getPath());
        String formattedMessage = getFormattedPrefix(logMessage.getTimeFormat())
                .append("-").append(logMessage.getLogLevel().name())
                .append("-").append(logMessage.getFqdn())
                .append("-").append(logMessage.getMessage()).toString();
        try {
            Files.write(path, formattedMessage.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }
}
