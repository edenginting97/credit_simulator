# Credit Simulator

## Requirements

- Java 17 or later

## Getting Started

### Run the application

```bash
java -jar target/credit_simulator-1.0-SNAPSHOT.jar
```
or
```bash
java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_inputs.txt
```
**Important: The .txt file MUST follow the exact format below. Field names must match, and each field
be written on a separate line using the : operator.**

Example (`file_inputs.txt`)

```text
Jenis Kendaraan: Mobil
Kondisi Kendaraan: Bekas
Tahun Kendaraan: 2026
Jumlah Total Pinjaman: 100000000
Tenor Pinjaman: 3
Jumlah DP: 25000000
```

**Required fields:**
- `Jenis Kendaraan`: Mobil or Motor
- `Kondisi Kendaraan`: Baru or Bekas
- `Tahun Kendaraan`: Vehicle manufacturing year
- `Jumlah Total Pinjaman`: Total vehicle price
- `Tenor Pinjaman`: Loan duration in years
- `Jumlah DP`: Down payment amount

## CLI Usage

The application provides the following commands:

### Menu Commands
| Command | Description                              |
|---|------------------------------------------|
| `show` | List all commands                        |
| `start` | Begin calculation                        |
| `load <file-path>` | Calculate existing data                  |
| `exit` | Quit the application                     |

### Calculation Commands

During a calculation, the following commands are available:

| Command | Description                                 |
|---|---------------------------------------------|
| `next` | Continue calculation                        |
| `save` | Store current session and start new session |
| `list` | Check all stored sessions                   |
| `back` | Return to the previous menu                 |
| `switch <index>` | Fetch saved data |
| `show` | List all commands                           |

## CLI Guide

After starting the application in interactive mode, you can use the following commands.

### Main Menu Commands

| Command | Description |
|---|---|
| `show` | Display available commands |
| `start` | Start a new vehicle loan calculation |
| `load <file-path>` | Load vehicle loan information from a `.txt` file |
| `switch <index>` | Switch to a previously saved calculation |
| `exit` | Exit the application |

### Calculation Commands

Once you start a calculation, the following commands are available:

| Command | Description |
|---|---|
| `next` | Proceed to the next calculation step |
| `save` | Save the current calculation |
| `list` | Display all saved calculations |
| `back` | Return to the main menu |
| `show` | Display available commands |

### Example Workflow

**1. Start a new calculation**

```text
> start
```

Follow the prompts to enter the vehicle information, including vehicle type, condition, manufacturing year, total loan amount, loan tenure, and down payment.

**2. Proceed with the calculation**

```text
> next
```

Continue through the calculation steps until the installment result is generated.

**Important: You must enter `next` to proceed to the next calculation step. Each time you enter `next`, the application
will prompt you for the next piece of information. This allows you to save the current session and start a new one without losing your saved calculation.**

**3. Save the calculation**

```text
> save
```

**4. View saved calculations**

```text
> list
```

**5. Switch to a saved calculation**

```text
> switch 0
```

Replace `0` with the index displayed by the `list` command.

**6. Return to the main menu**

```text
> back
```

**7. Exit the application**

```text
> exit
```

### Important Notes

- Saved calculations are stored in memory and are available only while the application is running.
- Use `show` whenever you need to see the available commands.
- The `switch` command requires an index corresponding to an existing saved calculation.
- The application validates user input before proceeding with calculations.

