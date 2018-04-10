package hei.tresorock.DAO.DataBaseCommunication;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataBaseProvider {

    private static MysqlDataSource dataBase;

    public static DataSource getdataBase() {
        if (dataBase == null) {
            dataBase = new MysqlDataSource();
            dataBase.setServerName("localhost");
            dataBase.setPort(3306);
            dataBase.setDatabaseName("projet100h");
            dataBase.setUser("root");
            dataBase.setPassword("root");
        }

        return dataBase;
    }
}
