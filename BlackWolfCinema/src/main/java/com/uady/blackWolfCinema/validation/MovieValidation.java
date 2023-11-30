package com.uady.blackWolfCinema.validation;

import org.springframework.web.multipart.MultipartFile;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieValidation {

    @NotBlank (message = "Este campo no puede ir vacio")
    private String name;

    @NotBlank(message = "Este campo no puede ir vacio ")
    private String synopsis;

    @NotNull
    @Min(value = 1, message = "La duración debe ser mayo que cero")
    private int duration;

    @NotBlank(message = "Este campo no puede ir vacio")
    private String trailer;

    @Transient
    private MultipartFile portada;
    //no estoy incluyedo la validación de portada y del filePath
    public MovieValidation(){

    }
}
