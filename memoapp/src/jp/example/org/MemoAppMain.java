package jp.example.org;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class MemoAppMain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection con = null;
        Statement smt = null;

        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=JST";
        String user = "root";
        String pass = "nobu112";

        try {
			con = DriverManager.getConnection(url, user, pass);
			smt = con.createStatement();

			String select_memo = "select * from test.user_master;";

			ResultSet result = smt.executeQuery(select_memo);

			//String first_name = result.getString("first_name");
			//String last_name = result.getString("last_name");

			String first_name = "first_name";
			String last_name = "last_name";

			request.setAttribute("first_name", first_name);
			request.setAttribute("last_name", last_name);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

//        String view = "/WEB-INF/jsp/index.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
//        dispatcher.forward(request, resp
//        		);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String form_title = req.getParameter("title");
        String form_memo = req.getParameter("memo");

        System.out.println("title: " + form_title);
        System.out.println("text: " + form_memo);

        // -- ここにDBへ保存処理 --
        Connection con = null;
        Statement smt = null;

        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=JST";
        // jdbc:mysql://localhost/sample?characterEncoding=UTF-8&serverTimezone=JST
        String user = "root";
        String pass = "nobu112";
        try {
        	//Class.forName("com.mysql.cj.jdbc.Driver");

        	con = DriverManager.getConnection(url, user, pass);
            smt = con.createStatement();
            System.out.println("smt: " + smt);

            String create_table = "create table if not exists memo_data (" +
                    "memo_id INT(11) auto_increment not null comment 'ID'," +
                    "category INT(11) comment 'カテゴリ'," +
                    "title VARCHAR(64) comment 'タイトル'," +
                    "memo TEXT comment 'メモ'," +
                    "create_date DATETIME comment '作成日'," +
                    "modified_date DATETIME comment '更新日'," +
                    "primary key (memo_id)" + ")";
            smt.executeUpdate(create_table);

            String insert_memo = "insert into memo_data (" +
                    "category, title, memo, create_date, modified_date" +
                ") values (" +
                    "0," +
                    "'" + form_title + "'," +
                    "'" + form_memo + "'," +
                    "cast(now() as datetime)," +
                    "cast(now() as datetime) " +
                ");";
            smt.executeUpdate(insert_memo);

            System.out.println("smt: " + smt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect(".");
    }

}

