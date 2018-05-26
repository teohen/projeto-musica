package topicos2.projeto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import topicos2.projeto.demo.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
    Artista findByNome(String nome);
}
