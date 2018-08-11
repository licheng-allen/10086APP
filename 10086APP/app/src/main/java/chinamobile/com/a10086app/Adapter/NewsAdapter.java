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
            onePicViewHolder.textViewTitle.setText(news[position].getTitle());
            onePicViewHolder.circleimageView.setImageBitmap(news[position].getImageById(0));
            onePicViewHolder.textViewAuthor.setText(news[position].getAutor());
            onePicViewHolder.textViewPageViews.setText(news[position].getPageViewNumber());
            onePicViewHolder.textViewtime.setText(news[position].getTime());
        } else if (holder instanceof ThreePicViewHolder) {
            ThreePicViewHolder threePicViewHolder=(ThreePicViewHolder) holder;
            threePicViewHolder.textViewTitle.setText(news[position].getTitle());
            threePicViewHolder.textViewTitle.setText(news[position].getTitle());
            threePicViewHolder.textViewAuthor.setText(news[position].getAutor());
            threePicViewHolder.textViewPageViews.setText(news[position].getPageViewNumber());
            threePicViewHolder.textViewtime.setText(news[position].getTime());
            threePicViewHolder.circleimageView1.setImageBitmap(news[position].getImageById(0));
            threePicViewHolder.circleimageView2.setImageBitmap(news[position].getImageById(1));
            threePicViewHolder.circleimageView3.setImageBitmap(news[position].getImageById(2));
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

    //单图片item 的ViewHolder
    public static class OnePicViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageView circleimageView;
        TextView textViewAuthor;
        TextView textViewPageViews;
        TextView textViewtime;
        public OnePicViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.item_onepic_title);
            circleimageView=(ImageView)itemView.findViewById(R.id.item_onepic_image);
            textViewAuthor= (TextView) itemView.findViewById(R.id.item_onepic_autor);
            textViewPageViews= (TextView) itemView.findViewById(R.id.item_onepic_totleviews);
            textViewtime=(TextView) itemView.findViewById(R.id.item_onepic_time);
        }
    }

    //多图片item 的ViewHolder
    public static class ThreePicViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewAuthor;
        TextView textViewPageViews;
        TextView textViewtime;
        ImageView circleimageView1;
        ImageView circleimageView2;
        ImageView circleimageView3;
        public ThreePicViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.item_threepic_title);
            textViewAuthor= (TextView) itemView.findViewById(R.id.item_threepic_autor);
            textViewPageViews= (TextView) itemView.findViewById(R.id.item_threepic_totleviews);
            textViewtime=(TextView) itemView.findViewById(R.id.item_threepic_time);
            circleimageView1=(ImageView)itemView.findViewById(R.id.item_threepic_image1);
            circleimageView2=(ImageView)itemView.findViewById(R.id.item_threepic_image2);
            circleimageView3=(ImageView)itemView.findViewById(R.id.item_threepic_image3);
        }
    }
}

