package gov.nci.ctrp.accrual.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static XSSFCell cell;

    public static void main(String[] args) {
        List<String> list = getCellHeaders("downloads" + File.separator + "trialsAuto_BxPGi.xlsx");
        System.out.println(list);
    }

    public static void setExcelObjects(String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsoluteFile());
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getCellHeaders(String fileName) {
        List<String> headers = new ArrayList<String>();
        try {
            setExcelObjects(fileName);
            int cols = getCols(fileName);
            for (int i = 0; i < cols; i++) {
                headers.add(getCellData(fileName, 1, i));
            }
        } catch (Exception e) {
        }
        return headers;
    }

    @SuppressWarnings("deprecation")
    public static String getCellData(String fileName, int RowNum, int ColNum) {
        String cellData;
        try {
            setExcelObjects(fileName);
            cell = sheet.getRow(RowNum).getCell(ColNum);
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                cellData = String.valueOf((int) cell.getNumericCellValue());
            else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
                cellData = String.valueOf(cell.getBooleanCellValue());
            else
                cellData = cell.getStringCellValue();
            return cellData;
        } catch (Exception e) {
            return "";
        }
    }

    public static int getRows(String fileName) {
        int row = 0;
        try {
            setExcelObjects(fileName);
            row = sheet.getLastRowNum();
        } catch (Exception e) {
        }
        return row;
    }

    public static int getCols(String fileName) {
        int col = 0;
        try {
            setExcelObjects(fileName);
            col = sheet.getRow(0).getPhysicalNumberOfCells();
        } catch (Exception e) {
        }
        return col;
    }
}
