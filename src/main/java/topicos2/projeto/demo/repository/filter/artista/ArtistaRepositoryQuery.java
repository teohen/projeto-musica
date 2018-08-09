package topicos2.projeto.demo.repository.filter.artista;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import topicos2.projeto.demo.model.Artista;
import topicos2.projeto.demo.repository.filter.ArtistaFiltro;

import java.util.List;

public interface ArtistaRepositoryQuery {
    List<Artista> filtrar(ArtistaFiltro filtro);

    Page<Artista> filtrar(ArtistaFiltro filtro, Pageable pageable);
}
