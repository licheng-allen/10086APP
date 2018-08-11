package chinamobile.com.a10086app.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;

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

public class DragAdapter extends BaseAdapter {
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

    public DragAdapter(Context context, ArrayList<Card> cards) {
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
            setProgressHolderData((ProgressCardHolder) holder, position);
        } else if (holder instanceof TextCardHolder) {
            setTextHolderData((TextCardHolder) holder, position);
        } else if (holder instanceof ImageCardHolder) {
            setImageHolderData((ImageCardHolder) holder, position);
        }
    }

    private void setImageHolderData(ImageCardHolder holder, int position) {
        ImageCard card = (ImageCard) cards.get(position);
        ImageCardHolder imageCardHolder = holder;
        imageCardHolder.OnBindData(context, card);
    }

    private void setTextHolderData(TextCardHolder holder, int position) {
        TextCard card = (TextCard) cards.get(position);
        TextCardHolder textCardHolder = holder;
        textCardHolder.OnBindData(context, card);
    }

    private void setProgressHolderData(ProgressCardHolder holder, int position) {
        ProgressCard card = (ProgressCard) cards.get(position);
        ProgressCardHolder progressCardHolder = holder;
        progressCardHolder.OnBindData(context, card);
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
        if (viewtype == 0) {
            convertView = mLayoutInflater
                    .inflate(R.layout.card_item_progress, parent, false);
            holder = new ProgressCardHolder(convertView);
        } else if (viewtype == 1) {
            convertView = mLayoutInflater
                    .inflate(R.layout.card_item_text, parent, false);
            holder = new TextCardHolder(convertView);
        } else {
            convertView = mLayoutInflater
                    .inflate(R.layout.card_item_image, parent, false);
            holder = new ImageCardHolder(convertView);
        }
        onBindViewHolder(holder, position);
        runEnterAnimation(holder.itemView, position);
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

    private void runEnterAnimation(View view, int position) {


        if (animationsLocked) return;              //animationsLocked是布尔类型变量，一开始为false
        //确保仅屏幕一开始能够容纳显示的item项才开启动画


        if (position > lastAnimatedPosition) {//lastAnimatedPosition是int类型变量，默认-1，
            //这两行代码确保了recyclerview滚动式回收利用视图时不会出现不连续效果
            lastAnimatedPosition = position;
            view.setTranslationY(300);     //Item项一开始相对于原始位置下方300距离
            view.setAlpha(0.f);           //item项一开始完全透明
            //每个item项两个动画，从透明到不透明，从下方移动到原始位置


            view.animate()
                    .translationY(0).alpha(1.f)                                //设置最终效果为完全不透明
                    //并且在原来的位置
                    //	.setStartDelay(delayEnterAnimation ? 20 * (position) : 0)//根据item的位置设置延迟时间
                    //达到依次动画一个接一个进行的效果
                    .setInterpolator(new DecelerateInterpolator(0.5f))     //设置动画位移先快后慢的效果
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;
                            //确保仅屏幕一开始能够显示的item项才开启动画
                            //也就是说屏幕下方还没有显示的item项滑动时是没有动画效果
                        }
                    })
                    .start();
        }
    }
}