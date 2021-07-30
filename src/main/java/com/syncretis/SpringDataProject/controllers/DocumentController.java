package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.models.Document;
import com.syncretis.SpringDataProject.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/documents")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public List<Document> geDocuments() {
        return documentService.getDocuments();
    }

    @PostMapping
    public void createNewDocument(@RequestBody Document document) {
        documentService.addNewDocument(document);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDocument(@PathVariable("id") String id) {
        documentService.deleteDocument(id);
    }

    @PutMapping(path = "{id}")
    public void updateDocument(@RequestBody Document document, @PathVariable("id") String id) {
        documentService.updateDocument(document, id);
    }
}
