package interviews.microsoft.design.logger;


import interviews.microsoft.design.logger.enums.Level;

public class CustomLogger implements LoggerFacade {
    private LoggerClient loggerClient;
    private Class className;

    public CustomLogger(Class className) {
        loggerClient = LoggerClient.getInstance();
        this.className = className;
    }

    @Override
    public void info(String cname, String message) {
        loggerClient.log(cname, message, Level.INFO);
    }

    @Override
    public void debug(String cname, String message) {
        loggerClient.log(cname, message, Level.DEBUG);
    }

    @Override
    public void warn(String cname, String message) {
        loggerClient.log(cname, message, Level.WARN);
    }

    @Override
    public void error(String cname, String message) {
        loggerClient.log(cname, message, Level.ERROR);
    }
}
