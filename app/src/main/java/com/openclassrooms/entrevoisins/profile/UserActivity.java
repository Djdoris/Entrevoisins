package com.openclassrooms.entrevoisins.profile;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class UserActivity extends AppCompatActivity {

    public static final String DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis iaculis ex, at dictum ante. Vestibulum ac velit in sem elementum aliquam in non tortor. Cras quis aliquam purus.\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        Neighbour neighbour = getIntent().getParcelableExtra("selectedNeighbour");
        FloatingActionButton changeFavorites = findViewById(R.id.changeFavorites);

        TextView UserName = findViewById(R.id.user_name);
        UserName.setText(neighbour.getName());

        TextView UserNameSecond = findViewById(R.id.UserName);
        UserNameSecond.setText(neighbour.getName());

        TextView Phone = findViewById(R.id.UserPhone);
        Phone.setText("07 83 82 98 16");
        TextView Facebook = findViewById(R.id.UserFacebook);
        Facebook.setText(""+neighbour.getName()+"Fbk");
        TextView Localisation = findViewById(R.id.UserLocalisation);
        Localisation.setText("5 rue Rataud 75005 Paris");
        TextView Description = findViewById(R.id.Description);
        Description.setText(DESCRIPTION);

        ImageView mAvatar = findViewById(R.id.neighbours_avatar);
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mAvatar);

        ImageButton mBackButton = findViewById(R.id.image_button_back);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        changeFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DI.getNeighbourApiService().setFavorites(neighbour);
            }
        });
    }
}
