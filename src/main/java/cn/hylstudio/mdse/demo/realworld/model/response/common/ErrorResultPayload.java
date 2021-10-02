package cn.hylstudio.mdse.demo.realworld.model.response.common;

import cn.hylstudio.mdse.demo.realworld.model.response.ResponsePayload;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ErrorResultPayload extends ResponsePayload {
    public ErrorResultPayload(ErrorResultDto errors) {
        this.errors = errors;
    }

    public ErrorResultDto getErrors() {
        return errors;
    }

    public void setErrors(ErrorResultDto errors) {
        this.errors = errors;
    }

    private ErrorResultDto errors;

    public static ErrorResultPayload build(List<String> errors) {
        ErrorResultDto result = new ErrorResultDto(errors);
        return new ErrorResultPayload(result);
    }
//    {
//        "errors":{
//            "body": [
//                "can't be empty"
//           ]
//        }
//    }

}
