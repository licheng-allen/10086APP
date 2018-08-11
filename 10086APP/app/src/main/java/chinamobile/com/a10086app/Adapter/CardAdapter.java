package chinamobile.com.a10086app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.progresviews.ProgressWheel;

import java.util.ArrayList;

import chinamobile.com.a10086app.Bean.Card;
import chinamobile.com.a10086app.Bean.ChannelItem;
import chinamobile.com.a10086app.Bean.ImageCard;
import chinamobile.com.a10086app.Bean.ProgressCard;
import chinamobile.com.a10086app.Bean.TextCard;
import chinamobile.com.a10086app.Interface.OnItemClickListener;
import chinamobile.com.a10086app.Interface.OnItemLongClickListener;
import chinamobile.com.a10086app.R;
import chinamobile.com.a10086app.ViewHoler.ImageCardHolder;
import chinamobile.com.a10086app.ViewHoler.ProgressCardHolder;
import chinamobile.com.a10086app.ViewHoler.TextCardHolder;

import static android.content.ContentValues.TAG;

public class CardAdapter extends Adapter {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private ArrayList<Card> cards;
    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;
    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        PROGRESS,//进度条样式
        TEXT,//文字样式
        IMAGE//单图模式
    }

    public CardAdapter(Context context, ArrayList<Card> cards) {
        this.cards = cards;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.PROGRESS.ordinal()) {
            return new ProgressCardHolder(mLayoutInflater.inflate(R.layout.card_item_progress, parent, false)
                    ,mOnItemClickListener,mOnItemLongClickListener);
        } else if(viewType == ITEM_TYPE.TEXT.ordinal()){
            return new TextCardHolder(mLayoutInflater.inflate(R.layout.card_item_text, parent, false)
                    ,mOnItemClickListener,mOnItemLongClickListener);
        }else {
            return new ImageCardHolder(mLayoutInflater.inflate(R.layout.card_item_image, parent, false)
                    ,mOnItemClickListener,mOnItemLongClickListener);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProgressCardHolder) {
            setProgressHolderData((ProgressCardHolder) holder, position);
        } else if (holder instanceof TextCardHolder) {
            setTextHolderData((TextCardHolder) holder, position);
        }else if(holder instanceof ImageCardHolder){
            setImageHolderData((ImageCardHolder) holder, position);
        }
    }

    private void setImageHolderData(ImageCardHolder holder, int position) {
        ImageCard card=(ImageCard) cards.get(position);
        ImageCardHolder imageCardHolder = holder;
        imageCardHolder.OnBindData(context,card);
    }

    private void setTextHolderData(TextCardHolder holder, int position) {
        TextCard card=(TextCard) cards.get(position);
        TextCardHolder textCardHolder = holder;
        textCardHolder.OnBindData(context,card);
    }

    private void setProgressHolderData(ProgressCardHolder holder, int position) {
        ProgressCard card=(ProgressCard) cards.get(position);
        ProgressCardHolder progressCardHolder = holder;
        progressCardHolder.OnBindData(context,card);
    }

    public int getItemViewType(int position) {
       return cards.get(position).getCardType();
    }

    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }

    //设置单击监听事件
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    //设置长按点击事件
    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}

