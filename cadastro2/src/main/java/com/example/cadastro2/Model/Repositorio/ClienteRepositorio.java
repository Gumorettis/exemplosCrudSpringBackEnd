package com.example.cadastro2.Model.Repositorio;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.cadastro2.Model.entidades.Cliente;

public interface ClienteRepositorio extends PagingAndSortingRepository<Cliente, Integer> {
    public Iterable<Cliente> findByCpfContainingIgnoreCase(String cpf);
    
}
