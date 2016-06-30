package obj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tweet {

    private Coordinates location;
    private Date dateTime;
    private String message;

    public Tweet() {}
    public Tweet(Coordinates location, Date dateTime, String message) {
        this.location = location;
        this.dateTime = dateTime;
        this.message = message;
    }

    public Coordinates getCoordinates() {
        return location;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public String getMessage()
    {
        return message;
    }

    public void output() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm kk:mm:ss");
        System.out.println("[" + location.getX() + ", " + location.getY() + "] " + sdf.format(dateTime) + " " + message);
    }
}
