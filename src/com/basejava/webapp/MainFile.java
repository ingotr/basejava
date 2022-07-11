package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainFile {
    public static void main(String[] args) throws IOException {
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

//        try (Stream<Path> walk = Files.walk(Paths.get("./src"))) {
//            List<String> result = walk.filter(Files::isRegularFile)
//                    .map(x -> x.toString()).collect(Collectors.toList());
//
//            result.forEach(System.out::println);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        final File folder = new File("./src");
        List<String> result = new ArrayList<>();
        search(".*\\.java", folder, result);

        for (String s : result) {
            System.out.println(s);
        }
    }

    public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : Objects.requireNonNull(folder.listFiles())) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getPath());
                }
            }
        }
    }
}
