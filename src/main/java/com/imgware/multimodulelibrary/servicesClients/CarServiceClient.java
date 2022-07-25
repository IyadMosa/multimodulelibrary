package com.imgware.multimodulelibrary.servicesClients;

import com.imgware.multimodulelibrary.webClients.DataWebClient;
import details.CarDetails;
import details.CarManufactureDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CarServiceClient {

    @Autowired
    private DataWebClient dataWebClient;

    public String getSuffixUrl() {
        return "/api/car/";
    }

    @Autowired
    CustomerServiceClient customerService;


    public CarDetails saveOrUpdateProjectCar(CarDetails projectCar) {
        HttpEntity<CarDetails> request = new HttpEntity<>(projectCar);
        return dataWebClient.post(getSuffixUrl(), request, CarDetails.class);
    }

    public CarDetails findProjectCarById(Long id) throws Exception {
        return dataWebClient.get(getSuffixUrl() + id, CarDetails.class);
    }

    public CarDetails findByCarOwnerName(String carOwnerName) throws Exception {
        return new CarDetails();// projectCarRepository.findByCarOwnerName(carOwnerName);
    }

    public CarDetails findByCarRegistrationNumber(String carRegistrationNumber) throws Exception {
        return new CarDetails();//projectCarRepository.findByCarRegistrationNumber(carRegistrationNumber);
    }

    public List<CarDetails> findAll() {
        return dataWebClient.get(getSuffixUrl() + "/all", List.class);
    }

    public void delete(Long id) {
        // projectCarRepository.deleteById(id);
    }

    public void deleteAll() {
        //projectCarRepository.deleteAll();
    }

    private void getAllCarManufactures(boolean isUpdate) throws Exception {

    }

    public Set<String> getAllCarsMake(boolean isUpdate) throws Exception {
        return dataWebClient.get(getSuffixUrl() + "/make/all", Set.class);
    }

    public List<String> getAllCarMakeModels(boolean isUpdate, String make) throws Exception {
        return dataWebClient.get(getSuffixUrl() + "/make/" + make, List.class);

    }

    public void addNewCarMakerModel(CarManufactureDetails details) throws Exception {

    }

}
