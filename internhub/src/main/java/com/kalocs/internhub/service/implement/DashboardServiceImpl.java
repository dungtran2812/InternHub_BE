package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.*;
import com.kalocs.internhub.payload.response.DashboardResponse;
import com.kalocs.internhub.service.DashboardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DashboardServiceImpl implements DashboardService {

    private final ApplicationBusiness applicationBusiness;
    private final JobBusiness jobBusiness;
    private final RecruiterBusiness recruiterBusiness;
    private final StudentBusiness studentBusiness;
    private final TransactionBusiness transactionBusiness;

    @Autowired
    public DashboardServiceImpl(ApplicationBusiness applicationBusiness, JobBusiness jobBusiness, RecruiterBusiness recruiterBusiness, StudentBusiness studentBusiness, TransactionBusiness transactionBusiness) {
        this.applicationBusiness = applicationBusiness;
        this.jobBusiness = jobBusiness;
        this.recruiterBusiness = recruiterBusiness;
        this.studentBusiness = studentBusiness;
        this.transactionBusiness = transactionBusiness;
    }

    public DashboardResponse dashboard(long startDate, long endDate) {
        try {
            log.info("dashboard() DashboardServiceImpl start");
            int jobCount = jobBusiness.getJobCount(startDate, endDate);
            int applicationCount = applicationBusiness.getApplicationCount(startDate, endDate);
            int studentCount = studentBusiness.getStudentCount(startDate, endDate);
            int recruiterCount = recruiterBusiness.getRecruiterCount(startDate, endDate);
            int transactionCount = transactionBusiness.getTransactionCount(startDate, endDate);
            int totalRevenue = transactionBusiness.getTotalRevenue(startDate, endDate);
            DashboardResponse dashboardResponse = new DashboardResponse(jobCount, applicationCount, studentCount, recruiterCount, transactionCount,totalRevenue);
            log.info("dashboard() DashboardServiceImpl end | {}", dashboardResponse);
            return dashboardResponse;
        } catch (Exception e) {
            log.error("Error in dashboard() DashboardServiceImpl | {}", e.getMessage());
            throw e;
        }
    }
}
