package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SearchIntroActivity extends AppCompatActivity {

    private Button btnStartSearch, btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_intro);

        btnStartSearch = findViewById(R.id.btn_start_search);
        btnSkip = findViewById(R.id.btn_skip);

        // Chuyển sang màn 1 của phần thu thập thông tin tìm kiếm
        btnStartSearch.setOnClickListener(v -> {
            Intent intent = new Intent(SearchIntroActivity.this, SurveyStep1Activity.class);
            startActivity(intent);
        });

        // Bỏ qua và vào thẳng Trang chủ
        btnSkip.setOnClickListener(v -> {
            Intent intent = new Intent(SearchIntroActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
