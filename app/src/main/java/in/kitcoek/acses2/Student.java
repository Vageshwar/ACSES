package in.kitcoek.acses2;

public class Student {

    private String Name,Email,Mobile,College,SerialNo;


    public  Student()
    {

    }

    public Student(String serialNo, String name, String email, String mobile, String college) {
        Name = name;
        Email = email;
        Mobile = mobile;
        College = college;
        SerialNo = serialNo;
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String serialNo) {
        SerialNo = serialNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }
}
