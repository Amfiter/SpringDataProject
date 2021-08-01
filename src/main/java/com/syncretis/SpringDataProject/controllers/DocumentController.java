package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.dto.DocumentDTO;
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
    public List<DocumentDTO> getDocuments() {
        return documentService.getDocuments();
    }

    @GetMapping(path = "{id}")
    public DocumentDTO getDocumentById(@PathVariable("id") String id) {
        return documentService.getDocuments(id);
    }

    @PostMapping
    public void createNewDocument(@RequestBody DocumentDTO documentDTO) {
        documentService.addNewDocument(documentDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDocument(@PathVariable("id") String id) {
        documentService.deleteDocument(id);
    }

    @PutMapping(path = "{id}")
    public DocumentDTO updateDocument(@RequestBody DocumentDTO documentDTO, @PathVariable("id") String id) {
        return documentService.updateDocument(documentDTO, id);
    }
}
