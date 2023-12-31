package ru.qwonix.test.social.media.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.qwonix.test.social.media.api.TestcontainersConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql("/sql/image_rest_controller/test_data.sql")
@SpringBootTest(classes = TestcontainersConfiguration.class)
@Transactional
@AutoConfigureMockMvc
class ImageControllerIT {

    private static final String NON_IMAGE = "sql/image_rest_controller/test_data.sql";
    private static final String IMAGE_2 = "img/image_rest_controller/image_2.png";
    private static final String IMAGE_3 = "img/image_rest_controller/image_3.png";

    @Autowired
    MockMvc mockMvc;

    @Test
    void handleGetByName_ValidImageName_ReturnsValidResponse() throws Exception {
        var requestBuilder = get("/api/v1/image/ca444eab-30e7-4bdc-ac1e-a2fe48db8f60_image_1.png");

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.IMAGE_PNG)
                );
    }

    @WithMockUser(username = "user1", authorities = {"UPLOAD_IMAGE"})
    @Test
    void handleUploadNewImage_ValidImage_ReturnsValidResponse() throws Exception {
        var image3 = new MockMultipartFile(
                "image",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                new ClassPathResource(IMAGE_3).getInputStream()
        );

        var requestBuilder = multipart("/api/v1/image/upload")
                .file(image3)
                .contentType(MediaType.IMAGE_PNG);

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isCreated(),
                        header().exists(HttpHeaders.LOCATION)
                );
    }

    @WithMockUser(username = "user1", authorities = {"UPLOAD_IMAGE"})
    @Test
    void handleUploadNewImage_DuplicateImageName_ReturnsValidResponse() throws Exception {
        var image2 = new MockMultipartFile(
                "image",
                "image_2.png",
                MediaType.IMAGE_PNG_VALUE,
                new ClassPathResource(IMAGE_2).getInputStream()
        );

        var requestBuilder = multipart("/api/v1/image/upload")
                .file(image2)
                .contentType(MediaType.IMAGE_PNG);

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isCreated(),
                        header().exists(HttpHeaders.LOCATION)
                );
    }

    @WithMockUser(username = "user1", authorities = {"UPLOAD_IMAGE"})
    @Test
    void handleUploadNewImage_NotAnImage_ReturnsValidErrorResponse() throws Exception {
        var image2 = new MockMultipartFile(
                "image",
                "non_image.png",
                MediaType.TEXT_PLAIN_VALUE,
                new ClassPathResource(NON_IMAGE).getInputStream()
        );

        var requestBuilder = multipart("/api/v1/image/upload")
                .file(image2)
                .contentType(MediaType.IMAGE_PNG);

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                    "errorMessages": [{
                                            "field": "image",
                                            "message": "not a image"
                                        }
                                    ]
                                }
                                """)
                );
    }
}