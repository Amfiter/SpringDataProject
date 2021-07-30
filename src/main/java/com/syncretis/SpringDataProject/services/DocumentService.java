package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.models.Document;
import com.syncretis.SpringDataProject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    public void addNewDocument(Document document) {
        System.out.println(document);
        documentRepository.save(document);
    }

    public void deleteDocument(String id) {
        boolean exists = documentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Department with id = " + id + " does not exists");
        }
        documentRepository.deleteById(id);
    }

    public Document updateDocument(Document newDocument, String id) {
        return documentRepository.findById(id)
                .map(department -> {
                    department.setNumber(newDocument.getNumber());
                    department.setExpireDate(newDocument.getExpireDate());
                    return documentRepository.save(department);
                })
                .orElseThrow(() -> new IllegalStateException("Document with id =" + id + " does not exist"));
    }
}
