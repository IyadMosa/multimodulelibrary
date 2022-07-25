package com.imgware.multimodulelibrary.servicesClients;

import com.imgware.multimodulelibrary.webClients.ReportWebClient;
import details.ReportDetails;
import details.TaskDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import search.SearchBy;

import java.util.List;

@Service
public class ReportServiceClient {

    @Autowired
    private ReportWebClient reportWebClient;

    public String getSuffixUrl() {
        return "/api/reports";
    }

    public List<ReportDetails> findAll() {
        return reportWebClient.get(getSuffixUrl() + "/all", List.class);
    }

    public ReportDetails createProjectReport(SearchBy searchBy) {
        HttpEntity<SearchBy> request = new HttpEntity<>(searchBy);
        return reportWebClient.post(getSuffixUrl(), request, ReportDetails.class);
    }

    public void delete(Long id) {
        reportWebClient.delete(getSuffixUrl() + id);
    }

    public ReportDetails show(Long id) {
        return reportWebClient.get(getSuffixUrl() + id, ReportDetails.class);
    }

    public ReportDetails save(ReportDetails projectReport) {
        HttpEntity<ReportDetails> request = new HttpEntity<>(projectReport);
        return reportWebClient.post(getSuffixUrl(), request, ReportDetails.class);
    }

    public void deleteAll() {
        reportWebClient.delete(getSuffixUrl() + "all");
    }

    public List<TaskDetails> searchByCarNumber(String number) {
        return reportWebClient.get(getSuffixUrl() + "/car-number/" + number, List.class);
    }

    public List<TaskDetails> searchByCustomer(String customerName) {
        return reportWebClient.get(getSuffixUrl() + "/customer/" + customerName, List.class);
    }
}
