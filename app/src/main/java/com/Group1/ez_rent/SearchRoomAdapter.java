package com.Group1.ez_rent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchRoomAdapter extends RecyclerView.Adapter<SearchRoomAdapter.ViewHolder> {

    private List<Room> roomList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Room room);
    }

    public SearchRoomAdapter(List<Room> roomList, OnItemClickListener listener) {
        this.roomList = roomList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = roomList.get(position);
        Context context = holder.itemView.getContext();

        holder.imgRoom.setImageResource(room.getImageResId());
        holder.tvPrice.setText(room.getPrice());
        holder.tvTitle.setText(room.getTitle());
        holder.tvInfo.setText(room.getInfo());
        holder.tvTime.setText(room.getTime());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(room);
            }
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("TITLE_TAG", room.getTitle());
            intent.putExtra("PRICE_TAG", room.getPrice());
            intent.putExtra("ADDRESS_TAG", room.getInfo());
            intent.putExtra("IMAGE_TAG", room.getImageResId());
            context.startActivity(intent);
        });

        if (holder.btnDetail != null) {
            holder.btnDetail.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(room);
                }
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("TITLE_TAG", room.getTitle());
                intent.putExtra("PRICE_TAG", room.getPrice());
                intent.putExtra("ADDRESS_TAG", room.getInfo());
                intent.putExtra("IMAGE_TAG", room.getImageResId());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public void updateList(List<Room> newList) {
        this.roomList = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRoom;
        TextView tvPrice, tvTitle, tvInfo, tvTime, btnDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRoom = itemView.findViewById(R.id.img_room);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvInfo = itemView.findViewById(R.id.tv_info);
            tvTime = itemView.findViewById(R.id.tv_time);

            btnDetail = itemView.findViewById(R.id.btn_room_detail);
        }
    }
}