package br.com.cursos.api_cursos.controller.request;

public record CourseCreateRequest(String name, String category, boolean active) {
}
