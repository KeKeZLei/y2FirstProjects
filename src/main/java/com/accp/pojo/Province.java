package com.accp.pojo;


public class Province {
    private int provinced;
    private String pname;

    public int getProvinced() {
        return provinced;
    }

    public void setProvinced(int provinced) {
        this.provinced = provinced;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Province province = (Province) o;

        if (provinced != province.provinced) return false;
        if (pname != null ? !pname.equals(province.pname) : province.pname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = provinced;
        result = 31 * result + (pname != null ? pname.hashCode() : 0);
        return result;
    }

    public Province() {
    }

    public Province(int provinced, String pname) {
        this.provinced = provinced;
        this.pname = pname;
    }

    public Province(String pname) {
        this.pname = pname;
    }
}
