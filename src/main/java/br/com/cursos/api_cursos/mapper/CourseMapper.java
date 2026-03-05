package br.com.cursos.api_cursos.mapper;

import br.com.cursos.api_cursos.controller.request.CourseCreateRequest;
import br.com.cursos.api_cursos.controller.response.CourseResponse;
import br.com.cursos.api_cursos.entity.CourseEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CourseMapper {

    public List<CourseResponse> toListCourseEntity(List<CourseEntity> courses) {
        return courses.stream()
                .map(CourseMapper::toCourseResponse)
                .toList();
    }

    public static CourseEntity toCourseEntity(CourseCreateRequest courseCreateRequest) {
        return CourseEntity.builder()
                .name(courseCreateRequest.name())
                .category(courseCreateRequest.category())
                .active(courseCreateRequest.active())
                .build();
    }
    public static CourseResponse toCourseResponse(CourseEntity courseEntity){
        return CourseResponse.builder()
                .id(courseEntity.getId())
                .name(courseEntity.getName())
                .category(courseEntity.getCategory())
                .active(courseEntity.isActive())
                .created_at(courseEntity.getCreatedAt())
                .updated_at(courseEntity.getUpdatedAt())
                .build();
    }
}
