package interviews.microsoft.design.logger.models;

import interviews.microsoft.design.logger.enums.Level;

public abstract class Sink {
    Level level;
    String type;

    public Sink(Level level, String type) {
        this.level = level;
        this.type = type;
    }

    public Sink() {
      this.level = getLevel();
      this.type = getType();
    }

    public Level getLevel() {
        return Level.DEBUG;
    }

    public String getType() {
        return "Console";
    }
}
