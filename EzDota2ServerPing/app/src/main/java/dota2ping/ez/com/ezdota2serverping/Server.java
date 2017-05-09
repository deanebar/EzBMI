package dota2ping.ez.com.ezdota2serverping;

/**
 * Created by duyluong on 5/9/2017.
 */

public class Server {
    private String name;

    private String location;

    private String ping = "un-known";

    public Server(String name, String location, String ping) {
        this.name = name;
        this.location = location;
        this.ping = ping;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
