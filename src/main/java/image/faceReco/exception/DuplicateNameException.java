package image.faceReco.exception;

import java.util.List;

public class DuplicateNameException extends RuntimeException {
    List<String> duplicateName;

    public DuplicateNameException(List<String> duplicateName) {
        this.duplicateName = duplicateName;
    }

    public DuplicateNameException(String message, List<String> duplicateName) {
        super(message);
        this.duplicateName = duplicateName;
    }

    public DuplicateNameException(String message, Throwable cause, List<String> duplicateName) {
        super(message, cause);
        this.duplicateName = duplicateName;
    }

    public DuplicateNameException(Throwable cause, List<String> duplicateName) {
        super(cause);
        this.duplicateName = duplicateName;
    }

    public List<String> getDuplicateName() {
        return duplicateName;
    }
}
