package com.MyProject;

public class Dev {
    private int age;
    private Computer computer;
    public Dev(){
    }
    public Dev(int age, String name) {
        this.age = age;
        this.name = name;
    }
    private String name;

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void build(){
        computer.compile();

       System.out.println("started building");
   }
}
