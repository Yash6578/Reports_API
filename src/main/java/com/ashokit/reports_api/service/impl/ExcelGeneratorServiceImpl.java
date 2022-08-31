package com.ashokit.reports_api.service.impl;

import com.ashokit.reports_api.model.SearchResponse;
import com.ashokit.reports_api.service.ExcelGeneratorService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelGeneratorServiceImpl implements ExcelGeneratorService {
    public ByteArrayInputStream getExcelReport(List<SearchResponse> data) {
        try(Workbook workbook = new HSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Enrolled Members Report");

            setHeaders(sheet.createRow(0));
            setData(sheet, data);

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());
        }
        catch (IOException exception) {
            throw new RuntimeException("fail to import data to Excel file: " + exception.getMessage());
        }
    }

    void setData(Sheet sheet, List<SearchResponse> data) {
        int rowIndex = 1;

        for(SearchResponse d : data) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(d.getName());
            row.createCell(1).setCellValue(d.getEmail());
            row.createCell(2).setCellValue(d.getMobileNumber());
            row.createCell(3).setCellValue(d.getSsn());
            row.createCell(4).setCellValue(d.getGender());
        }
    }

    void setHeaders(Row headerRow) {
        String[] headers = { "Name", "E-Mail", "Mobile Number", "SSN", "Gender" };

        for(int i = 0;i < headers.length;i++)
            headerRow.createCell(i).setCellValue(headers[i]);

    }
}
