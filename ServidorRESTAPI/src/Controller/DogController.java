package com.example.dogapi.controller;

import com.example.dogapi.service.DogService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DogController 
{
    private final DogService dogService;

    public DogController(DogService dogService) 
    {
        this.dogService = dogService;
    }

    @GetMapping(value = "/breeds", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Map<String, Object> listBreeds() 
    {
        return dogService.fetchAllBreeds();
    }

    @GetMapping("/breeds/{breed}/images")
    public List<String> getBreedImages(@PathVariable String breed) 
    {
        return dogService.fetchImagesOfBreed(breed);
    }

    @GetMapping(value = "/breeds.proto", produces = "application/x-protobuf")
    public byte[] getBreedsProto() 
    {
        return dogService.getBreedsAsProtobuf();
    }

    @PostMapping("/favorites")
    public ResponseEntity<String> addFavorite(@RequestParam String imageUrl) 
    {
        return dogService.addFavorite(imageUrl);
    }

    @GetMapping("/favorites")
    public List<String> getFavorites() 
    {
        return dogService.getFavorites();
    }

    @DeleteMapping("/favorites")
    public ResponseEntity<String> removeFavorite(@RequestParam String imageUrl) 
    {
        return dogService.removeFavorite(imageUrl);
    }
}
