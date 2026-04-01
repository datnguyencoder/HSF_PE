# Q2: Chức năng Xóa bản ghi (Delete Function)

Tệp này tóm tắt những gì đã làm cho **Câu 2 (Q2)** của bài thi thực hành PE HSF302.

## 1. Trong Controller (`ShoeController.java`)
- Tạo API `@GetMapping("/delete/{id}")` thực thi ngay khi đường link "Delete" được click từ màn danh sách Q1. Tham số `{id}` được hứng từ đường dẫn qua chú thích `@PathVariable`. Controller này lấy đôi giày đó và lôi nó quăng qua cho màn hình `delete.html` hiển thị form "Hỏi lại chắc chắn".
- Tạo API `@PostMapping("/delete/{id}")` chuyên để đón luồng xoá dứt điểm (thực thi xoá DB thật). Nó chỉ có mạch kích hoạt khi người dùng bóp cò nút **[ Yes ]**.
  - Xoá dưới tầng DataBase nhờ code: `shoeService.delete(id)`.
  - Giăng cái cảnh báo chữ xanh: Nạp cho cơ chế `RedirectAttributes` một biến tạm là Flash Attribute tên `message` mang trong mình linh hồn của cái text "Deleted successfully".
  - Chuyển hướng nảy người dùng bật ngược lại trang gốc: `redirect:/`.

## 2. Trong Service (`ShoeServiceImpl.java`)
- **getById(Integer id)**: Cắm lệnh `.findById(id)` tiêu chuẩn từ JpaRepository rồi lấy thực thể (Entity) qua bộ lọc chuyển hoá DTO như đề trước để dịch mã chữ (người dùng cần nhìn thấy nguyên tên đầy đủ thay vì Code trần trụi bám bụi).
- **delete(Integer id)**: Gọi cái chóp nón `.deleteById(id)` của repo là ăn tiền bài xoá.

## 3. Chỉnh sửa File View gốc màn danh sách (`list.html`)
- Mở rộng thêm 1 thanh thẻ `<p th:if="${message}" ...>` giáp dưới Title của list. Mặc định lặn ngụp, thẻ này sẽ sáng lòe dòng chữa màu xanh "Deleted successfully" chỉ khi sự kiện redirect từ Cục Controller xoá thành công bay về.

## 4. Giao diện Xác nhận xoá giả Màn 2 (`delete.html`)
- Tạo 1 form viền vuông ôm căn giữa theo mẫu có tên Screen 2 – Confirmation Dialog trên đề.
- Kẹp dòng chữ câu hỏi "Are you sure you want to delete...": Nối cái tên biến vào giữa câu query bằng đoạn code `<span th:text="${...}">`.
- Nút bấm **[ Yes ]**: Buộc phải khai sinh ra 1 thẻ `<form method="post">` ôm trọn cái nút này để gào thét gửi cái request POST thẳng vào`/delete/{id}`.
- Nút bấm **[ No ]**: Thuồn lại bằng cấu trúc `<a href...>` điều hướng cái một quay trở về `/` (trang chủ gốc Q1).

---

💡 **HƯỚNG DẪN KHI ĐỀ THI LÀ MỘT ĐỐI TƯỢNG KHÁC (Không phải Shoe)**:
- **Tương tác URL Controller**: Các path mapping kiểu `/delete/{id}` hay `/delete/{product_id}` là tuỳ chọn tuân theo đề, nhưng chú ý đổi kiểu hình thể dữ liệu của Cột Khoá Chính (ví dụ `String id` thay vì `Integer id` của Giày).
- **Giao diện xác nhận `delete.html`**: Lưu ý thay thế mã thông báo `${shoe.shoesName}` thành Tên Món khác theo logic của Đề (`${student.fullName}`, `${car.modelName}`, v.v). Không để tên lạ quặc không đáp ứng thông điệp!
- **Redirect Message**: Các đề khác nhau có thể đòi in chữ Flash "Successfully deleted record" thay vì "Deleted successfully". Quan trọng: Săn đúng mấy ký tự in đậm và in nét đứt trong ngoặc kép ở yêu cầu để điệp viên kiểm duyệt chấm 10/10.
