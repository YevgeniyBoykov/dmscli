package com.bjen.dmscli;
import java.sql.*;

public class DBWorker {
    DBWorker(String connectUrl, String user, String passwd) {
        String sqlQuery = "select ds.dnsname, ds.dnshostname, ds.dnsip, dd.name_dnsdislocations, dst.dnsstatus \n" +
                "    from dmscli.dnsservers ds, dmscli.dnsdislocations dd, dmscli.dnsstatus dst\n" +
                " where ds.id_dnsdislocation = dd.id_dnsdislocation and ds.id_dnsstatus = dst.id_dnsstatus\n" +
                " order by 1;";

        Statement stmt = null;
        ResultSet rs = null;
        loadDriver();
        try {
            Connection connection = DriverManager.getConnection(connectUrl, user, passwd);
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            printResult(rs);
        }catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        }
    }

    void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            System.out.println("MySQL JDBC Driver is not found");
            ex.printStackTrace();
        }
    }

    void printResult(ResultSet rs) throws SQLException {
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("%10s", "| dnsname ");
        System.out.printf("%14s", "| dnshostname ");
        System.out.printf("%-16s", "| dnsip ");
        System.out.printf("%23s", "| name_dnsdislocations ");
        System.out.printf("%10s%n", "| dnsstatus |");
        System.out.println("----------------------------------------------------------------------------");
        while(rs.next()){
            System.out.printf("%-10s", "| "+ rs.getString( "dnsname"));
            System.out.printf("%-14s", "| " + rs.getString("dnshostname"));
            System.out.printf("%-16s", "| " + rs.getString("dnsip"));
            System.out.printf("%-23s", "| " + rs.getString("name_dnsdislocations"));
            System.out.printf("%-12s", "| " + rs.getString("dnsstatus"));
            System.out.printf("%1s%n", "|");
        }
        System.out.println("----------------------------------------------------------------------------");
    }

}
