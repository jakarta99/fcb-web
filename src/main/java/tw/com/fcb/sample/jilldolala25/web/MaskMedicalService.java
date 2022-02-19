package tw.com.fcb.sample.jilldolala25.web;


import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaskMedicalService {
    List<MaskMedical> maskList ;
    MaskMedicalRepository maskMedicalRepository;

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    String updateDate = now.format(formatter);



    public List<MaskMedical> getAll() throws SQLException {
        MaskMedicalRepository maskMedicalRepository = new MaskMedicalRepository();
        maskList = maskMedicalRepository.findALl();
        return maskList;
    }
    public MaskMedical findById(Long id) throws SQLException {
        MaskMedicalRepository maskMedicalRepository = new MaskMedicalRepository();
          MaskMedical maskMedical = maskMedicalRepository.getById(id);
          return maskMedical;
    }
    public List<MaskMedical> runCrud() throws SQLException {

            MaskMedical maskMedical = setMaskMedical();
            // findAll
            maskList = maskMedicalRepository.findALl();
//            System.out.println("新增資料前，資料庫資料總共 = " + maskList.size() + "筆");
//            // insert資料
//            maskMedicalRepository.insertOneRecord(maskMedical);
//            maskList = maskMedicalRepository.findALl();
//           System.out.println("新增資料後，資料庫資料總共 = " + maskList.size() + "筆");
//            // updateByKey  更新日期
//            System.out.println("資料更新前 = "+ maskMedical);
//            maskMedicalRepository.updateDateByKey(maskMedical.getId(), updateDate);
//            MaskMedical maskRs = maskMedicalRepository.getById(maskMedical.getId());
//            System.out.println("資料更新後 = "+ maskRs);
//
//            //deleteByKey
//            maskMedicalRepository.deleteByKey(maskRs.getId());
//            System.out.println("已刪除資料 = " + maskRs);
//            maskList = maskMedicalRepository.findALl();
//            System.out.println("刪除資料後總共 = " + maskList.size()+ "筆");
        return maskList;
    }
    public void LoadMedicalFile()  {
        String inputFile = "c:\\data\\maskdata.csv";

        maskMedicalRepository = new MaskMedicalRepository();

        try ( Connection conn = maskMedicalRepository.getConnection()){
            BufferedReader fileInputStream = new BufferedReader(new FileReader(inputFile));
            String lineData;
            maskList = new ArrayList<>();


            // insert前先將資料清空
            maskMedicalRepository.deleteAll(conn);

            int count = 0;
            while ((lineData = fileInputStream.readLine()) != null) {
                count++;
                if (count == 1) continue;

                String[] data = lineData.split(",");

                MaskMedical maskMedical = new MaskMedical();
                maskMedical.setMedicalcode(data[0]);
                maskMedical.setMedicalname(data[1]);
                maskMedical.setMedicaladdress(data[2]);
                maskMedical.setMedicalphone(data[3]);
                maskMedical.setAldultcount(Integer.parseInt(data[4]));
                maskMedical.setKidscount(Integer.parseInt(data[5]));
                maskMedical.setDate(data[6]);
                maskMedical.setMaskMedicalEnum(MaskMedicalEnum.valueOf(data[7]));

                // add to list
                maskList.add(maskMedical);

                //insert into db
                maskMedicalRepository.insert(maskMedical,conn);

            }
            System.out.println("資料庫內目前共有"+ maskMedicalRepository.count(conn)+"筆 健保特約機構");
            // close db connection
            conn.close();
            System.out.println("目前共有"+ maskList.size()+"筆 健保特約機構");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public MaskMedical  setMaskMedical(){
        MaskMedical maskMedical = new MaskMedical();
        maskMedical.setMedicalcode("2331200010");
        maskMedical.setMedicalname("新北市坪林區衛生所");
        maskMedical.setMedicaladdress("新北市坪林區坪林街１０４號");
        maskMedical.setMedicalphone("(02)26656272");
        maskMedical.setAldultcount(1410);
        maskMedical.setKidscount(1440);
        maskMedical.setDate("2022/2/8");
        maskMedical.setMaskMedicalEnum(MaskMedicalEnum.valueOf("CHAIN"));
        return maskMedical;
    }

    public void FileWriter() throws IOException {

        FileWriter fileWriter = new FileWriter("c:\\data\\outputFile.txt",false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

//        for (int i = 0; i < maskList.size(); i++) {
//            if (i == 0){
//                bufferedWriter.write("來源資料時間" + ","+ "醫事機構代碼" + ","+ "醫事機構名稱"+ "," + "醫事機構地址"+ "," + "醫事機構電話"+ "," + "成人口罩剩餘數" + ","+ "兒童口罩剩餘數");
//                bufferedWriter.newLine();
//            }
//            String resultSet =  ListFormat(i);
//            System.out.println(resultSet);
//            bufferedWriter.write(resultSet);
//            bufferedWriter.newLine();
//
//        }
//        bufferedWriter.close();
//從資料庫讀出，再寫檔

        try {
            System.out.print("請輸入醫事機構代碼 : ");
            Scanner scanner = new Scanner(System.in);
            var medicalcode = scanner.next();
            List<MaskMedical> result = maskMedicalRepository.getByMedicalCode(medicalcode);
            for (int i = 0; i < result.size(); i++) {
                if (i == 0){
                bufferedWriter.write("來源資料時間" + ","+ "醫事機構代碼" + ","+ "醫事機構名稱"+ "," + "醫事機構地址"+ "," + "醫事機構電話"+ "," + "成人口罩剩餘數" + ","+ "兒童口罩剩餘數");
                bufferedWriter.newLine();}

                String resultSet = result.get(i).getDate() + ","
                        + result.get(i).getMedicalcode() + ","
                        + result.get(i).getMedicalname() + ","
                        + result.get(i).getMedicaladdress() + ","
                        + result.get(i).getAldultcount() + ","
                        + result.get(i).getKidscount();
                System.out.println(resultSet);
                bufferedWriter.write(resultSet);
                bufferedWriter.newLine();
            } {
                System.out.println("所輸入之醫事機構代碼之查詢結果筆數共 " +result.size()+ "筆，" +" 輸出之檔案筆數共" + result.size() + "筆" );

        }
        bufferedWriter.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String ListFormat(int i){
        String outPutFormat = maskList.get(i).getDate().substring(0,10)+ "," +
                maskList.get(i).getMedicalcode()+","+
                maskList.get(i).getMedicalname() + ","+
                maskList.get(i).getMedicaladdress()+ "," +
                maskList.get(i).getMedicalphone() + "," +
                maskList.get(i).getAldultcount() + "," +
                maskList.get(i).getKidscount() + "," ;


        return outPutFormat;
    }

    public void FindMedicalType() throws SQLException {
        System.out.print("請輸入查詢之醫事機構代碼 : ");
        Scanner scanner = new Scanner(System.in);
        var medicalcode = scanner.next();
        maskMedicalRepository.getBySpecifyMedicalCode(medicalcode);
    }
}
