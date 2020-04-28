package dev.duoplugins.website.api.error.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private final Integer status;
    private final String message;
    private final List<Field> fields;


    @RequiredArgsConstructor
    @Getter
    public static class Field {
        private final String name;
        private final String reason;
    }
}
