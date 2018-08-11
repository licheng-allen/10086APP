package chinamobile.com.a10086app.ViewHoler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import chinamobile.com.a10086app.Bean.ImageCard;
import chinamobile.com.a10086app.Interface.OnItemClickListener;
import chinamobile.com.a10086app.Interface.OnItemLongClickListener;
import chinamobile.com.a10086app.R;

//图片的ViewHolder
public class ImageCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public ImageView logo;
    public Button textButton;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongClickListener;
    public ImageCardHolder(View itemView) {
        super(itemView);
        logo = (ImageView) itemView.findViewById(R.id.card_image_logo);
        textButton=(Button) itemView.findViewById(R.id.card_image_button);
    }
    public ImageCardHolder(View itemView,OnItemClickListener listener,OnItemLongClickListener longClickListener) {
        super(itemView);
        logo = (ImageView) itemView.findViewById(R.id.card_image_logo);
        textButton=(Button) itemView.findViewById(R.id.card_image_button);
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
    public void OnBindData(Context context,ImageCard card) {
        logo.setImageDrawable(context.getResources().getDrawable(card.getLogo()));
        textButton.setText(card.getButtonText());
    }
}