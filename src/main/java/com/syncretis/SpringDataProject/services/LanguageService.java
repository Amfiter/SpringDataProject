package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.LanguageConverter;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Language;
import com.syncretis.SpringDataProject.exceptions.LanguageException;
import com.syncretis.SpringDataProject.repositories.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageConverter languageConverter;

    public LanguageService(LanguageRepository languageRepository, LanguageConverter languageConverter) {
        this.languageRepository = languageRepository;
        this.languageConverter = languageConverter;
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

    public Language addNewLanguages(LanguageDTO languageDTO) {
        Language language = languageConverter.dtoToEntity(languageDTO);
        return languageRepository.save(language);
    }

    public void deleteLanguage(Long id) {
        languageRepository.findById(id).orElseThrow(() -> new LanguageException(HttpStatus.NOT_FOUND));
        languageRepository.deleteById(id);
    }

    public Language updateLanguage(LanguageDTO newLanguage, Long id) {
        Language language;
        Language languageEntity = languageConverter.dtoToEntity(newLanguage);
        if (languageRepository.existsById(id)) {
            language = languageRepository.findById(id).orElse(null);
            language.setName(languageEntity.getName());
        } else {
            throw new LanguageException(HttpStatus.NOT_FOUND);
        }
        return languageRepository.save(language);
    }

    public List<Language> checkAndReturnLanguage(PersonDTO personDTO) {
        Language language;
        List<Language> listLanguage = new ArrayList<>();
        for (int i = 0; i < personDTO.getLanguageList().size(); i++) {
            if (personDTO.getLanguageList().get(i).getId() != null) {
                Optional<Language> optional = languageRepository.findById(personDTO.getLanguageList().get(i).getId());
                if (optional.isPresent()) {
                    language = optional.get();
                } else {
                    language = new Language();
                }
            } else {
                throw new LanguageException(HttpStatus.NOT_FOUND);
            }
            listLanguage.add(language);
        }
        return listLanguage;
    }

}
