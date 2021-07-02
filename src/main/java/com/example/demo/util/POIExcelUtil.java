package com.example.demo.util;
import com.example.demo.pojo.Userdomain.Teacher;
import com.example.demo.pojo.Workdomain.WorksPF;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Component
public class POIExcelUtil {

    static Logger log = LoggerFactory.getLogger(POIExcelUtil.class);
    /**
     * 读取教师信息excel
     * @param TeacherList
     * @param excelPath
     * @return
     */
    public static boolean readTeacherMesExcelData(List<Teacher> TeacherList, String excelPath){
        //xlsx
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Teacher");  //创建table工作薄


        //行对象
        XSSFRow row;
        //列对象
        XSSFCell cell;
        //构造0行列标签
        row = sheet.createRow(0);
        for (int i = 0; i < 8; i++) {
            cell = row.createCell(i);
        }
        row.getCell(0).setCellValue("ID");
        row.getCell(1).setCellValue("教师真实姓名");
        row.getCell(2).setCellValue("用户名");
        row.getCell(3).setCellValue("密码");
        row.getCell(4).setCellValue("所在学校");
        row.getCell(5).setCellValue("所在学院");
        row.getCell(6).setCellValue("邮箱");
        row.getCell(7).setCellValue("联系方式");


        //构造1-n行的列标签
        for (int i = 0; i <TeacherList.size(); i++) {
            row = sheet.createRow(i+1);

            for (int j = 0; j < 8; j++) {
                row.createCell(j);
            }
            row.getCell(0).setCellValue(TeacherList.get(i).getTid());
            row.getCell(1).setCellValue(TeacherList.get(i).getRealname());
            row.getCell(2).setCellValue(TeacherList.get(i).getUsername());
            row.getCell(3).setCellValue(TeacherList.get(i).getPassword());
            row.getCell(4).setCellValue(TeacherList.get(i).getSchool());
            row.getCell(5).setCellValue(TeacherList.get(i).getCollege());
            row.getCell(6).setCellValue(TeacherList.get(i).getEmail());
            row.getCell(7).setCellValue(TeacherList.get(i).getPhone());

        }
        try {
            wb.write(new FileOutputStream(excelPath));
            wb.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 获取 已发奖金的信息
     * @param worksMesList
     * @param excelPath
     * @return
     */
    public static boolean readRewardedWorksExcelData(List<WorksPF> worksMesList,String excelPath){
        //xlsx
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("AllWork");  //创建table工作薄


        //行对象
        XSSFRow row;
        //列对象
        XSSFCell cell;
        //构造0行列标签
        row = sheet.createRow(0);
        for (int i = 0; i < 9; i++) {
            cell = row.createCell(i);
        }
        row.getCell(0).setCellValue("ID");
        row.getCell(1).setCellValue("竞赛名称");
        row.getCell(2).setCellValue("竞赛类别");
        row.getCell(3).setCellValue("参赛作品");
        row.getCell(4).setCellValue("奖项");
        row.getCell(5).setCellValue("负责人名称");
        row.getCell(6).setCellValue("奖金数额/元");
        row.getCell(7).setCellValue("指导老师");
        row.getCell(8).setCellValue("选手所在学校");


        //构造1-n行的列标签
        for (int i = 0; i <worksMesList.size(); i++) {
            row = sheet.createRow(i+1);

            for (int j = 0; j < 9; j++) {
                row.createCell(j);
            }
            row.getCell(0).setCellValue(worksMesList.get(i).getWid());
            row.getCell(1).setCellValue(worksMesList.get(i).getMatch_name());
            row.getCell(2).setCellValue(worksMesList.get(i).getType());
            row.getCell(3).setCellValue(worksMesList.get(i).getWorksname());
            row.getCell(4).setCellValue(worksMesList.get(i).getPrize_name());
            row.getCell(5).setCellValue(worksMesList.get(i).getAuthor1_name());
            row.getCell(6).setCellValue(worksMesList.get(i).getReward());
            row.getCell(7).setCellValue(worksMesList.get(i).getTeacher());
            row.getCell(8).setCellValue(worksMesList.get(i).getSchool());

        }
        try {
            wb.write(new FileOutputStream(excelPath));
            wb.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 获取决赛获奖列表的信息
     * @param worksMesList
     * @param excelPath
     * @return
     */
    public static boolean readJSWorksExcelData(List<WorksPF> worksMesList,String excelPath){
        //xlsx
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("AllWork");  //创建table工作薄


        //行对象
        XSSFRow row;
        //列对象
        XSSFCell cell;
        //构造0行列标签
        row = sheet.createRow(0);
        for (int i = 0; i < 8; i++) {
            cell = row.createCell(i);
        }
        row.getCell(0).setCellValue("ID");
        row.getCell(1).setCellValue("竞赛名称");
        row.getCell(2).setCellValue("竞赛类别");
        row.getCell(3).setCellValue("参赛作品");
        row.getCell(4).setCellValue("奖项");
        row.getCell(5).setCellValue("负责人名称");
        row.getCell(6).setCellValue("指导老师");
        row.getCell(7).setCellValue("选手所在学校");

        //构造1-n行的列标签
        for (int i = 0; i <worksMesList.size(); i++) {
            row = sheet.createRow(i+1);

            for (int j = 0; j < 8; j++) {
                row.createCell(j);
            }
            row.getCell(0).setCellValue(worksMesList.get(i).getWid());
            row.getCell(1).setCellValue(worksMesList.get(i).getMatch_name());
            row.getCell(2).setCellValue(worksMesList.get(i).getType());
            row.getCell(3).setCellValue(worksMesList.get(i).getWorksname());
            row.getCell(4).setCellValue(worksMesList.get(i).getPrize_name());
            row.getCell(5).setCellValue(worksMesList.get(i).getAuthor1_name());
            row.getCell(6).setCellValue(worksMesList.get(i).getTeacher());
            row.getCell(7).setCellValue(worksMesList.get(i).getSchool());

        }
        try {
            wb.write(new FileOutputStream(excelPath));
            wb.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 读取所有作品信息
     * @param worksMesList
     * @param excelPath
     * @return
     */
    public static boolean readAllWorksMesExcelData(List<WorksPF> worksMesList,String excelPath){
        //xlsx
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("AllWork");  //创建table工作薄


        //行对象
        XSSFRow row;

        //构造0行列标签
        row = sheet.createRow(0);
        for (int i = 0; i < 10; i++) {
            row.createCell(i);
        }
        row.getCell(0).setCellValue("ID");
        row.getCell(1).setCellValue("竞赛名称");
        row.getCell(2).setCellValue("竞赛项目");
        row.getCell(3).setCellValue("竞赛类别");
        row.getCell(4).setCellValue("参赛作品");
        row.getCell(5).setCellValue("负责人名称");
        row.getCell(6).setCellValue("指导老师");
        row.getCell(7).setCellValue("作品提交时间");
        row.getCell(8).setCellValue("选手所在学校");
        row.getCell(9).setCellValue("联系电话");

        //构造1-n行的列标签
        for (int i = 0; i <worksMesList.size(); i++) {
            row = sheet.createRow(i+1);
            for (int j = 0; j < 10; j++) {
                row.createCell(j);
            }


            row.getCell(0).setCellValue(worksMesList.get(i).getWid());
            row.getCell(1).setCellValue(worksMesList.get(i).getMatch_name());
            row.getCell(2).setCellValue(worksMesList.get(i).getMatch_project());
            row.getCell(3).setCellValue(worksMesList.get(i).getType());
            row.getCell(4).setCellValue(worksMesList.get(i).getWorksname());
            row.getCell(5).setCellValue(worksMesList.get(i).getAuthor1_name());
            row.getCell(6).setCellValue(worksMesList.get(i).getTeacher());
            row.getCell(7).setCellValue(worksMesList.get(i).getDate());
            row.getCell(8).setCellValue(worksMesList.get(i).getSchool());
            row.getCell(9).setCellValue(worksMesList.get(i).getPhone());
        }
        try {
            wb.write(new FileOutputStream(excelPath));
            wb.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 读取 选手管理的信息
     * @param userMesMapList
     * @param excelPath
     * @return
     */
    public static boolean readUserMesExcelData(List<Map<String,Object>> userMesMapList,String excelPath)  {
        //xlsx
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("userMes");  //创建table工作薄
        //行对象
        XSSFRow row;
        row = sheet.createRow(0);
        for (int i = 0; i < 12; i++) {
            row.createCell(i);
        }
        row.getCell(0).setCellValue("ID");
        row.getCell(1).setCellValue("参赛选手名称");
        row.getCell(2).setCellValue("学号");
        row.getCell(3).setCellValue("竞赛名称");
        row.getCell(4).setCellValue("竞赛项目");
        row.getCell(5).setCellValue("竞赛类别");
        row.getCell(6).setCellValue("参赛作品");
        row.getCell(7).setCellValue("参赛注册时间");
        row.getCell(8).setCellValue("选手邮箱");
        row.getCell(9).setCellValue("选手所在学校");
        row.getCell(10).setCellValue("选手专业");
        row.getCell(11).setCellValue("联系电话");

        for(int i = 0; i < userMesMapList.size(); i++) {
            row = sheet.createRow(i+1);//创建表格行
            for (int j = 0; j < 12; j++) {
                row.createCell(j);
            }
            row.getCell(0).setCellValue((Integer) userMesMapList.get(i).get("uid"));
            row.getCell(1).setCellValue( (String) userMesMapList.get(i).get("username"));
            row.getCell(2).setCellValue((String) userMesMapList.get(i).get("number"));
            row.getCell(3).setCellValue( (String)userMesMapList.get(i).get("matchname"));
            row.getCell(4).setCellValue( (String)userMesMapList.get(i).get("matchproject"));
            row.getCell(5).setCellValue((String) userMesMapList.get(i).get("matchtype"));
            row.getCell(6).setCellValue((String) userMesMapList.get(i).get("workname"));
            row.getCell(7).setCellValue(String.valueOf(userMesMapList.get(i).get("date")) );
            row.getCell(8).setCellValue((String) userMesMapList.get(i).get("email"));
            row.getCell(9).setCellValue((String) userMesMapList.get(i).get("school"));
            row.getCell(10).setCellValue( (String)userMesMapList.get(i).get("major"));
            row.getCell(11).setCellValue( (String)userMesMapList.get(i).get("phone"));

        }
        try {
            wb.write(new FileOutputStream(excelPath));
            wb.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
    * 解析 课程管理员已得分作品的数据
    * */
    public static boolean readCourseAllScoreWorksMes(List<WorksPF> worksMesList,String excelPath){
        log.info("正在生成已得分作品列表");
        //xlsx
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("userMes");  //创建table工作薄
        //行对象
        XSSFRow row;
        row = sheet.createRow(0);
        for (int i = 0; i < 15; i++) {
            row.createCell(i);
        }
        row.getCell(0).setCellValue("竞赛名称");
        row.getCell(1).setCellValue("竞赛项目");
        row.getCell(2).setCellValue("竞赛类别");
        row.getCell(3).setCellValue("作品名称");
        row.getCell(4).setCellValue("团队负责人（个人）");
        row.getCell(5).setCellValue("指导老师");
        row.getCell(6).setCellValue("作品提交时间");
        row.getCell(7).setCellValue("学号");
        row.getCell(8).setCellValue("选手邮箱");
        row.getCell(9).setCellValue("选手所在学校");
        row.getCell(10).setCellValue("选手专业");
        row.getCell(11).setCellValue("联系电话");
        row.getCell(12).setCellValue("评委");
        row.getCell(13).setCellValue("分数");
        row.getCell(14).setCellValue("评语");
        int count = 0;
        for(int i = 0; i < worksMesList.size(); i++) {

            List<Map<String,Object>> judgeMapList = worksMesList.get(i).getJudge();
            for (int j = 0; j <judgeMapList.size() ; j++) {

                //创建表格行
                row = sheet.createRow(count+1);
                count++;
                //创建各行单元
                for (int a = 0; a < 15; a++) {
                    row.createCell(a);
                }
                row.getCell(0).setCellValue( worksMesList.get(i).getMatch_name());
                row.getCell(1).setCellValue( worksMesList.get(i).getMatch_project());
                row.getCell(2).setCellValue( worksMesList.get(i).getType());
                row.getCell(3).setCellValue( worksMesList.get(i).getWorksname());
                row.getCell(4).setCellValue( worksMesList.get(i).getAuthor1_name());
                row.getCell(5).setCellValue( worksMesList.get(i).getTeacher());
                row.getCell(6).setCellValue( worksMesList.get(i).getDate());
                row.getCell(7).setCellValue( worksMesList.get(i).getNumber());
                row.getCell(8).setCellValue( worksMesList.get(i).getEmail());
                row.getCell(9).setCellValue( worksMesList.get(i).getSchool());
                row.getCell(10).setCellValue( worksMesList.get(i).getMajor());
                row.getCell(11).setCellValue( worksMesList.get(i).getPhone());
                row.getCell(12).setCellValue( (String)judgeMapList.get(j).get("judge"));
                row.getCell(13).setCellValue( (Double) judgeMapList.get(j).get("score"));
                row.getCell(14).setCellValue( (String)judgeMapList.get(j).get("estimate"));
            }
        }
        try {
            wb.write(new FileOutputStream(excelPath));
            wb.close();
            log.info("已得分作品数据列表excel生成成功");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("已得分作品数据列表excel生成失败");
            return false;
        }
    }






    /**
     * 用于解析 评分表.xlsx
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readScoreExcelData(FileInputStream inputStream) throws IOException {

        //解析excel
       // POIFSFileSystem pSystem=new POIFSFileSystem(inputStream);
        //获取整个excel
        XSSFWorkbook hb=new XSSFWorkbook(inputStream);
        //获取第一个表单sheet
        XSSFSheet sheet=hb.getSheetAt(0);
        //获取第一行  从第1行开始
        int firstrow=sheet.getFirstRowNum();
        //获取最后一行
        int lastrow=sheet.getLastRowNum();
        //合并单元格的数量
        int cellCount=0;
        int i=firstrow+4;
        int temp=0;
        //输出的html字符串
        String htmlModel="";
        while (i<lastrow){
            htmlModel+="<tr>";
            Row row = sheet.getRow(i);
            //维度列
            Cell cell1 = row.getCell(0);
            String rd = cell1.getStringCellValue();
            htmlModel+="<td>"+rd+"</td>";
            //比例列
            Cell cell2 = row.getCell(1);
            String bl = cell2.getStringCellValue();
            htmlModel+="<td width='120px'>"+bl+"</td>";
            //指标列
              //获取单元格的合并数量
            int rowNum = POIExcelUtil.getMergeRowNum(cell1, sheet);
            htmlModel+="<td style='font-size:16px'>";
            temp=i;
            for (int j=0;j<rowNum;j++){

                htmlModel+="<div>";
                //获取当前指标列的行
                Row zbrow = sheet.getRow(temp);
                Cell cell3 = zbrow.getCell(2);
                String zb = cell3.getStringCellValue();
                htmlModel+=zb;
                if (j==rowNum-1){
                    htmlModel+="</div>";
                }else{
                    htmlModel+="</div><br />";
                }
                temp+=1;
            }
            htmlModel+="</td>";
            //分值列
            htmlModel+="<td width='60px' class='score_number' >";
            temp=i;
            for (int j=0;j<rowNum;j++){
                htmlModel+="<div flag="+j+">";
                //获取当前指标列的行
                Row fzrow = sheet.getRow(temp);
                Cell cell3 = fzrow.getCell(3);
                String fz = String.valueOf(cell3.getNumericCellValue());
                htmlModel+=fz;
                if (j==rowNum-1){
                    htmlModel+="</div>";
                }else{
                    htmlModel+="</div><br />";
                }
                temp+=1;
            }
            htmlModel+="</td>";
            //评委评分列
            htmlModel+="<td>";
            temp=i;
            for (int j=0;j<rowNum;j++){
                htmlModel+="<div><input class='score' name=a v_value=0 flag="+j+">";
                if (j==rowNum-1){
                    htmlModel+="</div>";
                }else{
                    htmlModel+="</div><br />";
                }
                temp+=1;
            }
            htmlModel+="</td>";
            i+=rowNum;
            htmlModel+="</tr>";

        }

        inputStream.close();
        return htmlModel;
    }

    /**
     * @param cell  当前cell
     * @param sheet 当前sheet
     * @Description: 获取当前cell合并的行数
     * @author: drj
     * @date: 2019/5/22 18:00
     */
    public static int getMergeRowNum(Cell cell, Sheet sheet) {
        int mergeSize = 1;
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress cellRangeAddress : mergedRegions) {
            if (cellRangeAddress.isInRange(cell)) {
                //获取合并的行数
                mergeSize = cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow() + 1;
                break;
            }
        }
        return mergeSize;
    }

    /**
     * @param cell  当前cell
     * @param sheet 当前sheet
     * @Description: 获取合并的列数
     * @author: drj
     * @date: 2019/5/22 17:59
     */
    public static int getMergeColumNum(Cell cell, Sheet sheet) {
        int mergeSize = 1;
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress cellRangeAddress : mergedRegions) {
            if (cellRangeAddress.isInRange(cell)) {
                //获取合并的列数
                mergeSize = cellRangeAddress.getLastColumn() - cellRangeAddress.getFirstColumn() + 1;
                break;
            }
        }
        return mergeSize;
    }



}
