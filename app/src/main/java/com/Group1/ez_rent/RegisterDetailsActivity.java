package com.Group1.ez_rent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.Group1.ez_rent.database.AppDatabase;
import com.Group1.ez_rent.database.entity.UserEntity;
import com.Group1.ez_rent.utils.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterDetailsActivity extends AppCompatActivity {

    private TextInputEditText edtFullName, edtPassword, edtConfirmPassword;
    private Button btnSubmit;
    private ImageButton btnBack;
    private TextView tvLoginLink;
    private String phoneNumber;
    private AppDatabase db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        db = AppDatabase.getInstance(this);
        session = new SessionManager(this);

        // Nhận SĐT từ màn hình trước
        phoneNumber = getIntent().getStringExtra("phone_number");

        // Ánh xạ
        edtFullName = findViewById(R.id.edt_full_name);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        btnSubmit = findViewById(R.id.btn_submit);
        btnBack = findViewById(R.id.btn_back);
        tvLoginLink = findViewById(R.id.tv_login_link);

        btnBack.setOnClickListener(v -> finish());
        tvLoginLink.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterDetailsActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        btnSubmit.setOnClickListener(v -> handleRegister());
    }

    private void handleRegister() {
        String fullName = edtFullName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (fullName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu vào Database
        new Thread(() -> {
            UserEntity newUser = new UserEntity(phoneNumber, password, fullName, "", "KHACH", null);
            db.userDao().insertUser(newUser);
            
            runOnUiThread(() -> {
                // Tự động tạo phiên đăng nhập ngay sau khi đăng ký
                session.createLoginSession(phoneNumber);
                
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterDetailsActivity.this, SearchIntroActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            });
        }).start();
    }
}
