package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, SuccessPaymentActivity.class);
            startActivity(intent);
            finish(); // Đóng trang thanh toán để người dùng không quay lại được
        });
    }
}