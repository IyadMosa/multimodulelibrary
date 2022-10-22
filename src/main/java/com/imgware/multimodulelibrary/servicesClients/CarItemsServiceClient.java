package com.imgware.multimodulelibrary.servicesClients;

import com.imgware.multimodulelibrary.webClients.CarItemsWebClient;
import details.CarItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import search.SearchByMakerAndModel;

import java.util.List;

@Service
public class CarItemsServiceClient {

    @Autowired
    private CarItemsWebClient carItemsWebClient;

    public String getSuffixUrl() {
        return "/api/car-items/";
    }


    public CarItemDetails add(CarItemDetails item) throws Exception {
        HttpEntity<CarItemDetails> request = new HttpEntity<>(item);
        return carItemsWebClient.post(getSuffixUrl(), request, CarItemDetails.class);
    }
    public List<CarItemDetails> getAllByIds(List<String> ids) throws Exception {
        HttpEntity<List<String>> request = new HttpEntity<>(ids);
        return carItemsWebClient.post(getSuffixUrl() + "get-by-ids", request, List.class);
    }

    public List<CarItemDetails> searchByMakerAndModel(SearchByMakerAndModel dto) {
        HttpEntity<SearchByMakerAndModel> request = new HttpEntity<>(dto);
        return carItemsWebClient.post(getSuffixUrl() + "search-by-maker-model", request, List.class);
    }


}
