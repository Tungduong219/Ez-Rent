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

public class SurveyStep4Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnNext;
    private List<MaterialButton> optionButtons = new ArrayList<>();
    private List<String> selectedAmenities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_step4);

        // Ánh xạ
        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btn_next);
        
        // Thêm các nút vào danh sách quản lý
        int[] ids = {
            R.id.opt_tu_quan_ao, R.id.opt_nong_lanh, R.id.opt_giuong,
            R.id.opt_may_giat, R.id.opt_dieu_hoa, R.id.opt_ve_sinh,
            R.id.opt_khong_chung_chu, R.id.opt_tu_lanh, R.id.opt_ke_bep, R.id.opt_ban_cong
        };

        for (int id : ids) {
            MaterialButton btn = findViewById(id);
            optionButtons.add(btn);
            btn.setOnClickListener(v -> toggleAmenity(btn));
        }

        btnBack.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {
            if (selectedAmenities.isEmpty()) {
                Toast.makeText(this, "Hãy chọn ít nhất một tiện ích", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SurveyStep4Activity.this, SurveyStep5Activity.class);
                // Truyền dữ liệu tích lũy từ các bước 1, 2, 3
                intent.putExtras(getIntent().getExtras());
                // Truyền thêm dữ liệu của bước 4 (tiện ích)
                intent.putExtra("amenities", selectedAmenities.toString());
                startActivity(intent);
            }
        });
    }

    private void toggleAmenity(MaterialButton btn) {
        String name = btn.getText().toString();
        if (selectedAmenities.contains(name)) {
            // Nếu đã chọn thì bỏ chọn
            selectedAmenities.remove(name);
            btn.setStrokeColorResource(R.color.color_border_light);
            btn.setTextColor(ContextCompat.getColor(this, R.color.color_darkslategray));
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        } else {
            // Nếu chưa chọn thì thêm vào list và đổi màu
            selectedAmenities.add(name);
            btn.setStrokeColorResource(R.color.color_steelblue);
            btn.setTextColor(ContextCompat.getColor(this, R.color.color_steelblue));
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.background_light));
        }
    }
}
