package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.Marker;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.models.Language;
import com.syncretis.SpringDataProject.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "api/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<LanguageDTO> getLanguages() {
        return languageService.getLanguages();
    }

    @GetMapping(path = "{id}")
    public LanguageDTO getLanguages(@PathVariable("id") Long id) {
        return languageService.getLanguages(id);
    }

    @Validated({Marker.OnCreate.class})
    @PostMapping
    public void createNewLanguage(@Valid @RequestBody LanguageDTO languageDTO) {
        languageService.addNewLanguages(languageDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteLanguage(@PathVariable("id") Long id) {
        languageService.deleteLanguage(id);
    }

    @Validated({Marker.OnUpdate.class})
    @PutMapping(path = "{id}")
    public Language updateLanguage(@Valid @RequestBody LanguageDTO languageDTO, @PathVariable("id") Long id) {
        return languageService.updateLanguage(languageDTO, id);
    }
}
