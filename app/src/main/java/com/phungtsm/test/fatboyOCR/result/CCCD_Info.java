package com.phungtsm.test.fatboyOCR.result;

public class CCCD_Info {
    private String address;
    private String dob;
    private String doe;
    private String gender;
    private String home;
    private String id_number;
    private String name;
    private String nationality;

    @Override
    public String toString() {
        return "CCCD_Info{" +
                "address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", doe='" + doe + '\'' +
                ", gender='" + gender + '\'' +
                ", home='" + home + '\'' +
                ", id_number='" + id_number + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoe() {
        return doe;
    }

    public void setDoe(String doe) {
        this.doe = doe;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
