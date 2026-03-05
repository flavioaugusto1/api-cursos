package br.com.cursos.api_cursos.service;

import br.com.cursos.api_cursos.controller.request.CourseCreateRequest;
import br.com.cursos.api_cursos.controller.response.CourseResponse;
import br.com.cursos.api_cursos.entity.CourseEntity;
import br.com.cursos.api_cursos.exception.CourseNotFoundException;
import br.com.cursos.api_cursos.mapper.CourseMapper;
import br.com.cursos.api_cursos.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public CourseResponse getById(UUID id){
        CourseEntity course = courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
        return CourseMapper.toCourseResponse(course);
    }

    public CourseResponse updateCourse(UUID id, CourseCreateRequest request){
        Optional<CourseEntity>courseEntity = courseRepository.findById(id);

        if (courseEntity.isPresent()) {
            CourseEntity course = courseEntity.get();

            Optional.ofNullable(request.name())
                    .ifPresent(course::setName);

            Optional.ofNullable(request.category())
                    .ifPresent(course::setCategory);

            Optional.ofNullable(request.active())
                    .ifPresent(course::setActive);

            return CourseMapper.toCourseResponse(courseRepository.save(course));
        }

        throw new CourseNotFoundException();
    }

    public void updateActive(UUID id){
        Optional<CourseEntity> course = courseRepository.findById(id);

        if (course.isPresent()) {
            course.get().setActive(!course.get().isActive());
            courseRepository.save(course.get());
            return;
        }

        throw new CourseNotFoundException();
    }

    public void delete(UUID id) {
        Optional<CourseEntity> courseExists = courseRepository.findById(id);

        if (courseExists.isPresent()) {
            courseRepository.deleteById(id);
            return;
        }

        throw new CourseNotFoundException();
    }
}
