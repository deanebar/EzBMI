package dota2ping.ez.com.ezdota2serverping;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtLocalIP = (TextView) findViewById(R.id.txt_localIP);
        txtLocalIP.setText(Networks.getFirstLocalNonLoopbackIpAddress());

        updateDota2ServerPings();
    }

    private void updateDota2ServerPings() {
        ListView listView = (ListView) findViewById(R.id.server_pings);
        List<Server> servers = Arrays.asList(new Server("SEA Asia", "111.1.1.1", "120ms"),
                new Server("US West", "111.1.1.1", "320ms"));
        LocationPingAdapter adapter = new LocationPingAdapter(this, servers);
        listView.setAdapter(adapter);
    }
}
