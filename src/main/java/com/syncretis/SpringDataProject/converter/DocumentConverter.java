package com.syncretis.SpringDataProject.converter;

import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.models.Document;

public class DocumentConverter {

    public DocumentDTO entityToDto(Document document){
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(documentDTO.getId());
        documentDTO.setNumber(documentDTO.getNumber());
        documentDTO.setExpireDate(documentDTO.getExpireDate());
        return documentDTO;
    }

    public Document dtoToEntity(DocumentDTO documentDTO){
        Document document = new Document();
        document.setId(document.getId());
        document.setNumber(documentDTO.getNumber());
        document.setExpireDate(documentDTO.getExpireDate());
        return document;
    }
}
