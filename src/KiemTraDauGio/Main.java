package KiemTraDauGio;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductRepository repository = new ProductRepository();

        int choice;
        do {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ SẢN PHẨM ===");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xóa sản phẩm theo ID");
            System.out.println("3. Tìm sản phẩm theo ID");
            System.out.println("4. Hiển thị toàn bộ sản phẩm");
            System.out.println("5. Sắp xếp theo giá");
            System.out.println("6. Thống kê loại sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();
            sc.nextLine(); // Xóa ký tự newline

            switch (choice) {
                case 1:
                    System.out.println("Chọn loại sản phẩm:");
                    System.out.println("1. Điện tử");
                    System.out.println("2. Thực phẩm");
                    System.out.print("Lựa chọn: ");
                    int type = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập giá: ");
                    double price = sc.nextDouble();
                    sc.nextLine();

                    if (type == 1) {
                        System.out.print("Nhập số tháng bảo hành: ");
                        int warrantyMonths = sc.nextInt();
                        sc.nextLine();
                        if (repository.add(new ElectronicProduct(id, name, price, warrantyMonths))) {
                            System.out.println("Thêm sản phẩm thành công!");
                        } else {
                            System.out.println("ID đã tồn tại hoặc có lỗi!");
                        }
                    } else if (type == 2) {
                        System.out.print("Nhập phần trăm giảm giá: ");
                        int discountPercent = sc.nextInt();
                        sc.nextLine();
                        if (repository.add(new FoodProduct(id, name, price, discountPercent))) {
                            System.out.println("Thêm sản phẩm thành công!");
                        } else {
                            System.out.println("ID đã tồn tại hoặc có lỗi!");
                        }
                    } else {
                        System.out.println("Lựa chọn không hợp lệ!");
                    }
                    break;

                case 2:
                    System.out.print("Nhập ID sản phẩm cần xóa: ");
                    String removeId = sc.nextLine();
                    if (repository.removeById(removeId)) {
                        System.out.println("Xóa sản phẩm thành công!");
                    } else {
                        System.out.println("Không tìm thấy sản phẩm!");
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID sản phẩm cần tìm: ");
                    String searchId = sc.nextLine();
                    Product found = repository.findById(searchId);
                    if (found != null) {
                        found.displayInfo();
                        System.out.println("Thành tiền: " + found.calculateFinalPrice());
                    } else {
                        System.out.println("Không tìm thấy sản phẩm!");
                    }
                    break;

                case 4:
                    System.out.println("=== DANH SÁCH SẢN PHẨM ===");
                    for (Product product : repository.findAll()) {
                        product.displayInfo();
                        System.out.println("Thành tiền: " + product.calculateFinalPrice());
                        System.out.println("------------------------");
                    }
                    break;

                case 5:
                    repository.sortProductsByPrice();
                    System.out.println("=== DANH SÁCH SAU KHI SẮP XẾP ===");
                    for (Product product : repository.findAll()) {
                        product.displayInfo();
                        System.out.println("Thành tiền: " + product.calculateFinalPrice());
                        System.out.println("------------------------");
                    }
                    break;

                case 6:
                    System.out.println("=== THỐNG KÊ LOẠI SẢN PHẨM ===");
                    Map<String, Integer> stats = repository.countProductTypes();
                    for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;

                case 0:
                    System.out.println("Kết thúc chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
