package model.dto;

public record RatingDto(Long id, int stars, String comment,DonationDto donation) {
}
