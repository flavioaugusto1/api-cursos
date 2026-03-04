package br.com.cursos.api_cursos.mapper;

import br.com.cursos.api_cursos.controller.request.CourseCreateRequest;
import br.com.cursos.api_cursos.controller.response.CourseResponse;
import br.com.cursos.api_cursos.entity.CourseEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CourseMapper {
    public static CourseEntity toCourseEntity(CourseCreateRequest courseCreateRequest) {
        return CourseEntity.builder()
                .name(courseCreateRequest.name())
                .category(courseCreateRequest.category())
                .build();
    }
    public static CourseResponse toCourseResponse(CourseEntity courseEntity){
        return CourseResponse.builder()
                .id(courseEntity.getId())
                .name(courseEntity.getName())
                .category(courseEntity.getCategory())
                .created_at(courseEntity.getCreatedAt())
                .updated_at(courseEntity.getUpdatedAt())
                .build();
    }
}
