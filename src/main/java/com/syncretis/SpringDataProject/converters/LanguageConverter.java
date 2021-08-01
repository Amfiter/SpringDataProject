package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.models.Language;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageConverter {

    public LanguageDTO entityToDto(Language language) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(language.getId());
        languageDTO.setName(language.getName());
        return languageDTO;
    }

    public List<LanguageDTO> entityToDto(List<Language> language) {
        return language.stream().map(languageEntity -> entityToDto(languageEntity)).collect(Collectors.toList());
    }

    public Language dtoToEntity(LanguageDTO languageDTO) {
        Language language = new Language();
        language.setId(languageDTO.getId());
        language.setName(languageDTO.getName());
        return language;
    }

    public List<Language> dtoToEntity(List<LanguageDTO> languageDTO) {
        return languageDTO.stream().map(DTO -> dtoToEntity(DTO)).collect(Collectors.toList());
    }
}
