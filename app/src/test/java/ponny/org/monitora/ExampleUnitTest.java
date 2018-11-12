package ponny.org.monitora;

import android.util.Log;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import ponny.org.monitora.utils.CipherMonitora;
import ponny.org.monitora.utils.Utils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
       String date = Utils.getPrettyDateFromString("2018-11-12T13:50:08.279Z");
       System.out.print("RESULTADO"+date);
    }
}