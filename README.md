# MANA-STUDENT 🎓

Một ứng dụng được thiết kế để giúp sinh viên quản lý lịch học, bài tập và các hoạt động trong cuộc sống học đường một cách hiệu quả, tích hợp trợ lý AI để hỗ trợ học tập.

---

## 🛠️ Công nghệ sử dụng

Dự án này được xây dựng theo kiến trúc client-server:

- **Client App (Frontend):** Vite, Vue.js, TypeScript, Tailwind CSS
- **Server (Backend):** Java, Spring Boot, Maven

---

## 🏃 Hướng dẫn cài đặt và chạy ứng dụng

Để chạy dự án này, bạn cần khởi động cả **Frontend** và **Backend**.

### 1. Backend (Server)

Yêu cầu: Đã cài đặt **Java (JDK)** và **Maven**.

```bash
# Di chuyển vào thư mục backend
cd student-life-backend

# Cài đặt các dependencies
mvn install

# Khởi động server
mvn spring-boot:run
