package com.api.crud.controller;

import com.api.crud.entity.Teachers;
import com.api.crud.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping(path = "/")
    @Operation(summary = "Get all teachers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return list object"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectAllTeacher(){
        List<Teachers> teachersList = teacherService.selectAll();
        return ResponseEntity.ok().body(teachersList);
    }

    @PostMapping(path = "/")
    @Operation(summary = "Register teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return register teacher"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> addTeacher(@Valid @RequestBody Teachers teachers){
        teacherService.insert(teachers);
        return new ResponseEntity<>("Teacher saved successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    @Operation(summary = "Get a teacher by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return a teacher"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectByID(@PathVariable Integer id){
        Optional<Teachers> persistedObject = Optional.ofNullable(teacherService.selectById(id));
        return new ResponseEntity<>(persistedObject.orElseThrow(),HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    @Operation(summary = "Get a teacher by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return a teacher"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectByName(@PathVariable String name){
        Optional<Teachers> persistedObject = Optional.ofNullable(teacherService.selectByName(name));
        return new ResponseEntity<>(persistedObject.orElseThrow(),HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    @Operation(summary = "Update a teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return update teacher"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> updateStudent(@Valid @RequestBody Teachers teachers){
        Teachers returnStudent= teacherService.selectById(teachers.id);
        if(returnStudent != null){
            teacherService.update(teachers);
            return new ResponseEntity<>("Update successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Teacher does not exit",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return Deleted successfully | Teacher does NOT exist"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id){
        Teachers returnStudent = teacherService.selectById(id);
        if(returnStudent!=null){
            teacherService.delete(returnStudent);
            return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Teacher does not exit ",HttpStatus.BAD_REQUEST);
    }
}
