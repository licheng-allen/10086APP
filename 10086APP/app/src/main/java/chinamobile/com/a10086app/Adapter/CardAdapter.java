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

import chinamobile.com.a10086app.Bean.Card;
import chinamobile.com.a10086app.Bean.ChannelItem;
import chinamobile.com.a10086app.Bean.ImageCard;
import chinamobile.com.a10086app.Bean.ProgressCard;
import chinamobile.com.a10086app.Bean.TextCard;
import chinamobile.com.a10086app.Interface.OnItemClickListener;
import chinamobile.com.a10086app.Interface.OnItemLongClickListener;
import chinamobile.com.a10086app.R;

import static android.content.ContentValues.TAG;

public class CardAdapter extends Adapter {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private Card[] cards;
    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;
    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        PROGRESS,//进度条样式
        TEXT,//文字样式
        IMAGE//单图模式
    }

    public CardAdapter(Context context, Card[] cards) {
        this.cards = cards;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.PROGRESS.ordinal()) {
            return new ProgressCardHolder(mLayoutInflater.inflate(R.layout.card_item_progress, parent, false));
        } else if(viewType == ITEM_TYPE.TEXT.ordinal()){
            return new TextCardHolder(mLayoutInflater.inflate(R.layout.card_item_text, parent, false),mOnItemClickListener,mOnItemLongClickListener);
        }else {
            return new ImageCardHolder(mLayoutInflater.inflate(R.layout.card_item_image, parent, false));
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProgressCardHolder) {
            ProgressCard card=(ProgressCard) cards[position];
            ProgressCardHolder progressCardHolder =(ProgressCardHolder) holder;
            progressCardHolder.progressWheeltitle.setText(card.getTitle());
            progressCardHolder.progressWheel.setDefText(card.getUnit());
            progressCardHolder.progressWheel.setPercentage(card.getPercent());
            progressCardHolder.progressWheel.setStepCountText(card.getSurplusToString(2));
        } else if (holder instanceof TextCardHolder) {
           TextCard card=(TextCard) cards[position];
            TextCardHolder textCardHolder =(TextCardHolder) holder;
            textCardHolder.textLogo.setImageBitmap(card.getLogo());
            textCardHolder.textTitle.setText(card.getNumberToString(1));
            textCardHolder.textUnit.setText(card.getUnit());
            textCardHolder.textButton.setText(card.getButtonText());
        }else if(holder instanceof ImageCardHolder){
            ImageCard card=(ImageCard) cards[position];
            ImageCardHolder imageCardHolder =(ImageCardHolder) holder;
            imageCardHolder.logo.setImageBitmap(card.getLogo());
            imageCardHolder.textButton.setText(card.getButtonText());
        }
    }

    /** 拖动变更频道排序 */
   /* public void exchange(int dragPostion, int dropPostion) {
        holdPosition = dropPostion;
        ChannelItem dragItem = getItem(dragPostion);
        Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
        if (dragPostion < dropPostion) {
            channelList.add(dropPostion + 1, dragItem);
            channelList.remove(dragPostion);
        } else {
            channelList.add(dropPostion, dragItem);
            channelList.remove(dragPostion + 1);
        }
        isChanged = true;
        notifyDataSetChanged();
    }*/


    public int getItemViewType(int position) {
       return cards[position].getCardType();
    }

    public int getItemCount() {
        return cards == null ? 0 : cards.length;
    }

    //圆形进度条 的ViewHolder
    public static class ProgressCardHolder extends RecyclerView.ViewHolder {
        TextView progressWheeltitle;
        ProgressWheel progressWheel;
        Button progressButton;

        public ProgressCardHolder(View itemView) {
            super(itemView);
            progressWheeltitle = (TextView) itemView.findViewById(R.id.card_progress_title);
            progressWheel=(ProgressWheel)itemView.findViewById(R.id.wheelprogress);
            progressButton=(Button)itemView.findViewById(R.id.card_progress_button);
        }
    }

    //文字的ViewHolder
    public static class TextCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        ImageView textLogo;
        TextView textTitle;
        TextView textUnit;
        Button textButton;
        private OnItemClickListener mListener;
        private OnItemLongClickListener mLongClickListener;
        public TextCardHolder(View itemView,OnItemClickListener listener,OnItemLongClickListener longClickListener) {
            super(itemView);
            textLogo = (ImageView) itemView.findViewById(R.id.card_text_logo);
            textTitle= (TextView) itemView.findViewById(R.id.card_text_title);
            textUnit= (TextView) itemView.findViewById(R.id.card_text_unit);
            textButton=(Button) itemView.findViewById(R.id.card_text_button);
            this.mListener = listener;
            this.mLongClickListener = longClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }

        }

        @Override
        public boolean onLongClick(View v) {
            if(mLongClickListener != null){
                mLongClickListener.onItemLongClick(v, getPosition());
            }
            return true;
        }
    }
    //图片的ViewHolder
    public static class ImageCardHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        Button textButton;
        public ImageCardHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.card_image_logo);
            textButton=(Button) itemView.findViewById(R.id.card_image_button);
        }
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

