package chinamobile.com.a10086app.Bean;

import java.io.Serializable;

public abstract class Card implements Serializable {
    protected int cardType;//0=进度条模式，1=文字模式，2=图片模式
    private static final long serialVersionUID = 1L;
    public int getCardType() {
        return cardType;
    }    public void setCardType(int cardType) {
        this.cardType = cardType;
    }
}
