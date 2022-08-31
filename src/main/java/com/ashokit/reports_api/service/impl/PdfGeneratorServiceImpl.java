package com.ashokit.reports_api.service.impl;

import com.ashokit.reports_api.model.SearchResponse;
import com.ashokit.reports_api.service.PdfGeneratorService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {
    public ByteArrayInputStream getPdfReport(List<SearchResponse> data) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, out);

        // Opening the created document to modify it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("Enrolled Customers Details", fontTitle);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 3.0f, 4.0f, 2.5f, 4.0f, 4.0f});
        table.setSpacingBefore(10);

        setHeaders(table);
        setData(table, data);

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    void setHeaders(PdfPTable table) {
        String[] headers = { "S.No", "Name", "E-Mail", "Gender", "Mobile Number", "SSN" };

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        Arrays.stream(headers).forEach(header-> {
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        });
    }

    void setData(PdfPTable table, List<SearchResponse> data) {
        AtomicInteger i = new AtomicInteger(1);
        data.forEach(d-> {
            table.addCell(String.valueOf(i));
            table.addCell(d.getName());
            table.addCell(d.getEmail());
            table.addCell(d.getGender());
            table.addCell(d.getMobileNumber());
            table.addCell(d.getSsn());
            i.getAndIncrement();
        });
    }
}
