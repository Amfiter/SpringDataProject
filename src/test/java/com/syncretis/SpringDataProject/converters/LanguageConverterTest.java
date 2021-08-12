package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.entities.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LanguageConverterTest {

    private final LanguageConverter languageConverter = new LanguageConverter();

    @Test
    @DisplayName("shouldConvertEntityToDto")
    void entityToDto() {
        //given
        Language language = new Language();
        language.setId(12L);
        language.setName("English");

        //when
        LanguageDTO actual = languageConverter.entityToDto(language);

        //then
        LanguageDTO expected = new LanguageDTO();
        expected.setId(12L);
        expected.setName("English");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoToEntity")
    void dtoToEntity() {
        //given
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(12L);
        languageDTO.setName("English");

        //when
        Language actual = languageConverter.dtoToEntity(languageDTO);

        //then
        Language expected = new Language();
        expected.setId(12L);
        expected.setName("English");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertEntityListToDtoList")
    void entityListToDtoList() {
        //given
        List<Language> languageList = new ArrayList<>();
        Language language1 = new Language();
        language1.setId(12L);
        language1.setName("English");

        Language language2 = new Language();
        language2.setId(10L);
        language2.setName("Dutch");

        languageList.add(language1);
        languageList.add(language2);

        //when
        List<LanguageDTO> actual = languageConverter.entityToDto(languageList);

        //then
        List<LanguageDTO> expected = new ArrayList<>();
        LanguageDTO languageDTO1 = new LanguageDTO();
        languageDTO1.setId(12L);
        languageDTO1.setName("English");

        LanguageDTO languageDTO2 = new LanguageDTO();
        languageDTO2.setId(10L);
        languageDTO2.setName("Dutch");

        expected.add(languageDTO1);
        expected.add(languageDTO2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoListToEntityList")
    void dtoListToEntityList() {
        //given
        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO1 = new LanguageDTO();
        languageDTO1.setId(12L);
        languageDTO1.setName("English");

        LanguageDTO languageDTO2 = new LanguageDTO();
        languageDTO2.setId(10L);
        languageDTO2.setName("Dutch");

        languageListDTO.add(languageDTO1);
        languageListDTO.add(languageDTO2);

        //when
        List<Language> actual = languageConverter.dtoToEntity(languageListDTO);

        //then
        List<Language> expected = new ArrayList<>();
        Language language1 = new Language();
        language1.setId(12L);
        language1.setName("English");

        Language language2 = new Language();
        language2.setId(10L);
        language2.setName("Dutch");

        expected.add(language1);
        expected.add(language2);

        assertThat(actual).isEqualTo(expected);
    }
}