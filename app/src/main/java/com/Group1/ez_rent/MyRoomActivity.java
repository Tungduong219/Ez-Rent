package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MyRoomActivity extends AppCompatActivity {

    private ImageView btnBack;
    private MaterialButton btnViewBill, btnPay;
    private TextView btnReportIssue, btnHistory, tvViewContractDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);

        initViews();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnViewBill = findViewById(R.id.btnViewBill);
        btnPay = findViewById(R.id.btnPay);
        btnReportIssue = findViewById(R.id.btnReportIssue);
        btnHistory = findViewById(R.id.btnHistory); // Đây là ID chúng ta vừa thêm vào XML
        tvViewContractDetail = findViewById(R.id.tvViewContractDetail);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        // Hóa đơn
        btnViewBill.setOnClickListener(v -> {
            startActivity(new Intent(MyRoomActivity.this, ElectricWaterActivity.class));
        });

        btnPay.setOnClickListener(v -> {
            startActivity(new Intent(MyRoomActivity.this, PaymentActivity.class));
        });

        // TÍCH HỢP: Xem chi tiết hợp đồng
        tvViewContractDetail.setOnClickListener(v -> {
            Intent intent = new Intent(MyRoomActivity.this, ContractActivity.class);
            startActivity(intent);
        });

        // Sự cố & Lịch sử
        // Xử lý Sự cố (Mở trang Báo cáo)
        btnReportIssue.setOnClickListener(v -> {
            Intent intent = new Intent(MyRoomActivity.this, ReportIssueActivity.class);
            startActivity(intent);
        });

        btnHistory.setOnClickListener(v -> 
            Toast.makeText(this, "Mở lịch sử", Toast.LENGTH_SHORT).show()
        );
    }
}