package chinamobile.com.a10086app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import chinamobile.com.a10086app.Bean.News;
import chinamobile.com.a10086app.R;
import chinamobile.com.a10086app.ViewHoler.OnePicViewHolder;
import chinamobile.com.a10086app.ViewHoler.ThreePicViewHolder;

public class NewsAdapter extends Adapter {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private News[] news;

    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        ONEPIC,//单图
        THREEPIC//多图
    }

    public NewsAdapter(Context context, News[] news) {
        this.news = news;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ONEPIC.ordinal()) {
            return new OnePicViewHolder(mLayoutInflater.inflate(R.layout.recycleview_item_onepic, parent, false));
        } else {
            return new ThreePicViewHolder(mLayoutInflater.inflate(R.layout.recycleview_item_threepic, parent, false));
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OnePicViewHolder) {
            OnePicViewHolder onePicViewHolder=(OnePicViewHolder) holder;
            onePicViewHolder.OnBindData(news[position]);
        } else if (holder instanceof ThreePicViewHolder) {
            ThreePicViewHolder threePicViewHolder=(ThreePicViewHolder) holder;
            threePicViewHolder.OnBindData(news[position]);
        }
    }

    public int getItemViewType(int position) {
        int imageNumber=news[position].getImageNumber();
        if(imageNumber<3)//如果图片数量小于3采用单图片布局，否则采用多图片布局
            return ITEM_TYPE.ONEPIC.ordinal();
        return ITEM_TYPE.THREEPIC.ordinal();
    }

    public int getItemCount() {
        return news == null ? 0 : news.length;
    }

}

