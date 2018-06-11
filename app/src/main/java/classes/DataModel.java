package classes;

public class DataModel {

    String title;
    String author;
    String description;
    String content;
    String main_image;
    String link;
    String pub_date;
    String category;

    public DataModel(String title,String author,String description,
                     String content,String main_image,String pub_date,String link) {
        this.title=title;
        this.author = author;
        this.description = description;
        this.content = content;
        this.main_image = main_image;
        this.link = link;
        this.pub_date = pub_date;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getPubDate() {
        return pub_date;
    }

    public void setPubDate(String pubdate){
        this.pub_date = pub_date;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content){this.content = content;}

    public String getMainImage() {
        return main_image;
    }

    public void setMainImage(String main_image){
        this.main_image = main_image;

    }

    public String getLink(){return link;}

    public void setLink(String link){this.link = link;}

    public String getCategory(){return category;}

    public void setCategory(String category){this.category = category;}

}