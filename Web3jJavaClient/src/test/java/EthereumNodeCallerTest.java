

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Uint;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EthereumNodeCallerTest {

    static EthereumNodeCaller api;

    @BeforeEach
    void setUp() {
        api = new EthereumNodeCaller();

        try {
            api.DEFAULT_ADDRESS = api.getEthAccounts().getAccounts().get(0); //Select account on BC
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getEthAccountsTest() {
        try {
           EthAccounts accounts = api.getEthAccounts();
           assertNotEquals(accounts, null);
           System.out.println(accounts.getAccounts());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getBlockNumberTest() {
        try {
            EthBlockNumber blockNum = api.getBlockNumber();
            assertNotEquals(blockNum, null);
            System.out.println(blockNum.getBlockNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getEthereumClientVersionTest() {
        try {
            String ethNodeVersion = api.getEthereumClientVersion();
            System.out.println(ethNodeVersion);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//------------------------------------- Counter contract client side tests-------------------------
    @Test
    void getCounterTest() {
        try {
            List<Uint> count = api.getCounter();
            assertNotEquals(count.get(0), null);
            System.out.println(count.get(0).getValue());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void incrementCounterTest() {
        try {
            String transHash = api.incrementCounter();
            assertNotEquals(transHash, null);
            System.out.println(transHash);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void decrementCounterTest() {
        try {
            String transHash = api.decrementCounter();
            assertNotEquals(transHash, null);
            System.out.println(transHash);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}