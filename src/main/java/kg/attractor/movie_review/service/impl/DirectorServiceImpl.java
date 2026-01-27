package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.model.Director;
import kg.attractor.movie_review.repository.DirectorRepository;
import kg.attractor.movie_review.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;

    @Override
    public List<DirectorDto> findAll() {
        List<Director> list = directorRepository.findAll();

        return list.stream()
                .map(e -> {
                    DirectorDto dto = new DirectorDto();
                    dto.setId(e.getId());
                    dto.setFullName(e.getFullName());
                    return dto;
                })
                .toList();
    }


}
