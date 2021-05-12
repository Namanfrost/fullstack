package com.newpro.webpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoNotes {
    @Id
    private String id;
    private String title;
    private String description;
    private String deadline;
    private String userId;
}
