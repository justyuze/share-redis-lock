package demo.iyushu.service.b.api;

import demo.iyushu.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private FileService fileService;

    @GetMapping("/hello")
    public String hello(){
        int count =  fileService.getCount();
        fileService.setCount(++count);
        return "服务B获取到的计数为:" + count;
    }
}
