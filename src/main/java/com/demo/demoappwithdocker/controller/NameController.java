package com.demo.demoappwithdocker.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/names")
public class NameController {
    @Value("${app.file.path}")
    private String filepath;
    @PostMapping
    public String addName(@RequestBody String name) throws IOException {

        File file = new File(filepath);

        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(name.trim() + System.lineSeparator());
        }

        return "Name saved successfully";
    }
    @GetMapping

    public List<String>getName() throws  IOException{
        Path path= Paths.get(filepath);
        if(!Files.exists(path)){
            return List.of();
        }
        return  Files.readAllLines(path);
    }

    @DeleteMapping("{name}")
    public  String deleteName(@PathVariable String name) throws  IOException{
        Path path= Paths.get(filepath);
        if(!Files.exists(path)){
            return "File doesn't exists";
        }
          List<String>updatedNames=   Files.readAllLines(path)
                     .stream()
                     .map(String::trim)
                  .collect(Collectors.toList());
        Files.write(path, updatedNames);

        return "Name deleted successfully";

    }
}
