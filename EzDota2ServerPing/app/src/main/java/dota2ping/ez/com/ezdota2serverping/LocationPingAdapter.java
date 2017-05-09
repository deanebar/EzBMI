package dota2ping.ez.com.ezdota2serverping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by duyluong on 5/9/2017.
 */

public class LocationPingAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Server> mDataSource;
    private final LayoutInflater mInflater;

    public LocationPingAdapter(Context context, List<Server> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.server_pings, viewGroup, false);

        Server server = (Server) getItem(i);
        TextView txtLocation = (TextView) rowView.findViewById(R.id.location);
        txtLocation.setText(server.getName());

        TextView txtPing = (TextView) rowView.findViewById(R.id.ping);
        txtPing.setText(server.getPing());
        return rowView;
    }
}
