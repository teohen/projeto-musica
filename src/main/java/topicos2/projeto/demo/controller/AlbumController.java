package topicos2.projeto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topicos2.projeto.demo.model.Album;
import topicos2.projeto.demo.service.AlbumService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/albuns")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<Album> cria(@Validated @RequestBody Album album){
        Album albumSalva = albumService.salva(album);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(albumSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(albumSalva);
    }

    @GetMapping
    public ResponseEntity<?> listaAlbum(){
        List<Album> albuns = albumService.todos();
        if(albuns.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(albuns);
        }
    }

    @GetMapping("/{id}")
    public Album buscaPor(@PathVariable Integer id ) {
        return albumService.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir (@PathVariable Integer id){
        albumService.excluir(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizar(@PathVariable Integer id, @Validated @RequestBody Album album){
        Album albumManager = albumService.atualiza(id, album);
        return ResponseEntity.ok(albumManager);
    }
}
