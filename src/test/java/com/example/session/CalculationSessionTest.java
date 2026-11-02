package com.example.session;

import com.example.enums.CalculationState;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CalculationSessionTest {

    @ParameterizedTest
    @CsvSource({
            "Motor",
            "Mobil"
    })
    void shouldSelectVehicle_whenVehicleProvided(String vehicle) {
        var session = new CalculationSession();
        session.selectVehicle(vehicle);
        assertEquals(vehicle, session.getVehicleLoan().getVehicleType());
        assertEquals(CalculationState.SELECT_CONDITION, session.getInstallment().getState());
    }

    @ParameterizedTest
    @CsvSource({
            "Motor, Baru",
            "Motor, Bekas",
            "Mobil, Baru",
            "Mobil, Bekas"
    })
    void shouldSelectCondition_whenValidVehicleAndConditionProvided(String vehicle, String condition) {
        var session = new CalculationSession();
        session.selectVehicle(vehicle);
        session.selectCondition(condition);

        session.selectCondition(condition);
        assertEquals(condition, session.getVehicleLoan().getVehicleCondition());
        assertEquals(CalculationState.INPUT_VEHICLE_YEAR, session.getInstallment().getState());
    }

    @ParameterizedTest
    @CsvSource({
            "Mobil, Baru",
            "Motor, Bekas",
    })
    void shouldSetVehicleYear_whenValidYearProvided(
            String vehicle, String condition
    ) {
        var session = new CalculationSession();
        int validYear = Year.now().getValue() - 1;

        session.selectVehicle(vehicle);
        session.selectCondition(condition);
        session.setVehicleYear(validYear);

        assertEquals(validYear, session.getVehicleLoan().getVehicleYear());
        assertEquals(CalculationState.INPUT_TOTAL_LOAN, session.getInstallment().getState());
    }

    @ParameterizedTest
    @CsvSource({
            "Mobil, Baru, 2024",
            "Mobil, Bekas, 20248",
            "Motor, Baru, 2023",
            "Motor, Bekas, 20244",
    })
    void shouldThrowIllegalArgumentException_whenInvalidYearProvided(
            String vehicle, String condition, int year
    ) {
        var session = new CalculationSession();

        session.selectVehicle(vehicle);
        session.selectCondition(condition);

        assertThrows(IllegalArgumentException.class, () -> session.setVehicleYear(year));
    }

    @ParameterizedTest
    @CsvSource({
            "Mobil, Baru",
            "Mobil, Bekas",
            "Motor, Baru",
            "Motor, Bekas"
    })
    void shouldSetTotalLoan_whenAmountProvided(
            String vehicle, String condition
    ) {
        var session = new CalculationSession();
        var amount = new BigDecimal(1_000_000_000);

        session.selectVehicle(vehicle);
        session.selectCondition(condition);
        session.setTotalLoan(new BigDecimal(1_000_000_000));

        assertEquals(amount, session.getVehicleLoan().getTotalLoanAmount());
        assertEquals(CalculationState.INPUT_LOAN_TENURE, session.getInstallment().getState());
    }

    @ParameterizedTest
    @CsvSource({
            "Mobil, Baru",
            "Mobil, Bekas",
            "Motor, Baru",
            "Motor, Bekas"
    })
    void shouldThrowIllegalArgumentException_whenTotalLoanExceededLimit(
            String vehicle, String condition
    ) {
        var session = new CalculationSession();

        session.selectVehicle(vehicle);
        session.selectCondition(condition);

        assertThrows(IllegalArgumentException.class, () -> session.setTotalLoan(new BigDecimal(1_000_000_001)));
    }

    @ParameterizedTest
    @CsvSource({
            "Mobil, Baru, 1",
            "Mobil, Bekas, 3",
            "Motor, Baru, 5",
            "Motor, Bekas, 6"
    })
    void shouldSetLoanTenure_whenYearProvided(
            String vehicle, String condition, int year
    ) {
        var session = new CalculationSession();

        session.selectVehicle(vehicle);
        session.selectCondition(condition);
        session.setLoanTenure(year);

        assertEquals(year, session.getVehicleLoan().getLoanTenure());
        assertEquals(CalculationState.INPUT_DOWN_PAYMENT, session.getInstallment().getState());
    }

    @ParameterizedTest
    @CsvSource({
            "Mobil, Baru, 0",
            "Mobil, Bekas, 7",
    })
    void shouldThrowIllegalArgumentException_whenLoanTenureOutOfRange(
            String vehicle, String condition, int year
    ) {
        var session = new CalculationSession();

        session.selectVehicle(vehicle);
        session.selectCondition(condition);

        assertThrows(IllegalArgumentException.class, () -> session.setLoanTenure(year));
    }

    @ParameterizedTest
    @CsvSource({
            "Mobil, Baru, 100_000_000, 35_000_000",
            "Mobil, Bekas, 100_000_000, 30_000_000",
            "Motor, Baru, 100_000_000, 40_000_000",
            "Motor, Bekas, 100_000_000, 25_000_000",
    })
    void shouldSetDownPayment_whenAmountProvided(
            String vehicle, String condition, int amount, int downPayment
    ) {
        var session = new CalculationSession();
        var dp = new BigDecimal(downPayment);

        session.selectVehicle(vehicle);
        session.selectCondition(condition);
        session.setTotalLoan(new BigDecimal(amount));
        session.setDownPayment(dp);

        assertEquals(dp, session.getVehicleLoan().getDownPayment());
        assertEquals(CalculationState.INPUT_LOAN_TENURE, session.getInstallment().getState());
    }

    @ParameterizedTest
    @CsvSource({
            "Mobil, Baru, 100_000_000, 34_000_000",
            "Mobil, Bekas, 100_000_000, 24_000_000",
            "Motor, Baru, 100_000_000, 30_000_000",
            "Motor, Bekas, 100_000_000, 22_000_000",
    })
    void shouldThrowIllegalArgumentException_whenExceededMinimumDownPayment(
            String vehicle, String condition, int amount, int downPayment
    ) {
        var session = new CalculationSession();
        var dp = new BigDecimal(downPayment);

        session.selectVehicle(vehicle);
        session.selectCondition(condition);
        session.setTotalLoan(new BigDecimal(amount));

        assertThrows(IllegalArgumentException.class, () -> session.setDownPayment(dp));
    }
}
