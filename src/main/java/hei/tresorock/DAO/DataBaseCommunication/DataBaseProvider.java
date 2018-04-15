package hei.tresorock.DAO.DataBaseCommunication;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataBaseProvider {

    private static MysqlDataSource dataBase;
/*
    public static DataSource getdataBase() {
        if (dataBase == null) {
            dataBase = new MysqlDataSource();
            dataBase.setServerName("dz8959rne9lumkkw.chr7pe7iynqr.eu-west-1.rds.amazonaws.com");
            dataBase.setPort(3306);
            dataBase.setDatabaseName("ho7qiler6fglm8yv");
            dataBase.setUser("lpyjpurrzfa0p8yh");
            dataBase.setPassword("a70ghinfzetl55uf");
        }

        return dataBase;
    }*/

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

/**
    private static MysqlDataSource dataBase;

    public static DataSource getdataBase() {
        if (dataBase == null) {
            dataBase = new MysqlDataSource();
            dataBase.setServerName("localhost");
            dataBase.setPort(3306);
            dataBase.setDatabaseName("tresorockTEST");
            dataBase.setUser("root");
            dataBase.setPassword("root");
        }
        return dataBase;
    }
**/
}
