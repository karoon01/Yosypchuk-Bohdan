package com.epam.spring.homework3.model.DTO;

import com.epam.spring.homework3.model.entity.Activity;
import com.epam.spring.homework3.model.entity.Status;
import com.epam.spring.homework3.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RequestDTO {
    private Long id;
    private Status status;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date requestDate;

    private Activity activity;
    private User user;
}
