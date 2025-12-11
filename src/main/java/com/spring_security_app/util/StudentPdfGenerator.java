package com.spring_security_app.util;





import com.spring_security_app.entity.StudentModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class StudentPdfGenerator {


        public static byte[] generatePdf(List<StudentModel> studentList) throws IOException {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.setStrokingColor(Color.BLACK);
                    contentStream.addRect(20, 20, page.getMediaBox().getWidth() - 40, page.getMediaBox().getHeight() - 40);
                    contentStream.stroke();
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 28);
                    // Calculate the center of the page dynamically
                    float pageWidth = page.getMediaBox().getWidth();
                    float textWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Student List") / 1000f * 30;
                    float textPositionX = (pageWidth - textWidth) / 2;
                    // Set Y position and add space below
                    float yPosition = 730;
                    contentStream.newLineAtOffset(textPositionX, yPosition);
                    contentStream.showText("Student List");


                    contentStream.newLineAtOffset(-180, -30); // Add more space below the text
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
                    contentStream.showText("ID " +
                            " Name " +
                            " Address "+
                            " Semister Cost "+
                            " Semister No ");



                    contentStream.newLine();
                    contentStream.newLineAtOffset(0,-30);
                    contentStream.setFont(PDType1Font.HELVETICA, 20);
                    for (StudentModel student : studentList) {
                        contentStream.showText(
                                student.getStudentId() + "     "+
                                student.getStudentName() +"      "+
                                 student.getAddress()+"              "+
                                 student.getSemisterCost()+ "                  "+
                                 student.getSemisterNo());
                        contentStream.newLineAtOffset(0, -30);
                    }


//                    contentStream.newLine();
//                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
//                    contentStream.newLineAtOffset(80, -520); // Adjust position as needed
//                    contentStream.showText("Enhance Your Technology Integration Possibilities With Us.");
                    contentStream.endText();



//                    contentStream.setStrokingColor(Color.BLACK); // Set line color
//                    contentStream.setLineWidth(1); // Set line width
//                    float startXs = 50; // X-coordinate of the starting point
//                    float endXs = page.getMediaBox().getWidth() - 50; // X-coordinate of the ending point
//                    float ys = -500; // Y-coordinate of the line
//                    contentStream.moveTo(startXs, ys); // Move to the starting point
//                    contentStream.lineTo(endXs, ys); // Draw a line to the ending point
//                    contentStream.stroke(); // Stroke the line


                    contentStream.setStrokingColor(Color.BLACK); // Set line color
                    contentStream.setLineWidth(1); // Set line width
                    float startX = 50; // X-coordinate of the starting point
                    float endX = page.getMediaBox().getWidth() - 50; // X-coordinate of the ending point
                    float y = 80; // Y-coordinate of the line
                    contentStream.moveTo(startX, y); // Move to the starting point
                    contentStream.lineTo(endX, y); // Draw a line to the ending point
                    contentStream.stroke(); // Stroke the line
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                document.save(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
        }
}
