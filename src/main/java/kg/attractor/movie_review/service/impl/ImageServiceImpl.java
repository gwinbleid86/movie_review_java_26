package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.ImageDao;
import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.exceptions.ImageNotFoundException;
import kg.attractor.movie_review.model.MovieImage;
import kg.attractor.movie_review.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageDao imageDao;
    private final FileService fileService;

    @Override
    public ResponseEntity<?> findById(Long id) {
        MovieImage image = imageDao.findById(id)
                .orElseThrow(ImageNotFoundException::new);
        return fileService.getOutputFile(image.getFileName(), "images", MediaType.IMAGE_JPEG);
    }

    @Override
    public void save(MovieImageDto movieImageDto) {
        String filename = fileService.saveUploadFile(movieImageDto.getImage(), "images");
        MovieImage image = new MovieImage();
        image.setFileName(filename);
        image.setMovieId(movieImageDto.getMovieId());
        imageDao.save(image);
    }
}
