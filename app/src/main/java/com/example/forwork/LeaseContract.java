package com.example.forwork;

import java.math.BigDecimal;
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
import org.web3j.utils.Convert;

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
    private static final String BINARY = "6080604052670de0b6b3a764000060065560405162000b6a38038062000b6a8339818101604052608081101561003457600080fd5b8151602083015160408085018051915193959294830192918464010000000082111561005f57600080fd5b90830190602082018581111561007457600080fd5b825164010000000081118282018810171561008e57600080fd5b82525081516020918201929091019080838360005b838110156100bb5781810151838201526020016100a3565b50505050905090810190601f1680156100e85780820380516001836020036101000a031916815260200191505b506040526020018051604051939291908464010000000082111561010b57600080fd5b90830190602082018581111561012057600080fd5b825164010000000081118282018810171561013a57600080fd5b82525081516020918201929091019080838360005b8381101561016757818101518382015260200161014f565b50505050905090810190601f1680156101945780820380516001836020036101000a031916815260200191505b506040525050506004849055600654830260055581516101bb90600790602085019061026a565b5080516101cf90600890602084019061026a565b506009805460ff191690556006548302600a5542600b55600080546001600160a01b0319163317905561020061023c565b6040805133815290517fe2d73ccd7f21fd676f9af464e1606f7111f324811c07c0c1edcc27fa883a7f2f9181900360200190a150505050610305565b600a54341461024a57600080fd5b600080546001600160a01b03168152600260205260409020805434019055565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102ab57805160ff19168380011785556102d8565b828001600101855582156102d8579182015b828111156102d85782518255916020019190600101906102bd565b506102e49291506102e8565b5090565b61030291905b808211156102e457600081556001016102ee565b90565b61085580620003156000396000f3fe60806040526004361061014b5760003560e01c8063956501bb116100b6578063c27241e91161006f578063c27241e914610385578063c399ec88146103b8578063d0e30db0146103cd578063d297a89f146103e2578063e16ae120146103f7578063ed6265c3146103ff5761014b565b8063956501bb1461030357806398d5fdca14610336578063a035b1fe1461034b578063a709c4fe14610360578063ad2e8c9b14610368578063b8b4f1a01461037d5761014b565b80634e69d560116101085780634e69d5601461021057806356715761146102255780635fb02f4d1461023a57806361e3a9591461024f5780636f5c467b146102645780636f9fb98a146102ee5761014b565b8063103097811461014d578063188ec356146101745780631918629c14610189578063200d2ed2146101ba5780632fd949ca146101f357806344e5a91c146101fb575b005b34801561015957600080fd5b50610162610414565b60408051918252519081900360200190f35b34801561018057600080fd5b5061016261041a565b34801561019557600080fd5b5061019e610420565b604080516001600160a01b039092168252519081900360200190f35b3480156101c657600080fd5b506101cf61042f565b604051808260028111156101df57fe5b60ff16815260200191505060405180910390f35b61014b610438565b34801561020757600080fd5b5061014b610536565b34801561021c57600080fd5b506101cf61054d565b34801561023157600080fd5b50610162610556565b34801561024657600080fd5b5061014b61055c565b34801561025b57600080fd5b5061019e61056f565b34801561027057600080fd5b5061027961057e565b6040805160208082528351818301528351919283929083019185019080838360005b838110156102b357818101518382015260200161029b565b50505050905090810190601f1680156102e05780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156102fa57600080fd5b50610162610614565b34801561030f57600080fd5b506101626004803603602081101561032657600080fd5b50356001600160a01b0316610619565b34801561034257600080fd5b5061016261062b565b34801561035757600080fd5b50610162610631565b61014b610637565b34801561037457600080fd5b506101626106cf565b61014b6106d5565b34801561039157600080fd5b50610162600480360360208110156103a857600080fd5b50356001600160a01b031661076d565b3480156103c457600080fd5b5061016261077f565b3480156103d957600080fd5b50610162610785565b3480156103ee57600080fd5b5061016261078b565b61014b610791565b34801561040b57600080fd5b506102796107bf565b600b5481565b600b5490565b6001546001600160a01b031690565b60095460ff1681565b6000546001600160a01b0316331461044f57600080fd5b6001546001600160a01b0390811660009081526003602090815260408083205460029092528083205483549094168084528184205491519294939192909185156108fc02918691818181858888f193505050501580156104b3573d6000803e3d6000fd5b506001546040516001600160a01b039091169083156108fc029084906000818181858888f193505050501580156104ee573d6000803e3d6000fd5b50600080546040516001600160a01b039091169183156108fc02918491818181858888f19350505050158015610528573d6000803e3d6000fd5b50610531610536565b505050565b600980546002919060ff19166001835b0217905550565b60095460ff1690565b60045481565b600980546001919060ff19168280610546565b6000546001600160a01b031690565b60078054604080516020601f600260001961010060018816150201909516949094049384018190048102820181019092528281526060939092909183018282801561060a5780601f106105df5761010080835404028352916020019161060a565b820191906000526020600020905b8154815290600101906020018083116105ed57829003601f168201915b5050505050905090565b303190565b60026020526000908152604090205481565b60055490565b60055481565b6000546001600160a01b031633141561064f57600080fd5b60018060095460ff16600281111561066357fe5b1461066d57600080fd5b600554341461067b57600080fd5b3360008181526003602090815260409182902080543490810190915582519384529083015280517feccb9ee2cc97fb3a43b58066a4c087bdf26516e7c728ae7ec911d1b7948d56c19281900390910190a150565b60045490565b600a5434146106e357600080fd5b600180546001600160a01b03191633908117909155600090815260026020526040902034905561071161055c565b600a5460408051338152602081019290925260608282018190526006908301526514da59db995960d21b6080830152517f291f8f18c7bc783c8583e43eb978f007263548d13cde6ce94dce3827afdb50799181900360a00190a1565b60036020526000908152604090205481565b600a5490565b600a5481565b60065481565b600a54341461079f57600080fd5b600080546001600160a01b03168152600260205260409020805434019055565b60088054604080516020601f600260001961010060018816150201909516949094049384018190048102820181019092528281526060939092909183018282801561060a5780601f106105df5761010080835404028352916020019161060a56fea265627a7a7231582043e156950552863333d2e763e5b59b09a72f7d83e90c907f2b733e9df75c133f64736f6c634300050d0032";

    public static final String FUNC_ALLRENT = "allRent";

    public static final String FUNC_CREATEDTIMESTAMP = "createdTimestamp";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_DEPOSITBALANCE = "depositBalance";

    public static final String FUNC_GETCONTRACTBALANCE = "getContractBalance";

    public static final String FUNC_GETDEPOSIT = "getDeposit";

    public static final String FUNC_GETDURATION = "getDuration";

    public static final String FUNC_GETLESSEE = "getLessee";

    public static final String FUNC_GETLESSOR = "getLessor";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_GETRATEOFCHARGE = "getRateOfCharge";

    public static final String FUNC_GETSTATUS = "getStatus";

    public static final String FUNC_GETTIMESTAMP = "getTimestamp";

    public static final String FUNC_GETWORKSPACEID = "getWorkspaceId";

    public static final String FUNC_LESSORDEPOSIT = "lessorDeposit";

    public static final String FUNC_MINDURATION = "minDuration";

    public static final String FUNC_ONEETHER = "oneEther";

    public static final String FUNC_PAYRENT = "payRent";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_SIGNCONTRACT = "signContract";

    public static final String FUNC_STARTCONTRACT = "startContract";

    public static final String FUNC_STATUS = "status";

    public static final String FUNC_TERMINATECONTRACT = "terminateContract";

    public static final String FUNC_TERMINATEDCONTRACT = "terminatedContract";

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
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
            }, new TypeReference<Uint256>() {
            }));
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

    public static RemoteCall<LeaseContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialethValue, BigInteger _minDuration, BigInteger _price, String _rateOfCharge, String _workspaceId) {
        BigInteger initialWeiValue = Convert.toWei(new BigDecimal(initialethValue), Convert.Unit.ETHER).toBigInteger();
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minDuration),
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_rateOfCharge),
                new org.web3j.abi.datatypes.Utf8String(_workspaceId)));
        return deployRemoteCall(LeaseContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<LeaseContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialethValue, BigInteger _minDuration, BigInteger _price, String _rateOfCharge, String _workspaceId) {
        BigInteger initialWeiValue = Convert.toWei(new BigDecimal(initialethValue), Convert.Unit.ETHER).toBigInteger();
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minDuration),
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_rateOfCharge),
                new org.web3j.abi.datatypes.Utf8String(_workspaceId)));
        return deployRemoteCall(LeaseContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<LeaseContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialethValue, BigInteger _minDuration, BigInteger _price, String _rateOfCharge, String _workspaceId) {
        BigInteger initialWeiValue = Convert.toWei(new BigDecimal(initialethValue), Convert.Unit.ETHER).toBigInteger();
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minDuration),
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_rateOfCharge),
                new org.web3j.abi.datatypes.Utf8String(_workspaceId)));
        return deployRemoteCall(LeaseContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<LeaseContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialethValue, BigInteger _minDuration, BigInteger _price, String _rateOfCharge, String _workspaceId) {
        BigInteger initialWeiValue = Convert.toWei(new BigDecimal(initialethValue), Convert.Unit.ETHER).toBigInteger();
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minDuration),
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_rateOfCharge),
                new org.web3j.abi.datatypes.Utf8String(_workspaceId)));
        return deployRemoteCall(LeaseContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
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
            typedResponse._lessee = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse._lessee = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PaidRentEventResponse> paidRentEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAIDRENT_EVENT));
        return paidRentEventObservable(filter);
    }

    public RemoteCall<BigInteger> allRent(String param0) {
        final Function function = new Function(FUNC_ALLRENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteCall<BigInteger> depositBalance(String param0) {
        final Function function = new Function(FUNC_DEPOSITBALANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getContractBalance() {
        final Function function = new Function(FUNC_GETCONTRACTBALANCE,
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

    public RemoteCall<String> getWorkspaceId() {
        final Function function = new Function(FUNC_GETWORKSPACEID,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> lessorDeposit(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_LESSORDEPOSIT,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> minDuration() {
        final Function function = new Function(FUNC_MINDURATION,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> oneEther() {
        final Function function = new Function(FUNC_ONEETHER,
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

    public RemoteCall<TransactionReceipt> signContract(BigInteger ethValue) {
        BigInteger weiValue = Convert.toWei(new BigDecimal(ethValue), Convert.Unit.ETHER).toBigInteger();
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

    public RemoteCall<TransactionReceipt> terminateContract(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_TERMINATECONTRACT,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> terminatedContract() {
        final Function function = new Function(
                FUNC_TERMINATEDCONTRACT, 
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

        public String _lessee;

        public BigInteger _amount;
    }
}
