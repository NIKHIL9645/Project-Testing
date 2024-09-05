package com.employee.controller;


import com.employee.entity.Employee;
import com.employee.service.EmpServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "http://localhost:3000")
public class EmplController {


    private static final Logger LOGGER = LoggerFactory.getLogger(EmplController.class);


    @Autowired
    private EmpServiceImpl empService;


    @PostMapping()
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        Employee employee1 = empService.saveEmp(employee);
        return ResponseEntity.ok(employee1);
    }

    //Generate Response in PDF File Format
    @GetMapping("/getall")
    public void downloadPdf(
            @RequestParam(required = false, defaultValue = "employees.pdf")
            String filename, HttpServletResponse response,
//            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size)
            throws IOException {


//        List<Employee> employees = empService.getAllEmp();

        // #1 give specific page pdf like 0-15 , 1-15
//        List<Employee> employeesForPage = empService.getEmployeesForPage(page, size);


        try (PDDocument document = new PDDocument()) {

            int pageNo = 0;
            Page<Employee> employeePage;


            //getting first page
            do {
                employeePage = empService.getEmployees(pageNo, size);
                List<Employee> content = employeePage.getContent();

                if (!content.isEmpty()) {

                    // Create a new page for records in the PDF
                    PDPage pdfPage = new PDPage();
                    document.addPage(pdfPage);

                    //add content to that page


//            #1
//            }
//            PDPage page1 = new PDPage();
//            document.addPage(page1);


                    //add content to pdf
                    try (PDPageContentStream contentStream = new PDPageContentStream(document, pdfPage)) {
                        contentStream.beginText();
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                        contentStream.newLineAtOffset(25, 750);

                        for (Employee employee : content) {
                            contentStream.showText("ID: " + employee.getId() + " Name: " + employee.getName());
                            contentStream.newLineAtOffset(0, -15);
                        }

                        contentStream.endText();

                    }
                }
                pageNo++;
            } while (employeePage.hasNext());

            //write the pdf to an output stream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);


            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
//            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.pdf");

            // Write the PDF to the response output stream
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();


        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> allEmp = empService.getAllEmp();

        return ResponseEntity.ok(allEmp);
    }

    @GetMapping("/all/all")
    public List<Employee> getall() {
        return empService.getAll();

    }



    //Generate Response in XML File Format

    @GetMapping("/fetchall")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<Employee> all = empService.getAll();

        if (all == null || all.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No employee data found");
            return;
        }
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Employees");

            //create a header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Name", "ContactNo"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            //fill the data in rows
            int rowNo = 1;
            for (Employee employee : all) {
                Row row = sheet.createRow(rowNo++);
                row.createCell(0).setCellValue(employee.getId());
                row.createCell(1).setCellValue(employee.getName());
                row.createCell(2).setCellValue(employee.getContactNo());
            }


            //write output stream

            workbook.write(outputStream);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
            response.getOutputStream().write(outputStream.toByteArray());
            response.getOutputStream().flush();
        }


    }

}




