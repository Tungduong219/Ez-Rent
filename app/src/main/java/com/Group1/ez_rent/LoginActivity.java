package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Group1.ez_rent.database.AppDatabase;
import com.Group1.ez_rent.database.entity.UserEntity;
import com.Group1.ez_rent.utils.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtPhone, edtPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private AppDatabase db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        session = new SessionManager(this);
        // Kiểm tra nếu đã đăng nhập thì vào thẳng Main
        if (session.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.dang_nhap);

        db = AppDatabase.getInstance(this);

        // Ánh xạ View
        edtPhone = findViewById(R.id.phone_number);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register_now);
        TextView tvForgotPassword = findViewById(R.id.tv_forgot_password);

        // Tạo dữ liệu mẫu nếu DB trống
        createSampleUser();

        btnLogin.setOnClickListener(v -> handleLogin());

        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterPhoneActivity.class);
            startActivity(intent);
        });
    }

    private void createSampleUser() {
        new Thread(() -> {
            UserEntity existingUser = db.userDao().getUserByPhone("0123456789");
            if (existingUser == null) {
                UserEntity admin = new UserEntity("0123456789", "123456", "Admin EzRent", "Hà Nội", "NGUOI_THUE", null);
                db.userDao().insertUser(admin);
            }
        }).start();
    }

    private void handleLogin() {
        String phone = edtPhone.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            UserEntity user = db.userDao().getUserByPhone(phone);
            runOnUiThread(() -> {
                if (user != null && user.getPassword().equals(password)) {
                    // Lưu phiên đăng nhập
                    session.createLoginSession(phone);
                    
                    Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, SearchIntroActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Số điện thoại hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}
