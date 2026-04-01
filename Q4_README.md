# Q4: Chức năng Xem Chi Tiết (View Function)

Tệp này tóm tắt những gì đã làm cho **Câu 4 (Q4)** của bài thi thực hành PE HSF302.

## 1. Controller Xử Lý Đoạn Xem (`ShoeController.java`)
- Bắn `@GetMapping("/view/{id}")` đón yêu cầu GET khi người dùng click vô duyên vào hyperlink "View" ở trang danh sách bên Câu Q1.
- Dùng y hệt chức năng nội soi ruột ở câu Q2 để mổ xẻ Tham số `{id}` thông qua bộ vi xử lý `@PathVariable`.
- Kéo bộ phận tìm kiếm duy nhất đối tượng `shoeService.getById(id)` lôi thông số kỹ thuật ra để nhúng vào object Model đẩy sang Template html mới toanh mang thương hiệu `view`.
- Một sự ăn ý may mắn siêu khổng lồ: Vì cái hàm `getById` của mình có nhờ vả thằng hàm `convertEntityToResponse()` ép mã DTO từ Q1. Nên nghiễm nhiên chữ DropDown CODE ẩn sau Type (VD MÃ "SP") lại tiếp tục được hoán đổi phép lạ biến đắp thành nguyên văn tên Thật Ngữ Tự (VD "Sport") tự động trước khi tới View này! Chắp vá trúng phóc cái Screen 4 trừng mắt ra yêu cầu văn kiện!

## 2. Giao diện xem đối tượng tĩnh (`view.html`)
- Mạn phép tạo bộ khung box giống màn Add cồng kềnh (Q3) nhưng chôn 1 chữ khóa cực kỳ uy tín quyền lực là **`readonly`** của ngôn ngữ HTML vào mạn vạt đuôi của thẻ `<input>`. Lập tức khóa cứng họng cái text field từ "có thể gõ phím" sang "trật tự cấm gõ". Trông hệt 1 lồng kiếng.
- Chế thêm phụ phí tĩnh chữ "(read-only)" gán thẳng vào ngoài viền Input để phục vụ mục đích kiểm định đồ họa Screen 4. Cẩn tắc vô áy náy bám trọn layout rờn tóc gáy của giám thị.
- Các thuộc tính đều nạp tự động thông qua `th:value="${shoeDTO.shoesNo}"` v.v. Không hề dùng `th:field` (vì không còn gửi form post nữa).
- Bổ sung nút bấm bọc viền `<a th:href="@{/}"><button>` mang tên **[ Back ]** quay xe chấn chỉnh về lại gốc trang List. Xúi giục hoàn thành mệnh lệnh (4.2).

---

💡 **HƯỚNG DẪN KHI ĐỀ THI LÀ MỘT ĐỐI TƯỢNG KHÁC (Không phải Shoe)**:
- **Thymeleaf Template `view.html`**: Các thao tác setup form và CSS lăng nhăng thì gần như được tái chế 100%. Hãy thay đổi tiêu đề chữ trong ngoặc, chà sát Tên Input Field sao cho đúng với bản vẽ mô tả Screen 4 của đề bạn đang làm (Cấm làm thiếu input label!). Chữ hiển thị phải chuẩn xác Label trên giấy thi.
- Phải nhạy bén với thuộc tính liên kết ID với Bảng Ngoại: Ví dụ Khách Sạn `Room` thuộc nhánh `RoomType`, ở trang đầu (Q1) nó bắt hiện `Deluxe`, thì không đời nào mang xuống Q4 này nó lại bảo bắt in `L123_Code` ngượng nghịu hết nấc cả! Vì DTO chúng ta đã map chữ gốc `Deluxe` rồi thì cứ thế quăng `readonly` cho vào tag thôi chứ không cần thả mảng Dropdown nữa nghen.
- Rất hiếm khi đề bắt xài DropDown mờ (Disabled Dropdown) ở trang View. Chắc chắn 99.9% là nó bắt in Form Input Text dán nhãn `readonly`. Mẹo lấy full điểm cho form tĩnh đó!
