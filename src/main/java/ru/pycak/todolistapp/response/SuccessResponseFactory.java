package ru.pycak.todolistapp.response;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SuccessResponseFactory {

    public <Body> ResponseEntity<BaseResponse<Body>> makeSuccessResponse() {
        return makeSuccessResponse(null);
    }

    public <Body> ResponseEntity<BaseResponse<Body>> makeSuccessResponse(
            @Nullable Body body
    ) {
        return makeSuccessResponse(body, null);
    }

    public <Body> ResponseEntity<BaseResponse<Body>> makeSuccessResponse(
            @Nullable Body body,
            @Nullable String message
    ) {
        return ResponseEntity
                .ok()
                .body(BaseResponse.success(body, message));
    }
}
