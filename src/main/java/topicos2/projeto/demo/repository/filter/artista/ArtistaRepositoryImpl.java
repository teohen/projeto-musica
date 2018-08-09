package topicos2.projeto.demo.repository.filter.artista;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import topicos2.projeto.demo.model.Artista;
import topicos2.projeto.demo.repository.ArtistaRepository;
import topicos2.projeto.demo.repository.filter.ArtistaFiltro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ArtistaRepositoryImpl implements ArtistaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Artista> filtrar(ArtistaFiltro filtro) {
        // Select p From Produto p

        // 1. Usamos o CriteriaBuilder(cb) para criar a CriteriaQueyr (cq)
        // com a tipagem do tipo a ser selecionado (Produto)
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Artista> cq = cb.createQuery(Artista.class );

        // 2. clausula from e joins
        Root<Artista> produtoRoot = cq.from(Artista.class );

        // 3. adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cb, produtoRoot  );

        // 4. monta a consulta com as restrições
        cq.select(produtoRoot )
                .where(restricoes )
                .orderBy( cb.asc(produtoRoot.get("nome")) );


        // 5. cria e executa a consula
        return manager.createQuery(cq)
                .getResultList();
    }

    private Predicate[] criaRestricoes(ArtistaFiltro filtro,
                                       CriteriaBuilder cb,
                                       Root<Artista> produtoRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(filtro.getNome())) {
            // where nome like %asdfg%

            predicates.add(cb.like(produtoRoot.get("nome"),
                    "%" + filtro.getNome() + "%"));
        }

        if (!StringUtils.isEmpty(filtro.getNacionalidade())) {
            // where nome like %asdfg%

            predicates.add(cb.like(produtoRoot.get("nacionalidade"),
                    "%" + filtro.getNacionalidade() + "%"));
        }
        return predicates.toArray(new Predicate[ predicates.size() ] );
    }


        @Override
    public Page<Artista> filtrar(ArtistaFiltro filtro, Pageable pageable) {
                // Select p From Produto p

                // 1. Usamos o CriteriaBuilder(cb) para criar a CriteriaQueyr (cq)
                // com a tipagem do tipo a ser selecionado (Produto)
                CriteriaBuilder cb = manager.getCriteriaBuilder();
                CriteriaQuery<Artista> cq = cb.createQuery(Artista.class );

                // 2. clausula from e joins
                Root<Artista> produtoRoot = cq.from(Artista.class );

                // 3. adiciona as restrições (os predicados) que serão passadas na clausula where
                Predicate[] restricoes = this.criaRestricoes(filtro, cb, produtoRoot  );

                // 4. monta a consulta com as restrições de paginação
                TypedQuery<Artista> query = manager.createQuery(cq);
                adicionaRestricoesDePaginacao(query, pageable);

                return new PageImpl<>(query.getResultList(), pageable, total(filtro) );
    }

            private void adicionaRestricoesDePaginacao(TypedQuery<Artista> query, Pageable pageable) {
                Integer paginaAtual = pageable.getPageNumber();
                Integer totalObjetosPorPagina = pageable.getPageSize();
                Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;

                // 0 a n-1, n - (2n -1), ...
                query.setFirstResult(primeiroObjetoDaPagina );
                query.setMaxResults(totalObjetosPorPagina );

            }

    private Long total(ArtistaFiltro filtro) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Artista> rootProduto = cq.from(Artista.class);

        Predicate[] predicates = criaRestricoes(filtro, cb, rootProduto);
        cq.where(predicates );

        cq.select( cb.count(rootProduto) );

        return manager.createQuery(cq).getSingleResult();
    }
}
