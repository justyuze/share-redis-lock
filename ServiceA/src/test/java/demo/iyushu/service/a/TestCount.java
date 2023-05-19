package demo.iyushu.service.a;


import demo.iyushu.service.a.service.CountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCount {

    @Autowired
    private CountService countService;

    @Test
    public void testFileCount() throws InterruptedException {

        for(int i = 0; i < 10; i++){
            countService.getCount();
        }

    }

}
