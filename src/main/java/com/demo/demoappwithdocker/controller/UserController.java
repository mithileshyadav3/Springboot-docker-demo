package com.demo.demoappwithdocker.controller;

import com.demo.demoappwithdocker.entity.Users;
import com.demo.demoappwithdocker.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
     @PostMapping("/addusers")
     public String addUsers(@RequestBody Users users){
           Users user= userRepo.save(users);
           return  "User added successfully";
     }
    @GetMapping("/allusers")

    public List<Users>  allUsers(){
          List<Users>users=userRepo.findAll();
             users= users.stream()
                      .filter(user->user.getAge()>10)
                      .collect(Collectors.toList());
        return  users;
    }
    @GetMapping("/test")
    public  String showMessage(){
        return  "hello docker";
    }
}
