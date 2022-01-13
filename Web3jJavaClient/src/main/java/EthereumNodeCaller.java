import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;


/*
  Handles Java RPC calls to the local Ethereum block chain
 */
public class EthereumNodeCaller {
        Web3j myEthereumNode = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        String DEFAULT_ADDRESS = "0x1De6d9d1b1134bB805ada6535478C51E3D5ea39F";
        String CONTRACT_ADDRESS = "0xD5a7c8cC62b381596afE2859fe36a8eFA68360Fb";

        /*
          Retrieve the version and implementation of the Eth local BC
         */
        public String getEthereumClientVersion() throws IOException {
                Web3ClientVersion web3ClientVersion = myEthereumNode.web3ClientVersion().send();
                String clientVersion = web3ClientVersion.getWeb3ClientVersion();
                return clientVersion;
        }


        public EthAccounts getEthAccounts() throws ExecutionException, InterruptedException {
                EthAccounts result = new EthAccounts();
                result = this.myEthereumNode.ethAccounts()
                        .sendAsync()
                        .get();
                return result;
        }

        public EthBlockNumber getBlockNumber() throws ExecutionException, InterruptedException {
                EthBlockNumber result = new EthBlockNumber();
                result = this.myEthereumNode.ethBlockNumber()
                        .sendAsync()
                        .get();
                return result;
        }

        // Counter contract calls ------------------------------------

        public List getCounter() throws ExecutionException, InterruptedException {
                List inputParams = Arrays.asList();
                List outputParams = Arrays.asList(new TypeReference<Uint>() {});
                Function function = new Function("getCount", inputParams, outputParams);
                String encodedFunction = FunctionEncoder.encode(function);


                EthGetTransactionCount ethGetTransactionCount = myEthereumNode.ethGetTransactionCount(
                        DEFAULT_ADDRESS, DefaultBlockParameterName.LATEST).sendAsync().get();

                BigInteger nonce = ethGetTransactionCount.getTransactionCount();
                BigInteger gasprice = BigInteger.valueOf(1000000000);
                BigInteger gaslimit = BigInteger.valueOf(4712388);

                EthCall response = myEthereumNode.ethCall(Transaction
                        .createFunctionCallTransaction(DEFAULT_ADDRESS,
                                nonce, gasprice, gaslimit, CONTRACT_ADDRESS, encodedFunction),
                                DefaultBlockParameterName.LATEST)
                        .sendAsync().get();

                List<Type> someTypes = FunctionReturnDecoder.decode(
                        response.getValue(), function.getOutputParameters());
                return someTypes;
        }

        /*
           Returns transaction hash after incrementing the counter
         */
        public String incrementCounter() throws ExecutionException, InterruptedException {
                List inputParams = Arrays.asList();
                List outputParams = Arrays.asList(new TypeReference<Uint>() {});
                Function function = new Function("increment", inputParams, outputParams);
                String encodedFunction = FunctionEncoder.encode(function);


                EthGetTransactionCount ethGetTransactionCount = myEthereumNode.ethGetTransactionCount(
                        DEFAULT_ADDRESS, DefaultBlockParameterName.LATEST).sendAsync().get();

                BigInteger nonce = ethGetTransactionCount.getTransactionCount();
                BigInteger gasprice = BigInteger.valueOf(1000000000);
                BigInteger gaslimit = BigInteger.valueOf(4712388);

                Transaction ts = Transaction.createFunctionCallTransaction(DEFAULT_ADDRESS,
                                                nonce, gasprice, gaslimit, CONTRACT_ADDRESS, encodedFunction);
                EthSendTransaction response = myEthereumNode.ethSendTransaction(ts).sendAsync().get();
                return response.getTransactionHash();
        }

        public String decrementCounter() throws ExecutionException, InterruptedException {
                List inputParams = Arrays.asList();
                List outputParams = Arrays.asList(new TypeReference<Uint>() {});
                Function function = new Function("decrement", inputParams, outputParams);
                String encodedFunction = FunctionEncoder.encode(function);

                EthGetTransactionCount ethGetTransactionCount = myEthereumNode.ethGetTransactionCount(
                        DEFAULT_ADDRESS, DefaultBlockParameterName.LATEST).sendAsync().get();

                BigInteger nonce = ethGetTransactionCount.getTransactionCount();
                BigInteger gasprice = BigInteger.valueOf(1000000000);
                BigInteger gaslimit = BigInteger.valueOf(4712388);

                Transaction ts = Transaction.createFunctionCallTransaction(DEFAULT_ADDRESS,
                        nonce, gasprice, gaslimit, CONTRACT_ADDRESS, encodedFunction);
                EthSendTransaction response = myEthereumNode.ethSendTransaction(ts).sendAsync().get();
                return response.getTransactionHash();
        }




}
