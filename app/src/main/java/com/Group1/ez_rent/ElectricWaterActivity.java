package com.Group1.ez_rent;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ElectricWaterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_water);

        // SỬA DÒNG NÀY: Chuyển TextView thành ImageView cho khớp XML
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Các nút khác giữ nguyên
        findViewById(R.id.btnHistory).setOnClickListener(v -> 
            Toast.makeText(this, "Mở Lịch sử", Toast.LENGTH_SHORT).show());
            
        findViewById(R.id.btnViewAllBill).setOnClickListener(v -> 
            Toast.makeText(this, "Xem toàn bộ hóa đơn", Toast.LENGTH_SHORT).show());
    }
}