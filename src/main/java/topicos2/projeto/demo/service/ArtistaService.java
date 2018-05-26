package topicos2.projeto.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import topicos2.projeto.demo.model.Artista;
import topicos2.projeto.demo.repository.ArtistaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    @Autowired
    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    Optional<Artista> buscaPor(String nome){
        return Optional.ofNullable((artistaRepository.findByNome(nome)));
    }
    public Artista buscaPor(Integer id){
        Optional<Artista> optionalArtista = artistaRepository.findById(id);
        return optionalArtista.orElseThrow( () -> new EmptyResultDataAccessException(1));
    }

    @Transactional
    public Artista salva(Artista artista){
        return this.artistaRepository.save(artista);
    }

    @Transactional(readOnly = true)
    public List<Artista> obterTodosArtistas(){
        return artistaRepository.findAll();
    }

    @Transactional
    public void excluir(Integer id){
        artistaRepository.deleteById(id);
    }

    @Transactional
    public Artista atualiza(Integer id, Artista artista){
        Artista artistaManager = this.buscaPor(id);

        if(artistaManager == null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(artista, artistaManager, "id");
        this.salva(artistaManager);
        return artistaManager;
    }
}
