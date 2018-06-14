package app.google.acces.technicalexam.model;

/**
 * Created by cicciolina on 2/1/18.
 */

public class ItemModel {

    private String name;
    private String desc;
    private String photoPath;

    public ItemModel(String name, String desc, String photoPath) {
        this.name = name;
        this.desc = desc;
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
