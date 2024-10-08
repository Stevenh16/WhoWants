package model.service;

import model.dto.RatingDto;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    RatingDto save(RatingDto ratingDto);
    Optional<RatingDto> findById(Long id);
    Optional<RatingDto> update(Long id, RatingDto ratingDto);
    List<RatingDto> findAll();
    void delete(Long id);
}
