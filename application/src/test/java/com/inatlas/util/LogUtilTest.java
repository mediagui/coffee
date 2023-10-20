package com.inatlas.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LogUtilTest {

  private Logger log;
  private ByteArrayOutputStream logOutput;
  private PrintStream originalOut;

  @BeforeEach
  public void setUp() {
    // Redirect the logger output to a ByteArrayOutputStream
    log = LoggerFactory.getLogger(LogUtil.class);
    logOutput = new ByteArrayOutputStream();
    originalOut = System.out;
    System.setOut(new PrintStream(logOutput));
  }

  @Test
  void debugDebugTest() {

    LogUtil.debug("This is a debug message");
    String logOutputString = logOutput.toString();

    // Verify that the message has been recorded in the logger output
    if(log.isDebugEnabled())
      assertTrue(logOutputString.contains("This is a debug message"));
    else
      assertFalse(logOutputString.contains("This is a debug message"));
  }

  @Test
  void infoDebugTest() {
    LogUtil.info("This is a info message");
    String logOutputString = logOutput.toString();

    // Verify that the message has been recorded in the logger output
    if(log.isInfoEnabled())
      assertTrue(logOutputString.contains("This is a info message"));
    else
      assertFalse(logOutputString.contains("This is a info message"));
  }

  @Test
  void warnDebugTest() {
    LogUtil.error("This is a warn message");
    String logOutputString = logOutput.toString();

    // Verify that the message has been recorded in the logger output
    if(log.isWarnEnabled())
      assertTrue(logOutputString.contains("This is a warn message"));
    else
      assertFalse(logOutputString.contains("This is a warn message"));
  }

  @Test
  void errorDebugTest() {
    LogUtil.error("This is a error message");
    String logOutputString = logOutput.toString();

    // Verify that the message has been recorded in the logger output
    if(log.isErrorEnabled())
      assertTrue(logOutputString.contains("This is a error message"));
    else
      assertFalse(logOutputString.contains("This is a error message"));
  }


  @AfterEach
  void tearDown() throws Exception {
    logOutput.close();
    System.setOut(originalOut);
  }


}
