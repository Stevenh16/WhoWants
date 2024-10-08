package model.dto;

import java.util.List;

public record PersonDto(Long id, String name, String email, String password, List<DonationDto> donations, List<DonationDto> benefits) {
}
