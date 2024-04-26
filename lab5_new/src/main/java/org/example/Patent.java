package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
public class Patent {
    private String title;
    private String number;
    private Date date;
    private List<String> inventors;
    private List<String> companies;
    private List<String> mpk;
}