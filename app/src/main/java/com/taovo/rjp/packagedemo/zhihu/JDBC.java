package com.taovo.rjp.packagedemo.zhihu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gimpo create on 2017/10/9 12:23
 * @email : jimbo922@163.com
 */

public class JDBC {
    public static final String HOST = "https://www.zhihu.com/people/";
    public static List<String> userNames = new ArrayList<>();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private static JDBC jdbc;

    /**
     * 写一个连接数据库的方法
     */
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/zhihu";
        String userName = "root";
        String password = "123456";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动！");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("connection successful");
            }
        } catch (SQLException e) {
            System.out.println("connection fail");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 写一个查询数据库语句的方法
     */
    public void QuerySql() {
        //1、执行静态SQL语句。通常通过Statement实例实现。
        // 2、执行动态SQL语句。通常通过PreparedStatement实例实现。
        // 3、执行数据库存储过程。通常通过CallableStatement实例实现。
        System.out.println("query");
        ZhiHuInfo u;
//        j.Connection();
        String sql = "select * from articles";
        try {
            conn = getConnection();//连接数据库
            ps = conn.prepareStatement(sql);// 2.创建Satement并设置参数
            rs = ps.executeQuery(sql);  // 3.ִ执行SQL语句
            // 4.处理结果集
            while (rs.next()) {
                u = new ZhiHuInfo();
                u.setUserId(rs.getString("userId"));
                u.setUserName(rs.getString("userName"));
                u.setFollwed(rs.getString("follwed"));
                u.setFollwer(rs.getString("follwer"));
                u.setZan(rs.getString("zan"));
                u.setLinkUrl(rs.getString("linkUrl"));
                u.setQuestion(rs.getString("question"));
                u.setAnswer(rs.getString("answer"));
                System.out.println("---->" + u.getUserName());
            }
            System.out.println("---->" + rs.next());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("null")
    public int add(ZhiHuInfo user) {
        ZhiHuInfo u = new ZhiHuInfo();
        int row = 0;
//        j.Connection();
        String sql = "insert into articles(userId,userName,follwed,follwer,zan,linkUrl,question,answer) values(?,?,?,?,?,?,?,?)";
        try {
            conn = getConnection();//连接数据库
            ps = conn.prepareStatement(sql);// 2.创建Satement并设置参数
//            rs=ps.executeQuery();  // 3.ִ执行SQL语句,緊緊用于查找語句
            //sql語句中寫了幾個字段，下面就必須要有幾個字段
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFollwed());
            ps.setString(4, user.getFollwer());
            ps.setString(5, user.getZan());
            ps.setString(6, user.getLinkUrl());
            ps.setString(7, user.getQuestion());
            ps.setString(8, user.getAnswer());
            // 4.处理结果集
            row = ps.executeUpdate();
            System.out.println(row + user.getUserName() + user.getZan());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return row;
    }

    /**
     * @return修改数据
     */
    public int update(ZhiHuInfo user) {
        ZhiHuInfo u;
        int row = 0;
//        j.Connection();
        String sql = "update articles set userName=?,follwed=?,follwer=?,zan=?,linkUrl=?,question=?,answer=? where userId=?";
        try {
            conn = getConnection();//连接数据库
            ps = conn.prepareStatement(sql);// 2.创建Satement并设置参数
//            rs=ps.executeQuery(sql);  // 3.ִ执行SQL语句,緊緊用于查找語句
            //sql語句中寫了幾個字段，下面就必須要有幾個字段
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getFollwed());
            ps.setString(3, user.getFollwer());
            ps.setString(4, user.getZan());
            ps.setString(5, user.getLinkUrl());
            ps.setString(6, user.getQuestion());
            ps.setString(7, user.getAnswer());
            ps.setString(8, user.getUserId());
            // 4.处理结果集
            row = ps.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //释放资源
            try {
//                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return row;
    }

    /**
     * @return删除操作
     */
    public int delete(ZhiHuInfo user) {
        ZhiHuInfo u;
        int row = 0;
//        j.Connection();
        String sql = "delete from articles where userId=? and userName=?";
        try {
            conn = getConnection();//连接数据库
            ps = conn.prepareStatement(sql);// 2.创建Satement并设置参数
//            rs=ps.executeQuery(sql);  // 3.ִ执行SQL语句,緊緊用于查找語句
            //sql語句中寫了幾個字段，下面就必須要有幾個字段
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUserName());
            // 4.处理结果集
            row = ps.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //释放资源【執行完sql要記得釋放資源】
            try {
//                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return row;
    }

    public static void main(String[] args) {
        jdbc = new JDBC();
//        j.getConnection();
//        jdbc.QuerySql();//在控制台顯示出查找方法
//        UserInfo u=new UserInfo();
//        u.setUserId(5);
//        u.setUserName("cool");
//        u.setPassword("123abc");
//        j.update(u);////在控制台顯示出修改方法
//        NetUtils.get();
        //从一个用户入手
        searchUser("zhang-jia-wei");
    }

    private static void searchUser(String enter) {
        if(userNames.contains(enter)){
            return ;
        }else{
            userNames.add(enter);
        }
        int pageCount = getPage(HOST + enter + "/answers");
        for (int i = 1; i <= pageCount; i++) {
            getAnswer(HOST + enter, i);
        }
        int pageCount1 = getPage(HOST + enter + "/following");
        for (int i = 0; i < pageCount1; i++) {
            getFollowing(HOST + enter, i);
        }
        int pageCount2 = getPage(HOST + enter + "/followers");
        for (int i = 0; i < pageCount2; i++) {
            getFollower(HOST + enter, i);
        }
    }

    private static void getFollower(String url, int page) {
        String htmlContent = NetUtils.get(url + "/followers?page=" + page);
        List<String> childStrList = getChildStrList(htmlContent, "<a class=\"UserLink-link\" data-za-detail-view-element_name=\"User\" target=\"_blank\" href=\"/people/\\S+\">.*?</a>");
        for (int i = 0; i < childStrList.size(); i++) {
            String nameStr = childStrList.get(i);
            String childStr = getChildStr(nameStr, "<a class=\"UserLink-link\" data-za-detail-view-element_name=\"User\" target=\"_blank\" href=\"/people/(\\S+)\">.*?</a>", 1);
            searchUser(childStr);
        }
    }

    private static void getFollowing(String url, int page) {
        String htmlContent = NetUtils.get(url + "/following?page=" + page);
        List<String> childStrList = getChildStrList(htmlContent, "<a class=\"UserLink-link\" data-za-detail-view-element_name=\"User\" target=\"_blank\" href=\"/people/\\S+\">.*?</a>");
        for (int i = 0; i < childStrList.size(); i++) {
            String nameStr = childStrList.get(i);
            String childStr = getChildStr(nameStr, "<a class=\"UserLink-link\" data-za-detail-view-element_name=\"User\" target=\"_blank\" href=\"/people/(\\S+)\">.*?</a>", 1);
            searchUser(childStr);
        }
    }

    private static void getAnswer(String url, int page) {
        String htmlContent = NetUtils.get(url + "/answers?page=" + page);
//        List<String> authorList = getChildStrList(htmlContent, "<a class=\"UserLink-link\" data-za-detail-view-element_name=\"User\" href=\"/people/\\S+\">.*?</a>");
        List<String> childStrList = getChildStrList(htmlContent, "<a target=\"_blank\" data-za-detail-view-element_name=\"Title\" href=\"/question/\\d+/answer/\\d+\">.*?</a>");
        List<String> childStrList1 = getChildStrList(htmlContent, "<button class=\"Button Button--plain\" type=\"button\">(\\d*) 人赞同了该回答?</button>");
        for (int i = 0; i < childStrList1.size(); i++) {
            String zanStr = childStrList1.get(i);
            String childStr = getChildStr(zanStr, "<button class=\"Button Button--plain\" type=\"button\">(\\d*) 人赞同了该回答</button>", 1);
            String titleStr = childStrList.get(i);
            String childStr1 = getChildStr(titleStr, "<a target=\"_blank\" data-za-detail-view-element_name=\"Title\" href=\"/question/\\d+/answer/\\d+\">(.*)?</a>", 1);
            String urlStr = getChildStr(titleStr, "<a target=\"_blank\" data-za-detail-view-element_name=\"Title\" href=\"(/question/\\d+/answer/\\d+)\">(.*)?</a>", 1);
//            String authorStr = authorList.get(i);
//            String childStr2 = getChildStr(authorStr, "<a class=\"UserLink-link\" data-za-detail-view-element_name=\"User\" href=\"/people/\\S+\">(.*)?</a>", 1);
            System.out.println(urlStr + ":"  + childStr1 + "----------->" + childStr);
            if(Long.parseLong(childStr) > 1000) {
                ZhiHuInfo zhiHuInfo = new ZhiHuInfo();
                zhiHuInfo.setUserId(urlStr);
                zhiHuInfo.setUserName(urlStr);
                zhiHuInfo.setLinkUrl(urlStr);
                zhiHuInfo.setZan(childStr);
                zhiHuInfo.setQuestion(childStr1);
                jdbc.add(zhiHuInfo);
            }
        }
    }

    /**
     * 获取回答的总页数
     *
     * @param url
     * @return
     */
    public static int getPage(String url) {
        String htmlContent = NetUtils.get(url);
        //获取页码
        String regStr = "<button class=\"Button PaginationButton Button--plain\" type=\"button\">\\d*</button>";
        String childStr = getChildStr(htmlContent, regStr, 0);
        String childStr1 = getChildStr(childStr, "\\d+", 0);
        try {
            return Integer.parseInt(childStr1);
        }catch (Exception e){
            return 1;
        }
    }

    /**
     * 正则截取需要的字符串
     *
     * @param content
     * @param regStr
     * @return
     */
    public static String getChildStr(String content, String regStr, int pos) {
        String group = "";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            group = matcher.group(pos);
        }
        return group;
    }

    /**
     * 正则截取需要的字符串
     *
     * @param content
     * @param regStr
     * @return
     */
    public static List<String> getChildStrList(String content, String regStr) {
        List<String> group = new ArrayList<>();
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String group1 = matcher.group();
            group.add(group1);
        }
        return group;
    }
}
