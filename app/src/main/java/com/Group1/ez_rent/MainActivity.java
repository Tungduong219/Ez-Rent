package com.Group1.ez_rent;

import android.content.Context; 
import android.content.Intent; 
import android.os.Bundle; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.ImageView; 
import android.widget.LinearLayout; 
import android.widget.TextView; 
import androidx.annotation.NonNull; 
import androidx.appcompat.app.AppCompatActivity; 
import androidx.recyclerview.widget.LinearLayoutManager; 
import androidx.recyclerview.widget.RecyclerView; 
import java.util.ArrayList; 
import java.util.List; 

public class MainActivity extends AppCompatActivity { 
    private RecyclerView rvKhuVuc, rvMoiDang; 
    private LinearLayout btnKhamPha; 
    private LinearLayout btnCaNhan; 
    
    private View btnMyRoom, btnFindRoom, btnAppointment; 

    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main); 
        
        rvKhuVuc = findViewById(R.id.rv_khu_vuc); 
        rvMoiDang = findViewById(R.id.rv_moi_dang); 
        btnKhamPha = findViewById(R.id.btn_kham_pha); 
        
        btnCaNhan = findViewById(R.id.btn_nav_profile); 
        
        btnMyRoom = findViewById(R.id.btn_my_room); 
        btnFindRoom = findViewById(R.id.btn_find_room); 
        btnAppointment = findViewById(R.id.btn_appointment); 
        
        rvKhuVuc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); 
        List<KhuVuc> listKhuVuc = new ArrayList<>(); 
        listKhuVuc.add(new KhuVuc("Cầu Giấy", R.drawable.img_back_login)); 
        listKhuVuc.add(new KhuVuc("Đống Đa", R.drawable.img_back_login)); 
        listKhuVuc.add(new KhuVuc("Thanh Xuân", R.drawable.img_back_login)); 
        
        KhuVucAdapter khuVucAdapter = new KhuVucAdapter(listKhuVuc, this); 
        rvKhuVuc.setAdapter(khuVucAdapter); 
        
        rvMoiDang.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)); 
        List<PhongChoThue> listPhong = new ArrayList<>(); 
        listPhong.add(new PhongChoThue("Studio ban công thoáng", "Ngõ 175 Xuân Thủy", "4.5 Triệu", true, R.drawable.img_back_login)); 
        listPhong.add(new PhongChoThue("Phòng trọ khép kín 25m2", "Phố Chùa Láng", "3.2 Triệu", false, R.drawable.img_back_login));
        
        PhongAdapter phongAdapter = new PhongAdapter(listPhong, this); 
        rvMoiDang.setAdapter(phongAdapter); 
        
        if (btnKhamPha != null) { 
            btnKhamPha.setOnClickListener(v -> { 
                Intent intent = new Intent(MainActivity.this, SearchActivity.class); 
                startActivity(intent); 
            }); 
        } 
        
        if (btnCaNhan != null) { 
            btnCaNhan.setOnClickListener(v -> { 
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class); 
                startActivity(intent); 
            }); 
        } 
        
        if (btnMyRoom != null) { 
            btnMyRoom.setOnClickListener(v -> { 
                Intent intent = new Intent(MainActivity.this, MyRoomActivity.class); 
                startActivity(intent); 
            }); 
        } 
        
        if (btnFindRoom != null) { 
            btnFindRoom.setOnClickListener(v -> { 
                Intent intent = new Intent(MainActivity.this, SearchActivity.class); 
                startActivity(intent); 
            }); 
        } 
        
        if (btnAppointment != null) { 
            btnAppointment.setOnClickListener(v -> { 
                Intent intent = new Intent(MainActivity.this, AppointmentActivity.class); 
                startActivity(intent); 
            }); 
        } 
    } 

    public static class KhuVucAdapter extends RecyclerView.Adapter<KhuVucAdapter.ViewHolder> { 
        private final List<KhuVuc> list; 
        private final Context context; 
        
        public KhuVucAdapter(List<KhuVuc> list, Context context) { 
            this.list = list; 
            this.context = context; 
        } 
        
        @NonNull @Override 
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { 
            View view = LayoutInflater.from(context).inflate(R.layout.item_khu_vuc, parent, false); 
            return new ViewHolder(view); 
        } 
        
        @Override 
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) { 
            KhuVuc item = list.get(position); 
            holder.tvTen.setText(item.getTenKhuVuc()); 
            holder.img.setImageResource(item.getHinhAnh()); 
        } 
        
        @Override 
        public int getItemCount() { 
            return list.size(); 
        } 
        
        public static class ViewHolder extends RecyclerView.ViewHolder { 
            ImageView img; 
            TextView tvTen; 
            public ViewHolder(@NonNull View itemView) { 
                super(itemView); 
                img = itemView.findViewById(R.id.img_khu_vuc); 
                tvTen = itemView.findViewById(R.id.tv_ten_khu_vuc); 
            } 
        } 
    } 

    public static class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.ViewHolder> { 
        private final List<PhongChoThue> list; 
        private final Context context; 
        
        public PhongAdapter(List<PhongChoThue> list, Context context) { 
            this.list = list; 
            this.context = context; 
        } 
        
        @NonNull @Override 
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { 
            View view = LayoutInflater.from(context).inflate(R.layout.item_phong_cho_thue, parent, false); 
            return new ViewHolder(view); 
        } 
        
        @Override 
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) { 
            PhongChoThue item = list.get(position); 
            holder.tvTitle.setText(item.getTieuDe()); 
            holder.tvAddress.setText("📍 " + item.getDiaChi()); 
            holder.tvPrice.setText(item.getGia() + "/tháng"); 
            holder.imgPhong.setImageResource(item.getHinhAnh()); 
            
            if (item.isDaXacThuc()) { 
                holder.tvTag.setVisibility(View.VISIBLE); 
                holder.imgCheck.setVisibility(View.VISIBLE); 
            } else { 
                holder.tvTag.setVisibility(View.GONE); 
                holder.imgCheck.setVisibility(View.GONE); 
            } 
            
            holder.itemView.setOnClickListener(v -> { 
                Intent intent = new Intent(context, DetailActivity.class); 
                intent.putExtra("TITLE_TAG", item.getTieuDe()); 
                intent.putExtra("PRICE_TAG", item.getGia()); 
                intent.putExtra("ADDRESS_TAG", item.getDiaChi()); 
                intent.putExtra("IMAGE_TAG", item.getHinhAnh()); 
                context.startActivity(intent); 
            }); 
        } 
        
        @Override 
        public int getItemCount() { 
            return list.size(); 
        } 
        
        public static class ViewHolder extends RecyclerView.ViewHolder { 
            ImageView imgPhong, imgCheck; 
            TextView tvTitle, tvAddress, tvPrice, tvTag; 
            public ViewHolder(@NonNull View itemView) { 
                super(itemView); 
                imgPhong = itemView.findViewById(R.id.img_phong); 
                imgCheck = itemView.findViewById(R.id.img_check); 
                tvTitle = itemView.findViewById(R.id.tv_title); 
                tvAddress = itemView.findViewById(R.id.tv_address); 
                tvPrice = itemView.findViewById(R.id.tv_price); 
                tvTag = itemView.findViewById(R.id.tv_tag_verified); 
            } 
        } 
    }
}