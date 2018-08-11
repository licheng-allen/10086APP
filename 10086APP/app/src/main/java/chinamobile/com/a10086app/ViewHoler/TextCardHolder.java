package chinamobile.com.a10086app.ViewHoler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import chinamobile.com.a10086app.Bean.TextCard;
import chinamobile.com.a10086app.Interface.OnItemClickListener;
import chinamobile.com.a10086app.Interface.OnItemLongClickListener;
import chinamobile.com.a10086app.R;

//文字的ViewHolder
public class TextCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public ImageView textLogo;
    public TextView textTitle;
    public TextView textUnit;
    public Button textButton;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongClickListener;
    public TextCardHolder(View itemView) {
        super(itemView);
        textLogo = (ImageView) itemView.findViewById(R.id.card_text_logo);
        textTitle= (TextView) itemView.findViewById(R.id.card_text_title);
        textUnit= (TextView) itemView.findViewById(R.id.card_text_unit);
        textButton=(Button) itemView.findViewById(R.id.card_text_button);
    }
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
    public void OnBindData(Context context, TextCard card){
        textLogo.setImageDrawable(context.getResources().getDrawable(card.getLogo()));
        textTitle.setText(card.getNumberToString(1));
        textUnit.setText(card.getUnit());
        textButton.setText(card.getButtonText());
    }
}