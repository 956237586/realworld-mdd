package cn.hylstudio.mdse.demo.realworld.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BizException extends RuntimeException {
    private String errMsg;

    public BizException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }
}
