package com.ambev.projetopratico3.rest;


import com.ambev.projetopratico3.model.Produto;
import com.ambev.projetopratico3.repository.ProdutoRepository;
import com.ambev.projetopratico3.message.MessageProducer;
import com.ambev.projetopratico3.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    //private final ProdutoEventProducer eventProducer;

    private final MessageProducer messageProducer;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, MessageProducer messageProducer) {
        this.produtoRepository = produtoRepository;
        this.messageProducer = messageProducer;
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        //eventProducer.sendProductSavedEvent(produto.getNome());
        messageProducer.sendMessage(String.format("Produto %s cadastrado com sucesso !",produto.toString()));
        return produtoRepository.save(produto);
    }

    @GetMapping("/consultar/{nome}")
    public ResponseEntity<List<Produto>> consultarProdutos(@PathVariable String nome) {
        List<Produto> produto = produtoService.consultarPorNome(nome);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/error")
    public ResponseEntity errorProduct() {
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}