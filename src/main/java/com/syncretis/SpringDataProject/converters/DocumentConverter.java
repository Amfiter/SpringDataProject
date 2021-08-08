package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.entities.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentConverter {

    public DocumentDTO entityToDto(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(document.getId());
        documentDTO.setNumber(document.getNumber());
        documentDTO.setExpireDate(document.getExpireDate());
        return documentDTO;
    }

    public List<DocumentDTO> entityToDto(List<Document> document) {
        return document.stream().map(documentEntity -> entityToDto(documentEntity)).collect(Collectors.toList());
    }

    public Document dtoToEntity(DocumentDTO documentDTO) {
        Document document = new Document();
        document.setId(documentDTO.getId());
        document.setNumber(documentDTO.getNumber());
        document.setExpireDate(documentDTO.getExpireDate());
        return document;
    }

    public List<Document> dtoToEntity(List<DocumentDTO> documentDTO) {
        return documentDTO.stream().map(DTO -> dtoToEntity(DTO)).collect(Collectors.toList());
    }
}
