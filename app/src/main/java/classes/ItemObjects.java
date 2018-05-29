package classes;


import android.widget.ImageView;

public class ItemObjects {
    private String title;
    private String pubDate;
    private String photo;
    private String category;
    private String link;

    public ItemObjects(String title, String photo,String link) {
        this.title = title;
        this.photo = photo;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String photo) {
        this.link = link;
    }
}
