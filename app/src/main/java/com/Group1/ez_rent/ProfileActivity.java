package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private LinearLayout btnNavHome, btnNavKhamPha, btnNavMessages, btnCenterRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnNavHome = findViewById(R.id.btn_profile_nav_home);
        btnNavKhamPha = findViewById(R.id.btn_profile_nav_kham_pha);
        btnNavMessages = findViewById(R.id.btn_profile_nav_messages);
        btnCenterRoom = findViewById(R.id.btn_profile_center_room);

        if (btnNavHome != null) {
            btnNavHome.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        if (btnNavKhamPha != null) {
            btnNavKhamPha.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, SearchActivity.class);
                startActivity(intent);
            });
        }

        if (btnCenterRoom != null) {
            btnCenterRoom.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, MyRoomActivity.class);
                startActivity(intent);
            });
        }
    }
}