package android.example.mytodo;

import android.example.mytodo.databinding.WorkListItemBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends ListAdapter<Work, RecyclerViewAdapter.ViewHolder> {

    public RecyclerViewAdapter(){
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Work> CALLBACK = new DiffUtil.ItemCallback<Work>() {
        @Override
        public boolean areItemsTheSame(@NonNull Work oldItem, @NonNull Work newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Work oldItem, @NonNull Work newItem) {
            return oldItem.getHeading().equals(newItem.getHeading()) && oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Work work = getItem(position);
        holder.binding.heading.setText(work.getHeading());
        holder.binding.description.setText(work.getDescription());
    }

    public Work getWork(int position){
        return getItem(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        WorkListItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = WorkListItemBinding.bind(itemView);
        }
    }
}
