package com.biblioteca.bibliotecaSpringBoot.services;

import com.biblioteca.bibliotecaSpringBoot.dtos.EbookDTO;
import com.biblioteca.bibliotecaSpringBoot.mappers.MapperEbookI;
import com.biblioteca.bibliotecaSpringBoot.models.Ebook;
import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServiceEbookTest {

    private List<Ebook> ebooks() {

        var ebook = new Ebook();
        ebook.setId("H12");
        ebook.setNombreEbook("Ciete puertas");
        ebook.setFechaPrestadoEbook(LocalDate.parse("2019-20-11"));
        ebook.setCantidadDeEbookDisponible(5);
        ebook.setCantidadDeEbookPrestada(1);
        ebook.setTipoDeEbook("Literatura");
        ebook.setCategoriaDeLibro("terror");


        var ebookDos = new Ebook();
        ebookDos.setId("C15");
        ebookDos.setNombreEbook("Calculo diferencial");
        ebookDos.setFechaPrestadoEbook(LocalDate.parse("2019-01-10"));
        ebookDos.setCantidadDeEbookDisponible(5);
        ebookDos.setCantidadDeEbookPrestada(0);
        ebookDos.setTipoDeEbook("Ciencia");
        ebookDos.setCategoriaDeLibro("matematicas");


        var ebookTres = new Ebook();
        ebookTres.setId("J05");
        ebookTres.setNombreEbook("tv novelas");
        ebookTres.setFechaPrestadoEbook(LocalDate.parse("2021-09-08"));
        ebookTres.setCantidadDeEbookDisponible(50);
        ebookTres.setCantidadDeEbookPrestada(10);
        ebookTres.setTipoDeEbook("Revista");
        ebookTres.setCategoriaDeLibro("farandula");


        var ebooks = new ArrayList<Ebook>();
        ebooks.add(ebook);
        ebooks.add(ebookDos);
        ebooks.add(ebookTres);

        return ebooks;
    }


    @MockBean
    private RepositorieEbook repositorieEbook;

    @Autowired
    private ServiceEbook serviceEbook;

    @Autowired
    private MapperEbookI mapperEbookI;

    @Test
    @DisplayName("Prueba para agregar ebook")
    void agregarEbook() {
        var ebook = new Ebook();
        ebook.setId("L29");
        ebook.setNombreEbook("Masacre en cali");
        ebook.setFechaPrestadoEbook(LocalDate.parse("2018-11-15"));
        ebook.setCantidadDeEbookDisponible(1);
        ebook.setCantidadDeEbookPrestada(0);
        ebook.setTipoDeEbook("Periodico");
        ebook.setCategoriaDeLibro("informativos");


        var ebookDTO = new EbookDTO();
        ebookDTO.setNombreEbook("masacre en cali");
        ebookDTO.setFechaPrestadoEbook(LocalDate.parse("2018-11-15"));
        ebookDTO.setCantidadDeEbookDisponible(1);
        ebookDTO.setCantidadDeEbookPrestada(0);
        ebookDTO.setTipoDeEbook("Periodico");
        ebookDTO.setCategoriaDeLibro("informativos");

        Mockito.when(repositorieEbook.save(any())).thenReturn(ebook);

        var result = serviceEbook.agregarEbook(ebookDTO);

        Assertions.assertNotNull(result, "El valor guardado no debe ser nulo");

        Assertions.assertEquals(ebook.getNombreEbook(), result.getNombreEbook(), "el nombre no corresponde");
        Assertions.assertEquals(ebook.getFechaPrestadoEbook(), result.getFechaPrestadoEbook(), "La fecha no corresponde");
        Assertions.assertEquals(ebook.getCantidadDeEbookDisponible(), result.getCantidadDeEbookDisponible(), "La cantidad no corresponde");
        Assertions.assertEquals(ebook.getCantidadDeEbookPrestada(), result.getCantidadDeEbookPrestada(), "La cantidad no corresponde");
        Assertions.assertEquals(ebook.getTipoDeEbook(), result.getTipoDeEbook(), "el tipo no corresponde");
        Assertions.assertEquals(ebook.getCategoriaDeLibro(), result.getCategoriaDeLibro(), "La tematica no corresponde");


    }

    private List<Ebook> ebookslist(){
        var ebook = new Ebook();
        ebook.setId("L29");
        ebook.setNombreEbook("Masacre en cali");
        ebook.setFechaPrestadoEbook(LocalDate.parse("2018-11-15"));
        ebook.setCantidadDeEbookDisponible(1);
        ebook.setCantidadDeEbookPrestada(0);
        ebook.setTipoDeEbook("Periodico");
        ebook.setCategoriaDeLibro("informativos");

        var ebook2 = new Ebook();
        ebook2.setId("H29");
        ebook2.setNombreEbook("Ciete puertas");
        ebook2.setFechaPrestadoEbook(LocalDate.parse("2020-05-16"));
        ebook2.setCantidadDeEbookDisponible(5);
        ebook2.setCantidadDeEbookPrestada(9);
        ebook2.setTipoDeEbook("Literatura");
        ebook2.setCategoriaDeLibro("terror");

        var ebooks = new ArrayList<Ebook>();
        ebooks.add(ebook);
        ebooks.add(ebook2);

        return ebooks;
    }

    @Test
    @DisplayName("Test para evaluar mostrar lista por id")
    public void mostarlista(){
        Mockito.when(repositorieEbook.findAll()).thenReturn(ebookslist());

        var event = serviceEbook.mostrarTodos();

        Assertions.assertEquals(2,event.size());
        Assertions.assertEquals("Masacre en cali",event.get(0).getNombreEbook());
        Assertions.assertEquals("Ciete puertas",event.get(1).getNombreEbook());
    }



    @Test
    @DisplayName("Test para evalular actualizacion ebook")
    public void actualizar() {

        var ebookDTO = new EbookDTO();
        ebookDTO.setId("L29");
        ebookDTO.setNombreEbook("Masacre en cali");
        ebookDTO.setFechaPrestadoEbook(LocalDate.parse("2018-11-15"));
        ebookDTO.setCantidadDeEbookDisponible(1);
        ebookDTO.setCantidadDeEbookPrestada(0);
        ebookDTO.setTipoDeEbook("Periodico");
        ebookDTO.setCategoriaDeLibro("informativos");

        Mockito.when(repositorieEbook.save(Mockito.any())).thenReturn(mapperEbookI.toEbook(ebookDTO));
        Mockito.when(repositorieEbook.findById(ebookDTO.getId())).thenReturn(ebookslist().stream().findFirst());

        var event = serviceEbook.actualizarEbook(ebookDTO);

        Assertions.assertNotNull(event,"No debe estar vacio para actualizar");
        Assertions.assertEquals("L29",event.getId(),"No debe estar vacio");
        Assertions.assertEquals("Masacre en cali", event.getNombreEbook(),"El nombre no es igual");
        Assertions.assertEquals(1,event.getCantidadDeEbookDisponible(),"La cantidad disponible debes ser la misma");
        Assertions.assertEquals(LocalDate.parse("2018-11-15"),event.getFechaPrestadoEbook(),"La fecha debe estar valida");
        Assertions.assertEquals(0,event.getCantidadDeEbookPrestada(),"La cantidad debe ser cero");
        Assertions.assertEquals("Periodico",event.getTipoDeEbook(),"El tipo debe se igual");
        Assertions.assertEquals("informativos",event.getCategoriaDeLibro(),"La categoria debe concidir");
  }

  @Test
  @DisplayName("Test para mostrar por id")
  public void mostrarPorID(){

        Mockito.when(repositorieEbook.findById(Mockito.any())).thenReturn(ebookslist().stream().findFirst());

        var event = serviceEbook.getById(ebookslist().get(0).getId());

        Assertions.assertEquals(ebookslist().get(0).getId(), event.get().getId(), "El id ingresado no existe");
        Assertions.assertEquals("Masacre en cali", event.get().getNombreEbook(),"El nombre debe ser ifual");
        Assertions.assertEquals(1,event.get().getCantidadDeEbookDisponible(), "La cantidad no corresponde");
        Assertions.assertEquals(LocalDate.parse("2018-11-15"), event.get().getFechaPrestadoEbook(), "La fecha no corresponde");
        Assertions.assertEquals(0, event.get().getCantidadDeEbookPrestada(), "La cantidad debe ser igual");
        Assertions.assertEquals("Periodico", event.get().getTipoDeEbook(), "El tipo debe ser igual");
        Assertions.assertEquals("informativos", event.get().getCategoriaDeLibro(), "La tematica debe coincidir");

  }

}