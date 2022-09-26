/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE259_Hard_Coded_Password__passwordAuth_61b.java
Label Definition File: CWE259_Hard_Coded_Password.label.xml
Template File: sources-sink-61b.tmpl.java
*/
/*
 * @description
 * CWE: 259 Hard Coded Password
 * BadSource: hardcodedPassword Set data to a hardcoded string
 * GoodSource: Read data from the console using readLine()
 * Sinks: passwordAuth
 *    BadSink : data used as password in PasswordAuthentication()
 * Flow Variant: 61 Data flow: data returned from one method to another in different classes in the same package
 *
 * */





import java.util.logging.Level;
import java.io.*;
import java.util.Properties;
import java.util.Arrays;

public class CWE259_Hard_Coded_Password__passwordAuth_61b
{
    public String badSource() throws Throwable
    {
        String data;

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

        return data;
    }

    /* goodG2B() - use goodsource and badsink */
    public String goodG2BSource() throws Throwable
    {
        String data;

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

        return data;
    }
}
