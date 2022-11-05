package com.example.cadastro2.Controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro2.Model.Repositorio.PessoaRepositorio;
import com.example.cadastro2.Model.entidades.Pessoa;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    
    //criando um atributo do tipo objeto para interagir com o banco(repositorio)
    //deixando o Spring responsavel para criar o objeto de repositorio e gravar no BD
    @Autowired
    private PessoaRepositorio oPessoaRepositorio;

    //fazendo o reuso do método de cadastrar para cadastro e alterar dados
    //habilitando o metodo para trabalhar com 2 tipos de requisições
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Pessoa salvarPessoa(@Valid Pessoa oPessoa){
        oPessoaRepositorio.save(oPessoa);
        return oPessoa;
    }
    
    // @PostMapping
    // public Pessoa cadastrarPessoa(@Valid @RequestParam String nome, @RequestParam int idade, @RequestParam String endereco){
    //     Pessoa oPessoa = new Pessoa(nome,idade,endereco);
    //     oPessoaRepositorio.save(oPessoa);
    //     return oPessoa;
    // }

    //fazendo a listagem de todos os dados cadastrados
    @GetMapping
    public Iterable<Pessoa> listarPessoa(){
        //a função findAll retorna todos os dados da tabela
        //usando select *
        return oPessoaRepositorio.findAll();
    }

    //fazendo listagem de dados de forma paginada
    @GetMapping(path = "/pagina/{numeroPagina}")
    public Iterable<Pessoa> buscarPessoaPorPagina(@PathVariable int numeroPagina){
        Pageable page = PageRequest.of(numeroPagina, 3);

        return oPessoaRepositorio.findAll(page);
    }
    
    //fazendo a listagem de dados por ID(codigo)
    @GetMapping(path = "/{id}")
    //busca no banco por ID 
    public Optional<Pessoa> buscarPessoaID(@PathVariable int id){
        return oPessoaRepositorio.findById(id);
    }

    // @PutMapping //requisição do tipo Put, responsavel por alterações no HTML
    // public Pessoa alterarPessoa(@Valid Pessoa oPessoa){
    //     oPessoaRepositorio.save(oPessoa);
    //     return oPessoa;
    // }

    //método para exclusão por ID
    @DeleteMapping(path = "/{codigo}")
    public String excluirPessoa(@PathVariable int codigo){
        oPessoaRepositorio.deleteById(codigo);
        return "Exlcuido";
    }

    //método para buscar por nome
    @GetMapping(path = "/nome/{parteNome}")//passando o parametro para busca
    public Iterable<Pessoa> obterPessoaPorNome(@PathVariable String parteNome){
        
        return oPessoaRepositorio.findByNomeContainingIgnoreCase(parteNome);
    }

}
