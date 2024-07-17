package zad1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        Path startDir = Paths.get(dirName);
        Path result = Paths.get(resultFileName);

        try {
            if (!Files.exists(result)) {
                Files.createFile(result);
            }

            BufferedWriter writer = Files.newBufferedWriter(result, StandardCharsets.UTF_8);

            Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".txt")) {
                        try (BufferedReader reader = Files.newBufferedReader(file, Charset.forName("Cp1250"))) {
                            String line = reader.readLine();

                            while (line != null) {
                                writer.write(line + "\n");

                                line = reader.readLine();
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}