package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.dto.PagingObject;
import com.jlink.dto.RoleDeptObejct;
import com.jlink.entity.Userinfo;
import com.jlink.service.UserinfoService;
import com.jlink.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.STRING;

@Controller
@RequestMapping("userinfo")
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;
    /**
     * 新增
     */
    @RequestMapping(value = "addUserinfo", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult addUserinfo(@RequestBody Userinfo userinfo){
        try{
            if(StringUtils.isBlank(userinfo.getName()) || StringUtils.isBlank(userinfo.getSsn()) || StringUtils.isBlank(userinfo.getBadgenumber())){
                return ObjectResult.error("必填项未填！");
            }
            userinfo.setDefaultdeptid(Integer.valueOf(userinfo.getDeptNo()));
            if(userinfoService.save(userinfo)){
                return ObjectResult.success(userinfoService.getUserinfoByUserId(userinfo.getUserid()));
            }
            return ObjectResult.error("添加失败！");
        }catch(Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 筛选
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult query(@RequestParam String badgenumber, @RequestParam String name,
                              @RequestParam String phone, @RequestParam String deptNo,
                              @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer per){
        try{
            List<Userinfo> userinfos = userinfoService.query(badgenumber, name, phone, deptNo, page, per);
            int count = userinfoService.getCount(badgenumber, name, phone, deptNo);
            return ObjectResult.success(new PagingObject(userinfos, count));
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 删除
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResult delete(@RequestParam Integer userId){
        try{
            if(userinfoService.delete(userId)){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("删除失败！");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 导入员工
     */
    @RequestMapping(value = "importUserinfo", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult batchSave(@RequestParam MultipartFile file) {
        String fileName = FileUtil.getRandomFileName() + file.getOriginalFilename();
        String path = FileUtil.getFileBasePath();
        try {
            File excelFile = new File(path, fileName);
            file.transferTo(excelFile);
            Workbook wb =null;
            Sheet sheet = null;
            Row row = null;
            List<Map<String,String>> list = null;
            String cellData = null;
            String columns[] = {"DEPT_NO","BADGENUMBER","SSN","NAME","GENDER","HIREDDAY","CardNo","LeaveDate"};
            wb = readExcel(excelFile.getAbsolutePath());
            if(wb != null){
                //用来存放表中数据
                list = new ArrayList<Map<String,String>>();
                //获取第一个sheet
                sheet = wb.getSheetAt(0);
                //获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                //获取第一行
                row = sheet.getRow(0);
                //获取最大列数
                int colnum = row.getPhysicalNumberOfCells();
                for (int i = 1; i<rownum; i++) {
                    Map<String,String> map = new LinkedHashMap<String,String>();
                    row = sheet.getRow(i);
                    if(row !=null){
                        for (int j=0;j<colnum;j++){
                            cellData = (String) getCellFormatValue(row.getCell(j));
                            map.put(columns[j], cellData);
                        }
                    }else{
                        break;
                    }
                    list.add(map);
                }
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<Userinfo> userinfos = new LinkedList<>();
            //遍历解析出来的list
            for (Map<String,String> map : list) {
                Userinfo userinfo = new Userinfo();
                for (Map.Entry<String,String> entry : map.entrySet()) {
//                    System.out.print(entry.getKey()+":"+entry.getValue()+",");
                    switch(entry.getKey()){
                        case "DEPT_NO":
                            userinfo.setDeptNo(entry.getValue());
                            break;
                        case "BADGENUMBER":
                            userinfo.setBadgenumber(entry.getValue());
                            break;
                        case "SSN":
                            userinfo.setSsn(entry.getValue());
                            break;
                        case "NAME":
                            userinfo.setName(entry.getValue());
                            break;
                        case "GENDER":
                            userinfo.setGender(entry.getValue());
                            break;
                        case "HIREDDAY":
                            if(!StringUtils.isBlank(entry.getValue())){
                                userinfo.setHiredday(dateFormat.parse(entry.getValue()));
                            }
                            break;
                        case "CardNo":
                            userinfo.setCardno(entry.getValue());
                            break;
                        case "LeaveDate":
                            if(!StringUtils.isBlank(entry.getValue())){
                                userinfo.setHiredday(dateFormat.parse(entry.getValue()));
                            }
                            break;

                        default:
                            System.out.println(entry.getKey());

                    }
                }
                userinfos.add(userinfo);
//                System.out.println();
            }
            userinfoService.batchSave(userinfos);
            return ObjectResult.success(null);
        } catch (Exception e) {
            return ObjectResult.error(e.getMessage());
        }
    }
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
//            System.out.println(cell+","+cell.getCellType());
            //判断cell类型
            switch(cell.getCellType()){
                case NUMERIC:{
                    cell.setCellType(STRING);
                    cellValue = importByExcelForDate(cell.getStringCellValue());

                    break;
                }
                case FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
    public static String importByExcelForDate(String value) {//value就是它的天数
        String currentCellValue = "";
        if(value != null && !value.equals("")){
            Calendar calendar = new GregorianCalendar(1900,0,-1);
            Date d = calendar.getTime();
            Date dd = DateUtils.addDays(d,Integer.valueOf(value));
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            currentCellValue = formater.format(dd);
        }
        return currentCellValue;
    }
}
