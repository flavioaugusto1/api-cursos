package br.com.cursos.api_cursos.service;

import br.com.cursos.api_cursos.controller.request.CourseCreateRequest;
import br.com.cursos.api_cursos.controller.response.CourseResponse;
import br.com.cursos.api_cursos.entity.CourseEntity;
import br.com.cursos.api_cursos.mapper.CourseMapper;
import br.com.cursos.api_cursos.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public CourseResponse create(CourseCreateRequest request){
        CourseEntity course = CourseMapper.toCourseEntity(request);
        CourseEntity courseSaved = courseRepository.save(course);
        return CourseMapper.toCourseResponse(courseSaved);
    }
}
