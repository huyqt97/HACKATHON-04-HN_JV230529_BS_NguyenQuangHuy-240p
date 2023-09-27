package ra.run;

import ra.model.Mark;
import ra.model.Student;
import ra.model.Subject;

import java.util.Scanner;

public class SchoolManagement {
    static Student student = new Student();
    static Subject subject = new Subject();
    static Mark mark = new Mark();
    static Student[] students = new Student[100];
    static Subject[] subjects = new Subject[100];
    static Mark[] marks = new Mark[100];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        schoolManagement();
    }

    public static void schoolManagement() {
        System.out.println("************************SCHOOL-MANAGEMENT*************************");
        System.out.println("1. Quản lý học sinh");
        System.out.println("2. Quản lý môn học");
        System.out.println("3. Quản lý điểm thi");
        System.out.println("4. Thoát");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                studentManagement();
                break;
            case 2:
                subjectManagement();
                break;
            case 3:
                markManagement();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.err.println("Không hợp lệ ! mời nhập lại");
        }
    }

    public static void studentManagement() {
        System.out.println("**********************STUDENT-MANAGEMENT************************");
        System.out.println("1. Thêm mới học sinh");
        System.out.println("2. Hiển thị danh sách tất cả học sinh đã lưu trữ");
        System.out.println("3. Thay đổi thông tin học sinh theo mã id");
        System.out.println("4. Xóa học sinh theo mã id (kiểm tra xem nếu sinh viên có điểm thi thì không xóa ");
        System.out.println("5. Thoát");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                CreateStudent();
                break;
            case 2:
                displayStudent();
                break;
            case 3:
                updateStudent();
                break;
            case 4:
                deleteStudent();
                break;
            case 5:
                schoolManagement();
                break;
            default:
                System.err.println("Không hợp lệ! mời nhập lại");
                studentManagement();
        }
    }

    public static void CreateStudent() {
        student.inputData(students, autoIdStudent());
        studentManagement();
    }

    public static void displayStudent() {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                student.displayData(students[i]);
            }
        }
        studentManagement();
    }

    public static void updateStudent() {
        System.out.println("Nhập ID sinh viên muộn sửa : ");
        int choice = Integer.parseInt(sc.nextLine());
        int id = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getStudentId() == choice) {
                id = i;
            }
        }
        if (id != -1) {
            student.updateStudent(students[choice], students);
            System.out.println("sửa Thông tin thành công!");
            studentManagement();
        } else {
            System.err.println("Không tìm thấy Sinh viên");
            studentManagement();
        }
    }

    public static void deleteStudent() {
        System.out.println("Nhập id Sinh viên muốn xóa :");
        int choice = Integer.parseInt(sc.nextLine());
        int id = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getStudentId() == choice) {
                id = i;
            }
        }
        if (id != -1) {
            students[id] = null;
            System.out.println("xóa thành công");
            studentManagement();
        } else {
            System.err.println("Không tìm thấy Sinh viên muốn xóa");
            studentManagement();
        }
    }

    public static int autoIdStudent() {
        int max = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getStudentId() > max) {
                max = students[i].getStudentId();
            }
        }
        return max + 1;
    }

    public static void subjectManagement() {
        System.out.println(" **********************SUBJECT-MANAGEMENT*************************");
        System.out.println("1.Thêm mới môn học");
        System.out.println("2.Hiển thị danh sách tất cả môn học đã lưu trữ");
        System.out.println("4.Xóa môn học theo mã id (kiểm tra xem nếu môn học  có điểm thi thì không xóa được)");
        System.out.println("5.Thoát");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                createSubject();
                break;
            case 2:
                displaySubject();
                break;
            case 3:
                updateSubject();
                break;
            case 4:
                deleteSubject();
                break;
            case 5:
                schoolManagement();
                break;
            default:
                System.err.println("không hợp lẹ! mời nhập lại.");
                subjectManagement();
        }
    }

    public static void createSubject() {
        subject.inputData(subjects);
        subjectManagement();
    }

    public static void displaySubject() {
        for (Subject value : subjects) {
            if (value != null) {
                subject.displayData(value);
            }
        }
        subjectManagement();
    }

    public static void updateSubject() {
        displaySubject();
        System.out.println("Nhập mã Môn học muốn sửa");
        String id = sc.nextLine();
        int check = -1;
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i] != null && subjects[i].getSubjectId().equals(id)) {
                check = i;
            }
        }
        if (check != -1) {
            System.out.println("Nhập tên môn học:");
            while (true) {
                String name = sc.nextLine();
                boolean nameExists = false;
                for (int i = 0; i < subjects.length; i++) {
                    if (subjects[i] != null && subjects[i].getSubjectName().equals(name)) {
                        nameExists = true;
                        System.err.println("Tên Môn học đã tồn tại!");
                        break;
                    }
                }
                if (!nameExists) {
                    subjects[check].setSubjectName(name);
                    break;
                }
            }
            System.out.println("sửa thành công!");
            subjectManagement();
        }
    }

    public static void deleteSubject() {
        System.out.println("Nhập ID Môn học muốn xóa");
        String id = sc.nextLine();
        int indexToDelete = -1;

        // Kiểm tra xem môn học có tồn tại trong mảng subjects và có liên quan đến dữ liệu sinh viên hay không
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i] != null && subjects[i].getSubjectId().equals(id)) {
                // Kiểm tra xem môn học có liên quan đến dữ liệu sinh viên không
                boolean hasStudentData = false;
                for (int j = 0; j < marks.length; j++) {
                    if (marks[j] != null && marks[j].getSubject().getSubjectId().equals(id)) {
                        hasStudentData = true;
                        break;
                    }
                }

                if (!hasStudentData) {
                    indexToDelete = i;
                    break;
                } else {
                    System.out.println("Môn học không thể xóa vì có dữ liệu sinh viên");
                }
            }
        }

        if (indexToDelete != -1) {
            subjects[indexToDelete] = null;
            System.out.println("Xóa môn học thành công");
        } else {
            System.err.println("Không tìm thấy hoặc không thể xóa môn học");
        }
    }


    public static void markManagement() {
        System.out.println(" *********************MARK-MANAGEMENT************************");
        System.out.println("1. Thêm mới điểm thi cho 1 sinh viên\t\t\t\t\t\t\n" +
                "2. Hiển thị danh sách tất cả điểm thi theo thứ tự điểm tăng dần\t\t    \n" +
                "3. Thay đổi điểm theo mã id\t\t\t\t\t\t\t\n" +
                "4. Xóa điểm theo mã id\t\t\t\t\t\t\t\n" +
                "5. Hiển thị danh sách điểm thi theo mã môn học \n" +
                "6. Hiển thị đánh giá học lực của từng học sinh theo mã môn học (giả sử <5 là yếu , <=6.5 là trung bình, <= 8 là khá, <= 9 là giỏi, còn lại là xuất sắc).\n" +
                "7. Thoát");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                createMark();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                schoolManagement();
                break;
            default:
                System.err.println("Không hợp lệ mời nhập lại");
        }
    }

    public static void createMark() {
        int emptyIndex = -1;
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] == null) {
                emptyIndex = i;
                break;
            }
        }

        if (emptyIndex != -1) {
            marks[emptyIndex] = new Mark();
            int markId = autoIdMark();
            marks[emptyIndex].setMarkId(markId);

            // Hiển thị danh sách sinh viên để người dùng chọn
            System.out.println("Danh sách sinh viên:");
            for (int i = 0; i < students.length; i++) {
                if (students[i] != null) {
                    System.out.println(students[i].getStudentId() + ". " + students[i].getStudentName());
                }
            }
            System.out.print("Nhập ID sinh viên: ");
            int studentId = Integer.parseInt(sc.nextLine());

            // Tìm sinh viên trong danh sách
            Student selectedStudent = null;
            for (Student student : students) {
                if (student != null && student.getStudentId() == studentId) {
                    selectedStudent = student;
                    break;
                }
            }

            if (selectedStudent != null) {
                marks[emptyIndex].setStudent(selectedStudent);

                // Hiển thị danh sách môn học để người dùng chọn
                System.out.println("Danh sách môn học:");
                for (int i = 0; i < subjects.length; i++) {
                    if (subjects[i] != null) {
                        System.out.println(subjects[i].getSubjectId() + ". " + subjects[i].getSubjectName());
                    }
                }
                System.out.print("Nhập ID môn học: ");
                String subjectId = sc.nextLine();

                // Tìm môn học trong danh sách
                Subject selectedSubject = null;
                for (Subject subject : subjects) {
                    if (subject != null && subject.getSubjectId().equals(subjectId)) {
                        selectedSubject = subject;
                        break;
                    }
                }

                if (selectedSubject != null) {
                    marks[emptyIndex].setSubject(selectedSubject);

                    // Nhập điểm
                    System.out.print("Nhập điểm: ");
                    double point = Double.parseDouble(sc.nextLine());

                    if (point >= 0 && point <= 10) {
                        marks[emptyIndex].setPoint(point);
                        System.out.println("Đã tạo điểm thành công");
                    } else {
                        System.err.println("Điểm không hợp lệ");
                    }
                } else {
                    System.err.println("Môn học không tồn tại");
                }
            } else {
                System.err.println("Sinh viên không tồn tại");
            }
        } else {
            System.err.println("Không còn vị trí trống để tạo điểm");
        }
    }
    public static int autoIdMark() {
        int max = 0;
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] != null && marks[i].getMarkId() > max) {
                max = marks[i].getMarkId();
            }
        }
        return max + 1;
    }
}
