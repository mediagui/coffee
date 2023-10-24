package com.inatlas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtil {
  private LogUtil() {
  }

  private static final Logger log = LoggerFactory.getLogger(LogUtil.class);

  public static void debug(String text) {
    if (log.isDebugEnabled())
      log.debug(text);
  }

  public static void info(String text) {
    if (log.isInfoEnabled())
      log.info(text);
  }

  static void warn(String text) {
    if (log.isWarnEnabled())
      log.warn(text);
  }

  public static void error(String text) {
    if (log.isErrorEnabled())
      log.error(text);
  }

}
