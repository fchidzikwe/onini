package com.fortune.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsView;


import javax.sql.DataSource;

/**
 * Created by fortune on 7/28/17.
 */
@Service
public class ReportManager {

  @Autowired
  @Qualifier("dataSource")
  private DataSource dataSource;

  @Autowired
  private ApplicationContext appContext;

  public JasperReportsPdfView createReport(String reportName) {
    JasperReportsPdfView view = new JasperReportsPdfView();
    view.setJdbcDataSource(dataSource);
    view.setUrl(reportName);
    view.setApplicationContext(appContext);
    return view;
  }

  public JasperReportsXlsView createCsvReport(String reportName) {
      JasperReportsXlsView view = new JasperReportsXlsView();
      view.setJdbcDataSource(dataSource);
      view.setUrl(reportName);
      view.setApplicationContext(appContext);
    return view;
  }

}
