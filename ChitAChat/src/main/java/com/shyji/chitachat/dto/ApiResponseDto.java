package com.shyji.chitachat.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공통 API 응답 객체")
public class ApiResponseDto<T> {

  private static final String SUCCESS_STATUS = "success";
  private static final String FAIL_STATUS = "fail";
  private static final String ERROR_STATUS = "error";

  @Schema(description = "응답상태", example = "suuccess")
  private String status;

  @Schema(description = "응답 데이터")
  private T data;

  @Schema(description = "응답상태", example = "Operation completed successfully")
  private String message;


  public static <T> ApiResponseDto<T> success(T data) {
    return new ApiResponseDto<>(SUCCESS_STATUS, data, null);
  }

  public static ApiResponseDto<?> successWithNoContent() {
    return new ApiResponseDto<>(SUCCESS_STATUS, null, null);
  }

  public static ApiResponseDto<?> fail(BindingResult bindingResult) {
    Map<String, String> errors = new HashMap<>();

    List<ObjectError> allErrors = bindingResult.getAllErrors();
    for (ObjectError error : allErrors) {
      if (error instanceof FieldError) {
        errors.put(((FieldError) error).getField(), error.getDefaultMessage());
      } else {
        errors.put(error.getObjectName(), error.getDefaultMessage());
      }
    }
    return new ApiResponseDto<>(FAIL_STATUS, errors, null);
  }

  public static ApiResponseDto<?> error(String message) {
    return new ApiResponseDto<>(ERROR_STATUS, null, message);
  }
}
