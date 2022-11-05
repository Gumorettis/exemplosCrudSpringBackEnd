package com.example.cadastro2.Model.Repositorio;
//classe utilizada para servir como mecanismo para realizar consultas no BD
//ela funciona de forma semelhante a uma classe .DAO do MVC. 
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.cadastro2.Model.entidades.Pessoa;

public interface PessoaRepositorio extends PagingAndSortingRepository<Pessoa, Integer>{
    
    //metodo abstrato usando uma convenção de metodo do spring para fazer uma busca de dados por parametro
    //neste caso buscando por nome da pessoa ou parte do nome
    public Iterable<Pessoa> findByNomeContainingIgnoreCase(String parteNome);
}
