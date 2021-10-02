package cn.hylstudio.mdse.demo.realworld.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthException extends RuntimeException {
    private String errMsg;

    public AuthException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }
}
