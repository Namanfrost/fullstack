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
public class User {
    @Id
    private String id;
    private String Name;
    private String Email;
    private String Password;
    

}
