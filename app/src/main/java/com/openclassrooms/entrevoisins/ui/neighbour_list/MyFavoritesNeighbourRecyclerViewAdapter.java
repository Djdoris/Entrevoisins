package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.profile.UserActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoritesNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoritesNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFavorites;

    public MyFavoritesNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mFavorites = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mFavorites.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mNeighbourAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDescriptionIntent = new Intent(v.getContext(), UserActivity.class);
                toDescriptionIntent.putExtra("selectedNeighbour", neighbour);
                v.getContext().startActivity(toDescriptionIntent);
            }
        });

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });

        holder.mNeighbourName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDescriptionIntent = new Intent(v.getContext(), UserActivity.class);
                toDescriptionIntent.putExtra("selectedNeighbour", neighbour);
                v.getContext().startActivity(toDescriptionIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
