package com.heitor.projeto.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MensagemPadrao {

	private Integer status;
    private String cause;
    private String msg;
    private Long timeStamp;
    private LocalDateTime data;
    private String path;
}
