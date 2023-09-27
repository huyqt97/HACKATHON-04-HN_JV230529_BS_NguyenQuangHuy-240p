package ra.model;

import java.util.Scanner;

public class Subject {
    private String subjectId;
    private String subjectName;

    public Subject() {
    }

    public Subject(String subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void inputData(Subject[] subjects) {
        Scanner sc = new Scanner(System.in);
        int id = -1;
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i] == null) {
                id = i;
                break;
            }
        }

        if (id != -1) {
            subjects[id] = new Subject();
            System.out.println("Nhập mã môn học");
            while (true) {
                String idSubject = sc.nextLine();
                if (idSubject.matches("^MH\\d{3}$")) {
                    boolean idExists = false;
                    for (int i = 0; i < subjects.length; i++) {
                        if (i != id && subjects[i] != null && subjects[i].getSubjectId().equals(idSubject)) {
                            idExists = true;
                            System.err.println("Mã Môn học đã tồn tại!");
                            break;
                        }
                    }
                    if (!idExists) {
                        subjects[id].setSubjectId(idSubject);
                        break;
                    }
                } else {
                    System.err.println("Mã môn học không hợp lệ. Vui lòng nhập lại");
                }
            }
            System.out.println("Nhập tên môn học:");
            while (true) {
                String name = sc.nextLine();
                boolean nameExists = false;
                for (int i = 0; i < subjects.length; i++) {
                    if (i != id && subjects[i] != null && subjects[i].getSubjectName().equals(name)) {
                        nameExists = true;
                        System.err.println("Tên Môn học đã tồn tại!");
                        break;
                    }
                }
                if (!nameExists) {
                    subjects[id].setSubjectName(name);
                    break;
                }
            }
            System.out.println("Thêm mới thành công");
        }
    }


    public void displayData(Subject subject) {
        System.out.println("Subject : " +
                "subjectId = '" + subject.getSubjectId() + '\'' +
                ", subjectName = '" + subject.getSubjectName() + '\'');
    }
}
