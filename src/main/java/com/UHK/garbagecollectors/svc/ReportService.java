package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.model.Location;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    public ReportService() {

    }

    public InputStream generatePayments(List<GCollection> selectedCols) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        document.setTextAlignment(TextAlignment.JUSTIFIED);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date date = new Date();

        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN, PdfEncodings.CP1250);
        PdfFont fontItallic = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC, PdfEncodings.CP1250);
        PdfFont fontName = PdfFontFactory.createFont(FontConstants.HELVETICA, PdfEncodings.CP1250);

        for (GCollection gCol: selectedCols) {
            for (GCan gCan : gCol.getCans()) {

                // Address field
                Location loc = gCan.getLocation();
                Text text = new Text(loc.getStreet() + " " + loc.getHouseNumber() + "\n" + loc.getCity()).setFont(font);
                text.setFontSize(14);

                Paragraph pAddress = new Paragraph(text);
                pAddress.setFixedPosition(300, 700, 200);
                document.add(pAddress);

                Text textGreetings = new Text("Dobrý den, \n \n").setFont(font).setFontSize(12);

                Text textCan =
                        new Text("zasíláme vám podklady pro platbu za vaši svozovou nádobu o objemu "
                        + gCan.getVolume() + " l za rok " + formatter.format(date) + ". \n" +
                        "Částku prosím zašlete na účet 0023456789/0800. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi lobortis a urna tincidunt laoreet. Donec bibendum congue eros, vitae tincidunt libero tempus vitae. Aliquam semper ligula eu faucibus porttitor. Sed convallis nulla sollicitudin placerat ultrices. Nunc aliquam velit sed leo posuere, a blandit eros lacinia. Vestibulum scelerisque tincidunt ligula, eget sagittis erat venenatis ac. Fusce sodales sodales magna, vel pellentesque nibh faucibus tristique. Donec et lobortis quam. Nunc tellus ante, euismod vitae erat rutrum, semper gravida nisi. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
                        ).setFont(font).setFontSize(12);

                Text textThanks = new Text("\n \n Děkujeme že jste i nadále našimi zákazníky \n Vaši, \n \n").setFont(fontItallic).setFontSize(12);

                Text textName = new Text("Garbage Collectors").setFont(fontName);

                textName.setStrokeColor(Color.DARK_GRAY)
                        .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                        .setFontSize(16);

                Paragraph mainParagraph = new Paragraph();
                mainParagraph.setFixedPosition(50, 150,500);
                mainParagraph.add(textGreetings);
                mainParagraph.add(textCan);
                mainParagraph.add(textThanks);
                mainParagraph.add(textName);
                document.add(mainParagraph);
                document.add(new AreaBreak());
            }
        }

        document.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }


    public InputStream generateStickers(List<GCan> cans) throws IOException {


        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Svozové nádoby");
        sheet.setColumnWidth(0, 25 * 256);
        sheet.setColumnWidth(1, 10 * 256);
        sheet.setColumnWidth(2, 60 * 256);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Typ odpadu");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Objem");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Adresa");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        int currentRow = 2;

        for (GCan can: cans) {

            Row row = sheet.createRow(currentRow);

            Cell cell = row.createCell(0);
            cell.setCellValue(can.getGarbageType().toString());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(can.getVolume());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(can.getLocation().getAddress());
            cell.setCellStyle(style);

            currentRow++;

        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        workbook.write(outputStream);

        return new ByteArrayInputStream(outputStream.toByteArray());

    }
}
