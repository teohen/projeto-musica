package topicos2.projeto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topicos2.projeto.demo.model.Album;
import topicos2.projeto.demo.model.Musica;
import topicos2.projeto.demo.service.MusicaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {
    private final MusicaService musicaService;

    @Autowired
    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @PostMapping
    public ResponseEntity<Musica> cria(@Validated @RequestBody Musica musica){
        Musica musicaSalva = musicaService.salva(musica);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(musicaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(musicaSalva);
    }

    @GetMapping
    public ResponseEntity<?> listaAlbum(){
        List<Musica> musicas = musicaService.todos();
        if(musicas.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(musicas);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir (@PathVariable Integer id){
        musicaService.excluir(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Integer id, @Validated @RequestBody Musica musica){
        Musica musicaManager = musicaService.atualiza(id, musica);

        return ResponseEntity.ok(musicaManager);
    }
}
