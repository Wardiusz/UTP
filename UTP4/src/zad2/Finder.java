package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {

    private List<String> data;

    public Finder(String path) {
        try {
            data = Files.readAllLines(Paths.get(path));
            data = removeWhiteSpace(data);
            data = removeComments(data);
            data = removeIfsInString(data);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file: " + e.getMessage(), e);
        }
    }

    private List<String> removeWhiteSpace(List<String> data) {
        List<String> result = new ArrayList<>();
        for (String line : data) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }
        return result;
    }

    private List<String> removeComments(List<String> data) {
        List<String> result = new ArrayList<>();
        boolean inBlockComment = false;

        for (String line : data) {
            if (inBlockComment) {
                if (line.contains("*/")) {
                    inBlockComment = false;
                    int end = line.indexOf("*/") + 2;
                    line = line.substring(end).trim();
                } else {
                    continue;
                }
            }

            if (line.startsWith("/*")) {
                inBlockComment = true;
                int start = line.indexOf("/*");
                if (line.contains("*/")) {
                    inBlockComment = false;
                    int end = line.indexOf("*/") + 2;
                    line = line.substring(0, start) + line.substring(end);
                } else {
                    line = line.substring(0, start).trim();
                }
            } else if (line.startsWith("//")) {
                continue;
            }

            if (!inBlockComment && !line.isEmpty()) {
                result.add(line);
            }
        }
        return result;
    }

    private List<String> removeIfsInString(List<String> data) {
        List<String> result = new ArrayList<>();
        for (String line : data) {
            StringBuilder cleanedLine = new StringBuilder();
            boolean inString = false;

            for (char c : line.toCharArray()) {
                if (c == '"') {
                    inString = !inString;
                }

                if (inString && (c == 'i' || c == 'f')) {
                    continue;
                }

                cleanedLine.append(c);
            }

            result.add(cleanedLine.toString());
        }
        return result;
    }

    public int getIfCount() {
        int count = 0;
        Pattern pattern = Pattern.compile("if\\s*\\(.*?\\)");

        for (String line : data) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                count++;
            }
        }

        return count;
    }

    public int getStringCount(String str) {
        int count = 0;
        Pattern pattern = Pattern.compile(Pattern.quote(str));

        for (String line : data) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                count++;
            }
        }

        return count;
    }
}
