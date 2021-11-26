package com.api.crud.controller;

import com.api.crud.entity.Students;
import com.api.crud.service.StudentService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(path = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return list object"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectAllStudents(){
        List<Students> studentsList = studentService.selectAll();
        return ResponseEntity.ok().body(studentsList);
    }

    @PostMapping(path= "/")
    @Operation(summary = "Register student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return register student"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> addStudent(@Valid @RequestBody Students students){
        studentService.insert(students);
        return new ResponseEntity<>("Students saved successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    @Operation(summary = "Get a student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return a student"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectByID(@PathVariable Integer id){
        Optional<Students> persistedObject = Optional.ofNullable(studentService.selectById(id));
        return new ResponseEntity<>(persistedObject.orElseThrow(),HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    @Operation(summary = "Get a student by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return a student"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectByName(@PathVariable String name){
        Optional<Students> persistedObject = Optional.ofNullable(studentService.selectByName(name));
        return new ResponseEntity<>(persistedObject.orElseThrow(),HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    @Operation(summary = "Update a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return update student"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> updateStudent(@Valid @RequestBody Students students){
        Students returnStudent= studentService.selectById(students.id);
        if(returnStudent != null){
            studentService.update(students);
            return new ResponseEntity<>("Update successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Student does not exit",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return Deleted successfully | Department does NOT exist"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id){
        Students returnStudent = studentService.selectById(id);
                if(returnStudent!=null){
                    studentService.delete(returnStudent);
                    return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
                }
        return new ResponseEntity<>("Student does not exit ",HttpStatus.BAD_REQUEST);
    }

}
