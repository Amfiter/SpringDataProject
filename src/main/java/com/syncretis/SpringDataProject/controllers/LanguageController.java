package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/languages")
public class LanguageController {
    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<LanguageDTO> getLanguages() {
        return languageService.getLanguages();
    }

    @GetMapping(path = "{id}")
    public LanguageDTO getLanguages(@PathVariable("id") Long id) {
        return languageService.getLanguages(id);
    }

    @PostMapping
    public void createNewLanguage(@RequestBody LanguageDTO languageDTO) {
        languageService.addNewLanguages(languageDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteLanguage(@PathVariable("id") Long id) {
        languageService.deleteLanguage(id);
    }

    @PutMapping(path = "{id}")
    public LanguageDTO updateLanguage(@RequestBody LanguageDTO languageDTO, @PathVariable("id") Long id) {
        return languageService.updateLanguage(languageDTO, id);
    }
}
