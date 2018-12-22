package com.reewaj.betterhealthdemo.Activity;

public class ModelMedicine {
    int medicineId;
    String medicineName;
    int quantity;
    byte[] medicineImage;
    String purchaseDate;
    int consumptionFrequency;
    String notes;
    int stockfordays;
    int stockfortimes;
    String endStockDate;

    public ModelMedicine() {

    }


    public ModelMedicine(int medicineId, String medicineName, int quantity, byte[] medicineImage, String purchaseDate, int consumptionFrequency, String notes, int stockfordays, int stockfortimes, String endStockDate) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.medicineImage = medicineImage;
        this.purchaseDate = purchaseDate;
        this.consumptionFrequency = consumptionFrequency;
        this.notes = notes;
        this.stockfordays = stockfordays;
        this.endStockDate=endStockDate;
        this.stockfortimes = stockfortimes;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(byte[] medicineImage) {
        this.medicineImage = medicineImage;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getConsumptionFrequency() {
        return consumptionFrequency;
    }

    public void setConsumptionFrequency(int consumptionFrequency) {
        this.consumptionFrequency = consumptionFrequency;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStockfordays() {
        return stockfordays;
    }

    public void setStockfordays(int stockfordays) {
        this.stockfordays = stockfordays;
    }

    public int getStockfortimes() {
        return stockfortimes;
    }

    public void setStockfortimes(int stockfortimes) {
        this.stockfortimes = stockfortimes;
    }


    public String getEndStockDate() {
        return endStockDate;
    }

    public void setEndStockDate(String endStockDate) {
        this.endStockDate = endStockDate;
    }
}
