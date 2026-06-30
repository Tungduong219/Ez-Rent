package com.Group1.ez_rent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView rvSearchResults;
    private SearchRoomAdapter adapter;
    private EditText edtSearch;
    private List<Room> fullRoomList;
    private List<Room> currentDisplayList;
    
    private TextView tvFilterSort, tvFilterPrice, tvFilterType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (findViewById(R.id.btn_back_detail) != null) {
            findViewById(R.id.btn_back_detail).setOnClickListener(v -> finish());
        }

        tvFilterSort = findViewById(R.id.tv_filter_sort);
        tvFilterPrice = findViewById(R.id.tv_filter_price);
        tvFilterType = findViewById(R.id.tv_filter_type);

        fullRoomList = new ArrayList<>();
        fullRoomList.add(new Room(R.drawable.img_back_login, "4.5 Triệu", "Studio Cửa Sổ Lớn", "25m² • Cầu Giấy", "2 giờ trước"));
        fullRoomList.add(new Room(R.drawable.img_back_login, "3.2 Triệu", "Phòng trọ khép kín", "18m² • Đống Đa", "5 giờ trước"));
        fullRoomList.add(new Room(R.drawable.img_back_login, "5.0 Triệu", "Chung cư mini mới", "30m² • Thanh Xuân", "1 ngày trước"));
        fullRoomList.add(new Room(R.drawable.img_back_login, "2.8 Triệu", "Phòng gác xếp", "15m² • Nam Từ Liêm", "2 ngày trước"));
        fullRoomList.add(new Room(R.drawable.img_back_login, "4.0 Triệu", "Phòng ban công thoáng", "22m² • Đông Anh", "3 giờ trước"));
        fullRoomList.add(new Room(R.drawable.img_back_login, "3.5 Triệu", "Phòng trọ giá rẻ", "16m² • Cầu Giấy", "6 giờ trước"));

        currentDisplayList = new ArrayList<>(fullRoomList);

        rvSearchResults = findViewById(R.id.rv_search_results);
        rvSearchResults.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new SearchRoomAdapter(currentDisplayList, room -> {
            Toast.makeText(SearchActivity.this, "Xem chi tiết: " + room.getTitle(), Toast.LENGTH_SHORT).show();
        });
        rvSearchResults.setAdapter(adapter);

        edtSearch = findViewById(R.id.edt_search_input);
        if (findViewById(R.id.btn_clear) != null) {
            findViewById(R.id.btn_clear).setOnClickListener(v -> {
                if (edtSearch != null) edtSearch.setText("");
            });
        }

        if (edtSearch != null) {
            edtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterSearch(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        setupFilterActions();
    }

    private void setupFilterActions() {
        LinearLayout btnSort = findViewById(R.id.btn_filter_sort);
        LinearLayout btnPrice = findViewById(R.id.btn_filter_price);
        LinearLayout btnType = findViewById(R.id.btn_filter_type);

        if (btnSort != null) {
            btnSort.setOnClickListener(v -> {
                PopupMenu popup = new PopupMenu(this, btnSort);
                popup.getMenu().add(Menu.NONE, 1, 1, "Mặc định");
                popup.getMenu().add(Menu.NONE, 2, 2, "Giá tăng dần");
                popup.getMenu().add(Menu.NONE, 3, 3, "Giá giảm dần");

                popup.setOnMenuItemClickListener(item -> {
                    tvFilterSort.setText("三 " + item.getTitle());
                    if (item.getItemId() == 2) {
                        Collections.sort(currentDisplayList, (r1, r2) -> Float.compare(parsePrice(r1.getPrice()), parsePrice(r2.getPrice())));
                    } else if (item.getItemId() == 3) {
                        Collections.sort(currentDisplayList, (r1, r2) -> Float.compare(parsePrice(r2.getPrice()), parsePrice(r1.getPrice())));
                    } else {
                        currentDisplayList.clear();
                        currentDisplayList.addAll(fullRoomList);
                    }
                    adapter.notifyDataSetChanged();
                    return true;
                });
                popup.show();
            });
        }

        if (btnPrice != null) {
            btnPrice.setOnClickListener(v -> {
                PopupMenu popup = new PopupMenu(this, btnPrice);
                popup.getMenu().add(Menu.NONE, 1, 1, "Tất cả mức giá");
                popup.getMenu().add(Menu.NONE, 2, 2, "Dưới 3.5 Triệu");
                popup.getMenu().add(Menu.NONE, 3, 3, "Từ 3.5 - 4.5 Triệu");
                popup.getMenu().add(Menu.NONE, 4, 4, "Trên 4.5 Triệu");

                popup.setOnMenuItemClickListener(item -> {
                    tvFilterPrice.setText(item.getTitle() + " ∨");
                    List<Room> filtered = new ArrayList<>();
                    for (Room room : fullRoomList) {
                        float priceValue = parsePrice(room.getPrice());
                        if (item.getItemId() == 2 && priceValue < 3.5f) filtered.add(room);
                        else if (item.getItemId() == 3 && priceValue >= 3.5f && priceValue <= 4.5f) filtered.add(room);
                        else if (item.getItemId() == 4 && priceValue > 4.5f) filtered.add(room);
                        else if (item.getItemId() == 1) filtered.add(room);
                    }
                    currentDisplayList.clear();
                    currentDisplayList.addAll(filtered);
                    adapter.notifyDataSetChanged();
                    return true;
                });
                popup.show();
            });
        }

        if (btnType != null) {
            btnType.setOnClickListener(v -> {
                PopupMenu popup = new PopupMenu(this, btnType);
                popup.getMenu().add(Menu.NONE, 1, 1, "Tất cả loại phòng");
                popup.getMenu().add(Menu.NONE, 2, 2, "Studio");
                popup.getMenu().add(Menu.NONE, 3, 3, "Chung cư mini");
                popup.getMenu().add(Menu.NONE, 4, 4, "Phòng trọ");

                popup.setOnMenuItemClickListener(item -> {
                    tvFilterType.setText(item.getTitle() + " ∨");
                    List<Room> filtered = new ArrayList<>();
                    for (Room room : fullRoomList) {
                        if (item.getItemId() == 1) filtered.add(room);
                        else if (room.getTitle().toLowerCase().contains(item.getTitle().toString().toLowerCase())) {
                            filtered.add(room);
                        }
                    }
                    currentDisplayList.clear();
                    currentDisplayList.addAll(filtered);
                    adapter.notifyDataSetChanged();
                    return true;
                });
                popup.show();
            });
        }
    }

    private float parsePrice(String priceStr) {
        try {
            return Float.parseFloat(priceStr.replaceAll("[^0-9.]", ""));
        } catch (Exception e) {
            return 0f;
        }
    }

    private void filterSearch(String text) {
        List<Room> filteredList = new ArrayList<>();
        for (Room room : fullRoomList) {
            if (room.getTitle().toLowerCase().contains(text.toLowerCase()) || 
                room.getInfo().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(room);
            }
        }
        currentDisplayList.clear();
        currentDisplayList.addAll(filteredList);
        adapter.updateList(currentDisplayList);
    }
}