package com.api.crud.controller;

import com.api.crud.entity.Users;
import com.api.crud.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return list object"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectAllUsers(){
        logger.info("Select all users from table");
        List<Users> usersList = usersService.selectAll();
        return ResponseEntity.ok().body(usersList);
    }

    @PostMapping(path = "/")
    @Operation(summary = "Register an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return register user"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> addUser(@Valid @RequestBody Users users){
        Users returnAddUser = usersService.selectById(users.getId());
        if (returnAddUser==null){
            usersService.insert(users);
            return new ResponseEntity<>("User saved successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User does exist or empty request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return a user"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> selectByID(@PathVariable Integer id){
        logger.info("Find user with id {}", id);
        Optional<Users> persistedObject = Optional.ofNullable(usersService.selectById(id));
        return new ResponseEntity<>(persistedObject.orElseThrow(), HttpStatus.OK);
    }

    @GetMapping(value = "/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a user by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return a user"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object>selectByName(@PathVariable String name){
        logger.info("Find user with name {}", name);
        Optional<Users> persistedObject = Optional.ofNullable(usersService.selectByName(name));
        return new ResponseEntity<>(persistedObject.orElseThrow(),HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return update user"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> updateUsers(@RequestBody Users users){
        logger.debug("REST request to update users : {}", users);
        Users returnUser= usersService.selectById(users.id);
        if(returnUser!=null){
            usersService.update(users);
            return new ResponseEntity<>("Update successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("User does not exit",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return Deleted successfully | Department does NOT exist"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!")
    })
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id){
        logger.debug("REST request to delete user with id: {}", id);
        Users returnStudent = usersService.selectById(id);
        if(returnStudent!=null){
            usersService.delete(returnStudent);
            return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("User does not exit ",HttpStatus.BAD_REQUEST);
    }
}
