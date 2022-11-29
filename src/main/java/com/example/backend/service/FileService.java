package com.example.backend.service;

import java.io.IOException;
import java.nio.file.Path;

public interface FileService {
  void saveFile(String content, String fileName) throws IOException;
}
