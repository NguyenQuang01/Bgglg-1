package com.example.itspower.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ReportDetailsException extends RuntimeException {
    private final Integer statusCode;
    private final String causeMsg;
    private final  String detailsMessage;
    public ReportDetailsException(ErrorCode errorCode) {
        super(errorCode.getDetailsMessage());
        this.statusCode = errorCode.getResponseStatus();
        this.causeMsg = errorCode.getCauseMsg();
        this.detailsMessage = errorCode.getDetailsMessage();
    }

    public ReportDetailsException(Integer errorCode, String causeMsg, String detailsMessage) {
        super(detailsMessage);
        this.statusCode = errorCode;
        this.causeMsg = causeMsg;
        this.detailsMessage = detailsMessage;
    }
}
