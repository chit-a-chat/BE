package com.shyji.chitachat.controller;

import com.shyji.chitachat.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

  @GetMapping("/example")
  @Operation(summary = "Test API", description = "SWAGGER TEST 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Bad Request"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  public ApiResponseDto<String> getExample() {
    return ApiResponseDto.success("Example Response");
  }

  @GetMapping("/exception")
  @Operation(summary = "Exception Test API", description = "예외 발생 테스트")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Bad Request"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  public ApiResponseDto<?> getException(
      @Parameter(name = "input", description = "입력 매개변수", example = "error")
      @RequestParam(name = "input", required = false) String input) {
    if (input == null || input.trim().isEmpty()) {
      return ApiResponseDto.error("파라미터를 입력해주세요.");
    }
    if ("error".equals(input)) {
      throw new RuntimeException("예외 발생");
    }
    return ApiResponseDto.success("유효한 입력입니다: " + input);
  }
}
