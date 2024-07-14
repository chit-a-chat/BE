package com.shyji.chitachat.controller;

import com.shyji.chitachat.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

  @GetMapping("/responseTest")
  public ApiResponse<String> getTest() {
    String example = "Hello Test";
    return ApiResponse.success(example);
  }
}
