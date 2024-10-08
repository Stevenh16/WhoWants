package model.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DonationDto(Long id, LocalDateTime date, PersonDto donor, PersonDto beneficiary, ThingDto thing, List<RatingDto> ratings) {
}
