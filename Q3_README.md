# Q3: Chức năng Thêm mới (Add New Function)

Tệp này tóm tắt những gì đã làm cho **Câu 3 (Q3)** của bài thi thực hành PE HSF302.

## 1. Thêm Kiểm Duyệt bằng Annotation (`ShoeDTO.java`)
Biến đối tượng chuyển đổi `ShoeDTO` thành bộ siêu lọc thông qua chuẩn **Bean Validation API**:
- **Shoes No**: Xài combo `@NotBlank` (cấm trống, cấm để khoảng trắng nguyên cục), `@Size(max = 10)` bảo đảm không viết bài chửi thề dài thượt, kết hợp `@Pattern(regexp = "^[a-zA-Z0-9]+$")` đóng băng không cho ký tự đặc biệt hay dấu cách xen 2 bên.
- **Shoes Name**: Chặn dài loằng ngoằng cồng kềnh với `@Size(max=100)`. Chấm dứt thói mưu mô để dư khoảng trắng Leading/Trailing Space với biểu thức quỷ kiến sầu `^\S+(?:\s+\S+)*$`.
- **Price**: Gắn cho thẻ `@DecimalMin` & `@DecimalMax` (kích hoạt inclusive=false ép strict) cùng `@NotNull` giới hạn con số nhảy nhót chỉ ở ngưỡng `0` < tiền < `100,000`.

## 2. Lõi xử lý Controller (`ShoeController.java`)
- **Bước `GET /add`**: Load lên giao diện một khối form trống toang hoác `new ShoeDTO()` để đáp lại yêu cầu "When opened all fields are blank" của đề. Nạp song song từ điển mảng "Type" (danh sách Loại đổ từ csdl).
- **Bước `POST /add`**: Ép validation nhảy ra qua chốt chặn `@Valid` khai trước ngực biến đầu vào, đính kèm `BindingResult` để xem máy có ghi nhận vi phạm pháp luật nào không.
  - **Check trùng lặp tự chế (Duplicate)**: Khởi xướng dò tìm từ ID dưới service. Nếu hên là lặp thì ép cố tình gắn cờ ngã bằng code `bindingResult.rejectValue("shoesNo",...)`.
  - NẾU (IF) dính 1 cái lỗi nhỏ nằm gai nếm mật trong `bindingResult`: Load lại thanh mảng danh sách Dropdown Type, rũ bỏ việc lưu DB đi, hồi hương về chốn `add.html` cho người ta thấy Lỗi In Đỏ (Validation Rules).
  - NẾU HOÀN TOÀN HỢP LỆ: Chấp thuận lưu bằng thư viện Repository (`save()`), tiêm chất kích thích "Created new shoes successfully" vào trong Flash Attribute (RedirectAttributes), xong phi ngựa thẳng hướng `redirect:/add`. Thao tác redirect khiến toàn bộ Form trắng nõn nà như lúc mới vào cửa. Đạt KPI 3.2.

## 3. Tạo Form View (`add.html`)
- Chế tác form layout hình khối chữ nhật bám trụ y bản gốc đề thi (Screen 3).
- Cắm cọc `<form th:action="@{/add}" th:object="${tên_biến}" method="post">` lên mâm.
- Ở các ô Input (Trường Nhập Khẩu): Setup dây chuyền `th:field="*{tên_cột}"`.
- Giăng lưới bắt lỗi in tiếng rủa đỏ loét bằng `<span th:if="${#fields.hasErrors(...)}" th:errors="*..."/>` bọc sau mỗi Box.
- Thanh kéo thả `<select>` (Dropdown): Đi một vòng loop `th:each`, cài mã ID ở sau lưng `th:value` rồi show cấu trúc string giao diện là `${t.typeCode} + '-' + ${t.typeName}` giống y đúc mẫu in trên giấy thi (VD SP-Sport).
- Lệnh **[ Back ]**: Link href ngã thuyền về bến cũ `@{/}` theo đúng quy định câu mệnh lệnh.

---

💡 **HƯỚNG DẪN KHI ĐỀ THI LÀ MỘT ĐỐI TƯỢNG KHÁC (Không phải Shoe)**:
- **Chuyển đổi Validator Regex**: Nếu đề chỉ cho phép là số 100%, hãy xáo `@Pattern` thành `^[0-9]+$`. 
- **Bảng phụ (Dropdown Selects)**: Tương tự như Giày và LoaiGiay. Nếu bạn thi Quản lý Điện Thoại - Bảng nhánh là Thương Hiệu. Nhớ bổ sung riêng một Service móc cái DB Thương Hiệu nhét vào tham biến Model khi Show form thả vào bộ Chọn Select Option thay vì Code Type xập xệ của tớ.
- **Form lưu trạng thái Field**: Đừng đổi dòng Code `th:field`. Nó ăn sâu gốc rễ theo chuẩn DTO rồi, bạn đổi class DTO sao thì tên thuộc tính ném vào đây tự match hệt thế.
- **Xử lý Error Duplicate Custom**: Check mảng khoá Chính. Tầm quan trọng giống `studentId` hoặc `productCode`. Cứ chọc db lòi lên khác Null là nện hàm Reject vào ngay.
