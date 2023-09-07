import NewYearGifts.DataBaseConnection;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;
import org.mockito.*;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class DataBaseTest {
    @Test
    public void ConnectionExceptionTest() {
        DataBaseConnection connection = new DataBaseConnection();

        Assertions.assertThrows(SQLException.class, connection::Connect);
    }
}
