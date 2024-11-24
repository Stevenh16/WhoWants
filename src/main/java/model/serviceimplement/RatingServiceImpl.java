package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.RatingDto;
import model.mapper.DonationMapper;
import model.mapper.RatingMapper;
import model.repository.RatingRepository;
import model.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private DonationMapper donationMapper;
    private RatingRepository ratingRepository;
    private RatingMapper ratingMapper;

    @Override
    public RatingDto save(RatingDto ratingDto) {
        return ratingMapper.toIdDto(ratingRepository.save(ratingMapper.toEntity(ratingDto)));
    }

    @Override
    public Optional<RatingDto> findById(Long id) {
        return ratingRepository.findById(id).map(ratingMapper::toIdDto);
    }

    @Override
    public Optional<RatingDto> update(Long id, RatingDto ratingDto) {
        return ratingRepository.findById(id).map(oldRating -> {
            oldRating.setDonation(donationMapper.mapToDonation(ratingDto.donation()));
            oldRating.setComment(ratingDto.comment());
            oldRating.setStars(ratingDto.stars());
            return ratingMapper.toIdDto(ratingRepository.save(oldRating));
        });
    }

    @Override
    public List<RatingDto> findAll() {
        return ratingMapper.toListIdDto(ratingRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }
}
