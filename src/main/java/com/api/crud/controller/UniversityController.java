package com.api.crud.controller;

import com.api.crud.entity.University;
import com.api.crud.service.UniversityService;
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
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    UniversityService universityService;

    @GetMapping(path = "/")
    @Operation(summary = "Get all university")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return list object"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectAllUniversity(){
        List<University> teachersList = universityService.selectAll();
        return ResponseEntity.ok().body(teachersList);
    }

    @PostMapping(path = "/")
    @Operation(summary = "Register university")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return register university"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> addUniversity(@Valid @RequestBody University university){
        universityService.insert(university);
        return new ResponseEntity<>("university saved successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    @Operation(summary = "Get a university by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return a university"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectByID(@PathVariable Integer id){
        Optional<University> persistedObject = Optional.ofNullable(universityService.selectById(id));
        return new ResponseEntity<>(persistedObject.orElseThrow(),HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    @Operation(summary = "Get a university by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return a university"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectByName(@PathVariable String name){
        Optional<University> persistedObject = Optional.ofNullable(universityService.selectByName(name));
        return new ResponseEntity<>(persistedObject.orElseThrow(),HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    @Operation(summary = "Update a university")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return update university"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> updateUniversity(@Valid @RequestBody University university){
        University returnStudent= universityService.selectById(university.id);
        if(returnStudent != null){
            universityService.update(university);
            return new ResponseEntity<>("Update successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("university does not exit",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a university")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return delete university"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> deleteUniversity(@PathVariable Integer id){
        University returnStudent = universityService.selectById(id);
        if(returnStudent!=null){
            universityService.delete(returnStudent);
            return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("University does not exit ",HttpStatus.BAD_REQUEST);
    }
}
