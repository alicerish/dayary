package us.spring.dayary.domain;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

@JsonSerialize(using = StatusSerializer.class)
public enum Status {
    OK("OK"),
    BAD(":("),
    EMPTY_STRING("공백입력불가"),
    PASSWORD_MISMATCH("비밀번호불일치"),
    LOGINED_ANOTHER_DIVICE("다른기기에서로그인됨"),
    INVALID_TOKEN("유효하지않은토큰"),
    EXPIRED_TOKEN("만료된토큰"),
    DELETE_ALREADY("이미삭제되었음"),
    NO_CONTENT("존재하지않는일기"),
    EXIST_ID("존재하는아이디"),
    INVAILD_LOGIN_INFO("아이디비밀번호확인필요"),
    INVALID_PARAMETER("잘못된요청");

    private String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

class StatusSerializer extends StdSerializer {

    public StatusSerializer() {
        super(Status.class);
    }

    @Override
    public void serialize(Object obj, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Status status = (Status) obj;
        gen.writeStartObject();
        gen.writeFieldName("code");
        gen.writeString(status.name());
        gen.writeFieldName("message");
        gen.writeString(status.getMessage());
        gen.writeEndObject();
    }
}