package interviews.microsoft.design.logger.models;

import interviews.microsoft.design.logger.enums.Level;

public class LogMessage {
    private String fqdn;
    private String message;
    private String timeFormat;
    private Level logLevel;

    public LogMessage(String fqdn, String message, String timeFormat, Level logLevel) {
        this.fqdn = fqdn;
        this.message = message;
        this.timeFormat = timeFormat;
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public String getFqdn() {
        return fqdn;
    }
}
