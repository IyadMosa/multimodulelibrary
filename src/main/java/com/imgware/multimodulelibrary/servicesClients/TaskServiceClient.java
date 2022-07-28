package com.imgware.multimodulelibrary.servicesClients;

import com.imgware.multimodulelibrary.webClients.DataWebClient;
import details.CustomerDetails;
import details.PaymentDetails;
import details.ProjectUserDetails;
import details.TaskDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceClient {

    @Autowired
    private DataWebClient dataWebClient;

    @Autowired
    CustomerServiceClient customerService;

    public String getSuffixUrl() {
        return "/api/tasks/";
    }

    public TaskDetails saveOrUpdateProjectTask(TaskDetails projectTask) {
        HttpEntity<TaskDetails> request = new HttpEntity<>(projectTask);
        return dataWebClient.post(getSuffixUrl(), request, TaskDetails.class);
    }

    public TaskDetails findProjectTaskById(Long id) throws Exception {
        return dataWebClient.get(getSuffixUrl() + id + "", TaskDetails.class);

    }

    public List<TaskDetails> findAll() {
        return dataWebClient.get(getSuffixUrl() + "/all", List.class);
    }

    public List<TaskDetails> findAllByUser(ProjectUserDetails user) {
        HttpEntity<ProjectUserDetails> request = new HttpEntity<>(user);
        return dataWebClient.post(getSuffixUrl() + "filter/user/all", request, List.class);
    }

    public void delete(Long id) {
        dataWebClient.delete(getSuffixUrl() + id);
    }

    public void deleteAll() {
        dataWebClient.delete(getSuffixUrl() + "all");

    }

    public TaskDetails pay(TaskDetails projectTask) throws Exception {
        CustomerDetails customer = customerService.findByCarRegistrationNumber(projectTask.getCarRegistrationNumber());
        if (CollectionUtils.isEmpty(customer.getCustomerPays())) {
            customer.setCustomerPays(new ArrayList<>());
        }

        PaymentDetails details = new PaymentDetails();
        details.setCustomerId(customer.getId());
        details.setPayMethod("CASH");
        details.setAmount(projectTask.getLastPaid());
        details.setPaidAt(new Date());
        details.setOriginalTotal(projectTask.getTotal());
        if (projectTask.getRemain() != null) {
            details.setRemain(projectTask.getRemain());
        } else {
            details.setRemain(details.getOriginalTotal() - details.getAmount());
        }
        details.setCarRegistrationNumber(projectTask.getCarRegistrationNumber());
        customer.getCustomerPays().add(details);

        return saveOrUpdateProjectTask(projectTask);
    }

    public List<TaskDetails> findAllByCarRegistrationNumber(String number) {
        return dataWebClient.get(getSuffixUrl() + "filter/carNumber/" + number, List.class);
    }
}
