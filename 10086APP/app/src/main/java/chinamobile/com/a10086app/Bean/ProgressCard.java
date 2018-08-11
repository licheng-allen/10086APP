package chinamobile.com.a10086app.Bean;


import android.content.Context;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProgressCard extends Card implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private float total;//进度条极大值
    private float surplus;//进度条剩余值
    private String title;//标题
    private String unit;//单位

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public ProgressCard(float total, float surplus, String title, String unit) {
        this.total = total;
        this.surplus = surplus;
        this.title = title;
        this.unit = unit;
        cardType=0;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getSurplus() {
        return surplus;
    }
    public String getSurplusToString(int decimalcount) {
        BigDecimal   b  =   new BigDecimal(surplus);
        float mSurplus   =  b.setScale(decimalcount,  BigDecimal.ROUND_HALF_UP).floatValue();
        return mSurplus+"";
    }

    public void setSurplus(float surplus) {
        this.surplus = surplus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPercent() {
        return (int)((surplus / total)*360);
    }

}
