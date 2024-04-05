package ru.muctr;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) {
        Path root = Paths.get("src/Directory_for_lab3");

        if (Files.notExists(root)) {
            System.out.println("Папка " + root.toString() + " не существует");
            return;
        }

        try {
            Files.walkFileTree(root, new FileVisitor<Path>() {


                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                                                         BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file,
                                                 BasicFileAttributes attrs) throws IOException {
//                    System.out.println("Посетили файл " + file.getFileName().toString());
                    moveFile(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc)
                        throws IOException {
                    System.out.println("Ошибка при посещении файла " + file.toString());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                        throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println("При обходе дерева папок произошла ошибка");
        }
    }

    public static void moveFile(Path file) throws IOException {
        String filename = file.getFileName().toString();
        if (filename.toLowerCase().contains("lab")){
            moveProcessing(file, "Lab");
        }
        else if (filename.toLowerCase().contains("questions")){
            moveProcessing(file, "Questions");
        }
        else if (filename.toLowerCase().contains("lecture")){
            moveProcessing(file, "Lectures");
        }
    }

    public static void moveProcessing(Path file, String dirname) throws IOException {
        String filename = file.getFileName().toString();
        File theDir = new File("src\\Directory_for_lab3\\" + dirname);
        if (!theDir.exists()){
            boolean bool = theDir.mkdir();
            if (bool) {
                System.out.println("Folder is created successfully");
            }
        }
        Path targetPath = Paths.get("src\\Directory_for_lab3\\" + dirname + "\\" + filename);
        Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }
}