package com.example.cadastro2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro2.Model.Repositorio.ClienteRepositorio;
import com.example.cadastro2.Model.entidades.Cliente;
//import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteRepositorio oClienteRepositorio;

    //segunda forma de fazer o resquest dos dados
    // para não ter que usar a notação requestParam como parametro do método
    //metodo cadastrar e alterar juntos, diferenciando a requisição
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    private Cliente cadastrarCliente(@Valid Cliente oCliente){
        oClienteRepositorio.save(oCliente);
        return oCliente;
    }

    //listar todos os registro por meio de requisição get
    @GetMapping
    public Iterable<Cliente> consultarClientes(){
        return oClienteRepositorio.findAll();
    }

    //metodo para excluir por id
    @DeleteMapping(path = "/{id}")
    public String excluirCliente(@PathVariable int id){
        oClienteRepositorio.deleteById(id);
        return "Cliente Excluido";
    }

     //metodo para consultar por parametro(cpf)
     @GetMapping(path = "/busca/{cpf}")
     public Iterable<Cliente> obterCliente(@PathVariable String cpf){

        return oClienteRepositorio.findByCpfContainingIgnoreCase(cpf);
     }
     
}
