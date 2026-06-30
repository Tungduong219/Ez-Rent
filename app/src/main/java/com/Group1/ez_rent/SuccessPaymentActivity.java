package com.Group1.ez_rent; 

import android.content.Intent; 
import android.os.Bundle; 
import androidx.appcompat.app.AppCompatActivity; 

public class SuccessPaymentActivity extends AppCompatActivity { 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_success_payment); 

        Runnable goHome = () -> { 
            Intent intent = new Intent(this, MyRoomActivity.class); 
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); 
            startActivity(intent); 
            finish(); 
        }; 

        findViewById(R.id.btnClose).setOnClickListener(v -> goHome.run()); 
        findViewById(R.id.btnHome).setOnClickListener(v -> goHome.run()); 
    } 
}