// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
package Main.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<String, String> userData = new HashMap<>();

        System.out.println("Введите порядок номеров для ввода своих данных через запятую: ");
        System.out.println("1 - Фамилия Имя Отчество");
        System.out.println("2 - Дата рождения");
        System.out.println("3 - Номер телефона");
        System.out.println("4 - Пол");

        String userDataInput = scanner.nextLine();
        String[] userDataArray = userDataInput.split(",");

        for (String selectedNumber : userDataArray) {
            switch (selectedNumber.trim()) {
                case "1":
                    System.out.println("Введите фамилию, имя и отчество, разделяя их пробелом:");
                    String fio;
                    fio = scanner.nextLine().toLowerCase();
                    String[] words = fio.split(" ");

                    if (words.length != 3) {
                        throw new RuntimeException("Ошибка: Не соблюдены условия ввода ФИО.");
                    }

                    String lastName = words[0];
                    String firstName = words[1];
                    String secondName = words[2];

                    if (!lastName.matches("[a-zA-Zа-яА-Я ]+") || !firstName.matches("[a-zA-Zа-яА-Я ]+") ||
                            !secondName.matches("[a-zA-Zа-яА-Я ]+")) {
                        throw new RuntimeException("Ошибка: ФИО должно содержать только буквы.");
                    }

                    String name = lastName + " " + firstName + " " + secondName;
                    userData.put("ФИО", name);
                    break;
                case "2": // todo протестировать и внести корректировки
                    System.out.println("Введите свою дату рождения в формате ДД.ММ.ГГГГ:");
                    String dateOfBirth = "";
                    dateOfBirth = scanner.nextLine();

                    String[] dateParts = dateOfBirth.split("\\.");

                    if (dateParts.length != 3 || !dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                        throw new RuntimeException("Ошибка: Некорректный формат даты. Используйте формат ДД.ММ.ГГГГ.");
                    }

                    int day = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int year = Integer.parseInt(dateParts[2]);

                    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                    int[] daysInMonth = {31, isLeapYear ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                    if (day < 1 || day > daysInMonth[month - 1]) {
                        throw new RuntimeException("Ошибка: Некорректный день введенной даты.");
                    }

                    if (month < 1 || month > 12) {
                        throw new RuntimeException("Ошибка: Некорректный месяц введенной даты.");
                    }

                    if (year < 1900 || year > 2023) {
                        throw new RuntimeException("Ошибка: Некорректный год введенной даты.");
                    }

                    String formattedDateOfBirth = dateOfBirth.toString();
                    userData.put("дата_рождения", formattedDateOfBirth);
                    break;
                case "3":
                    System.out.println("Введите номер своего телефона в международном формате (11 цифр):");
                    String phoneNumber = scanner.nextLine();

                    if (!phoneNumber.matches("\\d{11}")) {
                        throw new IllegalArgumentException("Некорректный номер телефона. Должно быть 11 цифр.");
                    }

                    userData.put("телефон", phoneNumber);
                    break;
                case "4":
                    System.out.println("Введите свой пол (мужской -> male, женский -> female):");
                    String gender = scanner.nextLine().toLowerCase();
                    if (!gender.toLowerCase().equals("male") && !gender.toLowerCase().equals("female")) {
                        throw new RuntimeException("Неверно введен пол");
                    }
                    userData.put("пол", gender);
                    break;
                default:
                    System.out.println(
                            "Вы ввели неверный номер: " + userDataInput + ". Активируйте приложение заново.");
                    return;
            }
        }
        try {
            if (userData.size() != 4) {
                System.out.println("Вы ввели неверное количество данных! Активируйте приложение заново.");
                return;
            }

            String data = userData.get("ФИО").split(" ")[0];
            String fileName = "C:\\Users\\Оля\\IdeaProjects\\untitled1\\.idea"
                    + data + ".rtf";
            FileWriter writer = new FileWriter(fileName, true);

            String name = userData.get("ФИО");
            String dateOfBirth = userData.get("дата_рождения");
            double phoneNumber = Double.parseDouble(userData.get("телефон"));
            String gender = userData.get("пол");

            String userDataLine = name + " " + dateOfBirth + " " + (long) phoneNumber + " " + gender + "\n";
            writer.write(userDataLine);
            writer.close();
            System.out.println("Данные записаны в папку list_of_people -> файл " + data + ".rtf");
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }
}
