package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.models.Language;
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
    public List<Language> getLanguages() {
        return languageService.getLanguages();
    }

    @PostMapping
    public void createNewLanguage(@RequestBody Language language) {
        languageService.addNewLanguages(language);
    }

    @DeleteMapping(path = "{id}")
    public void deleteLanguage(@PathVariable("id") Long id) {
        languageService.deleteLanguage(id);
    }

    @PutMapping(path = "{id}")
    public void updateLanguage(@RequestBody Language language, @PathVariable("id") Long id) {
        languageService.updateLanguage(language, id);
    }
}
