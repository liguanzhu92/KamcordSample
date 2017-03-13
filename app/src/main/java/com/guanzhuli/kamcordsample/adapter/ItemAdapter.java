package com.guanzhuli.kamcordsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.guanzhuli.kamcordsample.R;
import com.guanzhuli.kamcordsample.model.Item;
import com.guanzhuli.kamcordsample.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Guanzhu Li on 3/12/2017.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> implements View.OnClickListener{
    private ArrayList<Item> mItems;
    private Context mContext;
    private OnCardItemClick mOnCardItemClick;

    /**
     * interface OnCardItemClick
     * expose position to adapter
     */
    public interface OnCardItemClick {
        void onItemClick(View view , String data);
    }

    public ItemAdapter(ArrayList<Item> items, Context context) {
        mItems = items;
        mContext = context;
        mOnCardItemClick = null;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        ItemHolder itemHolder = new ItemHolder(view);
        view.setOnClickListener(this);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        ImageLoader.getInstance().setImage(mItems.get(position).getThumbnail(), holder.mImageShot);
        holder.itemView.setTag(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnCardItemClick != null) {
            mOnCardItemClick.onItemClick(view,(String)view.getTag());
        }
    }

    public void setOnItemClickListener(OnCardItemClick listener) {
        this.mOnCardItemClick = listener;
    }
}

class ItemHolder extends RecyclerView.ViewHolder{
    ImageView mImageShot;
    public ItemHolder(View itemView) {
        super(itemView);
        mImageShot = (ImageView) itemView.findViewById(R.id.item_shot);
    }
}
