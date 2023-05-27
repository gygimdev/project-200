//package hello.project.controller;
//
//import hello.project.dto.task.TaskCreateForm;
//import hello.project.exception.ErrorResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//public class RestApiController {
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalExHandler(IllegalArgumentException e) {
//        return new ErrorResult("BAD", e.getMessage());
//    }
//
//    @GetMapping("/api/{memberId}")
//    public String check(@PathVariable("memberId") String id) {
////        throw new RuntimeException("잘못된 정보1");
////        throw new IllegalStateException("잘못된 정보2");
//        throw new IllegalArgumentException("잘못된 정보3");
//    }
//
//    @PostMapping("/api/createTask")
//    public Object addTask(@RequestBody @Validated TaskCreateForm form, BindingResult bidingResult) {
//        log.info("::: 확인");
//
//        if(bidingResult.hasErrors()) {
//            return bidingResult.getAllErrors();
//        }
//
//        return form;
//    }
//}
