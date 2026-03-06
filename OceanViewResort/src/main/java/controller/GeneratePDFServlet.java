package controller;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import dao.DBConnection;

@WebServlet("/generatePDF")
public class GeneratePDFServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM reservations WHERE reservation_id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition",
                        "attachment; filename=Invoice_" + id + ".pdf");

                Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                PdfWriter.getInstance(document, response.getOutputStream());
                document.open();

                /* ===== LOGO ===== */
                String logoPath = getServletContext().getRealPath("/images/touristlogo.png");
                Image logo = Image.getInstance(logoPath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);

                /* ===== TITLE ===== */
                Font titleFont = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD);
                Paragraph title = new Paragraph("Ocean View Resort", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                Font subTitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                Paragraph subTitle = new Paragraph("Invoice / Bill", subTitleFont);
                subTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(subTitle);

                /* ===== BILL DATE + INVOICE NUMBER ===== */
                LocalDate billDate = LocalDate.now();
                Random rand = new Random();
                String invoiceNumber = "INV" + rand.nextInt(9000 + 1000); // Random 4 digit

                Paragraph datePara = new Paragraph("Invoice Date: " + billDate + " | Invoice No: " + invoiceNumber);
                datePara.setAlignment(Element.ALIGN_CENTER);
                document.add(datePara);

                document.add(Chunk.NEWLINE);

                /* ===== RESERVATION DETAILS TABLE ===== */
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);
                table.setWidths(new float[]{1f, 2f});
                Font headFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

                table.addCell(new PdfPCell(new Phrase("Reservation Number:", headFont)));
                table.addCell(new PdfPCell(new Phrase(rs.getString("reservation_number"))));

                table.addCell(new PdfPCell(new Phrase("Guest Name:", headFont)));
                table.addCell(new PdfPCell(new Phrase(rs.getString("guest_name"))));
                
                table.addCell(new PdfPCell(new Phrase("Email:", headFont)));
                table.addCell(new PdfPCell(new Phrase(rs.getString("email"))));

                table.addCell(new PdfPCell(new Phrase("Room Type:", headFont)));
                table.addCell(new PdfPCell(new Phrase(rs.getString("room_type"))));

                table.addCell(new PdfPCell(new Phrase("Price/Night:", headFont)));
                table.addCell(new PdfPCell(new Phrase("Rs. " + rs.getDouble("price_per_night"))));

                table.addCell(new PdfPCell(new Phrase("Address:", headFont)));
                table.addCell(new PdfPCell(new Phrase(rs.getString("address"))));

                table.addCell(new PdfPCell(new Phrase("Contact Number:", headFont)));
                table.addCell(new PdfPCell(new Phrase(rs.getString("contact_number"))));

                LocalDate checkIn = rs.getDate("check_in").toLocalDate();
                LocalDate checkOut = rs.getDate("check_out").toLocalDate();
                long days = ChronoUnit.DAYS.between(checkIn, checkOut);
                double total = days * rs.getDouble("price_per_night");

                table.addCell(new PdfPCell(new Phrase("Check In:", headFont)));
                table.addCell(new PdfPCell(new Phrase(checkIn.toString())));

                table.addCell(new PdfPCell(new Phrase("Check Out:", headFont)));
                table.addCell(new PdfPCell(new Phrase(checkOut.toString())));

                table.addCell(new PdfPCell(new Phrase("Total Days:", headFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(days))));

                // Payment Status
                table.addCell(new PdfPCell(new Phrase("Payment Status:", headFont)));
                PdfPCell statusCell = new PdfPCell(new Phrase(rs.getString("payment_status")));
                String status = rs.getString("payment_status");
                if(status.equalsIgnoreCase("Paid")){
                    statusCell.setBackgroundColor(BaseColor.GREEN);
                    statusCell.setPhrase(new Phrase(status + " ✅"));
                } else {
                    statusCell.setBackgroundColor(BaseColor.RED);
                    statusCell.setPhrase(new Phrase(status + " ❌"));
                }
                table.addCell(statusCell);

                document.add(table);

                /* ===== TOTAL + VAT ===== */
                double vat = total * 0.12; // 12% VAT
                double grandTotal = total + vat;

                Paragraph totalPara = new Paragraph(String.format("Subtotal: Rs. %.2f\nVAT (12%%): Rs. %.2f\nGrand Total: Rs. %.2f", total, vat, grandTotal));
                totalPara.setAlignment(Element.ALIGN_RIGHT);
                totalPara.setFont(new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
                document.add(totalPara);

                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);

                // Signature
                Paragraph sign = new Paragraph("Authorized Signature: ____________________");
                sign.setAlignment(Element.ALIGN_RIGHT);
                document.add(sign);

                document.add(Chunk.NEWLINE);

                // Footer
                Paragraph footer = new Paragraph("Ocean View Resort | Tel: +94 11 1234567 | Email: info@oceanview.lk");
                footer.setAlignment(Element.ALIGN_CENTER);
                footer.setFont(new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC));
                document.add(footer);

                document.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}