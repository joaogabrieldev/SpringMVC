package tads.eaj.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produtos> findAll(){
        return produtoRepository.findAll();
    }

    public void add(Produtos produtos){
        produtoRepository.save(produtos);
    }

    public Produtos get(Long id){
        return produtoRepository.getOne(id);
    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }
}
