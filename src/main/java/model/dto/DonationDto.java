package model.dto;

import java.time.LocalDate;
import java.util.List;

public record DonationDto(Long id, LocalDate date, PersonDto donor, PersonDto beneficiary, ThingDto thing, List<RatingDto> ratingsx) {
}
