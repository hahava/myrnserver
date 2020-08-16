package me.hahajava.rnserver.config.exception;

public enum ExceptionMessage {

    DEFAULT_ERROR("서비스를 이용할 수 없습니다. 관리자에게 문의 하세요"),
    NO_LOGIN_DATA("아이디 또는 비밀번호가 일치하지 않습니다."),
    BAD_REQUEST_PARAMETER("요청 매개변수가 올바르지 않습니다.");

    public String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
