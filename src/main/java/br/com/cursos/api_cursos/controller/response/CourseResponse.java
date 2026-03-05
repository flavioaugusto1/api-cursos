package br.com.cursos.api_cursos.controller.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CourseResponse(
        UUID id,
        String name,
        String category,
        boolean active,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
}
