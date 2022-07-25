package com.imgware.multimodulelibrary.servicesClients;

import com.imgware.multimodulelibrary.webClients.DataWebClient;
import details.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceClient {
    public String getSuffixUrl() {
        return "/api/customer/";
    }

    @Autowired
    DataWebClient dataWebClient;

    public CustomerDetails saveOrUpdateProjectCustomer(CustomerDetails customer) throws Exception {
        HttpEntity<CustomerDetails> request = new HttpEntity<>(customer);
        return dataWebClient.post(getSuffixUrl(), request, CustomerDetails.class);
    }

    public List<CustomerDetails> findAll() {
        return dataWebClient.get(getSuffixUrl() + "/all", List.class);
    }

    public void delete(Long id) {
        dataWebClient.delete(getSuffixUrl() + id);
    }

    public CustomerDetails findByFullName(String fullName) {
        return dataWebClient.get(getSuffixUrl() + "filter/name/" + fullName, CustomerDetails.class);
    }

    public CustomerDetails findByPhoneNumber(String phoneNumber) {
        return dataWebClient.get(getSuffixUrl() + "filter/phoneNumber/" + phoneNumber, CustomerDetails.class);
    }

    public CustomerDetails findByCarRegistrationNumber(String carRegistrationNumber) throws Exception {
        return new CustomerDetails();//projectCarService.findByCarRegistrationNumber(carRegistrationNumber).getCarOwner();
    }

    public void deleteAll() {
        dataWebClient.delete("all");
    }

    public CustomerDetails show(Long id) {
        return dataWebClient.get(getSuffixUrl() + id, CustomerDetails.class);
    }

}
