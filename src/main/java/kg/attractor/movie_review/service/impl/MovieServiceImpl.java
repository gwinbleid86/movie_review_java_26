package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dto.CastDto;
import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.exceptions.MovieNotFountException;
import kg.attractor.movie_review.model.Movie;
import kg.attractor.movie_review.model.MovieCastMember;
import kg.attractor.movie_review.repository.DirectorRepository;
import kg.attractor.movie_review.repository.MovieRepository;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    @Override
    public Page<MovieDto> getMovies(Pageable page) {
//        Sort sort = Sort.by(Sort.Order.desc("releaseYear"));

//        Pageable page = PageRequest.of(pa, count, sort);

        Page<Movie> movies = movieRepository.findAll(page);
//        movies.
        Page<MovieDto> moviesDto = movies.map(e -> MovieDto.convertToDto(e));
        return moviesDto;
    }

    @Override
    public MovieDto findById(Long id) {
        var director = directorRepository.findByMovieId(id);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(MovieNotFountException::new);
        Set<MovieCastMember> castList = movie.getMovieCastMemberSet();
        List<CastDto> list = castList.stream()
                .map(e -> CastDto.builder()
                        .id(e.getId().getCast().getId())
                        .role(e.getRole())
                        .fullName(e.getId().getCast().getFullName())
                        .build())
                .toList();
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .year(movie.getReleaseYear())
                .description(movie.getDescription())
                .director(DirectorDto.builder()
                        .id(movie.getDirector().getId())
                        .fullName(movie.getDirector().getFullName())
                        .build())
                .castList(list)
                .build();

    }
}
