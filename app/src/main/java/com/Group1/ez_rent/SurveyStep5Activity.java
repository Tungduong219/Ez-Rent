package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.card.MaterialCardView;

public class SurveyStep5Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnFinish;
    private MaterialCardView cardYes, cardNo;
    private int needsSupport = -1; // -1: Chưa chọn, 1: Có, 0: Không

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_step5);

        btnBack = findViewById(R.id.btn_back);
        btnFinish = findViewById(R.id.btn_finish);
        cardYes = findViewById(R.id.card_yes);
        cardNo = findViewById(R.id.card_no);

        cardYes.setOnClickListener(v -> selectOption(1));
        cardNo.setOnClickListener(v -> selectOption(0));

        btnBack.setOnClickListener(v -> finish());

        btnFinish.setOnClickListener(v -> {
            if (needsSupport == -1) {
                Toast.makeText(this, "Vui lòng chọn một phương án", Toast.LENGTH_SHORT).show();
            } else {
                saveSurveyAndFinish();
            }
        });
    }

    private void selectOption(int option) {
        needsSupport = option;
        
        // Reset styles
        cardYes.setStrokeColor(ContextCompat.getColor(this, R.color.color_border_light));
        cardNo.setStrokeColor(ContextCompat.getColor(this, R.color.color_border_light));
        cardYes.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
        cardNo.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));

        // Highlight selected
        if (option == 1) {
            cardYes.setStrokeColor(ContextCompat.getColor(this, R.color.color_steelblue));
            cardYes.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_light));
        } else {
            cardNo.setStrokeColor(ContextCompat.getColor(this, R.color.color_steelblue));
            cardNo.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_light));
        }
    }

    private void saveSurveyAndFinish() {
        // Lấy toàn bộ dữ liệu truyền từ các bước trước
        Bundle data = getIntent().getExtras();
        if (data == null) return;

        // Trong một dự án thực tế, bạn sẽ gọi DAO ở đây để lưu vào Database.
        // Ví dụ: UserPreferenceEntity pref = new UserPreferenceEntity(...);
        // db.userPreferenceDao().insert(pref);

        Toast.makeText(this, "Khảo sát thành công! Chào mừng bạn đến với EzRent", Toast.LENGTH_LONG).show();

        // Xóa lịch sử và vào Trang chủ
        Intent intent = new Intent(SurveyStep5Activity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
