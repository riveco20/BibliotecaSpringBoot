package com.biblioteca.bibliotecaSpringBoot.mappers;


import com.biblioteca.bibliotecaSpringBoot.dtos.EbookDTO;
import com.biblioteca.bibliotecaSpringBoot.models.Ebook;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperEbookI {
    EbookDTO toEbookDto(Ebook ebook);
    List<EbookDTO> toEbookDto(List<Ebook> recursos);

    @InheritInverseConfiguration
    Ebook toEbook(EbookDTO ebookDTO);
}

