package topicos2.projeto.demo.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topicos2.projeto.demo.model.Album;
import topicos2.projeto.demo.model.Artista;
import topicos2.projeto.demo.repository.filter.ArtistaFiltro;
import topicos2.projeto.demo.service.ArtistaService;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {


    private final ArtistaService artistaService;

    @Autowired
    public ArtistaController(ArtistaService artistaService){
        this.artistaService = artistaService;
    }


    @PostMapping
    public ResponseEntity<Artista> cria(@Validated @RequestBody Artista artista){
        Artista artistaSalva = artistaService.salva(artista);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(artistaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(artistaSalva);
    }

    @GetMapping
    public ResponseEntity<?> listaArtista(){
        List<Artista> artistas = artistaService.todos();
        if(artistas.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(artistas);
        }
    }


    @GetMapping("/todos")
    public Page<Artista> todosProdutos(Pageable pageable  ) {
        return artistaService.buscaPaginada(pageable );
    }

    @GetMapping("/buscapornome")
    public List<Artista> buscaPeloNome(@RequestParam String nome ) {
        return artistaService.buscaPor(nome );
    }

    @GetMapping("/{id}")
    public Artista buscaPor(@PathVariable Integer id ) {
        return artistaService.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir (@PathVariable Integer id){
        artistaService.excluir(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> atualizar(@PathVariable Integer id, @Validated @RequestBody Artista artista){
        Artista artistaManager = artistaService.atualiza(id, artista);
        return ResponseEntity.ok(artistaManager);
    }

}
