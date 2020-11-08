package interviews.microsoft.design.logger.appenders;

import interviews.microsoft.design.logger.models.Console;
import interviews.microsoft.design.logger.models.LogMessage;

public class ConsoleAppender extends Appender<Console> {
    public ConsoleAppender(Console sink) {
        super(sink);
    }

    @Override
    public void publish(LogMessage logMessage) {
        String formattedMessage = getFormattedPrefix(logMessage.getTimeFormat())
                .append(" - ").append(logMessage.getLogLevel().name())
                .append(" - ").append(logMessage.getFqdn())
                .append(" - ").append(logMessage.getMessage()).toString();
        System.out.println(formattedMessage);
    }
}
