package com.moss.exception;

import com.moss.result.CodeMessage;

public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMessage codeMessage;

    public GlobalException(CodeMessage codeMessage) {
        super();
        this.codeMessage = codeMessage;
    }

    public CodeMessage getCodeMessage() {
        return codeMessage;
    }

    public void setCodeMessage(CodeMessage codeMessage) {
        this.codeMessage = codeMessage;
    }
}
