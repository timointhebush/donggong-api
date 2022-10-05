package com.timointhebush.donggongapi.model.input;

import com.timointhebush.donggongapi.model.enums.FileType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class PerformanceFileCreateInput extends FileInput {
    @Setter
    private FileType type;

    public static PerformanceFileCreateInput of(MultipartFile file, FileType type) {
        PerformanceFileCreateInput input = new PerformanceFileCreateInput();
        input.setFile(file);
        input.setType(type);
        return input;
    }
}
