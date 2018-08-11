package chinamobile.com.a10086app.ViewHoler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import chinamobile.com.a10086app.Bean.News;
import chinamobile.com.a10086app.R;

/**单图片item 的ViewHolder*/
public class OnePicViewHolder extends RecyclerView.ViewHolder {
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
    public void OnBindData(News news){
        textViewTitle.setText(news.getTitle());
        circleimageView.setImageBitmap(news.getImageById(0));
        textViewAuthor.setText(news.getAutor());
        textViewPageViews.setText(news.getPageViewNumber());
        textViewtime.setText(news.getTime());
    }
}
