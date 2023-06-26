import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

import static javax.swing.JOptionPane.showMessageDialog;

public class Search {

    private String response;

    public void search(String iparam) {
        if(iparam.equals("")) {
            setResponse("No value was entered");
            return;
        }

        FileDestination fd = new FileDestination();

        try (FileInputStream fileInputStream = new FileInputStream(fd.getFileDestination())){

            if (fd.getFileDestination().endsWith(".xlsx")) {
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet = workbook.getSheetAt(0);

                for (Row row : sheet) {
                    if (row.getCell(0).getStringCellValue().equals(iparam)) {
                        setResponse(row.getCell(1).getStringCellValue());
                        break;
                    } else {
                        setResponse("iparam not found");
                    }
                }
            } else if (fd.getFileDestination().endsWith("xls")) {
                HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
                HSSFSheet sheet = workbook.getSheetAt(0);

                for (Row row : sheet) {
                    if (row.getCell(0).getStringCellValue().equals(iparam)) {
                        setResponse(row.getCell(1).getStringCellValue());
                        break;
                    } else {
                        setResponse("iparam not found");
                    }
                }
            }

        }
        catch (Exception e) {
            showMessageDialog(null, "Cannot read file or file type is incorrect. \nPlease check file location and confirm" +
                    " that the extension is either .xlsx or .xls");
            setResponse("iparam not found");
        }

    }

    public void setResponse(String R) {
        this.response = R;
    }

    public String getResponse() {
        return response;
    }

}
