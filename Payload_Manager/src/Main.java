import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome del file che contiene i log di tutti i sensori:");
        String mainFile = scanner.nextLine();
        System.out.println("Nome del sensore:");
        String sensorName = scanner.nextLine();
        //String sensorFile = sensorName + ".csv";
        ArrayList<String> sensorLogs;
        ArrayList<Payload> sensorDatas;
        System.out.println("Digitare il giorno da cui si vuole partire (YYYY-MM-DD):");
        String[] startDateParts = scanner.nextLine().split("-");
        LocalDateTime startDate = LocalDate.of(
                Integer.parseInt(startDateParts[0]),
                Integer.parseInt(startDateParts[1]),
                Integer.parseInt(startDateParts[2])
        ).atTime(0,0,0,0);
        System.out.println("Digitare il giorno in cui si vuole finire (YYYY-MM-DD):");
        String[] endDateParts = scanner.nextLine().split("-");
        LocalDateTime endDate = LocalDate.of(
                Integer.parseInt(endDateParts[0]),
                Integer.parseInt(endDateParts[1]),
                Integer.parseInt(endDateParts[2])
        ).atTime(23, 59, 59, 59);

        String sensorFile = sensorName + "-" +
                + startDate.getDayOfMonth() + "-" + startDate.getMonthValue() + "-" + startDate.getYear()+
                ".csv";

        //Controllo dell'esistenza del file principale
        checkMainLogsFile(mainFile);
        //Creazione del file che conterrà i log del sensore deciso
        createFile(sensorFile);
        //Estrazione dei log del sensore deciso dal file principale
        sensorLogs = getSensorLogs(mainFile, sensorName);
        //Estrazione dei dati interessati dalla lista dei log
        sensorDatas = getDataFromLogs(sensorLogs, startDate, endDate);
        //Scrittura dei dati su file
        writeFile(sensorDatas, sensorFile);
    }

    //returns 0 if the file il empty or doesn't exist
    public static void checkMainLogsFile(String mainFile) {
        File toCheck = new File(mainFile);
        long mainFileStatus = toCheck.length();
        if(mainFileStatus != 0) {
            System.out.println("File found");
        } else {
            System.out.println("File doesn't exist or is empty. " +
                    "Create it or fill it with logs");
            System.exit(0);
        }
    }

    //creazione del file di appoggio per i log di un singolo sensore
    public static void createFile(String sensorFileName) {
        try {
            File myFile = new File(sensorFileName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void writeFile(ArrayList<Payload> payloads, String sensorFile) {
        try (PrintWriter writer = new PrintWriter(sensorFile)) {
            writer.println("time;battery;ec;humidity;temperature");
            for (Payload p : payloads) {
                writer.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //salvataggio dei log di un solo sensore
    public static ArrayList<String> getSensorLogs(String mainFile, String sensorName) {
        ArrayList<String> sensorLogs = new ArrayList<>();
        try(Scanner scanFile = new Scanner(Paths.get(mainFile))) {
            while(scanFile.hasNextLine()) {
                String row = scanFile.nextLine();
                if(row.contains(sensorName)) {
                    sensorLogs.add(row);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sensorLogs;
    }

    //estrazione dei dati dai log
    public static ArrayList<Payload> getDataFromLogs(ArrayList<String> sensorLogs, LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<Payload> payloads = new ArrayList<>();

        //pattern per i dati interessati
        Pattern datePat = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
        Pattern hourPat = Pattern.compile("(\\d{2}:\\d{2}:\\d{2})");
        Pattern batteryPat = Pattern.compile("\"battery\":([^,]*)");
        Pattern ecPat = Pattern.compile("\"ec\":([^,]*)");
        Pattern humPat = Pattern.compile("\"humidity\":([^,]*)");
        Pattern temPat = Pattern.compile("\"temperature\":([^}]*)");

        boolean isMidnight = true;
        boolean isAm = false;
        boolean isMidday = false;
        boolean isPm = false;

        for(String log : sensorLogs) {
            String date = getMatch(datePat, log);
            String time = getMatch(hourPat, log);
            int battery = -1;
            if(log.contains("battery")) {
                battery = Integer.parseInt(getMatch(batteryPat, log));
            }
            double ec = Double.parseDouble(getMatch(ecPat, log));
            double humidity = Double.parseDouble(getMatch(humPat, log));
            double temperature = Double.parseDouble(getMatch(temPat,log));

            //Inserimento di data e ora in un oggetto che possa gestirle
            String[] dateParts = date.split("-");
            int day = Integer.parseInt(dateParts[2]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[0]);
            String[] hourParts = time.split(":");
            int hour = Integer.parseInt(hourParts[0]);
            int minute = Integer.parseInt(hourParts[1]);
            int second = Integer.parseInt(hourParts[2]);

            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);

            if(dateTime.isAfter(startDate) &&
                    dateTime.isBefore(endDate) ||
                    dateTime.isEqual(startDate) ||
                    dateTime.isEqual(endDate)) {

                if (dateTime.getHour() == 12) {
                    if (isMidnight) {
                        isPm = false;
                        LocalDateTime midnight = dateTime.minusHours(12);
                        payloads.add(new Payload(midnight, battery, ec, humidity, temperature));
                    }
                    if (isAm) {
                        isAm = false;
                        isMidday = true;
                    }
                    if (isMidday) {
                        isAm = false;
                        LocalDateTime midday = dateTime;
                        payloads.add(new Payload(midday, battery, ec, humidity, temperature));
                    }
                    if (isPm) {
                        isPm = false;
                        isMidnight = true;
                    }
                }
                if (dateTime.getHour() != 12) {
                    if (isMidnight) {
                        isMidnight = false;
                        isAm = true;
                    }
                    if (isAm) {
                        LocalDateTime am = dateTime;
                        payloads.add(new Payload(am, battery, ec, humidity, temperature));
                    }
                    if (isMidday) {
                        isMidday = false;
                        isPm = true;
                    }
                    if (isPm) {
                        LocalDateTime pm = dateTime.plusHours(12);
                        payloads.add(new Payload(pm, battery, ec, humidity, temperature));
                    }
                }
            }
        }
        return payloads;
    }

    //Ritorna il dato che corrisponde al pattern
    public static String getMatch(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return "-1";
    }
}