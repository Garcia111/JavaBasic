package com.example.javabasic.dto;

import lombok.*;

import java.util.Objects;


/**
 * @author：Cheng.
 * @date：Created in 18:37 2019/9/4
 */
@Data
@Getter
@Setter
@ToString
public class Student {
    private Long id;

    private String name;

    private Integer score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) &&
                name.equals(student.name) &&
                score.equals(student.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, score);
    }

    public Student(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}
