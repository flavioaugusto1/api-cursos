package br.com.cursos.api_cursos.controller;

import br.com.cursos.api_cursos.controller.request.CourseCreateRequest;
import br.com.cursos.api_cursos.controller.response.CourseResponse;
import br.com.cursos.api_cursos.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/new")
    public ResponseEntity<Object> create(@RequestBody CourseCreateRequest courseCreateRequest) {
        try{
            CourseResponse courseResponse = courseService.create(courseCreateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
        try{
            List<CourseResponse> courses = courseService.getAll(name, category);
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        try{
            CourseResponse courseResponse = courseService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try{
            courseService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Course deleted");
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> updateActive(@PathVariable UUID id) {
        try{
            courseService.updateActive(id);
            return ResponseEntity.status(HttpStatus.OK).body("Course updated");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody CourseCreateRequest courseCreateRequest) {
        try {
            CourseResponse courseUpdated = courseService.updateCourse(id, courseCreateRequest);
            return ResponseEntity.status(HttpStatus.OK).body(courseUpdated);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
