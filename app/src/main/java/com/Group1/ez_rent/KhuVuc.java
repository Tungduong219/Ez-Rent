package com.Group1.ez_rent;

public class KhuVuc {
    private String tenKhuVuc;
    private int hinhAnh;

    public KhuVuc(String tenKhuVuc, int hinhAnh) {
        this.tenKhuVuc = tenKhuVuc;
        this.hinhAnh = hinhAnh;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }
}