package br.com.cursos.api_cursos.service;

import br.com.cursos.api_cursos.controller.request.CourseCreateRequest;
import br.com.cursos.api_cursos.controller.response.CourseResponse;
import br.com.cursos.api_cursos.entity.CourseEntity;
import br.com.cursos.api_cursos.mapper.CourseMapper;
import br.com.cursos.api_cursos.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public CourseResponse create(CourseCreateRequest request){
        CourseEntity course = CourseMapper.toCourseEntity(request);
        CourseEntity courseSaved = courseRepository.save(course);
        return CourseMapper.toCourseResponse(courseSaved);
    }

    public List<CourseResponse> getAll(String name, String category){

        List<CourseEntity> courses = courseRepository.findAll();

        if (name != null && category != null) {
            List<CourseEntity> coursesFiltered = courses.stream()
                    .filter(course -> {
                        return course.getName().toLowerCase().contains(name.toLowerCase()) || course.getCategory().toLowerCase().contains(category.toLowerCase());
                    }).toList();

            return CourseMapper.toListCourseEntity(coursesFiltered);
        }

        if(name != null){
            List<CourseEntity> coursesFiltered = courses.stream()
                    .filter(course -> course.getName()
                            .toLowerCase()
                            .contains(name.toLowerCase()))
                    .toList();
            return CourseMapper.toListCourseEntity(coursesFiltered);
        }

        if(category != null){
            List<CourseEntity> coursesFiltered = courses.stream()
                    .filter(course -> course.getCategory()
                            .toLowerCase()
                            .contains(category.toLowerCase()))
                    .toList();
            return CourseMapper.toListCourseEntity(coursesFiltered);
        }


        return CourseMapper.toListCourseEntity(courses);
    }


}
