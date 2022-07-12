package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "./.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/basejava/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final File folder = new File("./src");
       File[] maindir = folder.listFiles();
        List<String> result = new ArrayList<>();
        search(maindir, result, 0);

        for (String s : result) {
            System.out.println(s);
        }
    }

    public static void search(final File[] folder, List<String> result, int level) {
        for (final File f : Objects.requireNonNull(folder)) {
            StringBuilder prefix = new StringBuilder();
            prefix.append("\t".repeat(Math.max(0, level)));

            if (f.isFile()) {
                result.add(prefix + f.getName());
            } else if (f.isDirectory()) {
                result.add(prefix + "[" + f.getName() + "]");
                search(f.listFiles(), result, level + 1);
            }
        }
    }
}
