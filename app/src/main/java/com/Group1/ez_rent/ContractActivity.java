package com.Group1.ez_rent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ContractActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_detail);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Thêm sự kiện cho nút tải PDF ở đây nếu cần
        findViewById(R.id.btnFullDownload).setOnClickListener(v -> {
            // Xử lý logic tải file PDF
        });
    }
}