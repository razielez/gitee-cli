package com.razielez.gitee.cli;


import org.junit.jupiter.api.Test;

class ApplicationTest {

  @Test
  public void testCommand() {
    String home = System.getProperty("user.dir") + "/";
    String[] args = new String[]{"checksum", "-a", "md5", home + "pom.xml"};
    Application.main(args);
  }

  @Test
  public void test() {
    String property = System.getProperty("user.dir");
    System.out.println(property);
    String[] split = property.split("/");
    System.out.println(split[split.length - 1]);
  }

}