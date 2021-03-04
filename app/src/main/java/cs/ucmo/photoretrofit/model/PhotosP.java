package cs.ucmo.photoretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotosP {

    @SerializedName("total")
    @Expose
    private String total;


    @SerializedName("photo")
    @Expose
    private ArrayList<Photo> photo;


    public ArrayList<Photo>
    getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<Photo> photo) {
        this.photo = photo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PhotosP{" +
                "total='" + total + '\'' +
                ", photo=" + photo +
                '}';
    }
}
