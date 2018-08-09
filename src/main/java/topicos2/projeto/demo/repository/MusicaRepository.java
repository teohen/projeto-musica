package topicos2.projeto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import topicos2.projeto.demo.model.Album;
import topicos2.projeto.demo.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {
    Musica findByNome(String nome);
}
