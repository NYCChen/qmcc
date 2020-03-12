package com.qmcc.sys.common;

public class DirTest {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath + "/src/main/java");
    }
}
