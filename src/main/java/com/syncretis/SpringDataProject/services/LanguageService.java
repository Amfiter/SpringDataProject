package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.LanguageConverter;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.exceptions.LanguageException;
import com.syncretis.SpringDataProject.models.Language;
import com.syncretis.SpringDataProject.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageConverter languageConverter;

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
        if (!languageRepository.existsById(id)) {
            throw new LanguageException(HttpStatus.NOT_FOUND);
        }
        languageRepository.deleteById(id);
    }

    public Language updateLanguage(LanguageDTO newLanguage, Long id) {
        Language languageEntity = languageConverter.dtoToEntity(newLanguage);
        return languageRepository.findById(id)
                .map(language -> {
                    language.setName(languageEntity.getName());
                    return languageRepository.save(language);
                })
                .orElseThrow(() -> new LanguageException(HttpStatus.NOT_FOUND));
    }

    public List<Language> checkAndReturnLanguage(PersonDTO personDTO) {
        Language language;
        List<Language> listLanguage = new ArrayList<>();
        for (int i = 0; i < personDTO.getLanguageList().size(); i++) {
            if (personDTO.getLanguageList().get(i).getId() != null) {
                Optional<Language> optional = languageRepository.findById(personDTO.getLanguageList().get(i).getId());
                System.out.println("нашел optional languageById" + optional);
                if (optional.isPresent()) {
                    language = optional.get();
                } else {
                    language = new Language();
                }
            } else {
                throw new LanguageException(HttpStatus.BAD_REQUEST);
            }
            /*newLanguage.setId(personDTO.getLanguageList().get(i).getId());
            newLanguage.setName(personDTO.getLanguageList().get(i).getName());*/
            listLanguage.add(language);
        }
        return listLanguage;
    }

}
