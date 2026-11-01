package com.example.factory.car;

import com.example.factory.InstallmentCalculator;
import com.example.factory.InstallmentFactory;

public class CarFactory implements InstallmentFactory {

    @Override
    public InstallmentCalculator createNewVehicle() {
        return new NewCar();
    }

    @Override
    public InstallmentCalculator createOldVehicle() {
        return new OldCar();
    }
}
