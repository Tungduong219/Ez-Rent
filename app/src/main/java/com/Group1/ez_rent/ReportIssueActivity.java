package com.Group1.ez_rent;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ReportIssueActivity extends AppCompatActivity {

    private TextView tvTypeWater, tvTypeFurniture, tvTypeDevice, tvTypeOther;
    private TextView tvPriorityNormal, tvPriorityUrgent;
    private FrameLayout layoutUpload;
    private LinearLayout layoutUploadHint;
    private ImageView ivPreview;
    private MaterialButton btnSubmit;

    // BIẾN QUAN TRỌNG: Theo dõi xem người dùng đã chọn ảnh chưa
    private boolean isImageUploaded = false;

    // Trình khởi chạy để chọn ảnh từ thư viện
    private final ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    isImageUploaded = true; // Đánh dấu là ĐÃ CÓ ẢNH
                    layoutUploadHint.setVisibility(View.GONE);
                    ivPreview.setVisibility(View.VISIBLE);
                    ivPreview.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_issue);

        initViews();
        setupListeners();
    }

    private void initViews() {
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        tvTypeWater = findViewById(R.id.tvTypeWater);
        tvTypeFurniture = findViewById(R.id.tvTypeFurniture);
        tvTypeDevice = findViewById(R.id.tvTypeDevice);
        tvTypeOther = findViewById(R.id.tvTypeOther);

        layoutUpload = findViewById(R.id.layoutUpload);
        layoutUploadHint = findViewById(R.id.layoutUploadHint);
        ivPreview = findViewById(R.id.ivPreview);

        tvPriorityNormal = findViewById(R.id.tvPriorityNormal);
        tvPriorityUrgent = findViewById(R.id.tvPriorityUrgent);

        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupListeners() {
        tvTypeWater.setOnClickListener(v -> selectIssueType(tvTypeWater, "Điện / Nước"));
        tvTypeFurniture.setOnClickListener(v -> selectIssueType(tvTypeFurniture, "Nội thất (Giường, tủ)"));
        tvTypeDevice.setOnClickListener(v -> selectIssueType(tvTypeDevice, "Thiết bị (Điều hòa)"));
        tvTypeOther.setOnClickListener(v -> selectIssueType(tvTypeOther, "Khác"));

        layoutUpload.setOnClickListener(v -> mGetContent.launch("image/*"));

        tvPriorityNormal.setOnClickListener(v -> selectPriority(true));
        tvPriorityUrgent.setOnClickListener(v -> selectPriority(false));

        // XỬ LÝ NÚT GỬI KÈM ĐIỀU KIỆN RÀNG BUỘC
        btnSubmit.setOnClickListener(v -> {
            // Kiểm tra: Nếu chưa có ảnh thì chặn lại
            if (!isImageUploaded) {
                // Hiện thông báo nhắc nhở
                Toast.makeText(this, "Vui lòng tải lên Hình ảnh / Video hiện trạng!", Toast.LENGTH_SHORT).show();
                return; // Lệnh return này sẽ bắt code dừng ngay tại đây, không chạy xuống dưới
            }

            // Nếu vượt qua cửa kiểm tra (đã có ảnh), thực hiện đổi trạng thái nút
            btnSubmit.setText("ĐÃ GỬI YÊU CẦU XỬ LÝ");
            btnSubmit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#10B981")));
            btnSubmit.setEnabled(false);

            // Hiện thông báo thành công
            Toast.makeText(this, "Gửi báo cáo thành công!", Toast.LENGTH_SHORT).show();
        });
    }

    private void selectIssueType(TextView selectedTv, String rawText) {
        TextView[] allTypes = {tvTypeWater, tvTypeFurniture, tvTypeDevice, tvTypeOther};
        String[] originalTexts = {"Điện / Nước", "Nội thất (Giường, tủ)", "Thiết bị (Điều hòa)", "Khác"};

        for (int i = 0; i < allTypes.length; i++) {
            allTypes[i].setBackgroundResource(R.drawable.bg_issue_unselected);
            allTypes[i].setTextColor(Color.parseColor("#757575"));
            allTypes[i].setText(originalTexts[i]);
            allTypes[i].setTypeface(null, android.graphics.Typeface.NORMAL);
        }

        selectedTv.setBackgroundResource(R.drawable.bg_issue_selected);
        selectedTv.setTextColor(Color.parseColor("#5D9CEC"));
        selectedTv.setText("✓ " + rawText);
        selectedTv.setTypeface(null, android.graphics.Typeface.BOLD);
    }

    private void selectPriority(boolean isNormal) {
        if (isNormal) {
            tvPriorityNormal.setBackgroundResource(R.drawable.bg_edit_text);
            tvPriorityNormal.setTextColor(Color.parseColor("#1A1A1A"));
            tvPriorityNormal.setTypeface(null, android.graphics.Typeface.BOLD);

            tvPriorityUrgent.setBackground(null);
            tvPriorityUrgent.setTextColor(Color.parseColor("#757575"));
            tvPriorityUrgent.setTypeface(null, android.graphics.Typeface.NORMAL);
        } else {
            tvPriorityUrgent.setBackgroundResource(R.drawable.bg_edit_text);
            tvPriorityUrgent.setTextColor(Color.parseColor("#1A1A1A"));
            tvPriorityUrgent.setTypeface(null, android.graphics.Typeface.BOLD);

            tvPriorityNormal.setBackground(null);
            tvPriorityNormal.setTextColor(Color.parseColor("#757575"));
            tvPriorityNormal.setTypeface(null, android.graphics.Typeface.NORMAL);
        }
    }
}