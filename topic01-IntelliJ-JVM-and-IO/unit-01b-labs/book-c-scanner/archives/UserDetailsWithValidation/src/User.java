public class User {

    private String name;   // validation - max 20 characters - truncate to 20 chars in constructor if > 20
    private String address;  // validation - max 30 characters - truncate to 20 chars in constructor if > 30
    private String dateOfBirth;   // validation - max 10 characters - truncate to 10 chars in constructor if > 10
    private double height = 0.5;   // 0.5 <= height <= 3    (metres) - default to 0.5

    public User(String name, String address, String dateOfBirth, double height) {
        if (name.length() <= 20)
            this.name = name;
        else
            this.name = name.substring(0,20);
        if (address.length() <= 30)
            this.address = address;
        else
            this.address = address.substring(0,30);
        if (dateOfBirth.length() <= 10)
            this.dateOfBirth = dateOfBirth;
        else
            this.dateOfBirth = dateOfBirth.substring(0,10);

        setHeight(height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() <= 20)
            this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {

        if (address.length() <= 30)
            this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {

        if (dateOfBirth.length() <= 10)
            this.dateOfBirth = dateOfBirth;
    }

    public double getAge() {
        return height;
    }

    public void setHeight(double height) {

        if ((height >=.5) && (height <= 3.0) )
            this.height = height;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", height=" + height +
                '}';
    }
}
