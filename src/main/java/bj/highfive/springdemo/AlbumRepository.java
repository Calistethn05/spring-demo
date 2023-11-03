package bj.highfive.springdemo;

import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long>{
    /*
     * C'est comme si nous avons créer une classe java avec toutes les méthodes CRUD
     * (save, find, findById, findAll...) déjà implémentées.
     */
    
}
