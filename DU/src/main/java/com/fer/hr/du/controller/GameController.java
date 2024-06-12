package com.fer.hr.du.controller;

import com.fer.hr.du.model.GameEntity;
import com.fer.hr.du.model.ImageEntity;
import com.fer.hr.du.model.game.DTO.GameDTO;
import com.fer.hr.du.repository.ImageRepository;
import com.fer.hr.du.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ImageRepository imageRepository;


    @PostMapping
    public ResponseEntity<Long> createGame(@RequestBody GameDTO gameDTO) {
        GameEntity savedGame = gameService.saveGame(gameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame.getId());
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long gameId) {
        GameDTO newGame = gameService.getGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(newGame);
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage() throws IOException, URISyntaxException {

        URI uri = new URI("https://icons.iconarchive.com/icons/iconarchive/childrens-book-animals/512/Tiger-icon.png");

        byte[] imageBytes = restTemplate.getForObject(uri, byte[].class);

        if (imageBytes != null) {
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            byte[] imageByteArray = StreamUtils.copyToByteArray(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/jpeg");  // Change this based on the actual image type
            return new ResponseEntity<>(imageByteArray, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/image/res")
    public ResponseEntity<byte[]> getImageRes() throws IOException, URISyntaxException {

        // Path to the image in the resources directory
        Resource resource = new ClassPathResource("static/360_F_557779384_FwMJ5EFRPFT5ARBcJsIebCEmv2XRe2Fm.jpg");

        // Read the image as byte array
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/png");

        // Return the image as a byte array
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<ImageEntity> imageEntity = imageRepository.findById(id);
        if (imageEntity.isPresent()) {
            byte[] imageData = imageEntity.get().getImageData();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

