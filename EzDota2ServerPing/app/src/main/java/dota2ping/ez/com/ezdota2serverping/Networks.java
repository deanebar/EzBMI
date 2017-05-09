package dota2ping.ez.com.ezdota2serverping;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by duyluong on 5/9/2017.
 */

public class Networks {
    public static final String PING_TIME_PREFIX = "time=";
    public static final String MILLIS = "ms";
    public static final String UN_KNOWN = "un-known";
    private static List<NetworkInterface> NETWORK_INTERFACES;

    static {
        try {
            NETWORK_INTERFACES = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (SocketException e) {
            NETWORK_INTERFACES = Collections.emptyList();
        }
    }

    public static String getFirstLocalNonLoopbackIpAddress() {
        SortedSet<String> addresses = new TreeSet<>();
        Iterator<NetworkInterface> iterator = NETWORK_INTERFACES.iterator();
        while (iterator.hasNext()) {
            NetworkInterface ni = iterator.next();
            Enumeration<InetAddress> addressEnumeration = ni.getInetAddresses();
            while (addressEnumeration.hasMoreElements()) {
                InetAddress address = addressEnumeration.nextElement();

                if (!address.isLoopbackAddress() && !address.getHostAddress().contains(":")) {
                    addresses.add(address.getHostAddress());
                }
            }
        }

        return addresses.isEmpty() ? UN_KNOWN : addresses.first();
    }

    public static String getPing(String host) {
        String pingResult = executeCmd(String.format("ping -c1 %s", host));
        if (!pingResult.contains(PING_TIME_PREFIX)) {
            return UN_KNOWN;
        }
        String cutString = pingResult.substring(pingResult.indexOf(PING_TIME_PREFIX));
        return cutString.substring(PING_TIME_PREFIX.length(), cutString.indexOf(MILLIS) + MILLIS.length());
    }

    public static String executeCmd(String cmd) {
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s;
            String res = "";
            while ((s = stdInput.readLine()) != null) {
                res += s + "\n";
            }
            p.destroy();
            return res;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return "";
    }
}
