/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE259_Hard_Coded_Password__driverManager_68a.java
Label Definition File: CWE259_Hard_Coded_Password.label.xml
Template File: sources-sink-68a.tmpl.java
*/
/*
 * @description
 * CWE: 259 Hard Coded Password
 * BadSource: hardcodedPassword Set data to a hardcoded string
 * GoodSource: Read data from the console using readLine()
 * BadSink: driverManager data used as password in database connection
 * Flow Variant: 68 Data flow: data passed as a member variable in the "a" class, which is used by a method in another class in the same package
 *
 * */





import java.util.logging.Level;
import java.io.*;
import java.util.Properties;
import java.util.Arrays;

public class CWE259_Hard_Coded_Password__driverManager_68a extends AbstractTestCase
{
    public static String data;

    public void bad() throws Throwable
    {

        /* FLAW: Set data to a hardcoded string */
        char[] password = new char[0];
        try (InputStream inputStream = new FileInputStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            password = properties.getProperty("password").toCharArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = String.valueOf(password);
        Arrays.fill(password, ' ');

        (new CWE259_Hard_Coded_Password__driverManager_68b()).badSink();
    }

    public void good() throws Throwable
    {
        goodG2B();
    }

    /* goodG2B() - use goodsource and badsink */
    private void goodG2B() throws Throwable
    {

        data = ""; /* init data */

        /* FIX: Read data from the console using readLine() */
        try
        {
            InputStreamReader readerInputStream = new InputStreamReader(System.in, "UTF-8");
            BufferedReader readerBuffered = new BufferedReader(readerInputStream);

            /* POTENTIAL FLAW: Read data from the console using readLine */
            data = readerBuffered.readLine();
        }
        catch (IOException exceptIO)
        {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }

        /* NOTE: Tools may report a flaw here because readerBuffered and readerInputStream are not closed.  Unfortunately, closing those will close System.in, which will cause any future attempts to read from the console to fail and throw an exception */

        (new CWE259_Hard_Coded_Password__driverManager_68b()).goodG2BSink();
    }

    /* Below is the main(). It is only used when building this testcase on
     * its own for testing or for building a binary to use in testing binary
     * analysis tools. It is not used when compiling all the testcases as one
     * application, which is how source code analysis tools are tested.
     */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}
