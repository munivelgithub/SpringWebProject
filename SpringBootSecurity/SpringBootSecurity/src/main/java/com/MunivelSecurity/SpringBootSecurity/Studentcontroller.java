package com.MunivelSecurity.SpringBootSecurity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Studentcontroller {
    private List<Student> studentList=new ArrayList<>(List.of(new Student(1,"munivel",78)
            ,new Student(2,"gokul",89)));
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudent(){
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> getStudent(@RequestBody  Student student){
        studentList.add(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
//    // to get an token csrf
    @GetMapping("csrf-token")
    public CsrfToken token(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}

