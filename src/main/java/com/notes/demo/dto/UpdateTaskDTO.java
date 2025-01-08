package com.notes.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {
    String description;
    String deadline;
    Boolean completed;
}

