-- Tạo cơ sở dữ liệu
CREATE DATABASE e_learning12;
USE e_learning12;

-- Bảng Role (Danh sách các vai trò)
CREATE TABLE Role (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Role_Name NVARCHAR(50) NOT NULL
);

CREATE TABLE Status_Management (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Status_Code NVARCHAR(20) NOT NULL, 
    Type NVARCHAR(20) NOT NULL, -- Loại trạng thái (User, Course, Request)
    Description NVARCHAR(255) NULL -- Mô tả trạng thái
);

CREATE TABLE User (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    User_Code NVARCHAR(50) UNIQUE NOT NULL, 
	Encryption_Key NVARCHAR(255) NULL, -- key mã hoá
    Name NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255) UNIQUE NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    Phone NVARCHAR(20),
    Address NVARCHAR(255),
    Gender INT(1) NOT NULL DEFAULT 0, 
    Date_Of_Birth DATETIME NULL,
    Role_ID INT NOT NULL,
    Status_Code NVARCHAR(20) NOT NULL,
    Experience INT NULL,
    Certification NVARCHAR(255) NULL,
    Created_By NVARCHAR(50) NULL,
    Updated_By NVARCHAR(50) NULL,
    Created_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Updated_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (Role_ID) REFERENCES Role(ID) ON DELETE CASCADE
);

CREATE TABLE System_Function (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Function_Name NVARCHAR(255) NOT NULL,
    Description NVARCHAR(255) NULL
);
 
CREATE TABLE Role_Function (
    Role_ID INT,
    Function_ID INT,
    PRIMARY KEY (Role_ID, Function_ID),
    FOREIGN KEY (Role_ID) REFERENCES Role(ID) ON DELETE CASCADE,
    FOREIGN KEY (Function_ID) REFERENCES System_Function(ID) ON DELETE CASCADE
);

-- Bảng Course (Lưu thông tin khóa học)
CREATE TABLE Course (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Course_Code NVARCHAR(20) NOT NULL,
    Course_Name NVARCHAR(255) NOT NULL,
    Description NVARCHAR(255),
    Learning_Outcome TEXT NULL,
    Background_Img TEXT NULL,
    Start_Date DATETIME,
    End_Date DATETIME,
    Lesson_Count INT NOT NULL DEFAULT 0,
    Status_Code NVARCHAR(20) NOT NULL,
    Created_By NVARCHAR(50) NULL,
    Updated_By NVARCHAR(50) NULL,
    Created_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Updated_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Sửa bảng Course_Detail để lưu danh sách video dưới dạng JSON
CREATE TABLE Lesson_Detail (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Lesson_Code NVARCHAR(20) NOT NULL,
    Course_ID INT NOT NULL,
    Lesson_Order INT NOT NULL,
    Lesson_Name NVARCHAR(255) NOT NULL,
    Video_Link TEXT NULL,
    Resource_Link TEXT NULL,
    FOREIGN KEY (Course_ID) REFERENCES Course(ID) ON DELETE CASCADE
);
-- Bảng Chat_Message
CREATE TABLE Lesson_comment (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Course_Code INT NOT NULL,
    Lesson_ID INT NOT NULL,
    Send_User_ID NVARCHAR(50) NOT NULL,
    Message NVARCHAR(300) NOT NULL,
    Comment_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Lưu thời gian gửi tin nhắn
    FOREIGN KEY (Lesson_ID) REFERENCES Lesson_Detail(ID) ON DELETE CASCADE,
    FOREIGN KEY (Send_User_ID) REFERENCES User(User_Code) ON DELETE CASCADE
);
CREATE TABLE Instructor_Enrollment (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Instructor_ID INT NOT NULL,
    Course_ID INT NOT NULL,
    FOREIGN KEY (Instructor_ID) REFERENCES User(ID) ON DELETE CASCADE,
    FOREIGN KEY (Course_ID) REFERENCES Course(ID) ON DELETE CASCADE
);

CREATE TABLE Student_Enrollment (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Student_ID INT NOT NULL,
    Course_ID INT NOT NULL,
    Enrollment_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Progress_Percentage INT NOT NULL DEFAULT 0,
    Note NVARCHAR(255) NULL,
    FOREIGN KEY (Student_ID) REFERENCES User(ID) ON DELETE CASCADE,
    FOREIGN KEY (Course_ID) REFERENCES Course(ID) ON DELETE CASCADE
);

CREATE TABLE Notification (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Course_ID INT NOT NULL, 
    Message NVARCHAR(255) NOT NULL,
    Created_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (Course_ID) REFERENCES Course(ID) ON DELETE CASCADE 
);

/*
CREATE TABLE Request (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Request_Code NVARCHAR(50) NOT NULL,
    User_Request INT NOT NULL,
    Request_Type NVARCHAR(50) NOT NULL,
    Course_Code NVARCHAR(50) NULL, 
    Status_Code NVARCHAR(20), 
    Created_By NVARCHAR(50) NULL,
    Updated_By NVARCHAR(50) NULL,
    Created_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Updated_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (User_Request) REFERENCES User(ID) ON DELETE CASCADE,
    CHECK (
        (Request_Type = 'course_open' AND Course_Code IS NULL) OR 
        (Request_Type = 'course_registor' AND Course_Code IS NOT NULL)
    )
);
*/


-- --------------------------------------
DELIMITER $$

CREATE TRIGGER trg_before_insert_user
BEFORE INSERT ON User
FOR EACH ROW
BEGIN
    DECLARE max_code VARCHAR(10);
    DECLARE next_number INT;

    -- Lấy giá trị lớn nhất của User_Code trong bảng
    SELECT RIGHT(User_Code, 4) INTO max_code FROM User ORDER BY User_Code DESC LIMIT 1;

    -- Kiểm tra nếu không có dữ liệu thì bắt đầu từ 1, nếu có thì tăng lên 1
    IF max_code IS NULL THEN
        SET next_number = 1;
    ELSE
        SET next_number = max_code + 1;
    END IF;

    -- Gán giá trị mới cho User_Code với định dạng US0001
    SET NEW.User_Code = CONCAT('US', LPAD(next_number, 4, '0'));
END$$

DELIMITER ;



-- --------------------------------------
-- Thêm dữ liệu vào bảng Role
INSERT INTO Role (Role_Name) VALUES ('Admin'), ('Instructor'), ('Student');

-- Thêm dữ liệu vào bảng Status_Management
INSERT INTO Status_Management (Status_Code, Type, Description) VALUES
('ACTIVE', 'User', 'Người dùng đang hoạt động'),
('INACTIVE', 'User', 'Người dùng bị khóa'),
('ACTIVE', 'Course', 'Khóa học đang mở'),
('INACTIVE', 'Course', 'Khóa học bị đóng');

