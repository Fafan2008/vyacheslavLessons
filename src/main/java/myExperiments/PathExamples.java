package myExperiments;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExamples {
    public static void main(String[] args) {
        Path defaultpath = Paths.get("","dbH2");
        String path = defaultpath.toAbsolutePath().toString();

    }
}
