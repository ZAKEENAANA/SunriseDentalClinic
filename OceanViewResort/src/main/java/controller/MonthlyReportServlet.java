package controller;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import dao.DBConnection;

@WebServlet("/monthlyReport")
public class MonthlyReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String month = request.getParameter("month"); // e.g., "2026-02"
        if (month == null) {
            month = LocalDate.now().toString().substring(0,7); // default current month
        }

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM reservations WHERE DATE_FORMAT(check_in,'%Y-%m')=?"
            );
            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=Monthly_Report_" + month + ".pdf");

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Ocean View Resort\nMonthly Reservation Report\nMonth: " + month, titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6); // columns
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 2f, 2f, 1f, 1f, 2f});

            Font headFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            table.addCell(new PdfPCell(new Phrase("Resv No", headFont)));
            table.addCell(new PdfPCell(new Phrase("Guest Name", headFont)));
            table.addCell(new PdfPCell(new Phrase("Room Type", headFont)));
            table.addCell(new PdfPCell(new Phrase("Check In", headFont)));
            table.addCell(new PdfPCell(new Phrase("Check Out", headFont)));
            table.addCell(new PdfPCell(new Phrase("Payment Status", headFont)));

            while(rs.next()){
                table.addCell(rs.getString("reservation_number"));
                table.addCell(rs.getString("guest_name"));
                table.addCell(rs.getString("room_type"));
                table.addCell(rs.getDate("check_in").toString());
                table.addCell(rs.getDate("check_out").toString());

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
            }

            document.add(table);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}