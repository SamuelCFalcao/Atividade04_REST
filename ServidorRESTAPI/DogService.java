package com.example.dogapi.service;

import com.example.dogapi.dto.BreedsProto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DogService 
{

    private final List<String> favorites = new ArrayList<>();
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> fetchAllBreeds() 
    {
        String url = "https://dog.ceo/api/breeds/list/all";
        return restTemplate.getForObject(url, Map.class);
    }

    public List<String> fetchImagesOfBreed(String breed) 
    {
        String url = String.format("https://dog.ceo/api/breed/%s/images", breed);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (List<String>) response.get("message");
    }

    public byte[] getBreedsAsProtobuf() 
    {
        Map<String, Object> data = fetchAllBreeds();
        Map<String, Object> message = (Map<String, Object>) data.get("message");

        BreedsProto.Breeds.Builder breedsBuilder = BreedsProto.Breeds.newBuilder();
        message.keySet().forEach(breedsBuilder::addNames);
        return breedsBuilder.build().toByteArray();
    }

    public ResponseEntity<String> addFavorite(String imageUrl) 
    {
        if (favorites.contains(imageUrl))
            return ResponseEntity.badRequest().body("Já está nos favoritos.");
        favorites.add(imageUrl);
        return ResponseEntity.ok("Adicionado aos favoritos.");
    }

    public List<String> getFavorites() 
    {
        return favorites;
    }

    public ResponseEntity<String> removeFavorite(String imageUrl) 
    {
        if (favorites.remove(imageUrl))
            return ResponseEntity.ok("Removido dos favoritos.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado.");
    }
}
