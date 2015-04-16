package utl;  
  
import java.lang.reflect.Field;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
//DAO 数据访问对象：包含了各种数据库的操作方法

public class jdbcUtil  
{  
    private String url = "jdbc:mysql://10.109.20.238:3306/lab";  
      
    private String username = "root";  
      
    private String password = "root";  
      
    private String driver = "com.mysql.jdbc.Driver";  
      
    private String driver1 = "org.gjt.mm.mysql.Driver";  
     
    // 预定义声明对象，用于操作数据库  
    PreparedStatement pstm;  
      
    // java程序与数据库建立的链接  
    Connection conn;  
      
    // 查询数据库返回的对象  
    ResultSet rs;  
      
    public jdbcUtil(String url, String username, String password)  
    {  
        super();  
        this.url = url;  
        this.username = username;  
        this.password = password;  
        try  
        {  
            // 向驱动管理器注册一个jdbc驱动  
            Class.forName(driver).newInstance();  
            System.out.println("驱动注册成功");  
        }  
        catch (ClassNotFoundException e)  
        {  
            e.printStackTrace();  
        }  
        catch (InstantiationException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IllegalAccessException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
      
    public jdbcUtil()  
    {  
        try  
        {  
            // 向驱动管理器注册一个jdbc驱动  
            Class.forName(driver1);  
            System.out.println("驱动注册成功");  
        }  
        catch (ClassNotFoundException e)  
        {  
            e.printStackTrace();  
        }  
    }  
      
    public void getConnection()  
    {  
        try  
        {  
            conn = DriverManager.getConnection(url, username, password);  
            System.out.println("获取链接成功");  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
    }  
      
    public boolean updateDB(String sql, List<Object> params)  
    {  
        boolean retFlag = false;  
        try  
        {  
            pstm = conn.prepareStatement(sql);  
            // notice:if(params !=null && !params.equals(""))这个要加上，避免params为空  
            if (params != null && !params.equals(""))  
            {  
                for (int i = 0; i < params.size(); i++)  
                {  
                    pstm.setObject(i + 1, params.get(i));  
                }  
            }  
            return pstm.executeUpdate() > 0 ? true : false;  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
          
        return retFlag;  
    }  
      
    public Map<String, Object> QueryDB(String sql, List<Object> params)  
    {  
        Map<String, Object> map = null;  
        try  
        {  
            pstm = conn.prepareStatement(sql);  
            if (params != null && !params.equals(""))  
            {  
                for (int i = 0; i < params.size(); i++)  
                {  
                    pstm.setObject(i + 1, params.get(i));  
                }  
            }  
            rs = pstm.executeQuery();  
            ResultSetMetaData rsMetaData = rs.getMetaData();  
            while (rs.next())  
            {  
                map = new HashMap<String, Object>();  
                for (int i = 0; i < rsMetaData.getColumnCount(); i++)  
                {  
                    String columnName = rsMetaData.getColumnName(i + 1);  
                    Object columnVal = rs.getObject(i + 1);  
                    // 数据库中的值可能为空  
                    if (columnVal == null)  
                    {  
                        columnVal = "";  
                    }  
                    map.put(columnName, columnVal);  
                }  
                  
            }  
        }  
        catch (SQLException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        return map;  
          
    }  
      
    // 这个完全可以代替单个查询  
    public List<Map<String, Object>> QueryMoreDB(String sql, List<Object> params)  
    {  
        List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();  
        try  
        {  
            pstm = conn.prepareStatement(sql);  
            if (params != null && !params.equals(""))  
            {  
                for (int i = 0; i < params.size(); i++)  
                {  
                    pstm.setObject(i + 1, params.get(i));  
                }  
            }  
              
            ResultSet rs = pstm.executeQuery();  
            ResultSetMetaData rsMetaData = rs.getMetaData();  
            while (rs.next())  
            {  
                Map<String, Object> map = new HashMap<String, Object>();  
                for (int i = 0; i < rsMetaData.getColumnCount(); i++)  
                {  
                    String columnName = rsMetaData.getColumnName(i + 1);  
                    Object columnVal = rs.getObject(i + 1);  
                    if (columnVal == null)  
                    {  
                        columnVal = "";  
                    }  
                    map.put(columnName, columnVal);  
                }  
                retList.add(map);  
            }  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        return retList;  
    }  
      
    // 利用反射机制实现数据库的操作  
    public <T> T QueryDBObj(String sql, List<Object> params, Class<T> cls)  
    {  
        T t = null;  
        try  
        {  
            pstm = conn.prepareStatement(sql);  
            if (params != null && !params.equals(""))  
            {  
                for (int i = 0; i < params.size(); i++)  
                {  
                    pstm.setObject(i + 1, params.get(i));  
                }  
            }  
            rs = pstm.executeQuery();  
            ResultSetMetaData rsMetaData = rs.getMetaData();  
            while (rs.next())  
            {  
                t = cls.newInstance();  
                for (int i = 0; i < rsMetaData.getColumnCount(); i++)  
                {  
                    String columnName = rsMetaData.getColumnName(i + 1);  
                    Object columnVal = rs.getObject(i + 1);  
                    // 这个field是类加载器级别的，用于管理其类的属性  
                    Field field = cls.getDeclaredField(columnName);  
                      
                    field.setAccessible(true);  
                    field.set(t, columnVal);  
                }  
            }  
            return t;  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        catch (InstantiationException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IllegalAccessException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        catch (SecurityException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        catch (NoSuchFieldException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        return t;  
    }  
      
    // 利用反射机制实现数据库的操作,知道其cls的结构  
    public <T> List<T> QueryDBMoreObj(String sql, List<Object> params, Class<T> cls)  
    {  
        List<T> rstList = new ArrayList<T>();  
        try  
        {  
            pstm = conn.prepareStatement(sql);  
            if (params != null && !params.equals(""))  
            {  
                for (int i = 0; i < params.size(); i++)  
                {  
                    pstm.setObject(i + 1, params.get(i));  
                }  
            }  
            rs = pstm.executeQuery();  
            ResultSetMetaData rsMetaData = rs.getMetaData();  
            while (rs.next())  
            {  
                T t = cls.newInstance();  
                for (int i = 0; i < rsMetaData.getColumnCount(); i++)  
                {  
                    String columnName = rsMetaData.getColumnName(i + 1);  
                    Object columnVal = rs.getObject(i + 1);  
                    // 这个field是类加载器级别的，用于管理其类的属性  
                    Field field = cls.getDeclaredField(columnName);  
                    // 设置这个属性可以访问  
                    field.setAccessible(true);  
                    field.set(t, columnVal);  
                }  
                rstList.add(t);  
            }  
            return rstList;  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        catch (InstantiationException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IllegalAccessException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        catch (SecurityException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        catch (NoSuchFieldException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        return rstList;  
    }  
      
    public void releaseJdbc()  
    {  
          
        try  
        {  
            //后生成的先释放掉  
            if (rs != null)  
            {  
                rs.close();  
            }  
            if (pstm != null)  
            {  
                pstm.close();  
            }  
            if (conn != null)  
            {  
                conn.close();  
            }  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
          
    }  
}  