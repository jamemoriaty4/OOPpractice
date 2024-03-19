package ra.run;

import org.w3c.dom.ls.LSOutput;
import ra.config.InputMethods;
import ra.entity.Categories;
import ra.entity.Product;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ShopManagement {

    private static Categories[] categories = new Categories[0];

    private static Product[] products = new Product[0];

    public static void main(String[] args) throws ParseException {

        while (true) {
            System.out.println("******************SHOP MENU*******************\n" +
                    "1.\tQuản lý danh mục sản phẩm\n" +
                    "2.\tQuản lý sản phẩm\n" +
                    "3.\tThoát\n");
            System.out.println("nhap lua chon");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    Catalog();
                    break;
                case 2:
                    Product();
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình");
                    break;
                default:
                    System.out.println("Nhap lai lua chon");
                    break;

            }
            if (choice == 3) {
                return;
            }

        }

    }

    public static void Catalog() {
        while (true) {
            System.out.println("********************CATEGORIES MENU***********\n" +
                    "1.\tNhập thông tin các danh mục\n" +
                    "2.\tHiển thị thông tin các danh mục\n" +
                    "3.\tCập nhật thông tin danh mục\n" +
                    "4.\tXóa danh mục\n" +
                    "5.\tCập nhật trạng thái danh mục\n" +
                    "6.\tThoát\n");

            System.out.println("nhập lựa chọn cho catalog");
            byte choiceCata = InputMethods.getByte();

            switch (choiceCata) {
                case 1:
                    addCatagories();
                    break;
                case 2:
                    displayCatagories();
                    break;
                case 3:
                    changeCatagories();
                    break;
                case 4:
                    deleteCatagories();
                    break;
                case 5:
                    changeCatagoriesStatus();
                    break;
                case 6:
                    System.out.println("Thoát chuong trinh catalog");
                    return;

                default:
                    System.out.println("nhập lại lựa chọn phù hợp!");
                    break;
            }


        }
    }

    public static void Product() throws ParseException {
        while (true) {
            System.out.println("*******************PRODUCT MANAGEMENT*****************\n" +
                    "1.\tNhập thông tin các sản phẩm\n" +
                    "2.\tHiển thị thông tin các sản phẩm\n" +
                    "3.\tSắp xếp các sản phẩm theo giá\n" +
                    "4.\tCập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5.\tXóa sản phẩm theo mã sản phẩm\n" +
                    "6.\tTìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7.\tTìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8.\tThoát\n");
            System.out.println("nhập vào lựa chọn");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProduct();
                    break;
                case 3:
                    sortProduct();
                    break;
                case 4:
                    changeProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    findProduct();

                    break;
                case 7:
                    System.out.println("nhập vào giá a: ");
                    float a = InputMethods.getFloat();
                    System.out.println("nhập vào giá b: ");
                    float b = InputMethods.getFloat();
                    while (a > b) {
                        System.out.println("nhập vào giá a: ");
                        a = InputMethods.getFloat();
                        System.out.println("nhập vào giá b: ");
                        b = InputMethods.getFloat();
                    }
                    findProductPrice(a, b);
                    break;
                case 8:
                    return;

            }
        }
    }

    private static void addCatagories() {
//        nới rộng mảng thêm 1 phần tử
        Categories[] newCatagories = new Categories[categories.length + 1];
//        sao chép mảng
        for (int i = 0; i < categories.length; i++) {
            newCatagories[i] = categories[i];
        }
//        tạo mới giá trị
        Categories newCata = new Categories();
        newCata.inputData(true, true);
        newCatagories[newCatagories.length - 1] = newCata;
        categories = newCatagories;


        System.out.println("đã thêm thành công");
    }

    private static void addProduct() throws ParseException {
//        nới rộng mảng thêm 1 phần tử
        Product[] newProducts = new Product[products.length + 1];
//        sao chép mảng
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
//        tạo mới giá trị
        Product newPro = new Product();
        newPro.inputData(true, true);
        newProducts[newProducts.length - 1] = newPro;
        products = newProducts;


        System.out.println("đã thêm thành công");
    }

    private static void displayCatagories() {
        if (categories.length == 0) {
            System.err.println("danh sách trống!");
        }
        System.out.println("Danh sách catagories!");
        for (Categories k : categories) { // foreach
            k.displayData();
        }
    }

    private static void displayProduct() {
        if (products.length == 0) {
            System.err.println("danh sách trống!");
        }
        System.out.println("Danh sách product");
        for (Product k : products) { // foreach
            k.displayData();
        }
    }

    private static int indexOf(int id) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].getCatalogId() == id) {
                return i;
            }
        }
        return -1;
    }

    private static int Idproduct(String id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    //    sap xep product
    private static void sortProduct() {
        Arrays.sort(products, Comparator.comparingDouble(Product::getPrice));

    }

    private static void findProductPrice(float a, float b) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getPrice() > a && products[i].getPrice() < b) {
                products[i].displayData();
            }
        }
    }

    private static void changeCatagories() {
        System.out.println("nhap vao vi tri can thay doi danh muc!");
        int id = InputMethods.getInteger();
        int index = indexOf(id);
        if (index == -1) {
            // ko timm thay
            System.err.println("Khong tim thay danh muc phu hop");
        } else {
            System.out.println("Thông tin cũ có id = " + id + " là");
            categories[index].displayData();
            System.out.println("Nhập thông tin mới cho danh muc");
            categories[index].inputData(false, true);
            System.out.println("cập nhat thanh cong");
        }
    }

    private static void changeProduct() throws ParseException {
        System.out.println("nhap vao vi tri can thay doi thong tin sp!");
        String id = InputMethods.getString();
        int index = Idproduct(id);
        if (index==-1) {
            // ko timm thay
            System.err.println("Khong tim thay sp phu hop");
        } else {
            System.out.println("Thông tin cũ có id = " + id + " là");
            products[index].displayData();
            System.out.println("Nhập thông tin mới cho ma san pham");
            products[index].inputData(false, true);
            System.out.println("cập nhat thanh cong");
                }
            }




    private static void deleteCatagories() {
        System.out.println("Nhập mã catalog cần xóa");
        int id = InputMethods.getInteger();
        int index = indexOf(id);
        if (index == -1) {
            System.err.println("Khong tim thay thong tin phu hơp");
        } else {
            Categories[] newCata = new Categories[categories.length - 1];
            int indexNew = 0;
            for (int i = 0; i < categories.length; i++) {
                if (i != index) {
                    newCata[indexNew++] = categories[i];
                }
            }
            categories = newCata;
            System.out.println("đã xóa thanh cong");
        }
    }

    private static void deleteProduct() {
        System.out.println("Nhập mã product cần xóa");
        String id = InputMethods.getString();
        int index = Idproduct(id);
        if (index==-1) {
            System.err.println("Khong tim thay thong tin phu hơp");
        } else {
            Product[] newPro = new Product[products.length - 1];
            int indexNew = 0;
            for (int i = 0; i < products.length; i++) {
                if (i != index) {
                    newPro[indexNew++] = products[i];
                }
            }
            products = newPro;
            System.out.println("đã xóa thanh cong");
        }
    }

    private static void findProduct() {
        boolean flag = false;
        System.out.println("nhap ten sp muon tim!");
        String namePro = InputMethods.getString();
        for (Product product : products) {
            if (Objects.equals(product.getProductName(), namePro)) {
                product.displayData();
                flag = true;
            }
        }
        if (!flag) {
            System.err.println("khong tim thay san pham");
        }
    }

    private static void changeCatagoriesStatus() {
        System.out.println("nhap vao vi tri can thay doi  trang thai danh muc!");
        int id = InputMethods.getInteger();
        int index = indexOf(id);
        if (index == -1) {
            // ko timm thay
            System.err.println("Khong tim thay danh muc phu hop");
        } else {
            System.out.println("Thông tin cũ có id = " + id + " là");
            categories[index].displayData();
            System.out.println("Nhập thông tin mới cho danh muc");
            categories[index].inputData(false, false);
            System.out.println("cập nhat thanh cong");
        }
    }
}