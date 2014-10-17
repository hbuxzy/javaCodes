package com.ysu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class MysqlBatchInsert {

	public static void main(String[] args) {
		createStudent();
	}

	public static void createStudent() {
		String url = "jdbc:mysql://localhost:3306/ysusoftdemo1?rewriteBatchedStatements=true";
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			String driverClass = "com.mysql.jdbc.Driver";
			Class.forName(driverClass);
			con = DriverManager.getConnection(url,"root","root");
			String sql = "insert into t_student(c_name) values (?)";
			pstm = con.prepareStatement(sql);
			Date startTime = new Date();
			System.out.println("开始插入");
			int insertCount = 100000;
			int submitCount = 0;
			for(int i=0;i<insertCount;i++){
				pstm.setString(1, "c"+String.valueOf(i+1));
				pstm.addBatch();
				if(i%1000 == 0 ){
					submitCount++;
					pstm.executeBatch();
				}
//				pstm.execute();
			}
			pstm.executeBatch();
			submitCount++;
			System.out.println("结束插入");
			System.out.println("提交次数："+submitCount);
			Date endTime = new Date();
			long mills = endTime.getTime() - startTime.getTime();
			
			System.out.println("耗时:"+mills + "毫秒");
			//10,000条数据，从50秒提高到1秒
			//http://elf8848.iteye.com/blog/770032
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(pstm != null){
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
