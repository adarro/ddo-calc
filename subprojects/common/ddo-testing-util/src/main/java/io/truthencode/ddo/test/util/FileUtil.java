package io.truthencode.ddo.test.util;

import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

    public static File loadResource(
        String fileName
    ) {
        var url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        assert url != null;
        var in = java.nio.file.Paths.get(url.getPath());
        if (!in.toFile().exists()) logger.log(Level.WARNING, "can not load file!!");
        return in.toFile();
    }

}
