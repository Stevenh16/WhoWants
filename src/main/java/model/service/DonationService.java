package model.service;

import model.dto.DonationDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DonationService {
    DonationDto save(DonationDto donationDto);
    Optional<DonationDto> findById(Long id);
    Optional<DonationDto> update(Long id, DonationDto donationDto);
    List<DonationDto> findAll();
    List<DonationDto> findByDate(LocalDateTime date);
    void delete(Long id);
}
