package chinamobile.com.a10086app.Bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ImageCard extends Card implements Serializable {
    private int logo;
    private String buttonText;
    private static final long serialVersionUID = 1L;

    public ImageCard(int logo, String buttonText) {
        this.logo = logo;
        this.buttonText = buttonText;
        cardType=2;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
