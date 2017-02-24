package pl.biz.nsp.service;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import pl.biz.nsp.model.Contact;
import pl.biz.nsp.model.Interaction;
import pl.biz.nsp.model.Product;
import pl.biz.nsp.model.ProductCategory;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Piotr Larys
 */
@Service
public class ExcelReader {

    public List<Interaction> createInteraction() throws Exception {

        Workbook sheets = getWorkbook(getInteractionFile());
        List<Interaction> interactions = new ArrayList<>();
        String orderNumber = "";

        for (Row row : sheets.getSheetAt(0)) {
            if (row.getRowNum() == 0)
                continue;
            if (orderNumber.equals(row.getCell(21).getStringCellValue())) {
                continue;
            }

            interactions.add(new Interaction.Builder()
                    .key(row.getCell(21).getStringCellValue())
                    .sourceObjectId(row.getCell(21).getStringCellValue())
                    .contactId(row.getCell(22).getStringCellValue())
                    .amount("") //TODO do zaimplementowania logika
                    .interactionType("SALES_ORDER")
                    .communicationMedium("BUSINESS_DOCUMENT")
                    .build());

            orderNumber = row.getCell(21).getStringCellValue();
        }
        return interactions;
    }

    public List<Contact> createContact() throws Exception {
        Workbook sheets = getWorkbook(getContactFile());
        List<Contact> contacts = new ArrayList<>();
        String contactId = "";

        Random generator = new Random();
        long x = 10000000000000l;

        for (Row row : sheets.getSheetAt(0)) {
            if (row.getRowNum() == 0)
                continue;
            if (contactId.equals(row.getCell(22).getStringCellValue()))
                continue;

            long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
            timestamp = timestamp + generator.nextLong() / x;


            contacts.add(new Contact.Builder()
                    .Id(row.getCell(22).getStringCellValue())
                    .IdOrigin("Z_EOBUWIE_CONSUMER")
                    .timestamp("/Date(" + timestamp + ")/")
                    .FullName(row.getCell(23).getStringCellValue())
                    .EMailAddress(row.getCell(22).getStringCellValue())
                    .City(row.getCell(26).getStringCellValue())
                    .CountryDescription(row.getCell(29).getStringCellValue())
                    .PostlCode(row.getCell(27).getStringCellValue())
                    .HouseNumber((row.getCell(25) != null) ? (row.getCell(25).getStringCellValue()) : "")
                    .Street(row.getCell(24).getStringCellValue())
                    .build());

            contactId = row.getCell(22).getStringCellValue();
        }

        return contacts;
    }

    public List<Product> createProducts() throws Exception {

        List<Product> products = new ArrayList<>();
        List<ProductCategory> productCategories = new ArrayList<>();


        Workbook sheets = getWorkbook(getProductFile());


        for (Row row : sheets.getSheetAt(0)) {
            if (row.getRowNum() == 0)
                continue;

            if (row.getCell(11) != null) {
                productCategories.add(new ProductCategory.Builder()
                        .Name(row.getCell(11).getStringCellValue())
                        .builder());
            }

            if (row.getCell(12) != null) {
                productCategories.add(new ProductCategory.Builder()
                        .Name(row.getCell(12).getStringCellValue())
                        .builder());
            }

            products.add(new Product.Builder()
                    .Id(row.getCell(7).getStringCellValue())
                    .IdOrigin("Z_EOBUWIE_PRODUCT")
                    .Language("PL")
                    .Name((row.getCell(13) != null) ? (row.getCell(13).getStringCellValue()) : (row.getCell(12).getStringCellValue()))
                    .zBrand((row.getCell(31) != null) ? (row.getCell(31).getStringCellValue()) : "")
                    .zUpper((row.getCell(10) != null) ? (row.getCell(10).getStringCellValue()) : "")
                    .zPremium((row.getCell(16) != null) ? (row.getCell(16).getStringCellValue()) : "")
                    .zSeazon((row.getCell(18) != null) ? (row.getCell(18).getStringCellValue()) : "")
                    .zSeries((row.getCell(17) != null) ? (row.getCell(17).getStringCellValue()) : "")
                    .ProductCategoryList(productCategories)
                    .build());
        }

        return products;
    }


    private File getProductFile() {
        return new File("C:\\Users\\Asus\\Desktop\\eObuwie\\Sample_data\\magento_sample_data_product_sort.xlsx");
    }

    private File getContactFile() {
        return new File("C:\\Users\\Asus\\Desktop\\eObuwie\\Sample_data\\magento_sample_data_contact_sort.xlsx");
    }

    private File getInteractionFile() {
        return new File("C:\\Users\\Asus\\Desktop\\eObuwie\\Sample_data\\magento_sample_data_product_sort.xlsx");
    }

    private Workbook getWorkbook(File file) throws Exception {
        return StreamingReader.builder()
                .rowCacheSize(10)     // number of rows to keep in memory (defaults to 10)
                .bufferSize(1024)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .sheetIndex(0)        // index of sheet to use (defaults to 0)
                .open(new FileInputStream(file.getAbsolutePath()));
    }

}
