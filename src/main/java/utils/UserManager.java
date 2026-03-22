package utils;

import model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Random;

public class UserManager {

    private static final String FILE_NAME = "users.xlsx";

    public static User getRandomUser() throws Exception {

        Sheet sheet = ExcelUtils.getSheet(FILE_NAME);

        int rowCount = sheet.getLastRowNum();

        if (rowCount < 1) {
            throw new RuntimeException("No users found in Excel");
        }

        Random random = new Random();

        int randomRow = 1 + random.nextInt(rowCount);

        Row row = sheet.getRow(randomRow);

        String email = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();

        return new User(email, password);
    }

}