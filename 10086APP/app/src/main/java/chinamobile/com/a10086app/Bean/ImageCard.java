package chinamobile.com.a10086app.Bean;

import android.graphics.Bitmap;

public class ImageCard extends Card{
    private Bitmap logo;
    private String buttonText;

    public ImageCard(Bitmap logo, String buttonText) {
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

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }
}
