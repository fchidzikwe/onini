package com.fortune.controller;

import com.fortune.model.Case;
import com.fortune.service.CaseService;
import com.fortune.service.FacilityConfigurationService;
import com.fortune.service.UserService;
import com.fortune.util.ReportManager;
import com.fortune.util.ReportName;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by fortune on 7/27/17.
 */


@Controller
public class ReportController {

    @Autowired
    FacilityConfigurationService facilityConfigurationService;
    @Autowired
    private ReportManager reportManager;

    @Autowired
    CaseService caseService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/reportform", method = RequestMethod.GET)
    public String getFormWorking(Model model) {
        return "reports";
    }




    @RequestMapping(method = RequestMethod.GET, value = "/pdf")
    public ModelAndView generatePdfReport(ModelAndView modelAndView, HttpServletResponse httpServletResponse,
//                                          @RequestParam(value = "eventstatus", required = false) String status,
//                                          @RequestParam(value = "purpose", required = false) String purpose,
//                                          @RequestParam(value = "start", required = false) String startDate,
                                          @RequestParam(value = "reportType", required = false) String reportType
                                    /*    @RequestParam(value = "end", required = false) String endDate*/) throws IOException {
        JRDataSource jrDataSource;

        List<Case> caseList = caseService.findAllCases();

        if(reportType.equalsIgnoreCase("CSV")){
            StringBuilder sb = new StringBuilder();
            sb.append("Case Number,Case Matter,Client FName,Client LName,VS, Time Spent, Amount Charged");
            sb.append(System.lineSeparator());
            caseList.forEach(aCase   -> {
                sb.append(aCase.getId());
                sb.append(",");
                sb.append(aCase.getMatter().getName());
                sb.append(",");
                sb.append(aCase.getClient().getName());
                sb.append(",");
                sb.append(aCase.getClient().getLastName());
                sb.append(",");
                sb.append(aCase.getVersus());
                sb.append(",");
                sb.append(aCase.getTimeSpent());
                sb.append(",");
                sb.append(aCase.getAmount());
                sb.append(System.lineSeparator());
            });
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"","report.csv");
            httpServletResponse.setHeader(headerKey, headerValue);
            httpServletResponse.setContentType("text/csv");
            httpServletResponse.getWriter().append(sb);
            httpServletResponse.getWriter().flush();
            return  null;
        } else{
            if (caseList.isEmpty()) {
                ModelAndView reportsError = new ModelAndView("reports");
                reportsError.addObject("successMessage", "No Records found!, there might have been an error while fetching results");
                return reportsError;
            } else {

                jrDataSource = new JRBeanCollectionDataSource(caseList);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datasource", jrDataSource);
                parameterMap.put("REPORT_PARAMETERS", "START :");
                modelAndView = new ModelAndView(reportManager.createReport(ReportName.generateReportName("Cases")), parameterMap);

                return modelAndView;
            }
        }
    }

}//Report