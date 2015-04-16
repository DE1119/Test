package test;	

import java.util.ArrayList;
import java.util.List;

import bean.Component;  
	  
import utl.jdbcUtil; 

public class Test_Component {
	
	  
	
	  
 
	        jdbcUtil jUtilTest = new jdbcUtil();  
	        jUtilTest.getConnection();  
	        // sql语句通配符对应的字段  
	        List<Object> params = new ArrayList<Object>();  
	        
	        //删除旧表
	        String dropTable=
	        	"drop table if exists userinfo";
	        jUtilTest.updateDB(dropTable, null);
	        
	        // 创建表  
	        String createTable =  
	            "create table if not exists userinfo (id int primary key auto_increment,type varchar(64) not null,"+
	        "username varchar(64) not null,sex varchar(64) not null,email varchar(64) not null,tel varchar(64) not null,"+
	            		"work_unit varchar(64) not null,pswd varchar(64) not null,note varchar(100) not null)";  
	        jUtilTest.updateDB(createTable, null);  
	          
	        // 插入数据  
	        for (int i = 0; i < 10; i++)  
	        {  
	            String insertOne = "insert into userinfo (type,username,sex,email,tel,work_unit,pswd,note) values (?,?,?,?,?,?,?,?)";  
	            params.clear();  
	            params.add("teacher_"+i);
	            params.add("John_" + i); 
	            params.add("male_"+i);
	            params.add("913570723@qq.com_"+i);
	            params.add("17090072634_"+i);
	            params.add("BUPT_"+i);
	            params.add("hello1992623_" + i);  
	            params.add("credible_"+i);
	            jUtilTest.updateDB(insertOne, params);  
	        }  
	          
	        // 删除一条数据,sql语句对大小写不敏感，delete/Delete/DELETE等均可  
	        String deleteOne = "DeLete from userinfo where username = ?";  
	        params.clear();  
	        params.add("John_0");  
	        jUtilTest.updateDB(deleteOne, params);  
	          
	        // 改变数据  
	        String updateOne = "update userinfo set pswd = ? where username =?";  
	        params.clear();  
	        params.add("new");  
	        params.add("John_1");  
	        jUtilTest.updateDB(updateOne, params);  
	          
	        // 查询一条数据  
	        String findOne = "select * from userinfo where username = ?";  
	        // notice:数据库的列下标是从1开始计数的  
	        params.clear();  
	        params.add("John_2");  
	        Map<String, Object> rstOne = jUtilTest.QueryDB(findOne, params);  
	        System.out.println("-->" + rstOne);  
	          
	        // 查询多条数据  
	        String findMore = "select * from userinfo";  
	        List<Map<String, Object>> rstMore = jUtilTest.QueryMoreDB(findMore, null);  
	        System.out.println("-->" + rstMore);  
	          
	        // 反射查询一条数据  
	        String findOneRefl = "select * from userinfo where username =?";  
	        params.clear();  
	        params.add("John_2");  
	        User userOne = jUtilTest.QueryDBObj(findOneRefl, params, User.class);  
	        System.out.println("-->" + userOne);  
	          
	        // 反射查询多条数据  
	        String findMoreRefl = "select * from userinfo";  
	        List<User> userMore = jUtilTest.QueryDBMoreObj(findMoreRefl, null, User.class);  
	        System.out.println("-->" + userMore);  
	          
	        jUtilTest.releaseJdbc();  
	      
	      
	
}
