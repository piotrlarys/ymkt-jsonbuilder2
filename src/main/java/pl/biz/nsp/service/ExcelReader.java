package pl.biz.nsp.service;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import pl.biz.nsp.model.*;

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
        int counter = 0;
        double amount = 0;

        Random generator = new Random();
        long x = 10000000000000l;

        for (Row row : sheets.getSheetAt(0)) {

            long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
            timestamp = timestamp + generator.nextLong() / x;

            List<ProductInteraction> productInteractions = new ArrayList<>();


            if (row.getRowNum() == 0)
                continue;

            if (orderNumber.equals(row.getCell(21).getStringCellValue())) {


                interactions.get(counter - 1).setAmount(String.valueOf((amount + row.getCell(5).getNumericCellValue())));

                interactions.get(counter - 1).getProductInteractionList().add(ProductInteraction.ProductInteractionBuilder.aProductInteraction()
                        .itemId(row.getCell(7).getStringCellValue())
                        .amount(String.valueOf(row.getCell(5).getNumericCellValue()))
                        .itemType("Z_EOBUWIE_PRODUCT")
                        .SourceSystemId("MAGENTO_MERCH_SHOP")
                        .quantity(row.getCell(0).getStringCellValue())
                        .zDiscountCode((row.getCell(19) != null) ? (row.getCell(19).getStringCellValue()) : "")
                        .zSize((row.getCell(30) != null) ? (row.getCell(30).getStringCellValue()) : "")
                        .zColor((row.getCell(15) != null) ? (row.getCell(15).getStringCellValue()) : "")
                        .build());


                amount = 0;


                continue;
            } else {
                productInteractions.add(ProductInteraction.ProductInteractionBuilder.aProductInteraction()
                        .itemId(row.getCell(7).getStringCellValue())
                        .amount(String.valueOf(row.getCell(5).getNumericCellValue()))
                        .itemType("Z_EOBUWIE_PRODUCT")
                        .SourceSystemId("MAGENTO_MERCH_SHOP")
                        .quantity(row.getCell(0).getStringCellValue())
                        .zDiscountCode((row.getCell(19) != null) ? (row.getCell(19).getStringCellValue()) : "")
                        .zSize((row.getCell(30) != null) ? (row.getCell(30).getStringCellValue()) : "")
                        .zColor((row.getCell(15) != null) ? (row.getCell(15).getStringCellValue()) : "")
                        .build());

                interactions.add(new Interaction.Builder()
                        .key(row.getCell(21).getStringCellValue())
                        .sourceObjectId(row.getCell(21).getStringCellValue())
                        .idOrigin("Z_EOBUWIE_CONSUMER")
                        .contactId(row.getCell(22).getStringCellValue())
                        .amount(String.valueOf((row.getCell(5).getNumericCellValue())))
                        .currency("PLN")
                        .interactionType("SALES_ORDER")
                        .communicationMedium("BUSINESS_DOCUMENT")
                        .timestamp("/Date(" + timestamp + ")/")
                        .productInteractionList(productInteractions)
                        .build());

                amount = row.getCell(5).getNumericCellValue();
            }


            orderNumber = row.getCell(21).getStringCellValue();
            counter++;
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
        Workbook sheets = getWorkbook(getProductFile());

        for (Row row : sheets.getSheetAt(0)) {

            List<ProductCategory> productCategories = new ArrayList<>();

            if (row.getRowNum() == 0)
                continue;

            if (row.getCell(11) != null) {
                productCategories.add(new ProductCategory.Builder()
                        .Name(row.getCell(11).getStringCellValue())
                        .Id(row.getCell(32).getStringCellValue())
                        .HierarchyId("MagentoProducts")
                        .builder());
            }

            if (row.getCell(12) != null) {
                productCategories.add(new ProductCategory.Builder()
                        .Name(row.getCell(12).getStringCellValue())
                        .Id(row.getCell(32).getStringCellValue())
                        .HierarchyId("MagentoProducts")
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
                    //.ProductCategoryList(productCategories)
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
        //return new File("/home/piotr/soft/eObuwie/transactional_data_new.xlsx");
        return new File("C:\\Users\\Asus\\Desktop\\eObuwie\\Sample_data\\magento_sample_data_interaction_sort.xlsx");
    }

    private Workbook getWorkbook(File file) throws Exception {
        return StreamingReader.builder()
                .rowCacheSize(10)     // number of rows to keep in memory (defaults to 10)
                .bufferSize(1024)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .sheetIndex(0)        // index of sheet to use (defaults to 0)
                .open(new FileInputStream(file.getAbsolutePath()));
    }

}
