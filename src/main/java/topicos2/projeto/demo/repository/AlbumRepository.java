package topicos2.projeto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topicos2.projeto.demo.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Album findByNome(String nome);
}
