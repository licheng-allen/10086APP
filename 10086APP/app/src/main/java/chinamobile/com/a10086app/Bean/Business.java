package chinamobile.com.a10086app.Bean;

public class Business {
    int image;
    String title;
    int visible;
    public Business(int image, String title, int visible) {
        this.image = image;
        this.title = title;
        this.visible = visible;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
