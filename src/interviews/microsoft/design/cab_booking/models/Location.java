package interviews.microsoft.design.cab_booking.models;

public class Location {
    long xCord;
    long yCord;

    public long getDistance(Location other) {
        long ySquare = (long) Math.pow(other.yCord - yCord, 2);
        long xSquare = (long) Math.pow(other.xCord - xCord, 2);
        return (long) Math.sqrt(ySquare + xSquare);
    }
}
