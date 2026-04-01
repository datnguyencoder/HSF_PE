# Mẹo Đổi Tên Biến Giữa Controller Và Thymeleaf (Refactoring Guide)

Bất cứ khi nào bạn đi thi và bốc vào một đề khác (Ví dụ: `Student`, `Book`, `Product`...) thay vì `Shoe`, nút thắt quan trọng nhất thường làm sinh viên "vỡ trận" chính là **sự liên kết (Mapping) giữa Java Controller và HTML Thymeleaf**.

Tài liệu riêng này là "phao cứu sinh" chỉ rõ nguyên lý đổi tên biến mượt mà nhất mà không bị lỗi 500 của Web.

## 1. Nguyên lý Bất Di Bất Dịch: "Chữ gì ở Java thì chữ đó ở HTML"

Thymeleaf (trong file HTML) không thể tự hiểu ngầm các file Java của bạn tên gì. Nó chỉ nhận dữ liệu thông qua cái cầu nối tên là **Model** của Spring Boot, với cú pháp `model.addAttribute("TÊN_BIẾN", kết_quả)`.

**Ví dụ thực tế (Từ việc bạn vừa thử sửa chữ `shoe` thành `dat` ở file `delete.html`):**

Vì trong file `delete.html` ở dòng 38 bạn vừa đổi biến đại diện thành chữ `dat`:
```html
<!-- Dữ liệu HTML của bạn: -->
Are you sure you want to delete "<span th:text="${dat.shoesName}"></span>"?
```
Thì bắt buộc 100% trong `ShoeController.java`, chui vào lòng hàm `showDeleteConfirmation`, bạn CŨNG PHẢI đổi KEY string `"shoe"` thành `"dat"` y hệt như sau thì web mới chạy (nếu không đổi nó sẽ nổ lỗi Error 500):
```java
@GetMapping("/delete/{id}")
public String showDeleteConfirmation(...) {
    // Chữ "dat" ở đây KHỚP từng ký tự với ${dat...} trên HTML
    model.addAttribute("dat", shoeService.getById(id)); 
    return "delete";
}
```
**=> QUY TẮC VÀNG:** Tên khai báo trong ngoặc kép của hàm `.addAttribute("TÊN_CHUYỀN", ...)` chính là cái vỏ bọc để HTML gọi ra qua ký pháp `${TÊN_CHUYỀN}`.

---

## 2. Các Mẫu Thay Đổi Cần Phải Đổi Song Song Khi Gặp Đề Khác (Ví dụ đổi sang `Book`)

### A. Khi in Mảng Danh sách (List / Table) - Câu Q1
- **File Java Controller (Bên Gửi):**
  ```java
  // CŨ (Đề Shoes):
  model.addAttribute("shoes", shoeService.getAll());
  
  // MỚI (Đề Sách Book):
  model.addAttribute("books", bookService.getAll());
  ```
- **File HTML chứa Table (Bên Nhận):**
  ```html
  <!-- CŨ: th:each = "tên_ảo_một_đối_tượng_đại_diện : ${TÊN_CHUYỀN_Ở_MODEL}" -->
  <tr th:each="shoe : ${shoes}">
      <td th:text="${shoe.shoesName}"></td>
  </tr>

  <!-- MỚI (Đề Sách Book): -->
  <tr th:each="book : ${books}">
      <td th:text="${book.title}"></td> <!-- Nhớ đổi thuộc tính lấy trong Class DTO! -->
  </tr>
  ```

### B. Form Lưu Dữ Liệu Thêm Mới / Sửa (Add / Update) - Câu Q3
- **File Java Controller Mở Web:**
  ```java
  // CŨ: Tạo 1 DTO trống ném sang cho HTML
  model.addAttribute("shoeDTO", new ShoeDTO());
  
  // MỚI: 
  model.addAttribute("bookDTO", new BookDTO());
  ```
- **File HTML mở Cấu trúc Form Khai Báo:**
  ```html
  <!-- CŨ: th:object trúng phóc vào "TÊN_CHUYỀN_Ở_MODEL" -->
  <form th:action="@{/add}" th:object="${shoeDTO}" method="post">
      <!-- Dấu * biểu thị cho cái shoeDTO kia, lấy từng attribute rỗng ra -->
      <input th:field="*{shoesName}" />  
  </form>

  <!-- MỚI: -->
  <form th:action="@{/add}" th:object="${bookDTO}" method="post">
      <input th:field="*{title}" />
  </form>
  ```
- **File Java Controller Nhận Cú POST lưu từ bàn phím mổ vào HTML:**
  ```java
  // CŨ: Phải hứng cực kì chính xác bằng @ModelAttribute
  @PostMapping("/add")
  public String processAdd(@Valid @ModelAttribute("shoeDTO") ShoeDTO dto, ...)

  // MỚI:
  @PostMapping("/add")
  public String processAdd(@Valid @ModelAttribute("bookDTO") BookDTO mDto, ...)
  ```

### C. Mảng Menu Sổ Thông Tin Động (Dropdown Box) - Câu Q3
- **File Java Controller (Bên Gửi Dropdown):**
  ```java
  // CŨ: Do đề yêu cầu hiện list SP, TN, RN
  model.addAttribute("types", shoesTypeService.getAllShoesTypes());
  
  // MỚI (Đề Book: Menu Thể Loại Truyện):
  model.addAttribute("categories", categoryService.getAllCategories());
  ```
- **File HTML chứa Menu `<select>` (Bên Nhận):**
  ```html
  <!-- CŨ: -->
  <select th:field="*{type}">
      <option th:each="t : ${types}" th:value="${t.typeCode}" th:text="${t.typeName}">
  </select>

  <!-- MỚI: -->
  <select th:field="*{categoryCode}">
      <!-- Giả sử biến c nhận từ cục ${categories} -->
      <option th:each="c : ${categories}" th:value="${c.catId}" th:text="${c.catName}">
  </select>
  ```

---

## 3. Bắt Bệnh Lỗi Quốc Dân - Error 500 "Property or field cannot be found on null"
Nguyên nhân số 1 làm sinh viên trượt PE vì Web Load ra trắng xoá một chữ Exception màu đỏ.
* **Tình Hướng 1:** Đi sửa File HTML thành `${book.title}`. Nhưng lười / Quên vào Controller cập nhật chữ `shoes` thành cục đá gửi `model.addAttribute("book", ...)` ->  Làm cho Web HTML không tìm được object nạp vào nó bèn phán Null! 
* **Tình Hướng 2:** Sai hoa thường (Tính tự kị Case Insensitive). Trong File DTO bạn rón rén gõ chữ `private String fullName`. Nhưng ngoài giao diện bạn hí hửng ghi `${book.FullName}`. Tiêu cờ! Cả hai bắt buộc phải khớp 100% định dạng.

Trăm hay không bằng tay quen, cứ nhớ câu chú thích màu nhiệm này: **_Đổi tên biến bên Thymeleaf (File HTML) chữ nào, thì lập tức sang Model Attribute của Java Controller đổi String KEY y chang chữ đó!_**
