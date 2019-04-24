package com.example.workerattendance;

public class Material {
    private String materialName;
    private int materialQuantity;
    public Material(String materialName, int materialQuantity) {
        this.materialName = materialName;
        this.materialQuantity = materialQuantity;
    }

    public void addQuantity(int quantity) {
        this.materialQuantity += quantity;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(int materialQuantity) {
        this.materialQuantity = materialQuantity;
    }
}
