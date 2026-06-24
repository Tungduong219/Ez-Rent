package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Group1.ez_rent.database.AppDatabase;
import com.Group1.ez_rent.database.entity.UserEntity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterPhoneActivity extends AppCompatActivity {

    private TextInputEditText edtPhone;
    private Button btnContinue;
    private TextView tvLoginNow;
    private android.widget.ImageButton btnBack;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);

        db = AppDatabase.getInstance(this);

        // Ánh xạ View
        edtPhone = findViewById(R.id.phone_number);
        btnContinue = findViewById(R.id.btn_continue);
        tvLoginNow = findViewById(R.id.tv_login_now);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> handleContinue());

        tvLoginNow.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterPhoneActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void handleContinue() {
        String phone = edtPhone.getText().toString().trim();

        if (phone.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.length() < 10) {
            Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xem số điện thoại đã tồn tại chưa
        new Thread(() -> {
            UserEntity user = db.userDao().getUserByPhone(phone);
            runOnUiThread(() -> {
                if (user != null) {
                    Toast.makeText(this, "Số điện thoại này đã được đăng ký tài khoản!", Toast.LENGTH_LONG).show();
                } else {
                    // Nếu chưa tồn tại, chuyển sang bước tiếp theo
                    Intent intent = new Intent(RegisterPhoneActivity.this, RegisterDetailsActivity.class);
                    intent.putExtra("phone_number", phone);
                    startActivity(intent);
                }
            });
        }).start();
    }
}
