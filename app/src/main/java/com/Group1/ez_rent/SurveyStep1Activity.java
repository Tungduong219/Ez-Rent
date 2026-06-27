package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class SurveyStep1Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnNext;
    private List<MaterialButton> optionButtons = new ArrayList<>();
    private String selectedOption = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_step1);

        // Ánh xạ
        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btn_next);
        
        optionButtons.add(findViewById(R.id.opt_phong_tro));
        optionButtons.add(findViewById(R.id.opt_nha_nguyen_can));
        optionButtons.add(findViewById(R.id.opt_chung_cu));
        optionButtons.add(findViewById(R.id.opt_chung_cu_mini));
        optionButtons.add(findViewById(R.id.opt_khac));

        // Xử lý sự kiện chọn Option
        for (MaterialButton btn : optionButtons) {
            btn.setOnClickListener(v -> handleOptionSelect(btn));
        }

        btnBack.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {
            if (selectedOption.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn một loại hình phòng", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SurveyStep1Activity.this, SurveyStep2Activity.class);
                intent.putExtra("room_type", selectedOption);
                startActivity(intent);
            }
        });
    }

    private void handleOptionSelect(MaterialButton clickedBtn) {
        // Reset tất cả các nút về trạng thái chưa chọn
        for (MaterialButton btn : optionButtons) {
            btn.setStrokeColorResource(R.color.color_border_light);
            btn.setTextColor(ContextCompat.getColor(this, R.color.color_darkslategray));
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }

        // Highlight nút được chọn
        clickedBtn.setStrokeColorResource(R.color.color_steelblue);
        clickedBtn.setTextColor(ContextCompat.getColor(this, R.color.color_steelblue));
        clickedBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.background_light));
        
        selectedOption = clickedBtn.getText().toString();
    }
}
