package bj.highfive.springdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //ceci signifie que cette classe est un controlleur
@RequestMapping(path = "/api") // ceci signifie que l'URL commence par /api (après l'url de l'application)
public class AlbumController {
    @Autowired // Ceci demande à Java d'injecter le "bean" albumRepository
    AlbumRepository albumRepository;  // instance du bean albumRepository

	// Create

	@PostMapping("/album")
	public @ResponseBody Album createAlbum(@RequestBody Album album) {
		return this.albumRepository.save(album);
	}

	@PostMapping("/albums")
	public @ResponseBody Iterable<Album> creatAlbums(@RequestBody List<Album> albums) {
		return this.albumRepository.saveAll(albums);
	}

	// Read
    // @RequestMapping(path = "/hello", method = RequestMethod.POST)
	@GetMapping(path = "/albums")
	// public List<Album> getAlbums() {
	public @ResponseBody Iterable<Album> getAlbums() {
        // localhost:1234/api/albums
        return this.albumRepository.findAll();
        
	}
	/*
	 * RequestParameter: monsite.fr?id=1
	 * PathVariable: monsite.fr/1
	 * RequestBody
	 * ResponseBody
	 */

	@GetMapping("/albums/{id}")
	public @ResponseBody Album getAlbumById(@PathVariable Long id) {
		return this.albumRepository.findById(id).orElse(null);
	}

	// Update

	@PutMapping("/albums")
	public @ResponseBody Album upAlbum(@RequestBody Album album) {
		Album existingAlbum = this.albumRepository.findById(album.getId()).orElse(null);
		existingAlbum.setRef(album.getRef());
		existingAlbum.setName(album.getName());
		existingAlbum.setTitle(album.getTitle());
		existingAlbum.setDescription(album.getDescription());
		existingAlbum.setDuration(album.getDuration());
		existingAlbum.setStatus(album.getStatus());
		existingAlbum.setUrl(album.getUrl());
		existingAlbum.setTags(album.getTags());
		existingAlbum.setLike(album.getLike());

		return this.albumRepository.save(existingAlbum);
	}

	// Delete

	@DeleteMapping("/albums/{id}")
	public @ResponseBody String deleteAlbum(@PathVariable Long id) {
		this.albumRepository.deleteById(id);
		return "Album supprimé: " + id;
	}
}
