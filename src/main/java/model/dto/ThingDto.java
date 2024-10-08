package model.dto;

public record ThingDto(Long id, String name, String description, DonationDto donation) {
}
