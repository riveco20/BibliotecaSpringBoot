package com.biblioteca.bibliotecaSpringBoot.controllers;


import com.biblioteca.bibliotecaSpringBoot.dtos.EbookDTO;
import com.biblioteca.bibliotecaSpringBoot.services.ServiceEbook;
import lombok.experimental.PackagePrivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("biblioteca/Ebook")
public class Controller {

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private ServiceEbook serviceEbook;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EbookDTO> mostrarPorID(@PathVariable() String id){
        return serviceEbook.getById(id).map(ebookDTO -> new ResponseEntity<>(ebookDTO,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/mostraTodos")
    public List<EbookDTO> listarTodos(){
        return serviceEbook.mostrarTodos();
    }


    @PostMapping(value="crear")
    public ResponseEntity<EbookDTO> crear (@RequestBody EbookDTO ebookDTO){
        return new ResponseEntity<>(serviceEbook.agregarEbook(ebookDTO),HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        if(serviceEbook.eliminarEbookPorID(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/actualizar")
    public  ResponseEntity<EbookDTO> actualizar(@RequestBody EbookDTO ebookDTO){
        return new ResponseEntity<>(serviceEbook.actualizarEbook(ebookDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/disponible")
    public ResponseEntity<String> libroDisponible(@PathVariable() EbookDTO id){
        return new ResponseEntity<>(serviceEbook.verificarDisponibleEbook(id), HttpStatus.OK);
    }

    @GetMapping("/recomendar/tipo/{tipo}")
    public ResponseEntity<List<EbookDTO>> recomendarPorTipo(@PathVariable() String tipo){
        return new ResponseEntity<>(serviceEbook.recomendarPorTipo(tipo),HttpStatus.OK);
    }

    @GetMapping("/recomendar/tematica/{tematica}")
    public ResponseEntity<List<EbookDTO>> recomendarPorCategoria(@PathVariable() String categoria){
        return new ResponseEntity<>(serviceEbook.recomendarPorCategoria(categoria),HttpStatus.OK);
    }



}
