package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyStep2Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnNext;
    private Spinner spinnerCity, spinnerDistrict;
    private TextView lblDistrict;
    
    private Map<String, List<String>> locationData;
    private List<String> cities;
    private String roomType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_step2);

        // Nhận dữ liệu từ Step 1
        roomType = getIntent().getStringExtra("room_type");

        // Khởi tạo dữ liệu mẫu
        initLocationData();

        // Ánh xạ
        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btn_next);
        spinnerCity = findViewById(R.id.spinner_city);
        spinnerDistrict = findViewById(R.id.spinner_district);
        lblDistrict = findViewById(R.id.lbl_district);

        // Ban đầu ẩn phần Quận/Huyện
        lblDistrict.setVisibility(View.GONE);
        spinnerDistrict.setVisibility(View.GONE);

        // Cấu hình Spinner Tỉnh/Thành phố
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        // Xử lý sự kiện khi chọn Tỉnh/Thành phố
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Nếu chọn mục mặc định "Chọn Tỉnh..." thì ẩn Quận/Huyện
                    lblDistrict.setVisibility(View.GONE);
                    spinnerDistrict.setVisibility(View.GONE);
                } else {
                    // Nếu đã chọn Tỉnh thực sự
                    String selectedCity = cities.get(position);
                    updateDistrictSpinner(selectedCity);
                    
                    // Hiện phần Quận/Huyện với hiệu ứng mờ dần (Fade In)
                    if (spinnerDistrict.getVisibility() == View.GONE) {
                        showDistrictWithAnimation();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnBack.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {
            if (spinnerCity.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Vui lòng chọn Tỉnh / Thành phố", Toast.LENGTH_SHORT).show();
                return;
            }
            
            String city = spinnerCity.getSelectedItem().toString();
            String district = spinnerDistrict.getSelectedItem().toString();
            
            Toast.makeText(this, "Đã lưu khu vực: " + district + ", " + city, Toast.LENGTH_SHORT).show();
            
            Intent intent = new Intent(SurveyStep2Activity.this, SurveyStep3Activity.class);
            // Truyền toàn bộ dữ liệu tích lũy
            if (getIntent().getExtras() != null) {
                intent.putExtras(getIntent().getExtras());
            }
            intent.putExtra("city", city);
            intent.putExtra("district", district);
            startActivity(intent);
        });
    }

    private void showDistrictWithAnimation() {
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500); // 0.5 giây
        
        lblDistrict.setVisibility(View.VISIBLE);
        spinnerDistrict.setVisibility(View.VISIBLE);
        
        lblDistrict.startAnimation(fadeIn);
        spinnerDistrict.startAnimation(fadeIn);
    }

    private void initLocationData() {
        locationData = new HashMap<>();
        cities = new ArrayList<>();

        cities.add("--- Chọn Tỉnh / Thành phố ---"); // Mục mặc định

        cities.add("Hà Nội");
        List<String> hnDistricts = new ArrayList<>();
        hnDistricts.add("Quận Cầu Giấy");
        hnDistricts.add("Quận Đống Đa");
        hnDistricts.add("Quận Nam Từ Liêm");
        hnDistricts.add("Quận Thanh Xuân");
        locationData.put("Hà Nội", hnDistricts);

        cities.add("TP. Hồ Chí Minh");
        List<String> hcmDistricts = new ArrayList<>();
        hcmDistricts.add("Quận 1");
        hcmDistricts.add("Quận 7");
        hcmDistricts.add("Quận Bình Thạnh");
        hcmDistricts.add("Quận Thủ Đức");
        locationData.put("TP. Hồ Chí Minh", hcmDistricts);

        cities.add("Đà Nẵng");
        List<String> dnDistricts = new ArrayList<>();
        dnDistricts.add("Quận Hải Châu");
        dnDistricts.add("Quận Liên Chiểu");
        dnDistricts.add("Quận Ngũ Hành Sơn");
        locationData.put("Đà Nẵng", dnDistricts);
    }

    private void updateDistrictSpinner(String city) {
        List<String> districts = locationData.get(city);
        if (districts != null) {
            ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, 
                    android.R.layout.simple_spinner_item, districts);
            districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDistrict.setAdapter(districtAdapter);
        }
    }
}
