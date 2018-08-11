package chinamobile.com.a10086app.Bean;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.math.BigDecimal;

public class TextCard extends Card implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private int logo;
    private float number;
    private String unit;//进度条剩余值
    private String buttonText;
    public TextCard(int logo, float number, String unit, String buttonText) {
        this.logo = logo;
        this.number = number;
        this.unit = unit;
        this.buttonText = buttonText;
        cardType=1;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public String getNumberToString(int decimalcount) {
        BigDecimal   b  =   new BigDecimal(number);
        float mSurplus   =  b.setScale(decimalcount,  BigDecimal.ROUND_HALF_UP).floatValue();
        return mSurplus+"";
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}
