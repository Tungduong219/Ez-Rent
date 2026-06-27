package com.Group1.ez_rent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.Group1.ez_rent.database.AppDatabase;
import com.Group1.ez_rent.database.entity.UserEntity;
import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputEditText edtAccount;
    private Button btnSendCode;
    private ImageButton btnBack;
    private TextView tvBackToLogin;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        db = AppDatabase.getInstance(this);

        // Ánh xạ View
        edtAccount = findViewById(R.id.edt_account);
        btnSendCode = findViewById(R.id.btn_send_code);
        btnBack = findViewById(R.id.btn_back);
        tvBackToLogin = findViewById(R.id.tv_back_to_login);

        // Sự kiện quay lại
        btnBack.setOnClickListener(v -> finish());
        tvBackToLogin.setOnClickListener(v -> finish());

        btnSendCode.setOnClickListener(v -> handleResetPassword());
    }

    private void handleResetPassword() {
        String account = edtAccount.getText().toString().trim();

        if (account.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập Số điện thoại hoặc Email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra tài khoản trong database
        new Thread(() -> {
            // Giả định chúng ta kiểm tra theo SĐT (dựa trên cấu trúc UserEntity hiện tại)
            UserEntity user = db.userDao().getUserByPhone(account);
            
            runOnUiThread(() -> {
                if (user != null) {
                    // Trong thực tế, bước này sẽ gọi API gửi SMS/Email
                    Toast.makeText(this, "Mã xác nhận đã được gửi tới: " + account, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Tài khoản không tồn tại trên hệ thống", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}
