package com.rents.services;

import org.springframework.web.multipart.MultipartFile;

import com.rents.exceptions.FormatNotSupportedException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageInterface {
    String uploadImage(MultipartFile file) throws IOException, FormatNotSupportedException;

    byte[] getImage(String name) throws FileNotFoundException;
}
