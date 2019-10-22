package com.example.javabasic.jsonview;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author：Cheng.
 * @date：Created in 17:39 2019/9/11
 */
@Getter
@Setter
@Accessors(chain = true)
public class PersonInfo {

    public interface PersonSimpleView{}

    @JsonView(PersonSimpleView.class)
    private Student student;
}
