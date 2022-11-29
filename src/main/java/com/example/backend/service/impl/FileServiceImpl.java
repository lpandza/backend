package com.example.backend.service.impl;

import com.example.backend.service.FileService;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
  @Override
  public void saveFile(String content, String fileName) throws IOException {
    FileWriter fw = new FileWriter("./files/" + fileName + ".txt");
    fw.write(content);
    fw.close();
  }
}
