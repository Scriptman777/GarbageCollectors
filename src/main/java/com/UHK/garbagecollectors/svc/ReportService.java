package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GCollection;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    public ReportService() {

    }

    public InputStream generatePayments(List<GCollection> selectedCols) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        Font addressFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);

        for (GCollection col: selectedCols) {
            for (GCan can : col.getCans()) {
                Paragraph paragraph = new Paragraph("Collection - " + col.getName() + " give me money :) \n", font);
                document.add(paragraph);
            }

        }

        document.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
