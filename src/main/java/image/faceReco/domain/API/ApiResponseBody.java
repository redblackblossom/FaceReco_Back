package image.faceReco.domain.API;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseBody {
    private boolean success;
    private String message;

    public ApiResponseBody() {}
    public ApiResponseBody(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
