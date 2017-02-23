package pl.biz.nsp.service;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import pl.biz.nsp.model.Interaction;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Larys
 */
@Service
public class ExcelReader {

    public List<Interaction> excelReader() throws Exception {
        Workbook sheets = getWorkbook();

        List<Interaction> interactions = new ArrayList<>();

        sheets.getSheetAt(0).forEach(it -> {

            if (it.getRowNum() == 0)
                return;


            interactions.add(new Interaction.Builder()
                    .key(it.getCell(21).getStringCellValue())
                    .sourceObjectId(it.getCell(21).getStringCellValue())
                    .contactId(it.getCell(22).getStringCellValue())
                    .amount("") //TODO do zaimplementowania logika
                    .interactionType("SALES_ORDER")
                    .communicationMedium("BUSINESS_DOCUMENT")
                    .build());
        });
        return interactions;
    }


    private File getFile() {
        return new File("/home/piotr/soft/eObuwie/transactional_data.xlsx");
    }

    private Workbook getWorkbook() throws Exception {
        return StreamingReader.builder()
                .rowCacheSize(10)     // number of rows to keep in memory (defaults to 10)
                .bufferSize(1024)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .sheetIndex(0)        // index of sheet to use (defaults to 0)
                .open(new FileInputStream(getFile().getAbsolutePath()));
    }

}
