package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Random;

public class ExcelUtils {

    public static Sheet getSheet(String fileName) throws Exception {

        InputStream is = ExcelUtils.class
                .getClassLoader()
                .getResourceAsStream("testdata/" + fileName);

        if (is == null) {
            throw new RuntimeException("Excel file not found: " + fileName);
        }

        Workbook workbook = new XSSFWorkbook(is);

        return workbook.getSheetAt(0);
    }


    public static void addUser(String fileName, String email,String userName, String password) throws Exception {

        InputStream is = ExcelUtils.class
                .getClassLoader()
                .getResourceAsStream("testdata/" + fileName);

        if (is == null) {
            throw new RuntimeException("Excel file not found: " + fileName);
        }

        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        System.out.println("Last row num: " + sheet.getLastRowNum());
        System.out.println("Physical rows: " + sheet.getPhysicalNumberOfRows());

        // Eğer Excel boşsa header oluştur
        if (sheet.getPhysicalNumberOfRows() == 0) {

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("email");
            header.createCell(1).setCellValue("password");
            header.createCell(2).setCellValue("userName");
        }

        int lastRow = sheet.getLastRowNum();

        Row row = sheet.createRow(lastRow + 1);

        row.createCell(0).setCellValue(email);
        row.createCell(1).setCellValue(password);
        row.createCell(2).setCellValue(userName);

        FileOutputStream fos = new FileOutputStream("src/test/resources/testdata/" + fileName);

        workbook.write(fos);

        fos.close();
        workbook.close();
        is.close();
    }

    public static String[] getRandomUser(String fileName) throws Exception {

        InputStream is = ExcelUtils.class
                .getClassLoader()
                .getResourceAsStream("testdata/" + fileName);

        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();

        if (lastRow < 1) {
            throw new RuntimeException("No users found in Excel");
        }

        Random random = new Random();

        int randomRow = random.nextInt(lastRow) + 1;

        Row row = sheet.getRow(randomRow);

        String email = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();
        String userName = row.getCell(2).getStringCellValue();

        workbook.close();

        return new String[]{email, password, userName};
    }

}