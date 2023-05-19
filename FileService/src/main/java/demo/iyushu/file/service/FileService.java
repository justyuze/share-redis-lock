package demo.iyushu.file.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;

@Service
public class FileService {

    String filePath = "/Users/yuze/Documents/Java/info.kleven/redis-lock/CountFile.txt";

    public int getCount() {
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (StringUtils.hasLength(line)) {
                return Integer.valueOf(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void setCount(int count) {
        File file = new File(filePath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(count + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
