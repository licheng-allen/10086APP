package chinamobile.com.a10086app.ViewHoler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.progresviews.ProgressWheel;

import chinamobile.com.a10086app.Bean.ProgressCard;
import chinamobile.com.a10086app.Bean.TextCard;
import chinamobile.com.a10086app.Interface.OnItemClickListener;
import chinamobile.com.a10086app.Interface.OnItemLongClickListener;
import chinamobile.com.a10086app.R;

//圆形进度条 的ViewHolder
public class ProgressCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView progressWheeltitle;
    public ProgressWheel progressWheel;
    public Button progressButton;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongClickListener;

    public ProgressCardHolder(View itemView) {
        super(itemView);
        progressWheeltitle = (TextView) itemView.findViewById(R.id.card_progress_title);
        progressWheel = (ProgressWheel) itemView.findViewById(R.id.wheelprogress);
        progressButton = (Button) itemView.findViewById(R.id.card_progress_button);
    }

    public ProgressCardHolder(View itemView, OnItemClickListener listener, OnItemLongClickListener longClickListener) {
        super(itemView);
        progressWheeltitle = (TextView) itemView.findViewById(R.id.card_progress_title);
        progressWheel = (ProgressWheel) itemView.findViewById(R.id.wheelprogress);
        progressButton = (Button) itemView.findViewById(R.id.card_progress_button);
        this.mListener = listener;
        this.mLongClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(v, getPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mLongClickListener != null) {
            mLongClickListener.onItemLongClick(v, getPosition());
        }
        return true;
    }

    public void OnBindData(Context context, ProgressCard card) {
        progressWheeltitle.setText(card.getTitle());
        progressWheel.setDefText(card.getUnit());
        progressWheel.animate();
        progressWheel.setPercentage(card.getPercent());
        progressWheel.setStepCountText(card.getSurplusToString(2));
    }
}
