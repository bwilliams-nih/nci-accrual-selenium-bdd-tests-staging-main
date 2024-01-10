package gov.nci.ctrp.accrual.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PgSqlConnOverSSH {
    public static void main(String[] args) throws SQLException {

        int lport = 3370;
        String t_host = "transporter-int.biadcloud.com";
        String r_host = "ctrp-aurora-cluster.cluster-clb9vkosemwm.us-east-1.rds.amazonaws.com";
        int rport = 5432;
        //TODO: Remove password and ID both
        String t_user = "patelvir"; // Enter your own  db username
        String password = ""; // Enter your own db password
        String url = "jdbc:postgresql://localhost:" + lport + "/pa_ctrpn";
        String driverName = "org.postgresql.Driver";
        Connection conn = null;
        Session session = null;
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            jsch.addIdentity("key"+ File.separator+ "privatekey");
            session = jsch.getSession(t_user, t_host, 22);
            //session.setPassword(password);
            session.setConfig(config);
            session.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
            session.connect(120000);
            System.out.println("Connected");
            int assinged_port = session.setPortForwardingL(lport, r_host, rport);
            System.out.println("localhost:" + assinged_port + " -> " + r_host + ":" + rport);
            System.out.println("Port Forwarded");

            //mysql database connectivity
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection(url, t_user, password);
            System.out.println("Database connection established");
            System.out.println("DONE");
            ResultSet rs = conn.createStatement().executeQuery("select * from arm limit 10");
            while(rs.next()){
                String s = rs.getString("name");
                System.out.println(s);
            }
            //System.out.println(rs.getArray(2));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Closing Database Connection");
                conn.close();
            }
            if (session != null && session.isConnected()) {
                System.out.println("Closing SSH Connection");
                session.disconnect();
            }
        }
    }
}
