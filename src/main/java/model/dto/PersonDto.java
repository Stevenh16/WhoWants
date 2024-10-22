package model.dto;

import model.entity.Role;

import java.util.List;
import java.util.Set;

public record PersonDto(Long id, String username, String name, String email, String password, List<DonationDto> donations, List<DonationDto> benefits, Set<Role> roles) {
}
