/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE259_Hard_Coded_Password__kerberosKey_01.java
Label Definition File: CWE259_Hard_Coded_Password.label.xml
Template File: sources-sink-01.tmpl.java
*/
/*
* @description
* CWE: 259 Hard Coded Password
* BadSource: hardcodedPassword Set data to a hardcoded string
* GoodSource: Read data from the console using readLine()
* BadSink: kerberosKey data used as password in KerberosKey()
* Flow Variant: 01 Baseline
*
* */





import java.util.logging.Level;
import java.io.*;

import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KerberosKey;
import java.util.Properties;
import java.util.Arrays;

public class CWE259_Hard_Coded_Password__kerberosKey_01 extends AbstractTestCase
{
    /* uses badsource and badsink */
    public void bad() throws Throwable
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

        if (data != null)
        {
            KerberosPrincipal principal = new KerberosPrincipal("test");
            /* POTENTIAL FLAW: data used as password in KerberosKey() */
            KerberosKey key = new KerberosKey(principal, data.toCharArray(), null);
            IO.writeLine(key.toString());
        }

    }

    public void good() throws Throwable
    {
        goodG2B();
    }

    /* goodG2B() - uses goodsource and badsink */
    private void goodG2B() throws Throwable
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

        if (data != null)
        {
            KerberosPrincipal principal = new KerberosPrincipal("test");
            /* POTENTIAL FLAW: data used as password in KerberosKey() */
            KerberosKey key = new KerberosKey(principal, data.toCharArray(), null);
            IO.writeLine(key.toString());
        }

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

