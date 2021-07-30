package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.models.Language;
import com.syncretis.SpringDataProject.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    public void addNewLanguages(Language language) {
        System.out.println(language);
        languageRepository.save(language);
    }

    public void deleteLanguage(Long id) {
        boolean exists = languageRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Language with id = " + id + " does not exists");
        }
        languageRepository.deleteById(id);
    }

    public Language updateLanguage(Language newLanguage, Long id) {
        return languageRepository.findById(id)
                .map(language -> {
                    language.setName(newLanguage.getName());
                    return languageRepository.save(language);
                })
                .orElseThrow(() -> new IllegalStateException("Language with id =" + id + " does not exist"));
    }

}
