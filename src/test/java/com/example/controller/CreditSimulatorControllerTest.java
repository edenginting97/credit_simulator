package com.example.controller;

import com.example.builder.Director;
import com.example.cmd.MenuCommand;
import com.example.dto.VehicleLoan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.StringReader;
import java.util.Scanner;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditSimulatorControllerTest {

    @Mock
    MenuCommand menuCommand;

    @Mock
    Director director;

    @Test
    public void shouldStartInteractiveMode_whenNoArgumentsProvided() {
        var scanner = new Scanner(new StringReader("exit\n"));

        var controller = new CreditSimulatorController(
                scanner,
                menuCommand,
                director
        );
        controller.execute(new String[]{});

        verify(menuCommand).run(scanner);
        verifyNoInteractions(director);
    }

    @Test
    public void shouldCalculateCredit_whenFileIsProvided() {
        var scanner = new Scanner(new StringReader(""));

        var controller = new CreditSimulatorController(
                scanner,
                menuCommand,
                director
        );
        controller.execute(new String[]{"file_inputs.txt"});

        verify(director).calculateCredit(any(VehicleLoan.class));
        verifyNoInteractions(menuCommand);
    }
}
