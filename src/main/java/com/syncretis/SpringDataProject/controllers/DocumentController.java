package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.Marker;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.models.Document;
import com.syncretis.SpringDataProject.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public List<DocumentDTO> getDocuments() {
        return documentService.getDocuments();
    }

    @GetMapping(path = "{id}")
    public DocumentDTO getDocumentById(@Valid @PathVariable("id") String id) {
        return documentService.getDocuments(id);
    }

    @PostMapping
    @Validated({Marker.OnCreate.class})
    public void createNewDocument(@Valid @RequestBody DocumentDTO documentDTO) {
        documentService.addNewDocument(documentDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDocument(@PathVariable("id") String id) {
        documentService.deleteDocument(id);
    }

    @Validated({Marker.OnUpdate.class})
    @PutMapping(path = "{id}")
    public Document updateDocument(@Valid @RequestBody DocumentDTO documentDTO, @PathVariable("id") String id) {
        return documentService.updateDocument(documentDTO, id);
    }
}
