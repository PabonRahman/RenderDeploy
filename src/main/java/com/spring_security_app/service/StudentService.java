package com.spring_security_app.service;


import com.spring_security_app.entity.StudentModel;
import com.spring_security_app.repos.StudentRepository;
import com.spring_security_app.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;


//this method for update and save start
    public void save(StudentModel studentModel){
        if(studentModel.getStudentId()!=null){
            repository.findById(studentModel.getStudentId()).map(old ->{
                old.setStudentName(studentModel.getStudentName());
                old.setAddress(studentModel.getAddress());
                old.setSemisterCost(studentModel.getSemisterCost());
                old.setSemisterNo(studentModel.getSemisterNo());
                return repository.save(old);
            });
        }else {

           repository.save(studentModel);
        }
    }
//this method for update and save end



//this method for get all data start
    public List<StudentModel>getAllss(){
        return repository.findAll();
    }
//this method for get all data end



//this method for single  list start
    public ResponseEntity<Map<String, Object>> getById(String id){

        Integer studentId = Integer.parseInt(id);
       StudentModel model = repository.findById(studentId).orElseThrow(()-> new NotFoundException("Student not found "+ id));

        Map<String, Object> response = new TreeMap<>();
        if (model.getStudentId() != null) {
            response.put("data", model);
            response.put("status_code", 200);
            response.put("status", "Success");
            response.put("reason", "Category found " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status_code", 404);
            response.put("status", "Failed");
            response.put("reason", "Category not found " + id);
            response.put("data", new ArrayList<>());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


    }
//this method for single list end



//this method for delete by id start
    public void deleteStudent(Integer id){
        repository.deleteById(id);
    }
//this method for delete by id end






}
