package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.entities.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DocumentConverterTest {

    private final DocumentConverter documentConverter = new DocumentConverter();

    @Test
    @DisplayName("shouldConvertEntityToDto")
    void entityToDto() {
        //given
        Document document = new Document();
        document.setId("2c91948d7b101e2e017b102165920000");
        document.setExpireDate(LocalDate.of(2022, 7, 28));
        document.setNumber("12341234");

        //when
        DocumentDTO actual  = documentConverter.entityToDto(document);

        //then
        DocumentDTO expected  = new DocumentDTO();
        expected .setId("2c91948d7b101e2e017b102165920000");
        expected.setExpireDate(LocalDate.of(2022, 7, 28));
        expected.setNumber("12341234");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoToEntity")
    void dtoToEntity() {
        //given
        DocumentDTO documentDTO  = new DocumentDTO();
        documentDTO .setId("2c91948d7b101e2e017b102165920000");
        documentDTO.setExpireDate(LocalDate.of(2022, 7, 28));
        documentDTO.setNumber("12341234");

        //when
        Document actual  = documentConverter.dtoToEntity(documentDTO);

        //then
        Document expected = new Document();
        expected.setId("2c91948d7b101e2e017b102165920000");
        expected.setExpireDate(LocalDate.of(2022, 7, 28));
        expected.setNumber("12341234");
        assertThat(actual).isEqualTo(expected);
        
    }

    @Test
    @DisplayName("shouldConvertEntityListToDtoList")
    void entityListToDtoList() {
        //given
        List<Document> documentList = new ArrayList<>();

        Document document1 = new Document();
        document1.setId("2c91948d7b101e2e017b102165920000");
        document1.setExpireDate(LocalDate.of(2022, 7, 28));
        document1.setNumber("12341234");

        Document document2 = new Document();
        document2.setId("2c91948d7b101e2e017b1021659200001");
        document2.setExpireDate(LocalDate.of(2024, 8, 4));
        document2.setNumber("43214321");

        documentList.add(document1);
        documentList.add(document2);

        //when
        List<DocumentDTO> actual  = documentConverter.entityToDto(documentList);

        //then
        List<DocumentDTO> expected = new ArrayList<>();

        DocumentDTO documentDTO1  = new DocumentDTO();
        documentDTO1 .setId("2c91948d7b101e2e017b102165920000");
        documentDTO1.setExpireDate(LocalDate.of(2022, 7, 28));
        documentDTO1.setNumber("12341234");

        DocumentDTO documentDTO2  = new DocumentDTO();
        documentDTO2 .setId("2c91948d7b101e2e017b1021659200001");
        documentDTO2.setExpireDate(LocalDate.of(2024, 8, 4));
        documentDTO2.setNumber("43214321");

        expected.add(documentDTO1);
        expected.add(documentDTO2);

        for (int i = 0; i < expected.size(); i++) {
            assertThat(actual.get(i)).isEqualTo(expected.get(i));
        }
    }

    @Test
    @DisplayName("shouldConvertDtoListToEntityList")
    void dtoListToEntityList() {
        //given
        List<DocumentDTO> documentDTOList = new ArrayList<>();

        DocumentDTO documentDTO1  = new DocumentDTO();
        documentDTO1 .setId("2c91948d7b101e2e017b102165920000");
        documentDTO1.setExpireDate(LocalDate.of(2022, 7, 28));
        documentDTO1.setNumber("12341234");

        DocumentDTO documentDTO2  = new DocumentDTO();
        documentDTO2 .setId("2c91948d7b101e2e017b1021659200001");
        documentDTO2.setExpireDate(LocalDate.of(2024, 8, 4));
        documentDTO2.setNumber("43214321");

        documentDTOList.add(documentDTO1);
        documentDTOList.add(documentDTO2);

        //when
        List<Document> actual  = documentConverter.dtoToEntity(documentDTOList);

        //then
        List<Document> expected = new ArrayList<>();

        Document document1 = new Document();
        document1.setId("2c91948d7b101e2e017b102165920000");
        document1.setExpireDate(LocalDate.of(2022, 7, 28));
        document1.setNumber("12341234");

        Document document2 = new Document();
        document2.setId("2c91948d7b101e2e017b1021659200001");
        document2.setExpireDate(LocalDate.of(2024, 8, 4));
        document2.setNumber("43214321");

        expected.add(document1);
        expected.add(document2);

        for (int i = 0; i < expected.size(); i++) {
            assertThat(actual.get(i)).isEqualTo(expected.get(i));
        }
    }
}