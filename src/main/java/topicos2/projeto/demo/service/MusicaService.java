package topicos2.projeto.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topicos2.projeto.demo.model.Musica;
import topicos2.projeto.demo.repository.MusicaRepository;

import java.util.List;

@Service
public class MusicaService {
    private final MusicaRepository musicaRepository;
    private final GenericoService<Musica> genericoService;

    @Autowired
    public MusicaService(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;

        this.genericoService = new GenericoService<Musica>(musicaRepository);
    }
    @Transactional
    public Musica salva(Musica musica) {
        return genericoService.salva(musica);
    }

    @Transactional(readOnly = true)
    public List<Musica> todos() {

        return genericoService.buscaTodasAsEntities();

    }

    @Transactional
    public Musica atualiza(Integer id, Musica musica) {
        return genericoService.atualiza(musica, id);
    }

    @Transactional
    public void excluir(Integer id) {
        genericoService.excluir(id);
    }
}
