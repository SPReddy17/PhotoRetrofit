package cs.ucmo.photoretrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cs.ucmo.photoretrofit.model.Photo;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RequestHolder> {



    private List<Photo> photoList;

    public RecyclerViewAdapter(List<Photo> photoList){
        this.photoList =photoList;

    }



    @NonNull
    @Override
    public RecyclerViewAdapter.RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_images,parent,false);
       RequestHolder requestHolder = new RequestHolder(view);
        return requestHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RequestHolder holder, int position) {


        Photo photo = photoList.get(position);
        holder.BindPhotoItem(photo);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }




    public class RequestHolder extends RecyclerView.ViewHolder{

        ImageView image;
        RelativeLayout parenLayout;
        public RequestHolder(@NonNull View itemView) {
            super(itemView);



           image=  itemView.findViewById(R.id.imageview);
           parenLayout =itemView.findViewById(R.id.parent_layout);

        }
        public void BindPhotoItem(Photo photo){

            Picasso.get().load(photo.getUrl_s()).into(image);

        }
    }
}
