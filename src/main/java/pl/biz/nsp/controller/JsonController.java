package pl.biz.nsp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.biz.nsp.service.ExcelReader;
import pl.biz.nsp.model.Interaction;

import java.util.List;

/**
 * @author Piotr Larys
 */
@RestController
public class JsonController {

    private ExcelReader excelReader;

    public JsonController(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }

    @GetMapping("/convert")
    public @ResponseBody List<Interaction> interactions() throws Exception {
        return excelReader.excelReader();
    }



}
