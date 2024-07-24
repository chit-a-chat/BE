package com.shyji.chitachat.controller;

import com.shyji.chitachat.dto.ApiResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

  @GetMapping("/responseTest")
  public ApiResponseDto<String> getTest() {
    String example = "Hello Test";
    return ApiResponseDto.success(example);
  }
}
