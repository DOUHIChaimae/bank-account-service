package ma.enset.bankaccountservice.services;

import ma.enset.bankaccountservice.dtos.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.mappers.AccountMapper;
import ma.enset.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDto addAccount(BankAccountRequestDTO bankAccountRequestDto) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountRequestDto.getBalance())
                .currency(bankAccountRequestDto.getCurrency())
                .type(bankAccountRequestDto.getType())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDto bankAccountResponseDto = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDto;
    }

    @Override
    public BankAccountResponseDto updateAccount(String id, BankAccountRequestDTO bankAccountRequestDto) {
        BankAccount bankAccount = BankAccount.builder()
                .id(id)
                .createdAt(new Date())
                .balance(bankAccountRequestDto.getBalance())
                .currency(bankAccountRequestDto.getCurrency())
                .type(bankAccountRequestDto.getType())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDto bankAccountResponseDto = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDto;
    }

    @Override
    public List<BankAccountResponseDto> getAllAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        List<BankAccountResponseDto> responseDtoList = new ArrayList<>();
        for (BankAccount account : accounts) {
            BankAccountResponseDto responseDto = new BankAccountResponseDto();
            responseDto.setId(account.getId());
            responseDto.setType(account.getType());
            responseDto.setCurrency(account.getCurrency());
            responseDto.setBalance(account.getBalance());
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }
    @Override
    public BankAccountResponseDto getAccountById(String id){
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        return accountMapper.fromBankAccount(bankAccount);
    }
}
