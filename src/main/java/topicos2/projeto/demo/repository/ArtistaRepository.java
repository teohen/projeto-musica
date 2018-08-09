package topicos2.projeto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import topicos2.projeto.demo.model.Artista;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    Artista findByNome(String nome);

    Optional< List<Artista> > findByNomeContaining(String nome );
}
