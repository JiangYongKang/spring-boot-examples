package com.person.vincent;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
@RestController
public class Swagger2DemoApplication {

    @ApiOperation(value = "返回两个操作数的乘积", notes = "这里是接口的详细说明")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "operand1", value = "操作数一", required = true, dataType = "Double", paramType = "String"),
            @ApiImplicitParam(name = "operand2", value = "操作数二", required = true, dataType = "Double")
    })*/
    @RequestMapping(value = "/addition/{operand1}/{operand2}", method = RequestMethod.POST)
    public Double index(@PathVariable(value = "operand1") Double operand1,
                        @PathVariable(value = "operand2") Double operand2,
                        @RequestBody Map<String, Character> operator) {
        switch (operator.get("operator")) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 + operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
        }
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(Swagger2DemoApplication.class, args);
    }
}
