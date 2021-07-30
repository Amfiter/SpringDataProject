package com.syncretis.SpringDataProject.converter;

import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.models.Language;

public class LanguageConverter {

    public LanguageDTO entityToDto(Language language){
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(languageDTO.getId());
        languageDTO.setName(languageDTO.getName());
        return languageDTO;
    }

    public Language dtoToEntity(LanguageDTO languageDTO){
        Language language = new Language();
        language.setId(language.getId());
        language.setName(language.getName());
        return language;
    }
}
