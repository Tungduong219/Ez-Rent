package com.Group1.ez_rent;

public class PhongChoThue {
    private String tieuDe;
    private String diaChi;
    private String gia;
    private boolean daXacThuc;
    private int hinhAnh;

    public PhongChoThue(String tieuDe, String diaChi, String gia, boolean daXacThuc, int hinhAnh) {
        this.tieuDe = tieuDe;
        this.diaChi = diaChi;
        this.gia = gia;
        this.daXacThuc = daXacThuc;
        this.hinhAnh = hinhAnh;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getGia() {
        return gia;
    }

    public boolean isDaXacThuc() {
        return daXacThuc;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }
}