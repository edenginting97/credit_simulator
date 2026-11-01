package com.example.factory;

public interface InstallmentFactory {

    InstallmentCalculator createNewVehicle();

    InstallmentCalculator createOldVehicle();
}
