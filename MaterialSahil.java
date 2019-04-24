package com.example.workerattendance;

import java.util.ArrayList;

public class MaterialSahil {

    String materialName;
    int materialQuantity;
    boolean isMaterialSelected;
    int perUnitCost;

    public MaterialSahil(String materialName, int materialQuantity, boolean isMaterialSelected, int perUnitCost) {
        this.materialName = materialName;
        this.materialQuantity = materialQuantity;
        this.isMaterialSelected = isMaterialSelected;
        this.perUnitCost = perUnitCost;
    }

    public MaterialSahil(String materialName, int materialQuantity) {
        this.materialName = materialName;
        this.materialQuantity = materialQuantity;
        this.isMaterialSelected = true;
        this.perUnitCost = 500;
    }

    public int getPerUnitCost() {
        return perUnitCost;
    }

    public void setPerUnitCost(int perUnitCost) {
        this.perUnitCost = perUnitCost;
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

    public boolean isMaterialSelected() {
        return isMaterialSelected;
    }

    public void setMaterialSelected(boolean materialSelected) {
        isMaterialSelected = materialSelected;
    }
    public static ArrayList<MaterialSahil> getSuggestedSteelMaterials() {
        ArrayList<MaterialSahil> MaterialSahilList = new ArrayList<>();
        MaterialSahil t1= new MaterialSahil("A", 5);
        MaterialSahilList.add(t1);
        MaterialSahil t2= new MaterialSahil("B", 10);
        MaterialSahilList.add(t2);
        MaterialSahil t3= new MaterialSahil("C", 300);
        MaterialSahilList.add(t3);
        MaterialSahil t4= new MaterialSahil("D", 100);
        MaterialSahilList.add(t4);
        return MaterialSahilList;
    }

    public static ArrayList<MaterialSahil> getSuggestedCivilMaterials() {
        ArrayList<MaterialSahil> MaterialSahilList = new ArrayList<>();
        MaterialSahil t1= new MaterialSahil("A1", 5);
        MaterialSahilList.add(t1);
        MaterialSahil t2= new MaterialSahil("B1", 10);
        MaterialSahilList.add(t2);
        MaterialSahil t3= new MaterialSahil("C1", 300);
        MaterialSahilList.add(t3);
        MaterialSahil t4= new MaterialSahil("D1", 100);
        MaterialSahilList.add(t4);
        return MaterialSahilList;
    }
}
