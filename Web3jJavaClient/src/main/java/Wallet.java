import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Wallet {
    static String walletPassword = "myPassword";
    static String walletDirectory = "src/main/resources/wallet"; //add your own wallet directory path
    public Wallet() {}

    public static String createWallet() throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, IOException, NoSuchProviderException {
        String walletName = WalletUtils.generateNewWalletFile(walletPassword,
                new File(walletDirectory));
        return walletName;
    }

    public static String getAccountAddress(String walletName) throws CipherException, IOException {
        Credentials credentials = WalletUtils.loadCredentials(walletPassword, walletDirectory + "/" + walletName);
        String accountAddress = credentials.getAddress();System.out.println("Account address: " + accountAddress);
        return  accountAddress;
    }





}
