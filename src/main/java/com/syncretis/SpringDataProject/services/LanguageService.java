package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.LanguageConverter;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.exceptions.LanguageException;
import com.syncretis.SpringDataProject.models.Language;
import com.syncretis.SpringDataProject.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private LanguageConverter languageConverter = new LanguageConverter();

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<LanguageDTO> getLanguages() {
        List<Language> listLanguage = languageRepository.findAll();
        List<LanguageDTO> languageDTOS = languageConverter.entityToDto(listLanguage);
        return languageDTOS;
    }

    public LanguageDTO getLanguages(Long Id) {
        Language language = languageRepository.findById(Id).orElseThrow(() -> new LanguageException(HttpStatus.NOT_FOUND));
        LanguageDTO languageDTO = languageConverter.entityToDto(language);
        return languageDTO;
    }

    public void addNewLanguages(LanguageDTO languageDTO) {
        Language language = languageConverter.dtoToEntity(languageDTO);
        languageRepository.save(language);
    }

    public void deleteLanguage(Long id) {
        boolean exists = languageRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Language with id = " + id + " does not exists");
        }
        languageRepository.deleteById(id);
    }

    public LanguageDTO updateLanguage(LanguageDTO newLanguage, Long id) {
        Language languageEntity = languageConverter.dtoToEntity(newLanguage);
        LanguageDTO languageDTO = languageConverter.entityToDto(languageRepository.findById(id)
                .map(language -> {
                    language.setName(languageEntity.getName());
                    return languageRepository.save(language);
                })
                .orElseThrow(() -> new LanguageException(HttpStatus.BAD_REQUEST)));
        return languageDTO;
    }

}
