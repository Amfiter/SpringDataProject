package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.DocumentConverter;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Document;
import com.syncretis.SpringDataProject.exceptions.DocumentException;
import com.syncretis.SpringDataProject.repositories.DocumentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({MockitoExtension.class})
class DocumentServiceTest {

    @Mock
    DocumentRepository documentRepository;
    @Mock
    DocumentConverter documentConverter;

    @InjectMocks
    DocumentService documentService;

    @Test
    @DisplayName("shouldReturnAllDocuments")
    void getDocuments() {
        //given
        List<Document> documentList = new ArrayList<>();
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        Document document1 = new Document();
        document1.setExpireDate(LocalDate.of(2025, 3, 3));
        document1.setNumber("43214321");

        documentList.add(document);
        documentList.add(document1);

        List<DocumentDTO> documentsDto = new ArrayList<>();

        DocumentDTO documentDto = new DocumentDTO();
        documentDto.setNumber("12341234");
        documentDto.setExpireDate(LocalDate.of(2023, 1, 1));

        DocumentDTO documentDto1 = new DocumentDTO();
        documentDto1.setNumber("43214321");
        documentDto1.setExpireDate(LocalDate.of(2025, 3, 3));

        documentsDto.add(documentDto);
        documentsDto.add(documentDto1);
        //when
        Mockito.when(documentRepository.findAll()).thenReturn(documentList);
        Mockito.when(documentConverter.entityToDto(documentList)).thenReturn(documentsDto);

        List<DocumentDTO> actualDocumentsDto = documentService.getDocuments();
        //then
        Mockito.verify(documentRepository).findAll();
        Mockito.verify(documentConverter).entityToDto(documentList);
        assertThat(actualDocumentsDto).isEqualTo(documentsDto);
    }

    @Test
    @DisplayName("shouldReturnDocumentById")
    void getDocumenttById() {
        //given
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        DocumentDTO documentDto = new DocumentDTO();
        documentDto.setNumber("12341234");
        documentDto.setExpireDate(LocalDate.of(2023, 1, 1));

        //when
        Mockito.when(documentRepository.findById("qeqeqeqe")).thenReturn(Optional.of(document));
        Mockito.when(documentConverter.entityToDto(document)).thenReturn(documentDto);
        DocumentDTO actualDocumentDto = documentService.getDocuments("qeqeqeqe");

        //then
        Mockito.verify(documentRepository).findById("qeqeqeqe");
        Mockito.verify(documentConverter).entityToDto(document);
        assertThat(actualDocumentDto).isEqualTo(documentDto);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorFindById")
    void GetDocumentError() {
        //when
        Mockito.when(documentRepository.findById("qeqeqeqeqeq")).thenThrow(new DocumentException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> documentService.getDocuments("qeqeqeqeqeq"))
                .isInstanceOf(DocumentException.class);
        Mockito.verify(documentRepository).findById("qeqeqeqeqeq");
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorDeleteById")
    void deleteDocumentError() {
        //when
        Mockito.when(documentRepository.findById("qeqeqeqeqeq")).thenThrow(new DocumentException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> documentService.deleteDocument("qeqeqeqeqeq"))
                .isInstanceOf(DocumentException.class);
        Mockito.verify(documentRepository).findById("qeqeqeqeqeq");
    }

    @Test
    @DisplayName("shouldSaveDocument")
    void addNewDocument() {
        //given
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        DocumentDTO documentDto = new DocumentDTO();
        documentDto.setNumber("12341234");
        documentDto.setExpireDate(LocalDate.of(2023, 1, 1));

        //when
        Mockito.when(documentConverter.dtoToEntity(documentDto)).thenReturn(document);
        Mockito.when(documentRepository.save(document)).thenReturn(document);

        Document actualDocument = documentService.addNewDocument(documentDto);

        //then
        Mockito.verify(documentRepository).save(document);
        Mockito.verify(documentConverter).dtoToEntity(documentDto);
        assertThat(actualDocument).isEqualTo(document);
    }

    @Test
    @DisplayName("ShouldDeleteDocumentById")
    void deleteDocument() {
        //given
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        //when
        Mockito.when(documentRepository.findById("qeqeqeqe")).thenReturn(Optional.of(document));
        documentService.deleteDocument("qeqeqeqe");

        //then
        Mockito.verify(documentRepository).findById("qeqeqeqe");
        Mockito.verify(documentRepository).deleteById("qeqeqeqe");
    }

    @Test
    @DisplayName("checkAndReturnDocumentIfIdIsNotNullAndIsPresent")
    void checkAndReturnDepartment() {
        //given
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId("qeqeqeqe");
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(documentRepository.findById("qeqeqeqe")).thenReturn(Optional.of(document));
        Document expected = documentService.checkAndReturnDocument(personDTO);

        //then
        assertThat(document).isEqualTo(expected);

    }

    @Test
    @DisplayName("checkAndReturnDocumentIfIdIsNotNullAndIsNotPresent")
    void checkAndReturnDepartmentIfNotExists() {
        //given
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId("qeqeqeqe");
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(documentRepository.findById("qeqeqeqe")).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> documentService.checkAndReturnDocument(personDTO))
                .isInstanceOf(DocumentException.class);

    }

    @Test
    @DisplayName("checkAndReturnDocumentIfIdIsNullButNumberIsPresent")
    void checkAndReturnDepartmentError() {
        //given
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(documentRepository.findByNumber("12341234")).thenReturn(Optional.of(document));

        //then
        assertThatThrownBy(() -> documentService.checkAndReturnDocument(personDTO))
                .isInstanceOf(DocumentException.class);
    }


    @Test
    @DisplayName("checkAndReturnDocumentIfIdIsNullButNumberIsNotPresent")
    void checkAndReturnDepartmentErrorNumberIsNotPresent() {
        //given
        Document document = new Document();
        document.setNumber("12331234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12331234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(documentRepository.findByNumber("12331234")).thenReturn(Optional.empty());
        Document expected = documentService.checkAndReturnDocument(personDTO);

        //then
        assertThat(document).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldReturnUpdateDocumentIfIsExist")
    void updateDocument1() {
        //given
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        DocumentDTO documentDto = new DocumentDTO();
        documentDto.setNumber("12341234");
        documentDto.setExpireDate(LocalDate.of(2023, 1, 1));

        //when
        Mockito.when(documentConverter.dtoToEntity(documentDto)).thenReturn(document);
        Mockito.when(documentRepository.existsById("12341234")).thenReturn(true);
        Mockito.when(documentRepository.findById("12341234")).thenReturn(Optional.of(document));
        Mockito.when(documentRepository.save(document)).thenReturn(document);

        Document actualDocument = documentService.updateDocument(documentDto, "12341234");

        //then
        Mockito.verify(documentRepository).save(document);
        Mockito.verify(documentRepository).findById("12341234");
        Mockito.verify(documentConverter).dtoToEntity(documentDto);
        assertThat(actualDocument).isEqualTo(document);
    }

    @Test
    @DisplayName("shouldReturnUpdateDocumentIfIsNotExist")
    void updateDocument2() {
        //given
        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        DocumentDTO documentDto = new DocumentDTO();
        documentDto.setNumber("12341234");
        documentDto.setExpireDate(LocalDate.of(2023, 1, 1));

        //when
        Mockito.when(documentConverter.dtoToEntity(documentDto)).thenReturn(document);
        Mockito.when(documentRepository.existsById("12341234")).thenReturn(false);

        //then
        assertThatThrownBy(() -> documentService.updateDocument(documentDto, "12341234")).isExactlyInstanceOf(DocumentException.class);
    }
}