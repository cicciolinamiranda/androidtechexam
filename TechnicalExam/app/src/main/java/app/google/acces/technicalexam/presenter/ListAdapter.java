package app.google.acces.technicalexam.presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.google.acces.technicalexam.R;
import app.google.acces.technicalexam.model.ItemModel;
import app.google.acces.technicalexam.util.Util;

/**
 * Created by cicciolina on 2/1/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{

    private List<ItemModel> dataList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName, description;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.itemName);
            description = view.findViewById(R.id.description);
            img = view.findViewById(R.id.imageView);
        }
    }

    public ListAdapter(Context mContext, List<ItemModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemName.setText("Item Name: "+dataList.get(position).getName());
        holder.description.setText("Description: "+dataList.get(position).getDesc());


        if(dataList.get(position).getPhotoPath() != null && !dataList.get(position).getPhotoPath().isEmpty()) {
            holder.img.setImageBitmap(Util.getInstance().resizeBitmap(dataList.get(position).getPhotoPath() , 100, 100));
        }else{
            holder.img.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_default_image));
        }



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
