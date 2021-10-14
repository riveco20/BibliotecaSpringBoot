package com.biblioteca.bibliotecaSpringBoot.controllers;


import com.biblioteca.bibliotecaSpringBoot.dtos.BibliotecaDTO;
import com.biblioteca.bibliotecaSpringBoot.services.ServiceBiblioteca;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("biblioteca")
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    private ServiceBiblioteca servicio;

    @Autowired
    public Controller(ServiceBiblioteca servicio){
        this.servicio=servicio;
    }

    @PostMapping(value = "/agregar")
    public ResponseEntity<BibliotecaDTO> add(@RequestBody BibliotecaDTO bibliotecaDTO){
        return ResponseEntity.accepted().body(servicio.guardar(bibliotecaDTO));
         }

    @GetMapping()
    public ResponseEntity<BibliotecaDTO> findAll(){
        return new ResponseEntity(servicio.obtenerElementos(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BibliotecaDTO> edit(@RequestBody BibliotecaDTO bibliotecaDTO){
        if (!bibliotecaDTO.getId().isEmpty()){
            return new ResponseEntity(servicio.actualizar(bibliotecaDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BibliotecaDTO> delete(@PathVariable("id") String id){
        try {
            servicio.eliminar(id);
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.error("ocurrio un error: "+e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/disponible/{id}")
    public ResponseEntity availability(@PathVariable("id") String id){
        return new ResponseEntity(servicio.comprobarDisponibilidad(id), HttpStatus.OK);
    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity lend(@PathVariable("id") String id){
        return  new ResponseEntity(servicio.prestar(id), HttpStatus.OK);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity returnResource(@PathVariable("id") String id){
        return  new ResponseEntity(servicio.regresarRecurso(id),HttpStatus.OK);
    }

}
