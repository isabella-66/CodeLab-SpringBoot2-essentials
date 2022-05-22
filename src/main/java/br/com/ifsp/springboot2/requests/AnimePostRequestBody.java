package br.com.ifsp.springboot2.requests;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "The name cannot be empty")
    private String name;
}
