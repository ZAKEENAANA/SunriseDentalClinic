package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import dao.DBConnection;

@WebServlet("/sendMonthlyReportEmail")
public class SendMonthlyReportEmailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String month = request.getParameter("monthEmail");
        if (month == null || month.isEmpty()) {
            month = LocalDate.now().toString().substring(0, 7);
        }

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM reservations WHERE DATE_FORMAT(check_in,'%Y-%m')=?"
            );
            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();

            // ✅ STEP 1: Store all reservations in List
            List<Map<String, Object>> reservations = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> res = new HashMap<>();
                res.put("reservation_number", rs.getString("reservation_number"));
                res.put("guest_name", rs.getString("guest_name"));
                res.put("room_type", rs.getString("room_type"));
                res.put("check_in", rs.getDate("check_in"));
                res.put("check_out", rs.getDate("check_out"));
                res.put("payment_status", rs.getString("payment_status"));
                res.put("email", rs.getString("email"));
                reservations.add(res);
            }

            if (reservations.isEmpty()) {
                response.getWriter().println("No reservations found for month: " + month);
                return;
            }

            // ✅ STEP 2: Create Temporary PDF
            File tempPDF = File.createTempFile("Monthly_Report_" + month, ".pdf");

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(tempPDF));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph(
                    "Ocean View Resort\nMonthly Reservation Report\nMonth: " + month,
                    titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 2f, 2f, 1.5f, 1.5f, 2f});

            Font headFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            table.addCell(new PdfPCell(new Phrase("Resv No", headFont)));
            table.addCell(new PdfPCell(new Phrase("Guest Name", headFont)));
            table.addCell(new PdfPCell(new Phrase("Room Type", headFont)));
            table.addCell(new PdfPCell(new Phrase("Check In", headFont)));
            table.addCell(new PdfPCell(new Phrase("Check Out", headFont)));
            table.addCell(new PdfPCell(new Phrase("Payment Status", headFont)));

            // ✅ STEP 3: Fill PDF using List
            for (Map<String, Object> res : reservations) {

                table.addCell((String) res.get("reservation_number"));
                table.addCell((String) res.get("guest_name"));
                table.addCell((String) res.get("room_type"));
                table.addCell(res.get("check_in").toString());
                table.addCell(res.get("check_out").toString());

                String status = (String) res.get("payment_status");
                PdfPCell statusCell = new PdfPCell(new Phrase(status));

                if ("Paid".equalsIgnoreCase(status)) {
                    statusCell.setBackgroundColor(BaseColor.GREEN);
                    statusCell.setPhrase(new Phrase(status + " ✅"));
                } else {
                    statusCell.setBackgroundColor(BaseColor.RED);
                    statusCell.setPhrase(new Phrase(status + " ❌"));
                }

                table.addCell(statusCell);
            }

            document.add(table);
            document.close();

            // ✅ STEP 4: Send Email to Each Customer
            for (Map<String, Object> res : reservations) {
                String email = (String) res.get("email");

                if (email != null && !email.isEmpty()) {
                    EmailUtility.sendEmailWithAttachment(
                            email,
                            "Monthly Reservation Report - " + month,
                            "Dear " + res.get("guest_name") +
                                    ",\n\nPlease find attached the monthly reservation report from Ocean View Resort.\n\nThank you!",
                            tempPDF
                    );
                }
            }

            response.sendRedirect("dashboard.jsp?msg=success");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error sending emails: " + e.getMessage());
        }
    }
}
