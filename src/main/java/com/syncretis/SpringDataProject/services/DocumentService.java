package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.DocumentConverter;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.exceptions.DepartmentNotFoundException;
import com.syncretis.SpringDataProject.exceptions.DocumentException;
import com.syncretis.SpringDataProject.entities.Document;
import com.syncretis.SpringDataProject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentConverter documentConverter;

    public DocumentService(DocumentConverter documentConverter, DocumentRepository documentRepository) {
        this.documentConverter = documentConverter;
        this.documentRepository = documentRepository;
    }

    public List<DocumentDTO> getDocuments() {
        List<Document> listDocument = documentRepository.findAll();
        List<DocumentDTO> documentDTOS = documentConverter.entityToDto(listDocument);
        return documentDTOS;
    }

    public DocumentDTO getDocuments(String Id) {
        Document document = documentRepository.findById(Id).orElseThrow(() -> new DocumentException(HttpStatus.NOT_FOUND));
        DocumentDTO documentDTO = documentConverter.entityToDto(document);
        return documentDTO;
    }

    public Document addNewDocument(DocumentDTO documentDTO) {
        Document document = documentConverter.dtoToEntity(documentDTO);
        return documentRepository.save(document);
    }

    public void deleteDocument(String id) {
        documentRepository.findById(id).orElseThrow(() -> new DocumentException(HttpStatus.NOT_FOUND));
        documentRepository.deleteById(id);
    }

    public Document updateDocument(DocumentDTO newDocument, String id) {
        Document documentEntity = documentConverter.dtoToEntity(newDocument);
        Optional<Document> optionalDocument = documentRepository.findById(id);
        Document document = optionalDocument.orElseThrow(() -> new DocumentException(HttpStatus.NOT_FOUND));
        document.setNumber(documentEntity.getNumber());
        document.setExpireDate(documentEntity.getExpireDate());
        return documentRepository.save(document);
    }

    public Document checkAndReturnDocument(PersonDTO personDTO) {
        Document document;
        if (personDTO.getDocument().getId() == null) {
            Optional<Document> optional = documentRepository.findByNumber(personDTO.getDocument().getNumber());
            if (optional.isPresent()) {
                throw new DocumentException(HttpStatus.CONFLICT);
            } else {
                document = new Document();
                document.setId(personDTO.getDocument().getId());
                document.setExpireDate(personDTO.getDocument().getExpireDate());
                document.setNumber(personDTO.getDocument().getNumber());
            }
        } else {
            Optional<Document> optional = documentRepository.findById(personDTO.getDocument().getId());
            if (optional.isPresent()) {
                document = optional.get();
            } else {
                throw new DocumentException(HttpStatus.NOT_FOUND);
            }
        }
        return document;
    }
}
