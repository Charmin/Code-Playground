package interviews.microsoft.design.logger;

public interface LoggerFacade {

    void info(String cname, String message);
    void debug(String cname, String message);
    void warn(String cname, String message);
    void error(String cname, String message);
}
