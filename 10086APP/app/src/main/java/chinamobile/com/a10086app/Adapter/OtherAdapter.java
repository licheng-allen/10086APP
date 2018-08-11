package chinamobile.com.a10086app.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import chinamobile.com.a10086app.Bean.Card;
import chinamobile.com.a10086app.Bean.ChannelItem;
import chinamobile.com.a10086app.Bean.ImageCard;
import chinamobile.com.a10086app.Bean.ProgressCard;
import chinamobile.com.a10086app.Bean.TextCard;
import chinamobile.com.a10086app.R;
import chinamobile.com.a10086app.ViewHoler.ImageCardHolder;
import chinamobile.com.a10086app.ViewHoler.ProgressCardHolder;
import chinamobile.com.a10086app.ViewHoler.TextCardHolder;

import static android.content.ContentValues.TAG;

public class OtherAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private ArrayList<Card> cards;
    /**
     * 要删除的position
     */
    public int remove_position = -1;
    /**
     * 是否显示底部的ITEM
     */
    private boolean isItemShow = false;
    /**
     * 是否显示底部的ITEM
     */
    private boolean isVisible = false;
    /**
     * 控制的postion
     */
    private int holdPosition;
    /**
     * 是否改变
     */
    private boolean isChanged = false;
    /**
     * 最后一个动画执行的位置
     */
    private int lastAnimatedPosition = -1;
    /**
     * 是否不执行动画
     */
    private boolean animationsLocked = false;
    /**
     * 动画延迟
     */
    private boolean delayEnterAnimation = true;

    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        PROGRESS,//进度条样式
        TEXT,//文字样式
        IMAGE//单图模式
    }

    public OtherAdapter(Context context, ArrayList<Card> cards) {
        this.cards = cards;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 添加频道列表
     */
    public void addItem(Card channel) {
        cards.add(channel);
        notifyDataSetChanged();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProgressCardHolder) {
            ProgressCard card = (ProgressCard) cards.get(position);
            ProgressCardHolder progressCardHolder = (ProgressCardHolder) holder;
            progressCardHolder.progressWheeltitle.setText(card.getTitle());
            progressCardHolder.progressWheel.setDefText(card.getUnit());
            progressCardHolder.progressWheel.setPercentage(card.getPercent());
            progressCardHolder.progressWheel.setStepCountText(card.getSurplusToString(2));
        } else if (holder instanceof TextCardHolder) {
            TextCard card = (TextCard) cards.get(position);
            TextCardHolder textCardHolder = (TextCardHolder) holder;
            textCardHolder.textLogo.setImageDrawable(context.getResources().getDrawable(card.getLogo()));
            textCardHolder.textTitle.setText(card.getNumberToString(1));
            textCardHolder.textUnit.setText(card.getUnit());
            textCardHolder.textButton.setText(card.getButtonText());
        } else if (holder instanceof ImageCardHolder) {
            ImageCard card = (ImageCard) cards.get(position);
            ImageCardHolder imageCardHolder = (ImageCardHolder) holder;
            imageCardHolder.logo.setImageDrawable(context.getResources().getDrawable(card.getLogo()));
            imageCardHolder.textButton.setText(card.getButtonText());
        }
    }

    @Override
    public int getCount() {
        return cards == null ? 0 : cards.size();
    }

    @Override
    public Card getItem(int position) {
        if (cards != null && cards.size() != 0) {
            return cards.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclerView.ViewHolder holder;

        int viewtype = getItemViewType(position);
        //if (convertView == null) {
        if (viewtype == 0) {
            convertView = mLayoutInflater
                    .inflate(R.layout.card_item_progress, null);
            holder = new ProgressCardHolder(convertView);
        } else if (viewtype == 1) {
            convertView = mLayoutInflater
                    .inflate(R.layout.card_item_text, null);
            holder = new TextCardHolder(convertView);
        } else {
            convertView = mLayoutInflater
                    .inflate(R.layout.card_item_image, null);
            holder = new ImageCardHolder(convertView);
        }
        onBindViewHolder(holder, position);
        convertView.setTag(viewtype);
        return convertView;
    }

    /* 设置删除的position */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
    }

    /**
     * 删除频道列表
     */
    public void remove() {
        cards.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }

    /**
     * 获取是否可见
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * 设置是否可见
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * 显示放下的ITEM
     */
    public void setShowDropItem(boolean show) {
        isItemShow = show;
    }

    /**
     * 拖动变更频道排序
     */
    public void exchange(int dragPostion, int dropPostion) {
        holdPosition = dropPostion;
        Card dragItem = getItem(dragPostion);
        Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
        if (dragPostion < dropPostion) {
            cards.add(dropPostion + 1, dragItem);
            cards.remove(dragPostion);
        } else {
            cards.add(dropPostion, dragItem);
            cards.remove(dragPostion + 1);
        }
        isChanged = true;
        notifyDataSetChanged();
    }


    public int getItemViewType(int position) {
        return cards.get(position).getCardType();
    }

    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }
}