package com.uady.blackWolfCinema.model;

import org.springframework.web.multipart.MultipartFile;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmovie")
    private int id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "synopsis")
    @NotBlank
    private String synopsis;

    @Column(name = "duration_min")
    @NotNull
    @Min(value = 1, message = "La duración debe ser mayo que cero")
    private int duration;

    @Column(name = "trailer")
    @NotBlank
    private String trailer;

    @Transient
    private MultipartFile portada;

    

    public Movie(@NotBlank String name, String imagePath, @NotBlank String synopsis, @NotBlank int duration,
            @NotBlank String trailer, MultipartFile portada) {
        this.name = name;
        this.imagePath = imagePath;
        this.synopsis = synopsis;
        this.duration = duration;
        this.trailer = trailer;
        this.portada = portada;
    }



    public Movie(int id, @NotBlank String name, String imagePath, @NotBlank String synopsis, @NotBlank int duration,
            @NotBlank String trailer, MultipartFile portada) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.synopsis = synopsis;
        this.duration = duration;
        this.trailer = trailer;
        this.portada = portada;
    }

    
    
}
