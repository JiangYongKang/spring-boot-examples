package spring.boot.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    @ApiOperation("POST请求")
    public ResponseEntity<?> create(@ApiParam(name = "name", required = true, example = "vincent") @RequestParam("name") String name) {
        return ResponseEntity.ok("success");
    }

}
