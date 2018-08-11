package chinamobile.com.a10086app.ViewHoler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import chinamobile.com.a10086app.Bean.News;
import chinamobile.com.a10086app.R;

/**多图片item 的ViewHolder*/
public class ThreePicViewHolder extends RecyclerView.ViewHolder {
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
    public void OnBindData(News news){
        textViewTitle.setText(news.getTitle());
        textViewTitle.setText(news.getTitle());
        textViewAuthor.setText(news.getAutor());
        textViewPageViews.setText(news.getPageViewNumber());
        textViewtime.setText(news.getTime());
        circleimageView1.setImageBitmap(news.getImageById(0));
        circleimageView2.setImageBitmap(news.getImageById(1));
        circleimageView3.setImageBitmap(news.getImageById(2));
    }
}