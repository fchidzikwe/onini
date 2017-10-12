package com.fortune.util;

/**
 * Created by fortune on 7/28/17.
 */
public class ReportName {

  private final static String REPORT_PREFIX = "classpath:/static/jasper/";

  private final static String REPORT_SUFFIX = ".jasper";



  public static String generateReportName(String reportName) {

    return REPORT_PREFIX + reportName + REPORT_SUFFIX;
  }



}
