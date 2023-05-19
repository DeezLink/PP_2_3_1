package web.service;

import web.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarService {

    @Autowired
    private CarServiceImpl carService;

    public List<Car> showCars(int count, List<Car> carList) {
        return carList.stream().limit(count).collect(Collectors.toList());
    }

    public List<Car> getCarServiceList() {
        return carService.getCarList();
    }
}