package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Similarity {
    private String worksName;
    private String authorName;
    private Double title_similarity;
    private Double introduct_similarity;
    private Double design_similarity;
}
