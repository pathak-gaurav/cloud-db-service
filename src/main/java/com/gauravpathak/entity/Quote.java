package com.gauravpathak.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "QUOTES", catalog = "test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quoteId;
    private String username;
    private String quote;

    public Quote(String username, String quote) {
        this.username = username;
        this.quote = quote;
    }
}
