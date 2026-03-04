package br.com.cursos.api_cursos.controller;

import br.com.cursos.api_cursos.controller.request.CourseCreateRequest;
import br.com.cursos.api_cursos.controller.response.CourseResponse;
import br.com.cursos.api_cursos.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/new")
    public ResponseEntity<Object> create(CourseCreateRequest courseCreateRequest) {
        try{
            CourseResponse courseResponse = courseService.create(courseCreateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }



}
