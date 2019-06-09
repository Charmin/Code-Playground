package misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitra.kr on 31/10/18.
 */
public class ConnectionPoolTest {
    public static class MyConnectionPool implements ConnectionPool {

        public MyConnectionPool(List<Connection> connections) {
            // connections are initially open and we don't need to close these connection as long as our MyConnectionPool
            // objectis alive. We would just reuse these connections. When the client requesting for connections has done
            // it's work, it'll call connection.close() and that should return the connection to our pool.

            // implement
        }

        @Override
        public Connection getConnection() {
            // implement
            // 1st part Return null if connections are not available.
            // 2nd part Wait till the connection is not available.
            return null;
        }
    }

    public interface ConnectionPool {
        Connection getConnection();
    }

    public interface Connection {
        void close();

        // this is the least interesting part to this problem
        Object execute(Object query);
    }

    public static void main(String[] args) {
        //typical usage of this.
        List<Connection> connections = new ArrayList<>();
        MyConnectionPool myConnectionPool = new MyConnectionPool(connections);
        Connection connection = myConnectionPool.getConnection();
        connection.execute("");
        connection.close();
    }
}
