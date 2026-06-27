package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class SurveyStep3Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnNext;
    private List<MaterialButton> optionButtons = new ArrayList<>();
    private String selectedPriceRange = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_step3);

        // Ánh xạ
        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btn_next);
        
        optionButtons.add(findViewById(R.id.opt_1_2));
        optionButtons.add(findViewById(R.id.opt_2_3));
        optionButtons.add(findViewById(R.id.opt_3_45));
        optionButtons.add(findViewById(R.id.opt_45_6));
        optionButtons.add(findViewById(R.id.opt_6_8));
        optionButtons.add(findViewById(R.id.opt_8_10));
        optionButtons.add(findViewById(R.id.opt_10plus));

        for (MaterialButton btn : optionButtons) {
            btn.setOnClickListener(v -> handleOptionSelect(btn));
        }

        btnBack.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {
            if (selectedPriceRange.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn một khoảng giá", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SurveyStep3Activity.this, SurveyStep4Activity.class);
                // Truyền dữ liệu tích lũy
                intent.putExtras(getIntent().getExtras());
                intent.putExtra("price_range", selectedPriceRange);
                startActivity(intent);
            }
        });
    }

    private void handleOptionSelect(MaterialButton clickedBtn) {
        for (MaterialButton btn : optionButtons) {
            btn.setStrokeColorResource(R.color.color_border_light);
            btn.setTextColor(ContextCompat.getColor(this, R.color.color_darkslategray));
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }

        clickedBtn.setStrokeColorResource(R.color.color_steelblue);
        clickedBtn.setTextColor(ContextCompat.getColor(this, R.color.color_steelblue));
        clickedBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.background_light));
        
        selectedPriceRange = clickedBtn.getText().toString();
    }
}
