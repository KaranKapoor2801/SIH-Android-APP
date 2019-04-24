package com.example.workerattendance;

public class DataOfLabor {
    private String nameOfLabor;
    private double totalPayment;
    private int totalAttendance;
    private int attendanceOfLabor;
    private double payPerDay;
    private String category;
    private String siteId;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNameOfLabor() {
        return nameOfLabor;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public int getTotalAttendance() {
        return totalAttendance;
    }

    public int getAttendanceOfLabor() {
        return attendanceOfLabor;
    }

    public double getPayPerDay() {
        return payPerDay;
    }

    public void setNameOfLabor(String nameOfLabor) {
        this.nameOfLabor = nameOfLabor;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setTotalAttendance(int totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public void setAttendanceOfLabor(int attendanceOfLabor) {
        this.attendanceOfLabor = attendanceOfLabor;
    }

    public void setPayPerDay(double payPerDay) {
        this.payPerDay = payPerDay;
    }

    @Override
    public String toString() {
        return "DataOfLabor{" +
                "nameOfLabor='" + nameOfLabor + '\'' +
                ", totalPayment=" + totalPayment +
                ", totalAttendance=" + totalAttendance +
                ", attendanceOfLabor=" + attendanceOfLabor +
                ", payPerDay=" + payPerDay +
                ", category='" + category + '\'' +
                '}';
    }

}
