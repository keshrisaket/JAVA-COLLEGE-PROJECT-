package com.example.javacollegeproject.lab;

public class ModalLab {
    private int lab_num;
    private String lab_name;
    private String lab_id;
    private int total_seat;
    private int vacant_seat;

    public ModalLab(int lab_num, String lab_name, String lab_id, int total_seat, int vacant_seat) {
        this.lab_num = lab_num;
        this.lab_name = lab_name;
        this.lab_id = lab_id;
        this.total_seat = total_seat;
        this.vacant_seat = vacant_seat;
    }

    public int getLab_num() {
        return lab_num;
    }

    public void setLab_num(int lab_num) {
        this.lab_num = lab_num;
    }

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

    public String getLab_id() {
        return lab_id;
    }

    public void setLab_id(String lab_id) {
        this.lab_id = lab_id;
    }

    public int getTotal_seat() {
        return total_seat;
    }

    public void setTotal_seat(int total_seat) {
        this.total_seat = total_seat;
    }

    public int getVacant_seat() {
        return vacant_seat;
    }

    public void setVacant_seat(int vacant_seat) {
        this.vacant_seat = vacant_seat;
    }
}
