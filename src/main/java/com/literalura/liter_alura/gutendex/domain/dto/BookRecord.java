package com.literalura.liter_alura.gutendex.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) 
public record BookRecord(
    int id,
    String title,
    List<Author> authors
) {}