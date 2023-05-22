package br.edu.ifsp.dmos5.app13_listatarefas.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.dmos5.app13_listatarefas.R;
import br.edu.ifsp.dmos5.app13_listatarefas.model.entities.Task;
import br.edu.ifsp.dmos5.app13_listatarefas.mvp.MainMVP;
import br.edu.ifsp.dmos5.app13_listatarefas.view.RecyclerViewItemClickListener;

public class ItemPocketRecyclerAdapter extends
        RecyclerView.Adapter<ItemPocketRecyclerAdapter.ViewHolder> {

    private Context context;
    private MainMVP.Presenter presenter;
    private List<Task> data;
    private static RecyclerViewItemClickListener clickListener;

    public ItemPocketRecyclerAdapter(Context context, List<Task> data, MainMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = data.get(position);
        holder.titleTextView.setText(task.getTitle());
        if(task.isFavorite()){
            holder.favoriteImageView.setColorFilter(context.getColor(R.color.yellow));
        }else{
            holder.favoriteImageView.setColorFilter(context.getColor(R.color.gray));
        }
        holder.favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starClick(task);
            }
        });

        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penClick(task);
            }
        });

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trashClick(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }

    private void penClick(Task task){
        presenter.openDetails(task);
        notifyDataSetChanged();
    }

    private void trashClick(Task task){
        presenter.deleteTask(task);
        notifyDataSetChanged();
    }
    private void starClick(Task task){
        presenter.favoriteTask(task);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public TextView titleTextView;
        public ImageView favoriteImageView;
        public ImageView editImageView;
        public ImageView deleteImageView;

        public ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_title_listitem);
            favoriteImageView = itemView.findViewById(R.id.image_favorite_listitem);
            editImageView = itemView.findViewById(R.id.image_edit_listitem);
            deleteImageView = itemView.findViewById(R.id.image_delete_listitem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
