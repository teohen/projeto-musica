package topicos2.projeto.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topicos2.projeto.demo.model.Album;
import topicos2.projeto.demo.model.Artista;
import topicos2.projeto.demo.repository.AlbumRepository;
import topicos2.projeto.demo.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    private final GenericoService<Album> genericoService;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;

        this.genericoService = new GenericoService<Album>(albumRepository);
    }
    @Transactional
    public Album salva(Album album) {
        return genericoService.salva(album);
    }

    @Transactional(readOnly = true)
    public List<Album> todos() {

        return genericoService.buscaTodasAsEntities();

    }

    public Album buscaPor(Integer id) {
        return  genericoService.buscaPor(id);
    }

    @Transactional
    public Album atualiza(Integer id, Album album) {
        return genericoService.atualiza(album, id);
    }

    @Transactional
    public void excluir(Integer id) {
        genericoService.excluir(id);
    }
}
