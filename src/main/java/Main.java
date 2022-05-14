import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.lang.String;
import java.lang.*;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import static org.web3j.utils.Convert.fromWei;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException{
        Web3j web3 = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
        System.out.println("\tWeb3 got successfully connect to ethereum node at 127.0.0.1:7545  by way of JSON-RPC\n");
        EthAccounts result = new EthAccounts();
        result = web3.ethAccounts()
                .sendAsync()
                .get();
        String[] accounts = result.getAccounts().toArray(new String[0]);
        System.out.println("\t\t\t\tAccount\t\t\t\t\t\t\t\tBalance(in Wei)\t\t\t\tBalance(in Ether)");
        for(int i=0;i< accounts.length;i++){
            EthGetBalance balance = new EthGetBalance();
            balance = web3.ethGetBalance(accounts[0],
                    DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get();
            System.out.println(accounts[i] + "  -  "+ balance.getBalance() + " Wei     -    " + fromWei(new BigDecimal(balance.getBalance()), Convert.Unit.ETHER) + " Ether");
        }

    }
}
