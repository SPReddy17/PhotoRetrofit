package cs.ucmo.photoretrofit;

import java.util.List;

import cs.ucmo.photoretrofit.model.Feed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlickrApi {



    String url ="?api_key&format=json&nojsoncallback=1&extras=url_s&method=flickr.photos.getRecent";

    @Headers("Content-Type: application/json")
    @GET("?api_key=ca8165cc807b66a615c294a3f7ad6261&format=json&nojsoncallback=1&extras=url_s&method=flickr.photos.getRecent")
    Call<Feed> getData();

    @Headers("Content-Type: application/json")
    @GET("?api_key=ca8165cc807b66a615c294a3f7ad6261&format=json&nojsoncallback=1&extras=url_s&method=flickr.photos.search")
    Call<Feed> getSearch(@Query("text") String search);



}
