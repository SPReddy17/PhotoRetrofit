package cs.ucmo.photoretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Feed {
    @SerializedName("photos")
    @Expose
    private PhotosP photos;


    @Override
    public String toString() {
        return "Feed{" +
                "photos=" + photos +
                '}';
    }

    public PhotosP getPhotos() {
        return photos;
    }

    public void setPhotos(PhotosP photos) {
        this.photos = photos;
    }
}
