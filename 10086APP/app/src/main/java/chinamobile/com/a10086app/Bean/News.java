package chinamobile.com.a10086app.Bean
;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;

import java.util.ArrayList;

import chinamobile.com.a10086app.R;
import chinamobile.com.a10086app.Utils.NumberUtils;

public class News {

    private String title;//新闻标题
    private String autor;//新闻作者
    private String time;//新闻发布时间
    private double pageViewNumber;//页面浏览量
    private ArrayList<Bitmap> images;//图片集合
    private String content;//新闻内容

    public News(String title, String autor, String time, double pageViewNumber, ArrayList<Bitmap> images) {
        this.title = title;
        this.autor = autor;
        this.time = time;
        this.pageViewNumber = pageViewNumber;
        this.images = images;
    }

    //TODO 添加非空判断
    public News(String title, String autor, String time, double pageViewNumber, ArrayList<Bitmap> images, String content) {
        this.title = title;
        this.autor = autor;
        this.time = time;
        this.pageViewNumber = pageViewNumber;
        this.images =  images;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageNumber() {
        return images.size();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPageViewNumber() {
        return NumberUtils.amountConversion(pageViewNumber);
    }

    public void setPageViewNumber(double pageViewNumber) {
        this.pageViewNumber = pageViewNumber;
    }

    public ArrayList<Bitmap> getImages() {
        return images;
    }

    public void setImages(ArrayList<Bitmap> images) {
        this.images = images;
    }

    public Bitmap getImageById(int position) {
        Bitmap filletimage=getFilletBitmap(images.get(position),40);
        if(position<=images.size())
            return filletimage;
        return null;
    }
    public static Bitmap getFilletBitmap(Bitmap bitmap,float roundpx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffEEAD0E;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = roundpx;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); // 这句是关键
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

}
