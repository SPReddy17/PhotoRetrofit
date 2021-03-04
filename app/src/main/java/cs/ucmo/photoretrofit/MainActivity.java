package cs.ucmo.photoretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cs.ucmo.photoretrofit.model.Feed;
import cs.ucmo.photoretrofit.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private static final String BASE_URL = "https://api.flickr.com/services/rest/";


    private RecyclerView mRecyclerView;

    private static final String API_KEY = "ca8165cc807b66a615c294a3f7ad6261";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


   private List<Photo> photoList;

   private List<Photo> searchList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FlickrApi flickrApi = retrofit.create(FlickrApi.class);


        Call<Feed> call = flickrApi.getData();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {

                Log.d(TAG, "onResponse: Server response " + response.toString());
                Log.d(TAG, "onResponse: received info "+response.body().toString());
                 photoList = response.body().getPhotos().getPhoto();

                for(int i = 0 ; i < photoList.size();i++){

                    Log.d(TAG, "onResponse: \n"+
                            " id : " + photoList.get(i).getUrl_s()+"\n"+
                            "-------------------------------------------");

                    mRecyclerView = findViewById(R.id.recyclerview);
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(photoList);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: something went wrong..."+t.getMessage() );
                Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        MenuItem searchItem = menu.findItem(R.id.search_bar);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "QueryTextSubmit: " + s);

                updateItems(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "QueryTextChange: " + s);


                return false;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

        }
        return super.onOptionsItemSelected(item);
    }

    private void updateItems(String s) {


        FlickrApi flickrApi = retrofit.create(FlickrApi.class);


        Call<Feed> call = flickrApi.getSearch(s);

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {

                Log.d(TAG, "onResponse: Server response " + response.toString());
                Log.d(TAG, "onResponse: received info "+response.body().toString());
                searchList = response.body().getPhotos().getPhoto();

                for(int i = 0 ; i < searchList.size();i++){

                    Log.d(TAG, "onResponse: \n"+
                            " id : " + searchList.get(i).getUrl_s()+"\n"+
                            "-------------------------------------------");

                    mRecyclerView = findViewById(R.id.recyclerview);
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(searchList);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: something went wrong..."+t.getMessage() );
                Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

