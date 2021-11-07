package com.example.demo;

public class PersonDTO {

    public PersonDTO() {
    }

    public PersonDTO(Integer id, String name) {
        Id = id;
        Name = name;
    }

    public Integer Id;
    public String Name;

    @Override
    public String toString() {
        return "PersonDTO{" + "Id=" + Id +
                ", Name='" + Name + '\'' + '}';
    }
}