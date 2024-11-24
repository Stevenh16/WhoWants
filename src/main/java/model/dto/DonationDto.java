package model.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DonationDto(Long id, LocalDateTime date, String img, Long donor, Long beneficiary, Long thing, List<RatingDto> ratings) {
}
