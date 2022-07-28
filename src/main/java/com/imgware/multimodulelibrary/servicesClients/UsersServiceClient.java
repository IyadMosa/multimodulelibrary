package com.imgware.multimodulelibrary.servicesClients;

import com.imgware.multimodulelibrary.webClients.DataWebClient;
import details.ProjectUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceClient {
    @Autowired
    private DataWebClient dataWebClient;

    public String getSuffixUrl() {
        return "/api/users/";
    }

    public List<ProjectUserDetails> findAll() {
        return dataWebClient.get(getSuffixUrl(), List.class);
    }

    public ProjectUserDetails saveOrUpdateUser(ProjectUserDetails user) {
        HttpEntity<ProjectUserDetails> request = new HttpEntity<>(user);
        return dataWebClient.post(getSuffixUrl(), request, ProjectUserDetails.class);
    }

    public void deleteAll() {
        dataWebClient.delete(getSuffixUrl() + "all");
    }
}
