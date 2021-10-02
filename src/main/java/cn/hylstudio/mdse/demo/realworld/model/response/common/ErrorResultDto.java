package cn.hylstudio.mdse.demo.realworld.model.response.common;

import java.util.List;

public class ErrorResultDto {

    private List<String> body;

    public ErrorResultDto(List<String> body) {
        this.body = body;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }
}