package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.DirectorDto;

import java.util.List;

public interface DirectorService {
    List<DirectorDto> findAll();
}
