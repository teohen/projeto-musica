package topicos2.projeto.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import topicos2.projeto.demo.model.Album;
import topicos2.projeto.demo.model.Artista;
import topicos2.projeto.demo.repository.AlbumRepository;
import topicos2.projeto.demo.repository.ArtistaRepository;
import org.springframework.transaction.annotation.Transactional;
import topicos2.projeto.demo.repository.filter.ArtistaFiltro;
import topicos2.projeto.demo.repository.filter.artista.ArtistaRepositoryQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final ArtistaRepositoryQuery artistaRepositoryQuery;
    private final AlbumRepository albumRepository;

    private final GenericoService<Artista> genericoService;

@Autowired
    public ArtistaService(ArtistaRepository artistaRepository, AlbumRepository albumRepository, ArtistaRepositoryQuery artistaRepositoryQuery) {
        this.artistaRepository = artistaRepository;
        this.albumRepository = albumRepository;
        this.artistaRepositoryQuery = artistaRepositoryQuery;
        this.genericoService = new GenericoService<Artista>(artistaRepository);

}

    @Transactional
    public Artista salva(Artista artista) {
        return genericoService.salva(artista);
    }

    @Transactional(readOnly = true)
    public List<Artista> todos() {
        return genericoService.buscaTodasAsEntities();
    }

    public Artista buscaPor(Integer id) {
        return genericoService.buscaPor(id);
    }

    public List<Artista> buscaPor(String nome) {
        return
                artistaRepository.findByNomeContaining(nome )
                        .orElse(new ArrayList<>() );
    }

    @Transactional
    public Artista atualiza(Integer id, Artista artista) {
        return genericoService.atualiza(artista, id);
    }

    @Transactional
    public void excluir(Integer id) {
        genericoService.excluir(id);
    }

    public Page<Artista> buscaPaginada(Pageable pageable) {
        return artistaRepository.findAll(pageable );
    }

    public List<Artista> pesquisa(ArtistaFiltro filtro) {

        return artistaRepositoryQuery.filtrar(filtro );
    }

    public Page<Artista> pesquisa(ArtistaFiltro filtro, Pageable pageable) {
        return artistaRepositoryQuery.filtrar(filtro, pageable );

    }
}
