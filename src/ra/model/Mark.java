package ra.model;

public class Mark {
    private int markId;
    private Student student = new Student();
    private Subject subject = new Subject();
    private double point;

    public Mark() {
    }

    public Mark(int markId, Student student, Subject subject, double point) {
        this.markId = markId;
        this.student = student;
        this.subject = subject;
        this.point = point;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public void displayData(Subject subject){
        System.out.println("Mark : " +
                "markId = " + subject.getSubjectId() +
                ", student = " + subject +
                ", subject = " + subject +
                ", point = " + point);
    }
}
