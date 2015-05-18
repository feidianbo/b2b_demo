package demo.admin.controller;

import demo.core.domain.Finance;
import demo.core.persistence.FinanceMapper;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by fanjun on 15-1-24.
 */
@Controller
public class FinanceController {

    @Autowired
    protected FinanceMapper financeMapper;

    //金融客户列表
    @RequestMapping(value="/finance/list",method= RequestMethod.POST)
    @ResponseBody
    public Object list(int page) {
        Map map = new HashMap();
        map.put("finance", financeMapper.pageAllFinanceCustomer(page, 10));
        return map;
    }

    //导出Excel文件
    @RequestMapping("/finance/exportExcel")
    public void exportExcel(String scope,HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("金融客户");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        String[] excelHeader = {"序号", "类型", "公司名称", "公司地址", "业务区域","融资金额", "联系人", "联系电话", "创建时间"};
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }


        List<Finance> customersList = null;
        if(scope.equals("today")){
            customersList = financeMapper.getTodayFinaceCustomers();
        }
        if(scope.equals("all")){
            customersList = financeMapper.getAllFinanceCustomers();
        }
        for (int i = 0; i < customersList.size(); i++) {
            row = sheet.createRow(i + 1);
            Finance customer = customersList.get(i);
            row.createCell(0).setCellValue(customer.getId());
            row.createCell(1).setCellValue(customer.getType());
            row.createCell(2).setCellValue(customer.getCompanyname());
            row.createCell(3).setCellValue(customer.getAddress());
            row.createCell(4).setCellValue(customer.getBusinessarea());
            row.createCell(5).setCellValue(customer.getAmountnum());
            row.createCell(6).setCellValue(customer.getContact());
            row.createCell(7).setCellValue(customer.getPhone());
            row.createCell(8).setCellValue(customer.getCreatetime().toString());
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-download");
        String filename = "金融客户"+LocalDate.now();
        filename = URLEncoder.encode(filename, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ filename+".xls");
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
    }
}
