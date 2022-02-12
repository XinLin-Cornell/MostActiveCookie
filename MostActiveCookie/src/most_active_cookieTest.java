import org.junit.jupiter.api.Test;

class most_active_cookieTest {

    /** Given valid file and date, output the most active cookie */
    @Test
    void test1() {
        most_active_cookie.main(new String[] { "src/cookie_log.csv", "-d", "2018-12-09" });
    }

    /** Given valid file and date, if multiple cookies meet that criteria output all of them */
    @Test
    void test2() {
        most_active_cookie.main(new String[] { "src/cookie_log.csv", "-d", "2018-12-08" });
    }

    /** If no cookie meet that criteria, output the error message */
    @Test
    void test3() {
        most_active_cookie.main(new String[] { "src/cookie_log.csv", "-d", "2018-12-25" });
    }

    /** If size of input is incorrect, output the error message */
    @Test
    void test4() {
        most_active_cookie.main(new String[] { "src/cookie_log.csv", "-d", "2018-12-25", "1" });
    }

}
