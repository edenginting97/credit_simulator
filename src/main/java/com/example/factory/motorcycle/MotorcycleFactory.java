package com.example.factory.motorcycle;

import com.example.factory.InstallmentCalculator;
import com.example.factory.InstallmentFactory;

public class MotorcycleFactory implements InstallmentFactory {

    @Override
    public InstallmentCalculator createNewVehicle() {
        return new NewMotorcycle();
    }

    @Override
    public InstallmentCalculator createOldVehicle() {
        return new OldMotorcycle();
    }
}
