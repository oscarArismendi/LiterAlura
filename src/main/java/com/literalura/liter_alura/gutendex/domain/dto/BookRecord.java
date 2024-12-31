package com.literalura.liter_alura.gutendex.domain.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) 
public record BookRecord(
    Long id,
    String title,
    Set<AuthorRecord> authors,
    List<String> languages,
    int download_count
) {}