MANA-STUDENT 🎓
Một ứng dụng được thiết kế để giúp sinh viên quản lý lịch học, bài tập và các hoạt động trong cuộc sống học đường một cách hiệu quả, tích hợp trợ lý AI để hỗ trợ học tập.

🛠️ Công nghệ sử dụng
Dự án này được xây dựng theo kiến trúc client-server:

Client App (Frontend): Vite, Vue.js, TypeScript, Tailwind CSS

Server (Backend): Java, Spring Boot, Maven

🏃 Hướng dẫn cài đặt và chạy ứng dụng
Để chạy dự án này, bạn cần khởi động cả Frontend và Backend.

1. Backend (Server)
Yêu cầu: Đã cài đặt Java (JDK) và Maven.

Bash

# Di chuyển vào thư mục backend
cd student-life-backend

# Cài đặt các dependencies
mvn install

# Khởi động server
mvn spring-boot:run
Server sẽ chạy ở http://localhost:8080 (hoặc một cổng khác được cấu hình trong application.properties).

2. Frontend (Client)
Yêu cầu: Đã cài đặt Node.js và npm (hoặc bun).

Bash

# (Nếu bạn đang ở trong thư mục backend, hãy quay lại thư mục gốc)
# cd ..

# Cài đặt các thư viện cần thiết
npm install

# Chạy ứng dụng ở chế độ development
npm run dev
Ứng dụng sẽ có thể truy cập tại http://localhost:5173 (hoặc một cổng khác do Vite chỉ định).

🏗️ Cấu trúc dự án
Dự án được chia thành hai phần chính trong cùng một repository.

📁 MANA-STUDENT/
├── 📁 public/              # Chứa các tài sản tĩnh cho frontend
├── 📁 src/                 # Mã nguồn của ứng dụng Vue.js
├── 📄 tailwind.config.ts    # Cấu hình Tailwind CSS
├── 📄 tsconfig.json        # Cấu hình TypeScript
├── 📄 vite.config.ts       # Cấu hình Vite
├── 📄 package.json         # Quản lý thư viện và script cho frontend
│
└── 📁 student-life-backend/ # Thư mục chứa toàn bộ backend
    ├── 📁 src/             # Mã nguồn Java và Spring Boot
    ├── 📁 target/          # Thư mục chứa file build của backend
    ├── 📄 pom.xml          # File cấu hình Maven cho backend
    └── 📁 logs/            # Chứa file log của ứng dụng
⭐ Tính năng chính (Dự kiến)
📅 Quản lý Lịch học: Tạo và xem thời khóa biểu theo tuần, tháng.

📝 Theo dõi Bài tập: Thêm, sửa, xóa các bài tập và deadline.

🔔 Thông báo Nhắc nhở: Nhận thông báo về các deadline sắp tới.

📓 Ghi chú Môn học: Tạo và quản lý các ghi chú cho từng môn học.

🤖 Trò chuyện với AI: Tích hợp chatbot để hỗ trợ học tập, trả lời câu hỏi và giải đáp thắc mắc nhanh chóng.

📊 Thống kê Học tập: Theo dõi tiến độ và kết quả học tập.

📁 Lưu trữ dữ liệu
Cấu hình ứng dụng: Các file cấu hình được lưu trong mã nguồn.

Dữ liệu người dùng: Được quản lý bởi Spring Boot backend và thường được lưu trữ trong một hệ quản trị cơ sở dữ liệu như PostgreSQL, MySQL, hoặc H2 (được định nghĩa trong file src/main/resources/application.properties của backend).
