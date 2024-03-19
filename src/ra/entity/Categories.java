package ra.entity;

import ra.config.InputMethods;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

//    nhập dữ liệu
    public void inputData(boolean isChange,boolean status){
        if(status){
            if(isChange){
                System.out.println("nhập mã catalog");
                this.catalogId = InputMethods.getInteger();
            }
            System.out.println("nhập tên catalog");
            this.catalogName = InputMethods.getString();
            System.out.println("nhập mieu tả catalog");
            this.description = InputMethods.getString();

        }
        System.out.println("nhập tran thái catalog");
        this.catalogStatus = InputMethods.getBoolean();
    }

//    hiển thị dữ liêệu
    public void displayData(){
        System.out.printf("Mã : %-4d | Tên: %-15s |Miêu tả: %-30s |Trạng thái: %-15s \n",catalogId,catalogName,description,status());
    }

    public String status(){
        String signal = catalogStatus ?  "hoạt động":"không hoạt động";
        return signal;
    }
}
