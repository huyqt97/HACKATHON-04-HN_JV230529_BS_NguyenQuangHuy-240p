package ra.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Student {
    private int studentId;
    private String studentName;
    private Date birthDay;
    private String address;
    private boolean gender;
    private String phone;

    public Student() {
    }

    public Student(int studentId, String studentName, Date birthDay, String address, boolean gender, String phone) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthDay = birthDay;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void inputData(Student[] students, int autoId) {
        Scanner sc = new Scanner(System.in);
        int id = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                id = i;
                break; // Thoát khỏi vòng lặp khi tìm thấy vị trí null đầu tiên
            }
        }

        if (id != -1) {
            students[id] = new Student();
            students[id].setStudentId(autoId);
            System.out.println("Nhập tên sinh viên");
            String name = sc.nextLine();
            while (name.equals("")) {
                System.err.println("Tên không được để trống!");
                name = sc.nextLine();
            }
            students[id].setStudentName(name);

            boolean validDate = false;
            while (!validDate) {
                try {
                    System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
                    String dateStr = sc.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    students[id].setBirthDay(dateFormat.parse(dateStr));
                    validDate = true;
                } catch (ParseException e) {
                    System.out.println("Ngày không hợp lệ. Vui lòng nhập lại.");
                }
            }

            System.out.println("Nhập địa chỉ:");
            String address = sc.nextLine();
            while (address.equals("")) {
                System.err.println("Địa chỉ không được để trống!");
                address = sc.nextLine();
            }
            students[id].setAddress(address);

            System.out.println("Giới tính (1 - Nam, 2 - Nữ):");
            int sex = Integer.parseInt(sc.nextLine());
            switch (sex) {
                case 1:
                    students[id].setGender(true);
                    break;
                case 2:
                    students[id].setGender(false);
                    break;
                default:
                    System.err.println("Giới tính không hợp lệ. Mời nhập lại.");
                    break;
            }

            System.out.println("Nhập số điện thoại:");
            String phone = sc.nextLine();
            while (true) {
                if (phone.matches("^0[0-9]{9,10}$")) {
                    boolean phoneExists = false;
                    for (int i = 0; i < students.length; i++) {
                        if (i != id && students[i] != null && students[i].getPhone().equals(phone)) {
                            phoneExists = true;
                            System.err.println("Số điện thoại đã tồn tại. Mời nhập lại.");
                            break;
                        }
                    }
                    if (!phoneExists) {
                        students[id].setPhone(phone);
                        break;
                    }
                } else {
                    System.err.println("Số điện thoại không hợp lệ. Mời nhập lại.");
                }
                phone = sc.nextLine();
            }
        }
    }

    public void updateStudent(Student student, Student[] students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên sinh viên:");
        String name = sc.nextLine();
        while (name.equals("")) {
            System.err.println("Tên không được để trống!");
            name = sc.nextLine();
        }
        student.setStudentName(name);

        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
                String dateStr = sc.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                student.setBirthDay(dateFormat.parse(dateStr));
                validDate = true;
            } catch (ParseException e) {
                System.err.println("Ngày không hợp lệ. Vui lòng nhập lại.");
            }
        }

        System.out.println("Nhập địa chỉ:");
        String address = sc.nextLine();
        while (address.equals("")) {
            System.err.println("Địa chỉ không được để trống!");
            address = sc.nextLine();
        }
        student.setAddress(address);

        System.out.println("Giới tính:\n1. Nam\n2. Nữ");
        int sex = Integer.parseInt(sc.nextLine());
        switch (sex) {
            case 1:
                student.setGender(true);
                break;
            case 2:
                student.setGender(false);
                break;
            default:
                System.err.println("Không hợp lệ. Mời nhập lại.");
                break;
        }

        System.out.println("Nhập số điện thoại:");
        String phone = sc.nextLine();
        int index = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getPhone().equals(phone) && !students[i].equals(student)) {
                index = i;
                break;
            }
        }
        while (true) {
            if (phone.matches("^0[0-9]{9,10}$")) {
                if (phone.length() == 10 || phone.length() == 11) {
                    if (index == -1) {
                        student.setPhone(phone);
                        break;
                    } else {
                        System.err.println("Số điện thoại đã tồn tại. Mời nhập lại.");
                    }
                } else {
                    System.err.println("Số điện thoại phải có 10 hoặc 11 số.");
                }
            } else {
                System.err.println("Số điện thoại phải bắt đầu bằng số 0.");
            }
            phone = sc.nextLine();
        }
    }

    public void displayData(Student student) {
        System.out.println("Student : " +
                "studentId = " + student.getStudentId() +
                ", studentName = '" + student.getStudentName() + '\'' +
                ", birthDay = " + student.getBirthDay() +
                ", address = '" + student.getAddress() + '\'' +
                ", gender = " + (student.isGender() ? "Nam" : "Nữ") +
                ", phone = '" + student.getPhone() + '\'');
    }
}
