package web.service;


import web.model.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl {

    private List<Car> carList;

    {
        carList = new ArrayList<>();
        carList.add(new Car("ГАЗ 3109", "белый", 1985));
        carList.add(new Car("ВАЗ 2109", "красный", 1990));
        carList.add(new Car("УАЗ 469", "серый", 1975));
        carList.add(new Car("ЗАЗ 968", "жёлый", 1970));
        carList.add(new Car("ЛУАЗ 969", "синий", 1980));
    }

    public List<Car> getCarList() {
        return carList;
    }
}
