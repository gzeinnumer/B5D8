package com.gzeinnumer.b5d8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.b5d8.databinding.ItemRvBinding;
import com.gzeinnumer.b5d8.model.users.UsersResponseItem;

import java.util.ArrayList;
import java.util.List;

public class RvAdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<UsersResponseItem> list;
    private List<UsersResponseItem> listFilter;
    private List<ItemRvBinding> holders;

    private int emptyLayout = -1;

    public RvAdater() {
        this.list = new ArrayList<>();
        this.listFilter = new ArrayList<>(list);
        this.holders = new ArrayList<>(list.size());
        initHolders();
    }

    public void setList(List<UsersResponseItem> list) {
        this.list = list;
        this.listFilter = new ArrayList<>(list);
        this.holders = new ArrayList<>(list.size());
        initHolders();
        notifyDataSetChanged();
    }

    private void initHolders() {
        for (int i = 0; i < list.size(); i++) {
            holders.add(null);
        }
    }

    public List<UsersResponseItem> getList() {
        return list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_EMPTY) {
            return new ViewHolderEmpty(LayoutInflater.from(parent.getContext()).inflate(emptyLayout == -1 ? R.layout.empty_item : emptyLayout, parent, false));
        } else {
            return new MyHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), GblVariabel.anim));
        if (list.size() > 0) {
            holders.set(position, ItemRvBinding.bind(((MyHolder) holder).itemBinding.getRoot()));
            ((MyHolder) holder).bind(position, list.get(position), onItemClickListener);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<UsersResponseItem> fildteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                fildteredList.addAll(listFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (UsersResponseItem item : listFilter) {
                    if (item.toString().toLowerCase().contains(filterPattern)) {
                        fildteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = fildteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            initHolders();
            notifyDataSetChanged();
        }
    };

    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() > 0 ? list.size() : 1;
    }

    private static int TYPE_NORMAL = 1;
    private static int TYPE_EMPTY = 0;

    @Override
    public int getItemViewType(int position) {
        if (list.size() > 0) {
            return TYPE_NORMAL;
        } else {
            return TYPE_EMPTY;
        }
    }

    private BaseCallBackAdapter<UsersResponseItem> onItemClickListener;

    public void setOnItemClickListener(BaseCallBackAdapter<UsersResponseItem> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public ItemRvBinding itemBinding;

        public MyHolder(@NonNull ItemRvBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
        }

        public void bind(int position, UsersResponseItem data, BaseCallBackAdapter<UsersResponseItem> onItemClickListener) {
            itemBinding.tvDebug.setText(data.getEmail() + " - "+data.getAddress().getStreet());
            if (onItemClickListener != null) {
                itemView.setOnClickListener(view -> {
                    onItemClickListener.onClick(1, position, data);
                });
            }
        }
    }
}
