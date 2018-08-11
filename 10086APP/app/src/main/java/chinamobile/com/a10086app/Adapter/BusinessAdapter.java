package chinamobile.com.a10086app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import chinamobile.com.a10086app.Bean.Business;
import chinamobile.com.a10086app.R;

public class BusinessAdapter extends BaseAdapter{
    private Context mContext = null;
    private ArrayList<Business> mMarkerData = null;

    public BusinessAdapter(Context context, ArrayList<Business> markerItems)
    {
        mContext = context;
        mMarkerData = markerItems;
    }

    public void setBusinessData(ArrayList<Business> markerItems)
    {
        mMarkerData = markerItems;
    }

    @Override
    public int getCount()
    {
        int count = 0;
        if (null != mMarkerData)
        {
            count = mMarkerData.size();
        }
        return count;
    }

    @Override
    public Business getItem(int position)
    {
        Business item = null;

        if (null != mMarkerData)
        {
            item = mMarkerData.get(position);
        }

        return item;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (null == convertView)
        {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            convertView = mInflater.inflate(R.layout.grideview_item_linearlayout,parent,false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.grideview_text);
            viewHolder.isnew = (ImageView) convertView
                    .findViewById(R.id.icon_new);
            viewHolder.image = (ImageView) convertView
                    .findViewById(R.id.grideview_img);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set item values to the viewHolder:

        Business business = getItem(position);
        if (null != business)
        {
            viewHolder.title.setText(business.getTitle());
            viewHolder.isnew.setVisibility(business.getVisible());
            viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(business.getImage()));
        }

        return convertView;
    }

    private static class ViewHolder
    {
        TextView title;
        ImageView isnew;
        ImageView image;
    }
}
