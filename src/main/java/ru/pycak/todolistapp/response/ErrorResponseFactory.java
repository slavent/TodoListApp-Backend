package ru.pycak.todolistapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public final class ErrorResponseFactory {

    public ResponseEntity<?> makeNotFoundResponse(String message) {
        return makeErrorResponse(HttpStatus.NOT_FOUND, message);
    }

    public ResponseEntity<?> makeBadRequestResponse(String message) {
        return makeBadRequestResponse(null, message);
    }

    public <Body> ResponseEntity<BaseResponse<Body>> makeBadRequestResponse(
            Body body,
            String message
    ) {
        return makeErrorResponse(HttpStatus.BAD_REQUEST, body, message);
    }

    public ResponseEntity<?> makeUnauthorizedResponse(String message) {
        return makeErrorResponse(HttpStatus.UNAUTHORIZED, message);
    }

    public ResponseEntity<?> makeInternalErrorResponse() {
        return makeErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error"
        );
    }

    public ResponseEntity<?> makeErrorResponse(
            @NonNull HttpStatus status,
            @Nullable String message
    ) {
        return makeErrorResponse(status, null, message);
    }

    public <Body> ResponseEntity<BaseResponse<Body>> makeErrorResponse(
            @NonNull HttpStatus status,
            @Nullable Body body,
            @Nullable String message
    ) {
        return ResponseEntity
                .status(status)
                .body(BaseResponse.error(body, message));
    }
}
