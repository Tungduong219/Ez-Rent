package com.Group1.ez_rent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgMain, btnBack;
    private TextView tvTitle, tvPrice, tvAddress;
    private TextView btnChat, btnBook;
    private View btnAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgMain = findViewById(R.id.img_main_detail);
        btnBack = findViewById(R.id.btn_back_detail);
        tvTitle = findViewById(R.id.tv_title_detail);
        tvPrice = findViewById(R.id.tv_price_detail);
        tvAddress = findViewById(R.id.tv_address_detail);
        btnChat = findViewById(R.id.btn_chat_detail);
        btnBook = findViewById(R.id.btn_book_detail);

        btnAlert = findViewById(R.id.layout_fixed_bottom_detail) != null 
            ? ((android.view.ViewGroup) findViewById(R.id.layout_fixed_bottom_detail)).getChildAt(0) 
            : null;

        if (getIntent() != null && getIntent().hasExtra("TITLE_TAG")) {
            String title = getIntent().getStringExtra("TITLE_TAG");
            String price = getIntent().getStringExtra("PRICE_TAG");
            String address = getIntent().getStringExtra("ADDRESS_TAG");
            int imageRes = getIntent().getIntExtra("IMAGE_TAG", R.drawable.img_back_login);

            if (tvTitle != null) tvTitle.setText(title);

            if (tvPrice != null) {
                if (price != null && (price.contains("tháng") || price.contains("Thường"))) {
                    tvPrice.setText(price);
                } else {
                    tvPrice.setText(price + " / tháng");
                }
            }

            if (tvAddress != null) {
                if (address != null && address.contains("📍")) {
                    tvAddress.setText(address);
                } else {
                    tvAddress.setText("📍 " + address);
                }
            }

            if (imgMain != null) imgMain.setImageResource(imageRes);
        }

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (btnChat != null) {
            btnChat.setOnClickListener(v -> 
                Toast.makeText(DetailActivity.this, "Mở chức năng Chat với chủ nhà!", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnBook != null) {
            btnBook.setOnClickListener(v -> 
                Toast.makeText(DetailActivity.this, "Hệ thống đang đặt lịch xem phòng...", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnAlert != null) {
            btnAlert.setOnClickListener(v -> 
                Toast.makeText(DetailActivity.this, "Báo cáo sự cố", Toast.LENGTH_SHORT).show()
            );
        }
    }
}