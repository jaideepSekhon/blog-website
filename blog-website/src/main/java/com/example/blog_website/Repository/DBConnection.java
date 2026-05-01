package com.example.blog_website.Repository;

import com.example.blog_website.Entity.BlogEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DBConnection {
    private static final  String URL="jdbc:mysql://localhost:3306/spring_blog";
    private static final  String USERNAME="root";
    private static final  String PASSWORD="2004";

    Connection connection;
    DBConnection() throws SQLException
    {
        connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        System.out.println("Database connection established");
    }

    public Connection getConnection(){
        return connection;
    }

//    public ArrayList<BlogEntity> addBlog(String title, String content){
//
//    }

    public String addBlog(String title,String content)
    {
        String sql="Insert into blog(title,content) values(?,?)";
        try(PreparedStatement pstmt=connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);

            pstmt.executeUpdate();
        } catch(SQLException e){
            return "Failed to add blog";
        }

        return "Blog Added Successfully";
    }

    public ArrayList<BlogEntity> getBlog() {
        ArrayList<BlogEntity> list=new ArrayList<>();
        String sql="select * from blog";
        try(PreparedStatement pstmt=connection.prepareStatement(sql)){
//            pstmt.executeQuery();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                String title=rs.getString("title");
                String content=rs.getString("content");
                BlogEntity blog=new  BlogEntity();
                blog.setTitle(title);
                blog.setContent(content);
                list.add(blog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public String updateBlog(int index, String title, String content) {
        String sql="Update blog set title=?,content=? where id=?";
        try(PreparedStatement pstmt=connection.prepareStatement(sql)){
            pstmt.setString(1,title);
            pstmt.setString(2,content);
            pstmt.setInt(3,index);
            pstmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            return "Failed to update record";
        }
        return "Blog Updated Successfully";
    }

    public String deleteBlog(int index) {
        String sql="Delete from blog where id=?";
        try(PreparedStatement pstmt=connection.prepareStatement(sql)){
            pstmt.setInt(1,index);
            pstmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            return "Failed to delete record";
        }

        return "Blog Delete Successfully";
    }
}
