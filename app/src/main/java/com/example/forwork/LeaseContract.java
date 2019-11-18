package com.example.forwork;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import io.reactivex.Flowable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class LeaseContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506040516109053803806109058339818101604052608081101561003357600080fd5b8151602083015160408085018051915193959294830192918464010000000082111561005e57600080fd5b90830190602082018581111561007357600080fd5b825164010000000081118282018810171561008d57600080fd5b82525081516020918201929091019080838360005b838110156100ba5781810151838201526020016100a2565b50505050905090810190601f1680156100e75780820380516001836020036101000a031916815260200191505b506040526020018051604051939291908464010000000082111561010a57600080fd5b90830190602082018581111561011f57600080fd5b825164010000000081118282018810171561013957600080fd5b82525081516020918201929091019080838360005b8381101561016657818101518382015260200161014e565b50505050905090810190601f1680156101935780820380516001836020036101000a031916815260200191505b506040525050506002849055600383905581516101b79060049060208501906101f5565b506005805460ff1916905580516101d59060089060208401906101f5565b5050426007555050600080546001600160a01b0319163317905550610290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061023657805160ff1916838001178555610263565b82800160010185558215610263579182015b82811115610263578251825591602001919060010190610248565b5061026f929150610273565b5090565b61028d91905b8082111561026f5760008155600101610279565b90565b6106668061029f6000396000f3fe6080604052600436106101145760003560e01c806361e3a959116100a0578063a720d89c11610064578063a720d89c14610310578063ad2e8c9b14610325578063b8b4f1a01461033a578063c399ec8814610342578063d0e30db01461035757610114565b806361e3a9591461023f5780636f5c467b1461025457806398d5fdca146102de578063a035b1fe146102f3578063a709c4fe1461030857610114565b80632fd949ca116100e75780632fd949ca146101bf5780634e69d560146101d657806356715761146101eb5780635fb02f4d1461020057806360fe47b11461021557610114565b80631030978114610119578063188ec356146101405780631918629c14610155578063200d2ed214610186575b600080fd5b34801561012557600080fd5b5061012e61036c565b60408051918252519081900360200190f35b34801561014c57600080fd5b5061012e610372565b34801561016157600080fd5b5061016a610378565b604080516001600160a01b039092168252519081900360200190f35b34801561019257600080fd5b5061019b610387565b604051808260028111156101ab57fe5b60ff16815260200191505060405180910390f35b3480156101cb57600080fd5b506101d4610390565b005b3480156101e257600080fd5b5061019b6103be565b3480156101f757600080fd5b5061012e6103c7565b34801561020c57600080fd5b506101d46103cd565b34801561022157600080fd5b506101d46004803603602081101561023857600080fd5b50356103e0565b34801561024b57600080fd5b5061016a6103e5565b34801561026057600080fd5b506102696103f4565b6040805160208082528351818301528351919283929083019185019080838360005b838110156102a357818101518382015260200161028b565b50505050905090810190601f1680156102d05780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156102ea57600080fd5b5061012e61048a565b3480156102ff57600080fd5b5061012e610490565b6101d4610496565b34801561031c57600080fd5b506101d46103a7565b34801561033157600080fd5b5061012e610540565b6101d4610546565b34801561034e57600080fd5b5061012e610625565b34801561036357600080fd5b5061012e61062b565b60075481565b60075490565b6001546001600160a01b031690565b60055460ff1681565b6000546001600160a01b031633146103a757600080fd5b600580546002919060ff19166001835b0217905550565b60055460ff1690565b60025481565b600580546001919060ff191682806103b7565b600355565b6000546001600160a01b031690565b60048054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156104805780601f1061045557610100808354040283529160200191610480565b820191906000526020600020905b81548152906001019060200180831161046357829003601f168201915b5050505050905090565b60035490565b60035481565b6000546001600160a01b03163314156104ae57600080fd5b60018060055460ff1660028111156104c257fe5b146104cc57600080fd5b600034116104d957600080fd5b600080546040516001600160a01b03909116913480156108fc02929091818181858888f19350505050158015610513573d6000803e3d6000fd5b506040517fbade71dff67ec59cb8a653f4f7a5cd050285c740376f33d8570b0639d5a6d14990600090a150565b60025490565b6000546001600160a01b031633141561055e57600080fd5b6000341161056b57600080fd5b600180546001600160a01b03191633179055600080546040516001600160a01b0391909116913480156108fc02929091818181858888f193505050501580156105b8573d6000803e3d6000fd5b506005805460ff191660011790556006805460408051338152602081019290925260608282018190528201929092526514da59db995960d21b608082015290517f291f8f18c7bc783c8583e43eb978f007263548d13cde6ce94dce3827afdb50799160a0908290030190a1565b60065490565b6006548156fea265627a7a723158202ad31013d63a3fcd8cbf913ccc6c9d7a354e4df4d56303b9bf793e39945e987364736f6c634300050d0032";

    public static final String FUNC_CREATEDTIMESTAMP = "createdTimestamp";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_GETDEPOSIT = "getDeposit";

    public static final String FUNC_GETDURATION = "getDuration";

    public static final String FUNC_GETLESSEE = "getLessee";

    public static final String FUNC_GETLESSOR = "getLessor";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_GETRATEOFCHARGE = "getRateOfCharge";

    public static final String FUNC_GETSTATUS = "getStatus";

    public static final String FUNC_GETTIMESTAMP = "getTimestamp";

    public static final String FUNC_MINDURATION = "minDuration";

    public static final String FUNC_PAYRENT = "payRent";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_SET = "set";

    public static final String FUNC_SETTERMINATED = "setTerminated";

    public static final String FUNC_SIGNCONTRACT = "signContract";

    public static final String FUNC_STARTCONTRACT = "startContract";

    public static final String FUNC_STATUS = "status";

    public static final String FUNC_TERMINATECONTRACT = "terminateContract";

    public static final Event SIGNEDCONTRACT_EVENT = new Event("SignedContract",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
            }, new TypeReference<Uint256>() {
            }, new TypeReference<Utf8String>() {
            }));
    ;

    public static final Event CREATECONTRACT_EVENT = new Event("createContract",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
            }));
    ;

    public static final Event PAIDRENT_EVENT = new Event("paidRent",
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected LeaseContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LeaseContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LeaseContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LeaseContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<LeaseContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _minDuration, BigInteger _price, String _rateOfCharge, String _coworkspace) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minDuration),
                new org.web3j.abi.datatypes.generated.Uint256(_price),
                new org.web3j.abi.datatypes.Utf8String(_rateOfCharge),
                new org.web3j.abi.datatypes.Utf8String(_coworkspace)));
        return deployRemoteCall(LeaseContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<LeaseContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _minDuration, BigInteger _price, String _rateOfCharge, String _coworkspace) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minDuration),
                new org.web3j.abi.datatypes.generated.Uint256(_price),
                new org.web3j.abi.datatypes.Utf8String(_rateOfCharge),
                new org.web3j.abi.datatypes.Utf8String(_coworkspace)));
        return deployRemoteCall(LeaseContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<LeaseContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _minDuration, BigInteger _price, String _rateOfCharge, String _coworkspace) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minDuration),
                new org.web3j.abi.datatypes.generated.Uint256(_price),
                new org.web3j.abi.datatypes.Utf8String(_rateOfCharge),
                new org.web3j.abi.datatypes.Utf8String(_coworkspace)));
        return deployRemoteCall(LeaseContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<LeaseContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _minDuration, BigInteger _price, String _rateOfCharge, String _coworkspace) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minDuration),
                new org.web3j.abi.datatypes.generated.Uint256(_price),
                new org.web3j.abi.datatypes.Utf8String(_rateOfCharge),
                new org.web3j.abi.datatypes.Utf8String(_coworkspace)));
        return deployRemoteCall(LeaseContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<SignedContractEventResponse> getSignedContractEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SIGNEDCONTRACT_EVENT, transactionReceipt);
        ArrayList<SignedContractEventResponse> responses = new ArrayList<SignedContractEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SignedContractEventResponse typedResponse = new SignedContractEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._lessee = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._deposit = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._status = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SignedContractEventResponse> signedContractEventObservable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, SignedContractEventResponse>() {
            @Override
            public SignedContractEventResponse apply(Log log) throws Exception {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SIGNEDCONTRACT_EVENT, log);
                SignedContractEventResponse typedResponse = new SignedContractEventResponse();
                typedResponse.log = log;
                typedResponse._lessee = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._deposit = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._status = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SignedContractEventResponse> signedContractEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SIGNEDCONTRACT_EVENT));
        return signedContractEventObservable(filter);
    }

    public List<CreateContractEventResponse> getCreateContractEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATECONTRACT_EVENT, transactionReceipt);
        ArrayList<CreateContractEventResponse> responses = new ArrayList<CreateContractEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateContractEventResponse typedResponse = new CreateContractEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._lessor = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateContractEventResponse> createContractEventObservable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, CreateContractEventResponse>() {
            @Override
            public CreateContractEventResponse apply(Log log) throws Exception {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CREATECONTRACT_EVENT, log);
                CreateContractEventResponse typedResponse = new CreateContractEventResponse();
                typedResponse.log = log;
                typedResponse._lessor = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateContractEventResponse> createContractEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATECONTRACT_EVENT));
        return createContractEventObservable(filter);
    }

    public List<PaidRentEventResponse> getPaidRentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PAIDRENT_EVENT, transactionReceipt);
        ArrayList<PaidRentEventResponse> responses = new ArrayList<PaidRentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PaidRentEventResponse typedResponse = new PaidRentEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PaidRentEventResponse> paidRentEventObservable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PaidRentEventResponse>() {
            @Override
            public PaidRentEventResponse apply(Log log) throws Exception {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PAIDRENT_EVENT, log);
                PaidRentEventResponse typedResponse = new PaidRentEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<PaidRentEventResponse> paidRentEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAIDRENT_EVENT));
        return paidRentEventObservable(filter);
    }

    public RemoteCall<BigInteger> createdTimestamp() {
        final Function function = new Function(FUNC_CREATEDTIMESTAMP,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> deposit() {
        final Function function = new Function(FUNC_DEPOSIT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getDeposit() {
        final Function function = new Function(FUNC_GETDEPOSIT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getDuration() {
        final Function function = new Function(FUNC_GETDURATION,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getLessee() {
        final Function function = new Function(FUNC_GETLESSEE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getLessor() {
        final Function function = new Function(FUNC_GETLESSOR,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getPrice() {
        final Function function = new Function(FUNC_GETPRICE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getRateOfCharge() {
        final Function function = new Function(FUNC_GETRATEOFCHARGE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getStatus() {
        final Function function = new Function(FUNC_GETSTATUS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getTimestamp() {
        final Function function = new Function(FUNC_GETTIMESTAMP,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> minDuration() {
        final Function function = new Function(FUNC_MINDURATION,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> payRent(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PAYRENT,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> price() {
        final Function function = new Function(FUNC_PRICE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> set(BigInteger _price) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setTerminated() {
        final Function function = new Function(
                FUNC_SETTERMINATED,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> signContract(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SIGNCONTRACT,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> startContract() {
        final Function function = new Function(
                FUNC_STARTCONTRACT,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> status() {
        final Function function = new Function(FUNC_STATUS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> terminateContract() {
        final Function function = new Function(
                FUNC_TERMINATECONTRACT,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static LeaseContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LeaseContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LeaseContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LeaseContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LeaseContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new LeaseContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LeaseContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LeaseContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class SignedContractEventResponse {
        public Log log;

        public String _lessee;

        public BigInteger _deposit;

        public String _status;
    }

    public static class CreateContractEventResponse {
        public Log log;

        public String _lessor;
    }

    public static class PaidRentEventResponse {
        public Log log;
    }
}
