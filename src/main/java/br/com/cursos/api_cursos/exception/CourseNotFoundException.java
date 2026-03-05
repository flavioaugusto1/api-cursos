package br.com.cursos.api_cursos.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
        super("Course not found");
    }
}
