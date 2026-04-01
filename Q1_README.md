# Q1: Chức năng Hiển Thị Danh Sách (List Function)

Tệp này tóm tắt những gì đã làm cho **Câu 1 (Q1)** của bài thi thực hành PE HSF302.

## 1. Trong Controller (`ShoeController.java`)
- Tạo hàm gắn với `@GetMapping("/")` để làm trang chủ (giao diện mở lên đầu tiên).
- Nhận từ khóa tìm kiếm qua `@RequestParam("shoesName")` (cái này linh hoạt, không bắt buộc điền).
- **Nếu có từ khóa nhập vào**: Gọi hàm `shoeService.searchByName(...)` rồi trả về danh sách kết quả chứa từ khóa tìm kiếm đó. Đồng thời phải gửi kèm biến `shoesName` ném ngược lại View để trên thanh tìm kiếm còn giữ nguyên chữ mình vừa gõ.
- **Nếu không có chữ nào (rỗng)**: Gọi hàm lấy toàn bộ danh sách `shoeService.getAll()`.

## 2. Trong Service (`ShoeServiceImpl.java`)
- Tái sử dụng để tối ưu code: Hàm `getAll()` sẽ gọi thẳng luôn `searchByName("")` (gửi vào 1 chuỗi rỗng để gom logic 2 phía).
- Logic tìm kiếm hàm `searchByName()`: 
  - Nếu từ khoá trống: Xài `findAllByOrderByShoesNameAsc()` của JpaRepository (hoặc code Custom tự viết thêm) để lôi cạn kho xếp hạng theo Name từ A-Z.
  - Nếu có từ khoá: Xài `searchAllByShoesName...Containing...` để Spring Data JPA áp dụng bộ lọc Wildcard (`LIKE %từ_khóa%`).
- Quá trình chuyển bảng vật lý DB (Entity) lấy dữ liệu giao tiếp DTO (Data Transfer Object): Map chính xác từng trường! Đặc biệt đối với trường `type` (Mã loại Giày), ta chạy tìm kiếm trong Repository của bảng `ShoesType` để dò ngược cái ID (Ví dụ "SP") thành cái từ vựng mong muốn (Ví dụ "Sport").
- Cực kì lưu ý: Chắc chắn phải đổ `shoesId` ra DTO vì ở Q2 và Q4 ta cần cọc URL (VD: `/delete/1`) - thiếu cái ID đi link là hỏng giao diện ngay lập tức!

## 3. Tạo View bằng Thymeleaf (`list.html`)
- Cấu trúc file HTML theo đúng hình vẽ đồ hoạ khối "Shoes List" trên đề (sẽ viền form hệt ảnh).
- Tạo 1 form search bằng method GET, trỏ thẳng tới action `@{\/}`.
- Để lặp được danh sách cho lên Bảng HTML, khai báo loop ảo `th:each="shoe, iterStat : ${shoes}"`.
- Gọi hàm `${iterStat.count}` để tạo số bấm đếm tự động (1, 2, 3...) dành vinh danh cột đầu bảng (# No).
- Ô hiển thị Cột Action gồm "Delete | View": Tạo URL chèn ID động `<a th:href="@{/delete/{id}(id=${shoe.shoesId})}">...</a>`.

---

💡 **HƯỚNG DẪN KHI ĐỀ THI LÀ MỘT ĐỐI TƯỢNG KHÁC (Không phải Shoe)**:
- **Tên Class / File / Biến**: Rất hay bị rớt ở đây. Đề bài yêu cầu Sinh Viên (Student), Sách (Book), Sản Phẩm (Product) thì đổi tiền tố `Shoe` / `shoesName` / `shoeService`... thành tương đương. Ví dụ: `Student` / `studentName` / `studentService`.
- **Hàm thay thế trong DB**: Thay vì kiếm `findByShoesNo`, nó có thể là `findByStudentCode` hoặc `findByIsbn` (đối với thư viện sách). Bất cứ chữ gì liên quan đến mã số.
- **Xử lý Bảng Phụ (Giống Dropdown Database Type)**: Trường hợp bài không có `ShoesType` mà có `Category` hay `Major`... Ở trong cái hàm `convertEntityToResponse`, nhớ đổi hàm dò Name từ Repository của `Category_ID` sang biến tên để vứt lên List hiển thị theo chữ của loài người, không khoe mã CODE xấu xí ra nhé.
- **Giao diện HTML Layout (`list.html`)**: Cập nhật lại các tiêu đề bảng Table (điển hình `Student ID`, `Full Name`, `GPA`) sao cho bám trọn chữ gốc trong hình vẽ của đề! Đoạn `Action` hay `# No` giữ nguyên.
