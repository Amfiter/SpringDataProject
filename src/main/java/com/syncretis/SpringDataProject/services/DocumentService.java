package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.DocumentConverter;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.exceptions.DocumentException;
import com.syncretis.SpringDataProject.models.Document;
import com.syncretis.SpringDataProject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentConverter documentConverter;

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

    public void addNewDocument(DocumentDTO documentDTO) {
        Document document = documentConverter.dtoToEntity(documentDTO);
        documentRepository.save(document);
    }

    public void deleteDocument(String id) {
        if (!documentRepository.existsById(id)) {
            throw new DocumentException(HttpStatus.NOT_FOUND);
        }
        documentRepository.deleteById(id);
    }

    public Document updateDocument(DocumentDTO newDocument, String id) {
        Document documentEntity = documentConverter.dtoToEntity(newDocument);
        return documentRepository.findById(id)
                .map(department -> {
                    department.setNumber(documentEntity.getNumber());
                    department.setExpireDate(documentEntity.getExpireDate());
                    return documentRepository.save(department);
                })
                .orElseThrow(() -> new DocumentException(HttpStatus.BAD_REQUEST));
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
                throw new DocumentException(HttpStatus.BAD_REQUEST);
            }
        }
        return document;
    }
}
